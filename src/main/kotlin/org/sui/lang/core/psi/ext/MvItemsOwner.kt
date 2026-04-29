package org.sui.lang.core.psi.ext

import org.sui.lang.core.psi.*

interface MvItemsOwner: MvElement {
    val useStmtList: List<MvUseStmt>
}

val MvItemsOwner.itemElements: List<MvItemElement>
    get() = generateSequence(this.firstChild) { it.nextSibling }
        .filterIsInstance<MvItemElement>()
        .toList()
