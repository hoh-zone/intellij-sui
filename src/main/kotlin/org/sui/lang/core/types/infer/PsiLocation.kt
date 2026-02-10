package org.sui.lang.core.types.infer

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import org.sui.openapiext.document
import org.sui.openapiext.getOffsetPosition

data class PsiLocation(val line: Int, val column: Int) {
    override fun toString(): String = "line=$line, column=$column"
}

val PsiElement.location: PsiLocation?
    get() {
        val file = this.containingFile ?: return null
        val (line, col) = file.document?.getOffsetPosition(this.textOffset) ?: return null
        return PsiLocation(line, col)
    }

fun PsiFile.elementLocation(psiElement: PsiElement): PsiLocation {
    val (line, col) = document?.getOffsetPosition(psiElement.textOffset) ?: (-1 to -1)
    return PsiLocation(line, col)
}
