/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.utils.tests

import com.intellij.codeInsight.intention.IntentionAction
import org.intellij.lang.annotations.Language
import kotlin.reflect.KClass

/**
 * Base class for intention action tests.
 */
abstract class MvIntentionTestCase(
    private val intentionClass: KClass<out IntentionAction>
) : MvTestBase() {

    // Alternative constructor for Java Class
    constructor(intentionClass: Class<out IntentionAction>) : this(intentionClass.kotlin)

    protected val intention: IntentionAction by lazy {
        intentionClass.java.getDeclaredConstructor().newInstance()
    }

    protected fun doAvailableTest(
        @Language("Move") before: String,
        @Language("Move") after: String
    ) {
        InlineFile(myFixture, before)

        val intention = myFixture.findSingleIntention(intention.familyName)
        myFixture.launchAction(intention)

        myFixture.checkResult(replaceCaretMarker(after.trimIndent()))
    }

    protected fun doUnavailableTest(@Language("Move") code: String) {
        InlineFile(myFixture, code)

        val intentions = myFixture.filterAvailableIntentions(intention.familyName)
        check(intentions.isEmpty()) {
            "Intention should not be available, but found: ${intentions.map { it.text }}"
        }
    }
}
