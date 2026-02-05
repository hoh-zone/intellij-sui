/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.utils.tests

import com.intellij.psi.codeStyle.CodeStyleManager
import org.intellij.lang.annotations.Language

/**
 * Base class for formatter tests.
 */
abstract class MvFormatterTestBase : MvTestBase() {

    protected fun doTest() {
        val testName = getTestName(true)
        myFixture.configureByFile("$testName.move")
        reformatFile()
        myFixture.checkResultByFile("${testName}_after.move")
    }

    protected fun doTextTest(@Language("Move") before: String, @Language("Move") after: String) {
        myFixture.configureByText("main.move", before.trimIndent())
        reformatFile()
        myFixture.checkResult(after.trimIndent())
    }

    private fun reformatFile() {
        val codeStyleManager = CodeStyleManager.getInstance(project)
        codeStyleManager.reformatText(myFixture.file, 0, myFixture.file.textLength)
    }
}
