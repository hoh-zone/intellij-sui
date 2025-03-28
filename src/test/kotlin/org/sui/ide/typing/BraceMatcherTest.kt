package org.sui.ide.typing

import com.intellij.codeInsight.highlighting.BraceMatchingUtil
import com.intellij.openapi.editor.ex.EditorEx
import org.intellij.lang.annotations.Language
import org.sui.lang.MoveFileType
import org.sui.utils.tests.MvTypingTestCase
import org.sui.utils.tests.replaceCaretMarker

class BraceMatcherTest : MvTypingTestCase() {
    fun `test don't pair parenthesis before identifier`() = doTest(
        "script { fun main() { let _ = /*caret*/typing }}",
        '(',
        "script { fun main() { let _ = (/*caret*/typing }}"
    )

    fun `test pair parens before semicolon`() = doTest(
        "script { fun main() { let _ = /*caret*/; }}",
        '(',
        "script { fun main() { let _ = (/*caret*/); }}"
    )

    fun `test pair parens before brace`() = doTest(
        "script { fun main/*caret*/ {}}",
        '(',
        "script { fun main(/*caret*/) {}}"
    )

    fun `test pair square brackets for vector lit`() = doTest(
        "module 0x1::m { fun main() { vector/*caret*/ }}",
        '[',
        "module 0x1::m { fun main() { vector[/*caret*/] }}"
    )

    fun `test pair square brackets for index expr`() = doTest(
        "module 0x1::m { fun main() { v/*caret*/ }}",
        '[',
        "module 0x1::m { fun main() { v[/*caret*/] }}"
    )

    fun `test match parens`() = doMatch("script { fun main/*caret*/(x: u8) {}}", ")")

    fun `test match square brackets vector lit`() = doMatch("module 0x1::m { fun main() { vector/*caret*/[] }}", "]")

    fun `test match square brackets index expr`() = doMatch("module 0x1::m { fun main() { v/*caret*/[] }}", "]")

    fun `test match angle brackets`() = doMatch("script { fun main/*caret*/<T>(x: u8) {}}", ">")

    fun `test match angle with abilities`() =
        doMatch("script { fun main/*caret*/<T: drop + store>(x: u8) {}}", ">")

    fun `test match angle with phantom`() =
        doMatch("script { fun main/*caret*/<phantom T>(x: u8) {}}", ">")

    fun `test no match`() {
        noMatch("script { fun main() { let a = 4 /*caret*/< 5 && 2 > 1; } }")
        noMatch("script { fun main() { let a = 4 /*caret*/<< 5 || 2 >> 1; } }")
        noMatch("script { fun main() { let a = 4 /*caret*/< 5; let b = { 1 >> 2 }; } }")
    }

    private fun noMatch(@Language("Sui Move") source: String) {
        val sourceText = replaceCaretMarker(source)

        myFixture.configureByText(MoveFileType, sourceText)
        val editorHighlighter = (myFixture.editor as EditorEx).highlighter
        val iterator = editorHighlighter.createIterator(myFixture.editor.caretModel.offset)
        val matched = BraceMatchingUtil.matchBrace(
            myFixture.editor.document.charsSequence,
            myFixture.file.fileType,
            iterator,
            true
        )
        check(!matched)
    }

    private fun doMatch(@Language("Sui Move") source: String, coBrace: String) {
        val sourceText = replaceCaretMarker(source)

        myFixture.configureByText(MoveFileType, sourceText)
        val expected = sourceText.replace("<caret>", "").lastIndexOf(coBrace)
        check(BraceMatchingUtil.getMatchedBraceOffset(myFixture.editor, true, myFixture.file) == expected)
    }

//    private fun doTest(@Language("Sui Move") before: String, type: Char, @Language("Sui Move") after: String) {
//        val beforeText = replaceCaretMarker(before)
//        val afterText = replaceCaretMarker(after)
//
//        myFixture.configureByText(MoveFileType, beforeText)
//        myFixture.type(type)
//        myFixture.checkResult(afterText)
//    }
}
