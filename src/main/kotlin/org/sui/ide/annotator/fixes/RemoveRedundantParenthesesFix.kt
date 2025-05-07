package org.sui.ide.annotator.fixes

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import org.sui.ide.inspections.DiagnosticIntentionFix
import org.sui.lang.core.psi.MvParensExpr

class RemoveRedundantParenthesesFix(element: MvParensExpr) : DiagnosticIntentionFix<MvParensExpr>(element) {

    override fun getText(): String = "Remove parentheses from expression"

    override fun invoke(project: Project, file: PsiFile, element: MvParensExpr) {
        val wrappedExpr = startElement.expr ?: return
        startElement.replace(wrappedExpr)
    }
}
