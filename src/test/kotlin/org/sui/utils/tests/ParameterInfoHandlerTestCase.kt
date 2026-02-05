/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.utils.tests

import com.intellij.lang.parameterInfo.ParameterInfoHandler
import com.intellij.psi.PsiElement
import com.intellij.testFramework.utils.parameterInfo.MockCreateParameterInfoContext
import com.intellij.testFramework.utils.parameterInfo.MockParameterInfoUIContext
import com.intellij.testFramework.utils.parameterInfo.MockUpdateParameterInfoContext
import org.intellij.lang.annotations.Language

/**
 * Base class for parameter info handler tests.
 */
abstract class ParameterInfoHandlerTestCase<Owner : PsiElement, Hint : Any>(
    private val handler: ParameterInfoHandler<Owner, Hint>
) : MvTestBase() {

    protected fun checkByText(
        @Language("Move") text: String,
        expected: String,
        index: Int
    ) {
        InlineFile(myFixture, text)

        val createContext = MockCreateParameterInfoContext(myFixture.editor, myFixture.file)
        
        @Suppress("UNCHECKED_CAST")
        val owner = handler.findElementForParameterInfo(createContext) as? Owner

        if (owner == null) {
            check(expected.isEmpty()) {
                "No parameter info found, but expected: $expected"
            }
            return
        }

        handler.showParameterInfo(owner, createContext)
        val items = createContext.itemsToShow ?: emptyArray()

        if (items.isEmpty()) {
            check(expected.isEmpty()) {
                "No parameter hints, but expected: $expected"
            }
            return
        }

        val updateContext = MockUpdateParameterInfoContext(myFixture.editor, myFixture.file)
        updateContext.parameterOwner = owner

        @Suppress("UNCHECKED_CAST")
        val newOwner = handler.findElementForUpdatingParameterInfo(updateContext) as? Owner
        if (newOwner != null) {
            handler.updateParameterInfo(newOwner, updateContext)
        }

        val uiContext = MockParameterInfoUIContext(owner)
        uiContext.currentParameterIndex = if (index >= 0) index else 0

        @Suppress("UNCHECKED_CAST")
        for (item in items) {
            handler.updateUI(item as Hint, uiContext)
        }

        val actual = uiContext.text ?: ""
        check(actual.contains(expected) || normalizeText(actual) == normalizeText(expected)) {
            "Parameter info mismatch.\nExpected: $expected\nActual: $actual"
        }
    }

    protected fun checkNoParameterInfo(@Language("Move") text: String) {
        InlineFile(myFixture, text)

        val createContext = MockCreateParameterInfoContext(myFixture.editor, myFixture.file)
        val owner = handler.findElementForParameterInfo(createContext)

        check(owner == null || createContext.itemsToShow.isNullOrEmpty()) {
            "Expected no parameter info, but got some"
        }
    }

    private fun normalizeText(text: String): String {
        return text.replace("\\s+".toRegex(), " ").trim()
    }
}
