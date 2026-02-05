/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.utils.tests.parser

import com.intellij.testFramework.ParsingTestCase
import org.sui.lang.MoveParserDefinition
import org.sui.utils.tests.base.TestCase

/**
 * Base class for parser tests.
 */
abstract class MvParsingTestCase(dataPath: String) : ParsingTestCase(
    "org/sui/lang/parser/$dataPath",
    "move",
    true,
    MoveParserDefinition()
) {
    override fun getTestDataPath(): String = "src/test/resources"

    override fun getTestName(lowercaseFirstLetter: Boolean): String {
        val camelCase = super.getTestName(lowercaseFirstLetter)
        return TestCase.camelOrWordsToSnake(camelCase)
    }

    protected open fun doTest() {
        doTest(true)
    }
}
