/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.utils.tests.completion

import com.intellij.codeInsight.lookup.LookupElement
import org.intellij.lang.annotations.Language
import org.sui.utils.tests.MvTestBase
import org.sui.utils.tests.replaceCaretMarker

/**
 * Base class for completion tests.
 */
abstract class CompletionTestCase : MvTestBase() {

    protected lateinit var completionFixture: MvCompletionTestFixture

    override fun setUp() {
        super.setUp()
        completionFixture = MvCompletionTestFixture(myFixture)
        completionFixture.setUp()
    }

    override fun tearDown() {
        try {
            completionFixture.tearDown()
        } catch (e: Throwable) {
            addSuppressedException(e)
        } finally {
            super.tearDown()
        }
    }

    protected fun doSingleCompletion(
        @Language("Move") before: String,
        @Language("Move") after: String
    ) {
        completionFixture.doSingleCompletion(before, after)
    }

    protected fun checkNoCompletion(@Language("Move") code: String) {
        completionFixture.checkNoCompletion(code)
    }

    protected fun checkContainsCompletion(
        @Language("Move") code: String,
        expected: List<String>
    ) {
        myFixture.configureByText("main.move", replaceCaretMarker(code.trimIndent()))
        val lookups = myFixture.completeBasic()?.map { it.lookupString } ?: emptyList()

        for (item in expected) {
            check(item in lookups) {
                "Expected completion '$item' not found. Available: $lookups"
            }
        }
    }

    protected fun checkContainsCompletion(
        expected: List<String>,
        @Language("Move") code: String
    ) = checkContainsCompletion(code, expected)

    protected fun checkContainsCompletion(
        expected: String,
        @Language("Move") code: String
    ) = checkContainsCompletion(code, listOf(expected))

    protected fun checkContainsCompletion(
        @Language("Move") code: String,
        vararg expected: String
    ) = checkContainsCompletion(code, expected.toList())

    protected fun checkNotContainsCompletion(
        @Language("Move") code: String,
        vararg notExpected: String
    ) {
        myFixture.configureByText("main.move", replaceCaretMarker(code.trimIndent()))
        val lookups = myFixture.completeBasic()?.map { it.lookupString } ?: emptyList()

        for (item in notExpected) {
            check(item !in lookups) {
                "Unexpected completion '$item' found. Available: $lookups"
            }
        }
    }

    protected fun checkNotContainsCompletion(
        notExpected: String,
        @Language("Move") code: String
    ) = checkNotContainsCompletion(code, notExpected)

    protected fun doFirstCompletion(
        @Language("Move") before: String,
        @Language("Move") after: String
    ) {
        myFixture.configureByText("main.move", replaceCaretMarker(before.trimIndent()))
        val lookups = myFixture.completeBasic()

        if (lookups != null && lookups.isNotEmpty()) {
            myFixture.type('\n')
        }

        myFixture.checkResult(replaceCaretMarker(after.trimIndent()))
    }

    protected fun completionFixture(): MvCompletionTestFixture = completionFixture
}
