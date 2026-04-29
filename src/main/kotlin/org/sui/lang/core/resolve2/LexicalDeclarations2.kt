package org.sui.lang.core.resolve2

import com.intellij.openapi.util.Key
import com.intellij.psi.util.CachedValue
import com.intellij.psi.util.PsiTreeUtil
import org.sui.lang.core.psi.ext.usageScope
import org.sui.lang.core.psi.*
import org.sui.lang.core.psi.ext.*
import org.sui.lang.core.resolve.*
import org.sui.lang.core.resolve.ref.ENUMS
import org.sui.lang.core.resolve.ref.NONE
import org.sui.lang.core.resolve.ref.Namespace
import org.sui.lang.core.resolve.ref.Namespace.NAME
import org.sui.lang.core.resolve.ref.TYPES
import org.sui.lang.core.resolve2.ref.ResolutionContext
import org.sui.lang.core.resolve2.util.forEachLeafSpeck
import org.sui.utils.cache
import org.sui.utils.cacheManager
import org.sui.utils.psiCacheResult

fun processItemsInScope(
    scope: MvElement,
    cameFrom: MvElement,
    ns: Set<Namespace>,
    ctx: ResolutionContext,
    processor: MvResolveProcessor,
): Boolean {
    for (namespace in ns) {
        val elementNs = setOf(namespace)
        val stop = when (namespace) {
            NAME -> {
                val found = when (scope) {
                    is MvModule -> {
                        if (processor.processAll(elementNs, scope.enumVariants())) return true
                        if (processor.processAllItems(
                            elementNs,
                            scope.structs(),
                            scope.constList
                        )) return true

                        PreImportedModuleService.getInstance(scope.project)
                            .processPreImportedItems(elementNs, processor)
                    }
                    is MvFunctionLike -> processor.processAll(elementNs, scope.parametersAsBindings)
                    is MvLambdaExpr -> processor.processAll(elementNs, scope.patBindingList)
                    is MvForExpr -> {
                        val iterBinding = scope.forIterCondition?.patBinding
                        if (iterBinding != null) {
                            processor.process(elementNs, iterBinding)
                        } else {
                            false
                        }
                    }
                    is MvMatchArm -> {
                        if (cameFrom !is MvPat) {
                            if (processor.processAll(elementNs, scope.bindings)) return true
                            continue
                        }

                        false
                    }
                    is MvCodeBlock -> {
                        val visibleLetStmts = scope.letStmts
                            .filter { it.cameBefore(cameFrom) }
                            .filter {
                                cameFrom != it
                                        && !PsiTreeUtil.isAncestor(cameFrom, it, true)
                            }
                        val letBindings = visibleLetStmts
                            .asReversed()
                            .flatMap { it.pat?.bindings.orEmpty() }

                        val visited = mutableSetOf<String>()
                        val variablesProcessor = processor.wrapWithFilter {
                            val isVisited = it.name in visited
                            if (!isVisited) {
                                visited += it.name
                            }
                            !isVisited
                        }
                        variablesProcessor.processAll(elementNs, letBindings)
                    }
                    else -> false
                }
                found
            }
            Namespace.FUNCTION -> {
                val found = when (scope) {
                    is MvModule -> {
                        processor.processAllItems(
                            ns,
                            scope.builtinFunctions(),
                            scope.allNonTestFunctions()
                        )
                    }
                    is MvFunctionLike -> processor.processAll(elementNs, scope.lambdaParamsAsBindings)
                    is MvLambdaExpr -> processor.processAll(elementNs, scope.patBindingList)
                    else -> false
                }
                found
            }

            Namespace.TYPE -> {
                if (scope is MvTypeParametersOwner) {
                    if (processor.processAll(elementNs, scope.typeParameters)) return true
                }
                val found = when (scope) {
                    is MvModule -> {
                        if (processor.processAll(TYPES, scope.enumVariants())) return true
                        if (processor.processAllItems(
                            TYPES,
                            scope.structs(),
                        )) return true
                        PreImportedModuleService.getInstance(scope.project)
                            .processPreImportedItems(elementNs, processor)
                    }
                    else -> false
                }
                found
            }

            Namespace.ENUM -> {
                if (scope is MvModule) {
                    if (processor.processAllItems(ENUMS, scope.enumList)) return true
                }
                false
            }

            else -> false
        }
        if (stop) return true
    }

    if (scope is MvItemsOwner) {
        if (scope.processUseSpeckElements(ns, processor)) return true
    }

    return false
}

private fun MvItemsOwner.processUseSpeckElements(ns: Set<Namespace>, processor: MvResolveProcessor): Boolean {
    val preImportedService = PreImportedModuleService.getInstance(project)
    if (preImportedService.processPreImportedModules(ns, processor)) return true

    val useSpeckItems = getUseSpeckItems()
    for (useSpeckItem in useSpeckItems) {
        val speckPath = useSpeckItem.speckPath
        val alias = useSpeckItem.alias

        val resolvedItem = speckPath.reference?.resolve()
        if (resolvedItem == null) {
            if (alias != null) {
                val referenceName = useSpeckItem.referenceName ?: continue
                if (processor.process(referenceName, NONE, alias)) return true
            }
            continue
        }

        val element = alias ?: resolvedItem
        val namespace = resolvedItem.namespace
        if (namespace in ns) {
            val referenceName = useSpeckItem.referenceName ?: continue
            if (processor.process(
                    referenceName,
                    element,
                    ns,
                    adjustedItemScope = useSpeckItem.stmtUsageScope
                )
            ) return true
        }
    }
    return false
}

private val USE_SPECK_ITEMS_KEY: Key<CachedValue<List<UseSpeckItem>>> = Key.create("USE_SPECK_ITEMS_KEY")

private fun MvItemsOwner.getUseSpeckItems(): List<UseSpeckItem> =
    project.cacheManager.cache(this, USE_SPECK_ITEMS_KEY) {
        val items = buildList {
            for (useStmt in useStmtList) {
                val usageScope = useStmt.usageScope
                useStmt.forEachLeafSpeck { speckPath, alias ->
                    add(UseSpeckItem(speckPath, alias, usageScope))
                }
            }
        }
        this.psiCacheResult(items)
    }

private data class UseSpeckItem(
    val speckPath: MvPath,
    val alias: MvUseAlias?,
    val stmtUsageScope: NamedItemScope
) {
    val referenceName: String?
        get() {
            if (alias != null) {
                return alias.name
            } else {
                var n = speckPath.referenceName ?: return null
                if (n == "Self") {
                    n = speckPath.qualifier?.referenceName ?: return null
                }
                return n
            }
        }
}
