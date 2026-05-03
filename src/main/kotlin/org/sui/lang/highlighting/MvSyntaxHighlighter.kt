package org.sui.lang.highlighting

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet
import org.sui.lang.MvElementTypes
import org.sui.lang.MoveParserDefinition
import org.sui.lang.core.MOVE_COMMENTS
import org.sui.lang.core.MOVE_KEYWORDS
import org.sui.lang.core.lexer.createMoveLexer

class MvSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer = createMoveLexer()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        val key = when {
            tokenType in DECLARATION_KEYWORDS -> DECLARATION_KEYWORD
            tokenType in CONTROL_KEYWORDS -> CONTROL_KEYWORD
            tokenType == MvElementTypes.HAS -> ABILITIES_HAS_KEYWORD
            tokenType in MOVE_KEYWORDS -> KEYWORD
            tokenType == MoveParserDefinition.EOL_DOC_COMMENT -> DOC_COMMENT
            tokenType in MOVE_COMMENTS -> COMMENT
            tokenType == TokenType.BAD_CHARACTER -> BAD_CHARACTER
            tokenType in NUMBER_LITERALS -> NUMBER
            tokenType in ADDRESS_LITERALS -> ADDRESS
            tokenType in STRING_LITERALS -> STRING
            tokenType == MvElementTypes.BOOL_LITERAL -> BOOLEAN
            tokenType == MvElementTypes.QUOTE_IDENTIFIER -> ESCAPED_IDENTIFIER
            tokenType in MACRO_TOKENS -> MACRO
            tokenType in ANNOTATION_TOKENS -> ANNOTATION
            tokenType == MvElementTypes.COLON -> TYPE_ASCRIPTION_COLON
            tokenType in DELIMITERS -> DELIMITER
            tokenType == MvElementTypes.L_BRACE || tokenType == MvElementTypes.R_BRACE -> BRACES
            tokenType == MvElementTypes.L_BRACK || tokenType == MvElementTypes.R_BRACK -> BRACKETS
            tokenType == MvElementTypes.L_PAREN || tokenType == MvElementTypes.R_PAREN -> PARENTHESES
            tokenType in OPERATORS -> OPERATION_SIGN
            else -> null
        }
        return pack(key)
    }

    companion object {
        val KEYWORD: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
        val DECLARATION_KEYWORD: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.DECLARATION_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
        val CONTROL_KEYWORD: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.CONTROL_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
        val ABILITY: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.ABILITY", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL)
        /** `has` before `copy, drop, store` in `struct S has ...` / `enum E has ...` — distinct from generic keywords and ability names. */
        val ABILITIES_HAS_KEYWORD: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.ABILITIES_HAS_KEYWORD", DefaultLanguageHighlighterColors.MARKUP_TAG)
        val PRIMITIVE_TYPE: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.PRIMITIVE_TYPE", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL)
        val VECTOR_TYPE: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.VECTOR_TYPE", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL)
        val FUNCTION_DECLARATION: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.FUNCTION_DECLARATION", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION)
        val FUNCTION_CALL: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.FUNCTION_CALL", DefaultLanguageHighlighterColors.FUNCTION_CALL)
        val PARAMETER: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.PARAMETER", DefaultLanguageHighlighterColors.PARAMETER)
        val VARIABLE: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.VARIABLE", DefaultLanguageHighlighterColors.LOCAL_VARIABLE)
        /** `let mut x` / `let mut (a, b)` — same idea as RustRover “Reassigned local variable”. */
        val MUTABLE_LOCAL: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.MUTABLE_LOCAL", DefaultLanguageHighlighterColors.REASSIGNED_LOCAL_VARIABLE)
        /** `fun f(mut x: T)` — same idea as Rust “Reassigned parameter”. */
        val MUTABLE_PARAMETER: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.MUTABLE_PARAMETER", DefaultLanguageHighlighterColors.REASSIGNED_PARAMETER)
        val FIELD: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.FIELD", DefaultLanguageHighlighterColors.INSTANCE_FIELD)
        val TYPE_DECLARATION: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.TYPE_DECLARATION", DefaultLanguageHighlighterColors.CLASS_NAME)
        /**
         * Generic parameters (`fun f<T>()`, `struct S<T>`).
         * Rust-style “type parameter” coloring: `DefaultLanguageHighlighterColors.INTERFACE_NAME` is used
         * as the closest Language Defaults analogue (no dedicated type-parameter key on this platform).
         */
        val TYPE_PARAMETER_NAME: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.TYPE_PARAMETER_NAME", DefaultLanguageHighlighterColors.INTERFACE_NAME)
        /**
         * `module foo` name. Rust-style module / path: `CLASS_REFERENCE` rather than ADT `CLASS_NAME`.
         */
        val MODULE_NAME: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.MODULE_NAME", DefaultLanguageHighlighterColors.CLASS_REFERENCE)
        /** `self` — defaults same as `PARAMETER`; kept separate for custom schemes (Rust-style self). */
        val SELF_PARAMETER: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.SELF_PARAMETER", DefaultLanguageHighlighterColors.PARAMETER)
        /** Enum variant used as a value (`Option::None`, `Some`) — Rust-style constant-like variant. */
        val ENUM_VARIANT: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.ENUM_VARIANT", DefaultLanguageHighlighterColors.CONSTANT)
        val TYPE_ASCRIPTION_COLON: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.TYPE_ASCRIPTION_COLON", DefaultLanguageHighlighterColors.OPERATION_SIGN)
        /** `const FOO` — definition site (distinct from `let` locals in default themes). */
        val CONST_DECLARATION: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.CONST_DECLARATION", DefaultLanguageHighlighterColors.CONSTANT)
        /** Use of a constant (`BAR` where it resolves to `const BAR`). */
        val CONST_REFERENCE: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.CONST_REFERENCE", DefaultLanguageHighlighterColors.CONSTANT)
        /** Legacy id; new schemes should use [CONST_DECLARATION] / [CONST_REFERENCE]. Inherits [CONST_REFERENCE] defaults. */
        val CONST_NAME: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.CONST_NAME", CONST_REFERENCE)
        val COMMENT: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val DOC_COMMENT: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.DOC_COMMENT", DefaultLanguageHighlighterColors.DOC_COMMENT)
        val STRING: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.STRING", DefaultLanguageHighlighterColors.STRING)
        /** `true` / `false` — Rust-style primitive literal (not control-flow keyword). */
        val BOOLEAN: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.BOOLEAN", DefaultLanguageHighlighterColors.CONSTANT)
        val NUMBER: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.NUMBER", DefaultLanguageHighlighterColors.NUMBER)
        val ADDRESS: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.ADDRESS", DefaultLanguageHighlighterColors.NUMBER)
        val ESCAPED_IDENTIFIER: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.ESCAPED_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER)
        /** Macro / pattern call sites — no `MACRO_CALL` on this platform; `STATIC_METHOD` is closer than `PARAMETER`. */
        val MACRO: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.MACRO", DefaultLanguageHighlighterColors.STATIC_METHOD)
        val ANNOTATION: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.ANNOTATION", DefaultLanguageHighlighterColors.METADATA)
        val OPERATION_SIGN: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.OP_SIGN", DefaultLanguageHighlighterColors.OPERATION_SIGN)
        val DELIMITER: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.DELIMITER", DefaultLanguageHighlighterColors.COMMA)
        val PARENTHESES: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES)
        val BRACES: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.BRACES", DefaultLanguageHighlighterColors.BRACES)
        val BRACKETS: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.BRACKETS", DefaultLanguageHighlighterColors.BRACKETS)
        val BAD_CHARACTER: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("SUI_MOVE.BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

        private val DECLARATION_KEYWORDS = TokenSet.create(
            MvElementTypes.MODULE_KW,
            MvElementTypes.STRUCT_KW,
            MvElementTypes.ENUM_KW,
            MvElementTypes.FUN,
            MvElementTypes.CONST_KW,
            MvElementTypes.USE,
            MvElementTypes.FRIEND,
            MvElementTypes.ENTRY,
            MvElementTypes.NATIVE,
            MvElementTypes.PUBLIC,
            MvElementTypes.INLINE,
            MvElementTypes.LET
        )

        private val CONTROL_KEYWORDS = TokenSet.create(
            MvElementTypes.IF,
            MvElementTypes.ELSE,
            MvElementTypes.LOOP,
            MvElementTypes.WHILE,
            MvElementTypes.FOR,
            MvElementTypes.MATCH_KW,
            MvElementTypes.BREAK,
            MvElementTypes.CONTINUE,
            MvElementTypes.RETURN,
            MvElementTypes.ABORT
        )

        private val NUMBER_LITERALS = TokenSet.create(
            MvElementTypes.INTEGER_LITERAL,
            MvElementTypes.HEX_INTEGER_LITERAL
        )

        private val ADDRESS_LITERALS = TokenSet.create(
            MvElementTypes.SUI_ADDRESS,
            MvElementTypes.BECH32_ADDRESS,
            MvElementTypes.POLKADOT_ADDRESS,
            MvElementTypes.PLACEHOLDER_ADDRESS
        )

        private val STRING_LITERALS = TokenSet.create(
            MvElementTypes.BYTE_STRING_LITERAL,
            MvElementTypes.HEX_STRING_LITERAL
        )

        private val MACRO_TOKENS = TokenSet.create(
            MvElementTypes.MARCO_IDENTIFIER,
            MvElementTypes.FUNCTION_PATTERN_IDENT
        )

        private val ANNOTATION_TOKENS = TokenSet.create(
            MvElementTypes.HASH,
            MvElementTypes.AT,
            MvElementTypes.BACKTICK
        )

        private val DELIMITERS = TokenSet.create(
            MvElementTypes.COMMA,
            MvElementTypes.COLON_COLON,
            MvElementTypes.SEMICOLON,
            MvElementTypes.DOT
        )

        private val OPERATORS = TokenSet.create(
            MvElementTypes.EQ,
            MvElementTypes.EQ_EQ,
            MvElementTypes.NOT_EQ,
            MvElementTypes.PLUS,
            MvElementTypes.MINUS,
            MvElementTypes.MUL,
            MvElementTypes.DIV,
            MvElementTypes.MODULO,
            MvElementTypes.AND,
            MvElementTypes.OR,
            MvElementTypes.XOR,
            MvElementTypes.AND_AND,
            MvElementTypes.OR_OR,
            MvElementTypes.LT,
            MvElementTypes.LT_EQ,
            MvElementTypes.GT,
            MvElementTypes.GT_EQ,
            MvElementTypes.LT_LT,
            MvElementTypes.GT_GT,
            MvElementTypes.FAT_ARROW
        )
    }
}
