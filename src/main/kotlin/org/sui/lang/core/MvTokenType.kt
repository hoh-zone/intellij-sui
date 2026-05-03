package org.sui.lang.core

import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet
import org.sui.lang.MoveLanguage
import org.sui.lang.MoveParserDefinition.Companion.BLOCK_COMMENT
import org.sui.lang.MoveParserDefinition.Companion.EOL_COMMENT
import org.sui.lang.MoveParserDefinition.Companion.EOL_DOC_COMMENT
import org.sui.lang.MvElementTypes.*

class MvTokenType(debugName: String) : IElementType(debugName, MoveLanguage)

fun tokenSetOf(vararg tokens: IElementType) = TokenSet.create(*tokens)

val MOVE_KEYWORDS = tokenSetOf(
    LET, MUT, COPY, ABORT, BREAK, CONTINUE, IF, ELSE, LOOP, RETURN, AS, WHILE, FOR,
    ADDRESS, MODULE_KW, PUBLIC, FUN, STRUCT_KW, USE, HAS, PHANTOM,
    MOVE, CONST_KW, NATIVE, FRIEND, ENTRY, INLINE, MACRO_KW,
    IN, ENUM_KW, MATCH_KW
)
val CONTEXTUAL_KEYWORDS = tokenSetOf(MATCH_KW)

val MOVE_COMMENTS = tokenSetOf(BLOCK_COMMENT, EOL_COMMENT, EOL_DOC_COMMENT)
