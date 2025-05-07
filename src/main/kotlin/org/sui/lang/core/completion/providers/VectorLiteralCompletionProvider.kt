package org.sui.lang.core.completion.providers

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.PrioritizedLookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.editor.EditorModificationUtil
import com.intellij.patterns.ElementPattern
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.PsiElement
import com.intellij.util.ProcessingContext
import org.sui.lang.MvElementTypes
import org.sui.lang.core.MvPsiPattern
import org.sui.lang.core.completion.VECTOR_LITERAL_PRIORITY
import org.sui.lang.core.psi.MvPath

object VectorLiteralCompletionProvider : MvCompletionProvider() {
    override val elementPattern: ElementPattern<out PsiElement>
        get() = MvPsiPattern.path()
            .andNot(MvPsiPattern.pathType())
            .andNot(MvPsiPattern.schemaLit())
            .andNot(
                PlatformPatterns.psiElement()
                    .afterLeaf(PlatformPatterns.psiElement(MvElementTypes.COLON_COLON))
            )


    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet
    ) {
        val maybePath = parameters.position.parent
        val path = maybePath as? MvPath ?: maybePath.parent as MvPath

        if (parameters.position !== path.referenceNameElement) return

        val lookupElement = LookupElementBuilder
            .create("vector[]")
            .withTypeText("vector<?>")
            .withInsertHandler { ctx, _ ->
                EditorModificationUtil.moveCaretRelatively(ctx.editor, -1)
            }
        result.addElement(PrioritizedLookupElement.withPriority(lookupElement, VECTOR_LITERAL_PRIORITY))
    }

}
