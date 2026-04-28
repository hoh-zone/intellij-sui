package org.sui.lang.core.resolve2

import org.sui.lang.core.psi.*
import org.sui.lang.core.psi.ext.MvItemsOwner
import org.sui.lang.core.psi.ext.itemElements
import org.sui.lang.core.resolve.*
import org.sui.lang.core.resolve.ref.Namespace

val MvNamedElement.namespace
    get() = when (this) {
        is MvFunctionLike -> Namespace.FUNCTION
        is MvStruct -> Namespace.TYPE
        is MvEnum -> Namespace.ENUM
        is MvConst -> Namespace.NAME
        is MvModule -> Namespace.MODULE
        else -> error("when should be exhaustive, $this is not covered")
    }

fun processItemDeclarations(
    itemsOwner: MvItemsOwner,
    ns: Set<Namespace>,
    processor: MvResolveProcessor
): Boolean {
    val items = itemsOwner.itemElements

    for (item in items) {
        val name = item.name ?: continue
        val namespace = item.namespace
        if (namespace !in ns) continue

        if (processor.process(name, item, setOf(namespace))) return true
    }

    return false
}
