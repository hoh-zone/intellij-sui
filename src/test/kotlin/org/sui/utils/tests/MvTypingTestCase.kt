/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.utils.tests

import org.intellij.lang.annotations.Language

/**
 * Base class for typing/auto-completion tests.
 */
abstract class MvTypingTestCase : MvTestBase() {
    /**
     * File-based typing tests.
     *
     * Expects `<test_name>.move` and `<test_name>_after.move` under `src/test/resources/<dataPath>/`.
     * The before file should contain a caret marker (`<caret>` or `/*caret*/`).
     */
    protected fun doTestByFile(c: Char = '\n') {
        val testName = getTestName(true)
        val before = "$testName.move"
        val after = "${testName}_after.move"
        myFixture.configureByFile(before)
        myFixture.type(c)
        myFixture.checkResultByFile(after)
    }


    /**
     * Test typing by entering a newline (Enter key) at the caret position.
     */
    protected fun doTestByText(
        @Language("Move") before: String,
        @Language("Move") after: String
    ) {
        doTestByText(before, after, '\n')
    }

    protected fun doTestByText(
        @Language("Move") before: String,
        @Language("Move") after: String,
        c: Char
    ) {
        InlineFile(myFixture, before)
        myFixture.type(c)
        myFixture.checkResult(replaceCaretMarker(after.trimIndent()))
    }

    protected fun doTestByText(
        @Language("Move") before: String,
        @Language("Move") after: String,
        text: String
    ) {
        InlineFile(myFixture, before)
        myFixture.type(text)
        myFixture.checkResult(replaceCaretMarker(after.trimIndent()))
    }

    /**
     * Alternative method name for typing tests (before, char, after).
     */
    protected fun doTest(
        @Language("Move") before: String,
        c: Char,
        @Language("Move") after: String
    ) {
        doTestByText(before, after, c)
    }

    /**
     * Alternative method name for typing tests (before, text, after).
     */
    protected fun doTest(
        @Language("Move") before: String,
        text: String,
        @Language("Move") after: String
    ) {
        doTestByText(before, after, text)
    }
}
