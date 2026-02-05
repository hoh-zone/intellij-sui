/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.utils.tests.completion

import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.testFramework.fixtures.CodeInsightTestFixture
import com.intellij.testFramework.fixtures.impl.BaseFixture
import org.intellij.lang.annotations.Language
import org.sui.utils.tests.replaceCaretMarker

/**
 * Test fixture for Move completion tests.
 */
class MvCompletionTestFixture(
    private val codeInsightFixture: CodeInsightTestFixture,
    private val defaultFileName: String = "main.move"
) : BaseFixture() {

    override fun setUp() {
        super.setUp()
    }

    override fun tearDown() {
        super.tearDown()
    }

    private fun configureByText(text: String) {
        codeInsightFixture.configureByText(defaultFileName, replaceCaretMarker(text.trimIndent()))
    }

    fun doSingleCompletion(before: String, after: String) {
        check(hasCaretMarker(before)) { "No /*caret*/ marker in `before` text" }
        configureByText(before)
        executeSoloCompletion()
        codeInsightFixture.checkResult(replaceCaretMarker(after.trimIndent()))
    }

    fun checkNoCompletion(code: String) {
        configureByText(code)
        val lookups = codeInsightFixture.completeBasic()
        checkNotNull(lookups) {
            val element = codeInsightFixture.file.findElementAt(codeInsightFixture.caretOffset - 1)
            "Expected zero completions, but one completion was auto inserted: `${element?.text}`."
        }
        check(lookups.isEmpty()) {
            "Expected zero completions, got ${lookups.map { it.lookupString }}."
        }
    }

    fun checkContainsCompletion(code: String, expected: List<String>) {
        configureByText(code)
        val lookups = codeInsightFixture.completeBasic()?.map { it.lookupString } ?: emptyList()
        for (item in expected) {
            check(item in lookups) {
                "Expected completion '$item' not found. Available: $lookups"
            }
        }
    }

    fun checkNotContainsCompletion(code: String, notExpected: List<String>) {
        configureByText(code)
        val lookups = codeInsightFixture.completeBasic()?.map { it.lookupString } ?: emptyList()
        for (item in notExpected) {
            check(item !in lookups) {
                "Unexpected completion '$item' found. Available: $lookups"
            }
        }
    }

    fun invokeCompletion(code: String): Array<LookupElement> {
        configureByText(code)
        return codeInsightFixture.completeBasic() ?: emptyArray()
    }

    fun executeSoloCompletion() {
        val lookups = codeInsightFixture.completeBasic()

        if (lookups != null) {
            if (lookups.size == 1) {
                codeInsightFixture.type('\n')
                return
            }
            fun LookupElement.debug(): String = "$lookupString ($psiElement)"
            error("Expected a single completion, but got ${lookups.size}\n" +
                    lookups.joinToString("\n") { it.debug() })
        }
    }
}

private fun hasCaretMarker(text: String): Boolean =
    text.contains("/*caret*/") || text.contains("<caret>")
