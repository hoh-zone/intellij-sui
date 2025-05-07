package org.sui.ide.annotator.fixes

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import org.sui.ide.inspections.DiagnosticIntentionFix
import org.sui.ide.utils.getSignature
import org.sui.lang.core.psi.MvItemSpec
import org.sui.lang.core.psi.ext.funcItem
import org.sui.lang.core.psi.psiFactory

class ItemSpecSignatureFix(
    itemSpec: MvItemSpec
) :
    DiagnosticIntentionFix<MvItemSpec>(itemSpec) {

    override fun getText(): String = "Fix item signature"

    override fun invoke(project: Project, file: PsiFile, element: MvItemSpec) {
        val itemSpec = startElement
        val actualSignature = itemSpec.funcItem?.getSignature() ?: return

        val psiFactory = project.psiFactory
        val newSpecSignature = psiFactory.itemSpecSignature(actualSignature)
        itemSpec.itemSpecSignature?.replace(newSpecSignature)
    }
}
