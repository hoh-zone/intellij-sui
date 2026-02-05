package org.sui.ide.inspections.fixes

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import org.sui.ide.annotator.fixes.RemoveRedundantParenthesesFix
import org.sui.ide.inspections.DiagnosticFix
import org.sui.lang.core.psi.MvCastExpr
import org.sui.lang.core.psi.MvParensExpr
import org.sui.lang.core.types.infer.inference
import org.sui.lang.core.types.infer.loweredType

class RemoveRedundantCastFix(castExpr: MvCastExpr) : DiagnosticFix<MvCastExpr>(castExpr) {

    override fun getText(): String = "Remove redundant cast"

    override fun stillApplicable(project: Project, file: PsiFile, element: MvCastExpr): Boolean {
        val inference = element.inference(false) ?: return false
        val expr = element.exprList.firstOrNull() ?: return false
        val type = element.type ?: return false
        val elementExprTy = inference.getExprType(expr)
        val typeTy = type.loweredType(false)
        return elementExprTy == typeTy
    }

    override fun invoke(
        project: Project,
        file: PsiFile,
        element: MvCastExpr
    ) =
        element.replaceWithChildExpr()
}

fun MvCastExpr.replaceWithChildExpr() {
    val expr = this.exprList.firstOrNull() ?: return
    val newElement = this.replace(expr)
    val parent = newElement.parent
    if (parent is MvParensExpr) {
        RemoveRedundantParenthesesFix(parent).applyFix()
    }
}
