package org.sui.lang.core.psi.ext

import org.sui.lang.core.psi.*

fun MvSpecCodeBlock.schemaFields(): List<MvSchemaFieldStmt> = childrenOfType()

fun MvSpecCodeBlock.globalVariables(): List<MvGlobalVariableStmt> = childrenOfType()

val MvSpecCodeBlock.allLetStmts: List<MvLetStmt> get() = this.childrenOfType()

fun MvSpecCodeBlock.letStmts(post: Boolean): List<MvLetStmt> {
    val letStmts = this.allLetStmts
    return if (post) {
        letStmts.filter { it.post }
    } else {
        letStmts.filter { !it.post }
    }
}

fun MvSpecCodeBlock.specInlineFunctions(): List<MvSpecInlineFunction> {
    return this.childrenOfType<MvSpecInlineFunctionStmt>()
        .map { it.specInlineFunction }
}
