package org.sui.lang.highlighting

import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.TokenSet
import org.sui.lang.MvElementTypes
import org.sui.lang.core.psi.MvAbility
import org.sui.lang.core.psi.MvAbilitiesList
import org.sui.lang.core.psi.MvCallExpr
import org.sui.lang.core.psi.MvConst
import org.sui.lang.core.psi.MvEnum
import org.sui.lang.core.psi.MvEnumVariant
import org.sui.lang.core.psi.MvFunction
import org.sui.lang.core.psi.MvFunctionParameter
import org.sui.lang.core.psi.MvModule
import org.sui.lang.core.psi.MvNamedFieldDecl
import org.sui.lang.core.psi.MvPathExpr
import org.sui.lang.core.psi.MvPathType
import org.sui.lang.core.psi.MvPatBinding
import org.sui.lang.core.psi.MvPath
import org.sui.lang.core.psi.MvStruct
import org.sui.lang.core.psi.MvStructLitField
import org.sui.lang.core.psi.MvTypeParamBound
import org.sui.lang.core.psi.MvTypeParameter
import org.sui.lang.core.psi.MvTypeAnnotation
import org.sui.lang.core.psi.ext.ancestorStrict
import org.sui.lang.core.psi.ext.isSelfParam

class MvSemanticHighlightingAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        annotateContextualKeywordToken(element, holder)

        when (element) {
            is MvAbility -> {
                val abilityElement = element.identifier ?: element.copy
                if (abilityElement != null && abilityElement.text in ABILITIES) {
                    holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                        .range(abilityElement)
                        .textAttributes(MvSyntaxHighlighter.ABILITY)
                        .create()
                }
            }
            is MvFunction -> annotateNameElement(element.nameElement, holder, MvSyntaxHighlighter.FUNCTION_DECLARATION)
            is MvTypeParameter -> annotateNameElement(element.nameElement, holder, MvSyntaxHighlighter.TYPE_DECLARATION)
            is MvFunctionParameter -> {
                val binding = element.patBinding
                annotateNameElement(binding.nameIdentifier, holder, MvSyntaxHighlighter.PARAMETER)
            }
            is MvNamedFieldDecl -> annotateNameElement(element.nameIdentifier, holder, MvSyntaxHighlighter.FIELD)
            is MvStructLitField -> annotateNameElement(element.identifier, holder, MvSyntaxHighlighter.FIELD)
            is MvTypeAnnotation -> annotateNameElement(element.colon, holder, MvSyntaxHighlighter.TYPE_ASCRIPTION_COLON)
            is MvPatBinding -> {
                val owner = element.ancestorStrict<MvFunctionParameter>()
                if (owner == null) {
                    annotateNameElement(element.nameIdentifier, holder, MvSyntaxHighlighter.VARIABLE)
                }
            }
            is MvModule, is MvStruct, is MvEnum, is MvEnumVariant ->
                annotateNameElement((element as? org.sui.lang.core.psi.MvNamedElement)?.nameElement, holder, MvSyntaxHighlighter.TYPE_DECLARATION)
            is MvConst -> annotateNameElement(element.nameElement, holder, MvSyntaxHighlighter.CONST_NAME)
            is MvCallExpr -> annotateNameElement(element.path?.referenceNameElement, holder, MvSyntaxHighlighter.FUNCTION_CALL)
            is MvPathExpr -> {
                val path = element.path ?: return
                val target = path.reference?.resolve()
                if (target == null && path.referenceName == "Self") {
                    annotateNameElement(path.referenceNameElement, holder, MvSyntaxHighlighter.TYPE_DECLARATION)
                    return
                }
                if (target == null && path.referenceName == "self") {
                    annotateNameElement(path.referenceNameElement, holder, MvSyntaxHighlighter.PARAMETER)
                    return
                }
                if (target == null && path.referenceName in BUILTIN_TYPED_FUNCTIONS) {
                    annotateNameElement(path.referenceNameElement, holder, MvSyntaxHighlighter.FUNCTION_CALL)
                    return
                }
                if (target == null && path.parent is MvCallExpr) {
                    // Keep function-call color even when resolution is delayed or temporarily unavailable.
                    annotateNameElement(path.referenceNameElement, holder, MvSyntaxHighlighter.FUNCTION_CALL)
                    return
                }
                if (target == null) return
                when (target) {
                    is MvPatBinding -> {
                        val param = target.ancestorStrict<MvFunctionParameter>()
                        val attr = if (param?.isSelfParam == true || param != null
                        ) MvSyntaxHighlighter.PARAMETER else MvSyntaxHighlighter.VARIABLE
                        annotateNameElement(path.referenceNameElement, holder, attr)
                    }
                    is MvFunction -> annotateNameElement(path.referenceNameElement, holder, MvSyntaxHighlighter.FUNCTION_CALL)
                    is MvConst -> annotateNameElement(path.referenceNameElement, holder, MvSyntaxHighlighter.CONST_NAME)
                    is MvStruct, is MvEnum, is MvEnumVariant -> annotateNameElement(path.referenceNameElement, holder, MvSyntaxHighlighter.TYPE_DECLARATION)
                    is MvTypeParameter -> annotateNameElement(path.referenceNameElement, holder, MvSyntaxHighlighter.TYPE_DECLARATION)
                }
            }
            is MvPathType -> {
                val path = element.path
                val resolved = path.reference?.resolve()
                val typeName = path.referenceName
                val attrKey = when {
                    typeName in PRIMITIVE_TYPES || typeName in SPEC_PRIMITIVE_TYPES -> MvSyntaxHighlighter.PRIMITIVE_TYPE
                    typeName == "vector" -> MvSyntaxHighlighter.VECTOR_TYPE
                    resolved is MvTypeParameter -> MvSyntaxHighlighter.TYPE_DECLARATION
                    else -> MvSyntaxHighlighter.TYPE_DECLARATION
                }

                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(path.referenceNameElement ?: element)
                    .textAttributes(attrKey)
                    .create()
            }
            else -> {
                if (element.node.elementType == MvElementTypes.IDENTIFIER || element.node.elementType == MvElementTypes.COPY) {
                    when {
                        element.text in ABILITIES && element.isInAbilityContext() ->
                            annotateNameElement(element, holder, MvSyntaxHighlighter.ABILITY)
                        element.text in PRIMITIVE_TYPES || element.text in SPEC_PRIMITIVE_TYPES || element.text in BUILTIN_TYPE_NAMES ->
                            annotateNameElement(element, holder, MvSyntaxHighlighter.PRIMITIVE_TYPE)
                        element.text == "vector" -> annotateNameElement(element, holder, MvSyntaxHighlighter.VECTOR_TYPE)
                        element.text == "Self" -> annotateNameElement(element, holder, MvSyntaxHighlighter.TYPE_DECLARATION)
                        element.text == "self" && element.parent is MvPath ->
                            annotateNameElement(element, holder, MvSyntaxHighlighter.PARAMETER)
                        element.text in BUILTIN_TYPED_FUNCTIONS && (element.parent is org.sui.lang.core.psi.MvPath || element.parent is MvCallExpr) ->
                            annotateNameElement(element, holder, MvSyntaxHighlighter.FUNCTION_CALL)
                        element.parent is MvPath && element.parent.parent is MvCallExpr ->
                            annotateNameElement(element, holder, MvSyntaxHighlighter.FUNCTION_CALL)
                        element.text in DECLARATION_CONTEXTUAL_KEYWORDS ->
                            annotateNameElement(element, holder, MvSyntaxHighlighter.DECLARATION_KEYWORD)
                        element.text in CONTROL_CONTEXTUAL_KEYWORDS ->
                            annotateNameElement(element, holder, MvSyntaxHighlighter.CONTROL_KEYWORD)
                        element.text in SPEC_CONTEXTUAL_KEYWORDS ->
                            annotateNameElement(element, holder, MvSyntaxHighlighter.SPEC_KEYWORD)
                        element.text in GENERIC_CONTEXTUAL_KEYWORDS ->
                            annotateNameElement(element, holder, MvSyntaxHighlighter.KEYWORD)
                        element.text in BASE_KEYWORDS ->
                            annotateNameElement(element, holder, MvSyntaxHighlighter.KEYWORD)
                    }
                }
            }
        }
    }

    companion object {
        private val ABILITIES = setOf("store", "key", "drop", "copy")
        private val PRIMITIVE_TYPES = setOf("u8", "u16", "u32", "u64", "u128", "u256", "bool", "address", "signer")
        private val SPEC_PRIMITIVE_TYPES = setOf("num", "range", "bv")
        private val BUILTIN_TYPE_NAMES = setOf("option", "Option", "vector", "String", "ascii", "utf8")
        private val BUILTIN_TYPED_FUNCTIONS = setOf(
            "borrow_global", "borrow_global_mut", "exists", "move_from", "move_to", "move_to_sender"
        )
        private val BASE_KEYWORDS = setOf(
            "let", "mut", "abort", "break", "continue", "if", "else", "loop", "return", "as", "while", "for",
            "script", "address", "module", "public", "fun", "struct", "acquires", "use", "phantom",
            "move", "const", "native", "spec", "type", "macro"
        )
        private val DECLARATION_CONTEXTUAL_KEYWORDS = setOf("friend", "entry", "inline", "enum", "package", "internal")
        private val CONTROL_CONTEXTUAL_KEYWORDS = setOf("match", "for", "in", "where")
        private val SPEC_CONTEXTUAL_KEYWORDS = setOf(
            "reads", "writes", "pure", "pragma", "post", "local", "global",
            "forall", "exists", "with", "include", "choose", "min", "invariant", "axiom",
            "aborts_if", "aborts_with", "assert", "assume", "modifies", "ensures",
            "requires", "decreases", "emits", "apply", "except", "to", "update"
        )
        private val GENERIC_CONTEXTUAL_KEYWORDS = setOf("has")
        private val DECLARATION_TOKENS = TokenSet.create(
            MvElementTypes.FRIEND, MvElementTypes.ENTRY, MvElementTypes.INLINE,
            MvElementTypes.ENUM_KW, MvElementTypes.PACKAGE, MvElementTypes.INTERNAL
        )
        private val CONTROL_TOKENS = TokenSet.create(
            MvElementTypes.MATCH_KW, MvElementTypes.FOR, MvElementTypes.IN, MvElementTypes.WHERE
        )
        private val SPEC_TOKENS = TokenSet.create(
            MvElementTypes.READS, MvElementTypes.WRITES, MvElementTypes.PURE, MvElementTypes.PRAGMA,
            MvElementTypes.POST, MvElementTypes.LOCAL, MvElementTypes.GLOBAL, MvElementTypes.FORALL,
            MvElementTypes.EXISTS, MvElementTypes.WITH, MvElementTypes.INCLUDE, MvElementTypes.CHOOSE,
            MvElementTypes.MIN, MvElementTypes.INVARIANT, MvElementTypes.AXIOM, MvElementTypes.ABORTS_IF,
            MvElementTypes.ABORTS_WITH, MvElementTypes.ASSERT, MvElementTypes.ASSUME, MvElementTypes.MODIFIES,
            MvElementTypes.ENSURES, MvElementTypes.REQUIRES, MvElementTypes.DECREASES, MvElementTypes.EMITS,
            MvElementTypes.APPLY, MvElementTypes.EXCEPT, MvElementTypes.TO, MvElementTypes.UPDATE
        )
        private val GENERIC_TOKENS = TokenSet.create(MvElementTypes.HAS)
    }

    private fun annotateNameElement(element: PsiElement?, holder: AnnotationHolder, attrKey: com.intellij.openapi.editor.colors.TextAttributesKey) {
        if (element == null) return
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
            .range(element)
            .textAttributes(attrKey)
            .create()
    }

    private fun annotateContextualKeywordToken(element: PsiElement, holder: AnnotationHolder) {
        val tokenType = element.node.elementType
        when {
            tokenType in DECLARATION_TOKENS -> annotateNameElement(element, holder, MvSyntaxHighlighter.DECLARATION_KEYWORD)
            tokenType in CONTROL_TOKENS -> annotateNameElement(element, holder, MvSyntaxHighlighter.CONTROL_KEYWORD)
            tokenType in SPEC_TOKENS -> annotateNameElement(element, holder, MvSyntaxHighlighter.SPEC_KEYWORD)
            tokenType in GENERIC_TOKENS -> annotateNameElement(element, holder, MvSyntaxHighlighter.KEYWORD)
        }
    }

    private fun PsiElement.isInAbilityContext(): Boolean {
        return parent is MvAbility
            || parent is MvAbilitiesList
            || ancestorStrict<MvTypeParamBound>() != null
            || ancestorStrict<MvAbilitiesList>() != null
    }
}
