package org.sui.utils

import com.intellij.psi.PsiElement
import org.sui.lang.MvElementTypes
import org.sui.lang.MvElementTypes.IDENTIFIER
import org.sui.lang.core.psi.MvPsiFactory
import org.sui.lang.core.psi.ext.elementType
import org.sui.lang.core.lexer.createMoveLexer

fun doRenameIdentifier(identifier: PsiElement, newName: String) {
    val factory = MvPsiFactory(identifier.project)
    val newIdentifier = when (identifier.elementType) {
        MvElementTypes.IDENTIFIER -> {
            if (!isValidMoveVariableIdentifier(newName)) return
            factory.identifier(newName)
        }
        else -> error("Unsupported identifier type for `$newName` (${identifier.elementType})")
    }
    identifier.replace(newIdentifier)
}

private fun isValidMoveVariableIdentifier(name: String): Boolean {
    val lexer = createMoveLexer()
    lexer.start(name)
    return lexer.tokenEnd == name.length && lexer.tokenType == IDENTIFIER
}
