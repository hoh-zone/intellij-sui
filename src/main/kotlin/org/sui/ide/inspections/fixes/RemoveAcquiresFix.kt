package org.sui.ide.inspections.fixes

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import org.sui.ide.inspections.DiagnosticFix
import org.sui.lang.core.psi.MvAcquiresType
import org.sui.lang.core.psi.MvPathType
import org.sui.lang.core.psi.psiFactory

class RemoveAcquiresFix(ref: PsiElement) : DiagnosticFix<PsiElement>(ref) {
    override fun getText(): String = "Remove acquires"
    override fun getFamilyName(): String = text

    override fun invoke(project: Project, file: PsiFile, element: PsiElement) {
        when (element) {
            is MvAcquiresType -> element.delete()
            is MvPathType -> {
                val acquiresType = element.parent as MvAcquiresType
                val typeNames =
                    acquiresType.pathTypeList
                        .filter { it != element }
                        .joinToString(", ") { it.text }
                val newAcquiresType = project.psiFactory.acquires("acquires $typeNames")
                acquiresType.replace(newAcquiresType)
            }
        }
    }
}
