package org.sui.lang.core.resolve

import com.intellij.util.SmartList
import org.sui.lang.core.psi.*
import org.sui.lang.core.psi.NamedItemScope.MAIN
import org.sui.lang.core.psi.ext.MvItemElement
import org.sui.lang.core.psi.ext.MvMethodOrPath
import org.sui.lang.core.resolve.VisibilityStatus.Visible
import org.sui.lang.core.resolve.ref.Namespace
import org.sui.lang.core.resolve2.createFilter
import org.sui.lang.core.resolve2.ref.ResolutionContext
import org.sui.lang.core.resolve2.ref.MvPathResolveResult
import org.sui.lang.core.resolve2.visInfo
import org.sui.stdext.intersects

/**
 * ScopeEntry is some PsiElement visible in some code scope.
 *
 * [SimpleScopeEntry] handles the two cases:
 *   * aliases (that's why we need a [name] property)
 *   * lazy resolving of actual elements (that's why [element] can return `null`) - unused for now
 */
interface ScopeEntry {
    val name: String
    val element: MvNamedElement
    val namespaces: Set<Namespace>
    fun doCopyWithNs(namespaces: Set<Namespace>): ScopeEntry
}

@Suppress("UNCHECKED_CAST")
private fun <T: ScopeEntry> T.copyWithNs(namespaces: Set<Namespace>): T = doCopyWithNs(namespaces) as T

interface MvResolveProcessorBase<in T: ScopeEntry> {
    /**
     * Return `true` to stop further processing,
     * return `false` to continue search
     */
    fun process(entry: T): Boolean

    /**
     * Indicates that processor is interested only in [ScopeEntry]s with specified [names].
     * Improves performance for Resolve2.
     * `null` in completion
     */
    val names: Set<String>?

    fun acceptsName(name: String): Boolean {
        val names = names
        return names == null || name in names
    }
}

typealias MvResolveProcessor = MvResolveProcessorBase<ScopeEntry>

fun <T: ScopeEntry> MvResolveProcessorBase<T>.wrapWithFilter(
    filter: (T) -> Boolean
): MvResolveProcessorBase<T> {
    return FilteringProcessor(this, filter)
}

private class FilteringProcessor<in T: ScopeEntry>(
    private val originalProcessor: MvResolveProcessorBase<T>,
    private val filter: (T) -> Boolean,
): MvResolveProcessorBase<T> {
    override val names: Set<String>? = originalProcessor.names
    override fun process(entry: T): Boolean {
        return if (filter(entry)) {
            originalProcessor.process(entry)
        } else {
            false
        }
    }

    override fun toString(): String = "FilteringProcessor($originalProcessor, filter = $filter)"
}

fun <T: ScopeEntry> MvResolveProcessorBase<T>.wrapWithShadowingProcessorAndUpdateScope(
    prevScope: Map<String, Set<Namespace>>,
    currScope: MutableMap<String, Set<Namespace>>,
    ns: Set<Namespace>,
): MvResolveProcessorBase<T> {
    return ShadowingAndUpdateScopeProcessor(this, prevScope, currScope, ns)
}

private class ShadowingAndUpdateScopeProcessor<in T: ScopeEntry>(
    private val originalProcessor: MvResolveProcessorBase<T>,
    private val prevScope: Map<String, Set<Namespace>>,
    private val currScope: MutableMap<String, Set<Namespace>>,
    private val ns: Set<Namespace>,
): MvResolveProcessorBase<T> {
    override val names: Set<String>? = originalProcessor.names
    override fun process(entry: T): Boolean {
        if (!originalProcessor.acceptsName(entry.name) || entry.name == "_") {
            return originalProcessor.process(entry)
        }
        val prevNs = prevScope[entry.name]
        val newNs = entry.namespaces
        // drop entries from namespaces that were encountered before
        val entryWithIntersectedNs =
            if (prevNs != null) {
                val restNs = newNs.minus(prevNs)
                if (ns.intersects(restNs)) {
                    entry.copyWithNs(restNs)
                } else {
                    return false
                }
            } else {
                entry
            }
        // save encountered namespaces to the currScope
        currScope[entry.name] = prevNs?.let { it + newNs } ?: newNs
        return originalProcessor.process(entryWithIntersectedNs)
    }

    override fun toString(): String = "ShadowingAndUpdateScopeProcessor($originalProcessor, ns = $ns)"
}

fun collectResolveVariants(referenceName: String?, f: (MvResolveProcessor) -> Unit): List<MvNamedElement> {
    if (referenceName == null) return emptyList()
    val processor = ResolveVariantsCollector(referenceName)
    f(processor)
    return processor.result
}

private class ResolveVariantsCollector(
    private val referenceName: String,
    val result: MutableList<MvNamedElement> = SmartList(),
): MvResolveProcessorBase<ScopeEntry> {
    override val names: Set<String> = setOf(referenceName)

    override fun process(entry: ScopeEntry): Boolean {
        if (entry.name == referenceName) {
            result += entry.element
        }
        return false
    }
}

