package org.sui.lang.highlighting

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors as Default
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey

/**
 * Centralized [TextAttributesKey] catalogue for Sui Move highlighting.
 *
 * Categories mirror the official Sui Move 2024 VS Code grammar
 * (damirka/move-syntax) so that semantics stay aligned across IDEs.
 */
enum class MvColor(default: TextAttributesKey) {
    // ----- comments -----
    LINE_COMMENT(Default.LINE_COMMENT),
    BLOCK_COMMENT(Default.BLOCK_COMMENT),
    DOC_COMMENT(Default.DOC_COMMENT),
    DOC_COMMENT_LINK(Default.DOC_COMMENT_TAG),

    // ----- literals -----
    NUMBER(Default.NUMBER),
    HEX_NUMBER(Default.NUMBER),
    BOOLEAN(Default.KEYWORD),
    STRING(Default.STRING),
    BYTE_STRING(Default.STRING),
    HEX_STRING(Default.STRING),
    STRING_ESCAPE(Default.VALID_STRING_ESCAPE),
    ADDRESS(Default.NUMBER),
    NAMED_ADDRESS(Default.PREDEFINED_SYMBOL),

    // ----- identifiers -----
    IDENTIFIER(Default.IDENTIFIER),
    ESCAPED_IDENTIFIER(Default.IDENTIFIER),
    LABEL(Default.LABEL),

    // ----- generic keyword buckets -----
    KEYWORD(Default.KEYWORD),
    DECLARATION_KEYWORD(Default.KEYWORD),
    CONTROL_KEYWORD(Default.KEYWORD),

    // ----- single-token keywords with their own scopes in VS Code -----
    KEYWORD_AS(Default.KEYWORD),
    KEYWORD_MUT(Default.KEYWORD),
    KEYWORD_PHANTOM(Default.KEYWORD),
    KEYWORD_HAS(Default.KEYWORD),
    KEYWORD_MOVE_COPY(Default.KEYWORD),
    MATCH_ARROW(Default.OPERATION_SIGN),

    // ----- visibility -----
    VIS_PUBLIC(Default.KEYWORD),
    VIS_ENTRY(Default.KEYWORD),
    VIS_NATIVE(Default.KEYWORD),
    VIS_INLINE(Default.KEYWORD),
    VIS_PUBLIC_PACKAGE(Default.KEYWORD),
    VIS_PUBLIC_FRIEND(Default.KEYWORD),

    // ----- names -----
    FUNCTION_DECL(Default.FUNCTION_DECLARATION),
    FUNCTION_CALL(Default.FUNCTION_CALL),
    METHOD_CALL(Default.INSTANCE_METHOD),
    MACRO_CALL(Default.STATIC_METHOD),
    MACRO_BANG(Default.OPERATION_SIGN),
    BUILTIN_FUNCTION(Default.PREDEFINED_SYMBOL),
    PARAMETER(Default.PARAMETER),
    VARIABLE(Default.LOCAL_VARIABLE),
    FIELD(Default.INSTANCE_FIELD),
    TYPE_DECL(Default.CLASS_NAME),
    TYPE_PARAMETER(Default.CLASS_NAME),
    PRIMITIVE_TYPE(Default.PREDEFINED_SYMBOL),
    VECTOR_TYPE(Default.PREDEFINED_SYMBOL),
    ENUM_TYPE(Default.CLASS_NAME),
    ENUM_VARIANT(Default.STATIC_FIELD),
    STRUCT_TYPE(Default.CLASS_NAME),
    ABILITY(Default.PREDEFINED_SYMBOL),
    CONST_NAME(Default.CONSTANT),
    ERROR_CONST(Default.CONSTANT),

    // ----- macro params -----
    MACRO_DOLLAR(Default.OPERATION_SIGN),
    MACRO_PARAMETER(Default.PARAMETER),

    // ----- attributes -----
    ATTRIBUTE_BRACKETS(Default.METADATA),
    ATTRIBUTE_NAME(Default.METADATA),
    ATTRIBUTE_VALUE(Default.METADATA),

    // ----- punctuation / operators -----
    OPERATOR(Default.OPERATION_SIGN),
    TYPE_ASCRIPTION_COLON(Default.OPERATION_SIGN),
    SEMICOLON(Default.SEMICOLON),
    COMMA(Default.COMMA),
    DOT(Default.DOT),
    COLON_COLON(Default.DOT),
    PARENTHESES(Default.PARENTHESES),
    BRACES(Default.BRACES),
    BRACKETS(Default.BRACKETS),
    BAD_CHARACTER(HighlighterColors.BAD_CHARACTER);

    val textAttributesKey: TextAttributesKey =
        TextAttributesKey.createTextAttributesKey("SUI_MOVE.$name", default)

    val textAttributesKeys: Array<TextAttributesKey> = arrayOf(textAttributesKey)
}
