package org.sui.lang.core.resolve2.ref

import com.intellij.psi.ResolveResult
import org.sui.cli.MoveProject
import org.sui.lang.core.psi.*
import org.sui.lang.core.psi.ext.*
import org.sui.lang.core.resolve.*
import org.sui.lang.core.resolve.ref.*
import org.sui.lang.core.resolve.ref.Namespace.MODULE
import org.sui.lang.core.resolve2.*
import org.sui.lang.core.resolve2.PathKind.NamedAddress
import org.sui.lang.core.resolve2.PathKind.ValueAddress
import org.sui.lang.core.types.ty.Ty
import org.sui.lang.core.types.ty.TyAdt
import org.sui.lang.moveProject
import kotlin.LazyThreadSafetyMode.NONE

interface InferenceCachedPathElement: MvElement {
    val path: MvPath
}

class MvPath2ReferenceImpl(element: MvPath): MvPolyVariantReferenceBase<MvPath>(element),
                                             MvPath2Reference {

    override fun resolve(): MvNamedElement? =
        rawMultiResolveIfVisible().singleOrNull()?.element as? MvNamedElement

    override fun multiResolve(): List<MvNamedElement> =
        rawMultiResolveIfVisible().mapNotNull { it.element as? MvNamedElement }

    override fun multiResolve(incompleteCode: Boolean): Array<out ResolveResult> =
        rawMultiResolve().toTypedArray()

    fun rawMultiResolveIfVisible(): List<MvPathResolveResult<MvElement>> =
        rawMultiResolve().filter { it.isVisible }

    fun rawMultiResolve(): List<MvPathResolveResult<MvElement>> =
        rawCachedMultiResolve()

    private fun rawCachedMultiResolve(): List<MvPathResolveResult<MvElement>> {
        return MvResolveCache
            .getInstance(element.project)
            .resolveWithCaching(element, ResolveCacheDependency.LOCAL_AND_RUST_STRUCTURE, Resolver)
            .orEmpty()
    }

    private object Resolver: (MvElement) -> List<MvPathResolveResult<MvElement>> {
        override fun invoke(path: MvElement): List<MvPathResolveResult<MvElement>> {
            if (path !is MvPath) return emptyList()
            val resolutionCtx = ResolutionContext(path, isCompletion = false)
            return resolvePath(resolutionCtx, path)
        }
    }
}

fun filterEnumVariantsByExpectedType(expectedType: Ty?, processor: MvResolveProcessor): MvResolveProcessor {
    if (expectedType == null) return processor

    val enumItem = (expectedType as? TyAdt)?.item as? MvEnum
    if (enumItem == null) return processor

    val allowedVariants = enumItem.variants
    return processor.wrapWithFilter {
        val element = it.element
        element !is MvEnumVariant || element in allowedVariants
    }
}

fun resolveAliases(element: MvNamedElement): MvNamedElement {
    if (element is MvUseAlias) {
        val aliasedPath = element.parentUseSpeck.path
        val resolvedItem = aliasedPath.reference?.resolve()
        if (resolvedItem != null) {
            return resolvedItem
        }
    }
    return element
}

fun processPathResolveVariants(
    ctx: ResolutionContext,
    pathKind: PathKind,
    processor: MvResolveProcessor
): Boolean {
    return when (pathKind) {
        is NamedAddress, is ValueAddress -> false
        is PathKind.UnqualifiedPath -> {
            if (MODULE in pathKind.ns) {
                // Self::
                if (processor.lazy("Self", MODULES) { ctx.containingModule }) return true
            }
            // local
            processNestedScopesUpwards(ctx.element, pathKind.ns, ctx, processor)
        }
        is PathKind.QualifiedPath.Module -> {
            processModulePathResolveVariants(ctx, pathKind.address, processor)
        }
        is PathKind.QualifiedPath -> {
            processQualifiedPathResolveVariants(ctx, pathKind.ns, pathKind.qualifier, processor)
        }
    }
}

/**
 * foo::bar
 * |    |
 * |    [path]
 * [qualifier]
 */
fun processQualifiedPathResolveVariants(
    ctx: ResolutionContext,
    ns: Set<Namespace>,
    qualifier: MvPath,
    processor: MvResolveProcessor
): Boolean {
    val resolvedQualifier = qualifier.reference?.resolveFollowingAliases()
    if (resolvedQualifier == null) {
        if (MODULE in ns) {
            // can be module, try for named address as a qualifier
            val addressName = qualifier.referenceName ?: return false
            val address = ctx.moveProject?.getNamedAddressTestAware(addressName) ?: return false
            if (processModulePathResolveVariants(ctx, address, processor)) return true
        }
        return false
    }
    if (resolvedQualifier is MvModule) {
        if (processor.process("Self", MODULES, resolvedQualifier)) return true

        val module = resolvedQualifier
        if (processItemDeclarations(module, ns, processor)) return true
    }
    if (resolvedQualifier is MvEnum) {
        if (processor.processAll(TYPES, resolvedQualifier.variants)) return true
    }
    return false
}

class ResolutionContext(val element: MvElement, val isCompletion: Boolean) {
    private var lazyContainingMoveProject: Lazy<MoveProject?> = lazy(NONE) {
        element.moveProject
    }
    val moveProject: MoveProject? get() = lazyContainingMoveProject.value

    private var lazyContainingModule: Lazy<MvModule?> = lazy(NONE) {
        element.containingModule
    }
    val containingModule: MvModule? get() = lazyContainingModule.value

    val methodOrPath: MvMethodOrPath? get() = element as? MvMethodOrPath
    val path: MvPath? get() = element as? MvPath
}

private fun resolvePath(
    ctx: ResolutionContext,
    path: MvPath,
): List<MvPathResolveResult<MvElement>> {
    val pathKind = path.pathKind()
    return collectMethodOrPathResolveVariants(path, ctx) {
        processPathResolveVariants(ctx, pathKind, processor = it)
    }
}