fun <T: ScopeEntry> collectResolveVariantsAsScopeEntries(
    referenceName: String?,
    f: (MvResolveProcessorBase<T>) -> Unit
): List<T> {
    if (referenceName == null) return emptyList()
    val processor = ResolveVariantsAsScopeEntriesCollector<T>(referenceName)
    f(processor)
    return processor.result
}

private class ResolveVariantsAsScopeEntriesCollector<T: ScopeEntry>(
    private val referenceName: String,
    val result: MutableList<T> = mutableListOf(),
): MvResolveProcessorBase<T> {
    override val names: Set<String> = setOf(referenceName)

    override fun process(entry: T): Boolean {
        if (entry.name == referenceName) {
            result += entry
        }
        return false
    }
}

/// checks for visibility of items
fun collectMethodOrPathResolveVariants(
    methodOrPath: MvMethodOrPath,
    ctx: ResolutionContext,
    f: (MvResolveProcessor) -> Unit
): List<MvPathResolveResult<MvElement>> {
    val referenceName = methodOrPath.referenceName ?: return emptyList()
    val processor = SinglePathResolveVariantsCollector(ctx, referenceName)
    f(processor)
    return processor.result
}

private class SinglePathResolveVariantsCollector(
    private val ctx: ResolutionContext,
    private val referenceName: String,
    val result: MutableList<MvPathResolveResult<MvElement>> = SmartList(),
): MvResolveProcessorBase<ScopeEntry> {
    override val names: Set<String> = setOf(referenceName)

    override fun process(entry: ScopeEntry): Boolean {
        if (entry.name == referenceName) {
            collectMethodOrPathScopeEntry(ctx, result, entry)
        }
        return false
    }
}

private fun collectMethodOrPathScopeEntry(
    ctx: ResolutionContext,
    result: MutableList<MvPathResolveResult<MvElement>>,
    e: ScopeEntry
) {
    val element = e.element
    val visibilityStatus = ctx.methodOrPath?.let { e.getVisibilityStatusFrom(it) } ?: Visible
    val isVisible = visibilityStatus == Visible
    result += MvPathResolveResult(element, isVisible)
}

data class SimpleScopeEntry(
    override val name: String,
    override val element: MvNamedElement,
    override val namespaces: Set<Namespace>,
): ScopeEntry {
    override fun doCopyWithNs(namespaces: Set<Namespace>): ScopeEntry = copy(namespaces = namespaces)
}

fun interface VisibilityFilter {
    fun filter(methodOrPath: MvMethodOrPath, ns: Set<Namespace>): VisibilityStatus
}

fun ScopeEntry.getVisibilityStatusFrom(methodOrPath: MvMethodOrPath): VisibilityStatus =
    if (this is ScopeEntryWithVisibility) {
        val visFilter = this.element
            .visInfo(adjustScope = this.adjustedItemScope)
            .createFilter()
        visFilter.filter(methodOrPath, this.namespaces)
    } else {
        Visible
    }

enum class VisibilityStatus {
    Visible,
    Invisible,
}

data class ScopeEntryWithVisibility(
    override val name: String,
    override val element: MvNamedElement,
    override val namespaces: Set<Namespace>,

    val adjustedItemScope: NamedItemScope = MAIN,
): ScopeEntry {
    override fun doCopyWithNs(namespaces: Set<Namespace>): ScopeEntry = copy(namespaces = namespaces)
}

fun MvResolveProcessor.process(
    name: String,
    e: MvNamedElement,
    ns: Set<Namespace>,
    adjustedItemScope: NamedItemScope = MAIN,
): Boolean = process(ScopeEntryWithVisibility(name, e, ns, adjustedItemScope))

fun MvResolveProcessor.processAllItems(
    namespaces: Set<Namespace>,
    vararg collections: Iterable<MvItemElement>,
): Boolean {
    return sequenceOf(*collections).flatten().any { itemElement ->
        val name = itemElement.name ?: return false
        process(ScopeEntryWithVisibility(name, itemElement, namespaces))
    }
}

fun MvResolveProcessor.process(
    name: String,
    namespaces: Set<Namespace>,
    e: MvNamedElement,
): Boolean =
    process(SimpleScopeEntry(name, e, namespaces))

inline fun MvResolveProcessor.lazy(
    name: String,
    namespaces: Set<Namespace>,
    e: () -> MvNamedElement?
): Boolean {
    if (!acceptsName(name)) return false
    val element = e() ?: return false
    return process(name, namespaces, element)
}

fun MvResolveProcessor.process(
    ns: Set<Namespace>,
    e: MvNamedElement,
): Boolean {
    val name = e.name ?: return false
    return process(name, ns, e)
}

fun MvResolveProcessor.processAll(
    ns: Set<Namespace>,
    elements: List<MvNamedElement>,
): Boolean {
    return elements.any { process(ns, it) }
}
