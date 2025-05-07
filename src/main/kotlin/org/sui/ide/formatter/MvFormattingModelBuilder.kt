package org.sui.ide.formatter

import com.intellij.formatting.*

class MvFormattingModelBuilder : FormattingModelBuilder {
    override fun createModel(formattingContext: FormattingContext): FormattingModel {
        val formatterBlock =
            MoveFmtBlock(
                formattingContext.psiElement.node,
                null,
                null,
                Indent.getNoneIndent(),
                MvFmtContext.create(formattingContext.codeStyleSettings),
                debugName = "FileBlock"
            )
        return FormattingModelProvider.createFormattingModelForPsiFile(
            formattingContext.containingFile,
            formatterBlock,
            formattingContext.codeStyleSettings
        )
    }
}
