package org.sui.lang.core.completion.providers

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.editor.EditorModificationUtil
import com.intellij.openapi.project.Project
import com.intellij.psi.util.elementType
import com.intellij.util.ProcessingContext
import org.sui.lang.MvElementTypes.L_BRACE
import org.sui.lang.core.completion.*
import org.sui.lang.core.psi.ext.isErrorElement
import org.sui.lang.core.psi.ext.isWhitespace
import org.sui.lang.core.psi.ext.rightSiblings

/**
 * When user has "public s" and invokes completion, offer "public struct" (lookup "struct")
 * so selecting it replaces "s" with "struct" -> "public struct".
 */
class PublicStructAfterPublicProvider : CompletionProvider<CompletionParameters>() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet,
    ) {
        val builder = LookupElementBuilder.create("struct")
            .withPresentableText("public struct")
            .bold()
        val withHandler = addInsertionHandler("struct", builder, parameters)
        result.addElement(withHandler.withPriority(KEYWORD_PRIORITY))
    }
}

class KeywordCompletionProvider(
    private val fillCompletions: (Project) -> List<String>
) :
    CompletionProvider<CompletionParameters>() {

    constructor(vararg keywords: String) : this({ keywords.toList() })

    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet,
    ) {
        val project = parameters.position.project
        val keywords = fillCompletions(project)
        for (keyword in keywords) {
            var builder = LookupElementBuilder.create(keyword).bold()
            // For compound keywords with spaces (like "public struct"),
            // add the first word as additional lookup string so it matches when typing "p"
            if (keyword.contains(" ")) {
                val firstWord = keyword.substringBefore(" ")
                builder = builder.withLookupString(firstWord)
            }
            builder = addInsertionHandler(keyword, builder, parameters)
            result.addElement(builder.withPriority(KEYWORD_PRIORITY))
        }
    }
}

internal fun addInsertionHandler(
    keyword: String,
    builder: LookupElementBuilder,
    parameters: CompletionParameters
): LookupElementBuilder {
    val posParent = parameters.position.parent
    val posRightSiblings =
        posParent.rightSiblings.filter { !it.isWhitespace() && !it.isErrorElement() }
    val posParentNextSibling = posRightSiblings.firstOrNull()?.firstChild

    return builder.withInsertHandler { ctx, _ ->
        val elemSibling = parameters.position.nextSibling
        val suffix = when {
            keyword == "acquires" && posParentNextSibling.elementType == L_BRACE -> {
                when {
                    ctx.nextCharIs(' ', 0) && ctx.nextCharIs(' ', 1) -> {
                        EditorModificationUtil.moveCaretRelatively(ctx.editor, 1)
                        ""
                    }
                    ctx.nextCharIs(' ') -> {
                        " "
                    }
                    else -> {
                        EditorModificationUtil.moveCaretRelatively(ctx.editor, -1)
                        "  "
                    }
                }
            }
            elemSibling != null && elemSibling.isWhitespace() -> ""
            posParentNextSibling == null || !ctx.alreadyHasSpace -> " "
            else -> ""
        }
        ctx.addSuffix(suffix)
    }
}
