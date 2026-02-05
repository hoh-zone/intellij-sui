/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.utils.tests

import com.intellij.lexer.Lexer
import com.intellij.testFramework.LexerTestCase
import org.sui.lang.core.lexer.MoveLexer
import org.sui.utils.tests.base.TestCase

/**
 * Base class for lexer tests.
 */
abstract class MvLexerTestBase : LexerTestCase() {
    override fun createLexer(): Lexer = MoveLexer()

    override fun getDirPath(): String = "src/test/resources/lexer"

    override fun getTestName(lowercaseFirstLetter: Boolean): String {
        val camelCase = super.getTestName(lowercaseFirstLetter)
        return TestCase.camelOrWordsToSnake(camelCase)
    }

    protected fun doTest() {
        doFileTest("move")
    }
}
