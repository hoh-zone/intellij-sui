/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.utils.tests

import com.intellij.psi.PsiFile
import com.intellij.testFramework.fixtures.CodeInsightTestFixture

/**
 * Helper class to create inline files for testing.
 * Creates a temporary file with the given code and configures the fixture.
 */
class InlineFile(
    fixture: CodeInsightTestFixture,
    code: String,
    name: String = "main.move"
) {
    val psiFile: PsiFile

    init {
        psiFile = fixture.configureByText(name, replaceCaretMarker(code.trimIndent()))
    }
}
