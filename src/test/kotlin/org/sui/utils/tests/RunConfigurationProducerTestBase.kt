/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.utils.tests

import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.actions.ConfigurationFromContext
import com.intellij.execution.actions.RunConfigurationProducer
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.testFramework.MapDataContext
import com.intellij.testFramework.PlatformTestUtil

/**
 * Base class for testing run configuration producers.
 */
abstract class RunConfigurationProducerTestBase(
    protected val configurationName: String
) : MvProjectTestBase() {

    protected fun checkOnFsItem(fsItem: PsiElement? = null, shouldProduceConfiguration: Boolean = true) {
        PlatformTestUtil.dispatchAllEventsInIdeEventQueue()

        val item = fsItem ?: myFixture.file ?: error("No file in fixture")
        val context = createContext(item)
        val configsFromContext = context.configurationsFromContext

        if (shouldProduceConfiguration) {
            check(configsFromContext != null && configsFromContext.isNotEmpty()) {
                "Expected run configuration to be produced"
            }
        } else {
            check(configsFromContext == null || configsFromContext.isEmpty()) {
                "Expected no run configuration, but got: ${configsFromContext?.map { it.configuration.name }}"
            }
        }
    }

    protected inline fun <reified T : PsiElement> checkOnElement(shouldOpenEditor: Boolean = false) {
        PlatformTestUtil.dispatchAllEventsInIdeEventQueue()

        val file = myFixture.file ?: error("No file in fixture")
        val elementAtCaret = file.findElementAt(myFixture.caretOffset) ?: error("No element at caret")
        val element = PsiTreeUtil.getParentOfType(elementAtCaret, T::class.java, false)
            ?: error("No ${T::class.simpleName} at caret")
        val context = createContext(element)
        val configsFromContext = context.configurationsFromContext

        check(configsFromContext != null && configsFromContext.isNotEmpty()) {
            "Expected run configuration to be produced for ${T::class.simpleName}"
        }
    }

    protected fun checkOnElement(element: PsiElement) {
        PlatformTestUtil.dispatchAllEventsInIdeEventQueue()

        val context = createContext(element)
        val configsFromContext = context.configurationsFromContext

        check(configsFromContext != null && configsFromContext.isNotEmpty()) {
            "Expected run configuration to be produced"
        }
    }

    protected inline fun <reified T : PsiElement> checkNoConfigurationOnElement() {
        PlatformTestUtil.dispatchAllEventsInIdeEventQueue()

        val file = myFixture.file ?: error("No file in fixture")
        val elementAtCaret = file.findElementAt(myFixture.caretOffset) ?: error("No element at caret")
        val element = PsiTreeUtil.getParentOfType(elementAtCaret, T::class.java, false)
        val context = createContext(element ?: elementAtCaret)
        val configsFromContext = context.configurationsFromContext

        check(configsFromContext == null || configsFromContext.isEmpty()) {
            "Expected no run configuration, but got: ${configsFromContext?.map { it.configuration.name }}"
        }
    }

    protected fun checkNoConfigurationOnFsItem(fsItem: PsiElement? = null) {
        checkOnFsItem(fsItem, shouldProduceConfiguration = false)
    }

    @PublishedApi
    internal fun createContext(element: PsiElement): ConfigurationContext {
        val dataContext = MapDataContext().apply {
            put(CommonDataKeys.PROJECT, myFixture.project)
            put(CommonDataKeys.PSI_FILE, myFixture.file)
            put(CommonDataKeys.PSI_ELEMENT, element)
        }
        return ConfigurationContext.getFromContext(dataContext, "")
    }
}
