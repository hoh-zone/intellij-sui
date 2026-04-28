package org.sui.lang.core.psi.ext

import org.sui.lang.core.psi.*

interface MvItemsOwner: MvElement {
    val useStmtList: List<MvUseStmt>
}

fun MvItemsOwner.items(): Sequence<MvElement> {
    val startChild = this.firstChild
    return generateSequence(startChild) { it.nextSibling }
        .filterIsInstance<MvElement>()
}

val MvItemsOwner.itemElements: List<MvItemElement>
    get() {
        return this.items().filterIsInstance<MvItemElement>().toList()
    }
