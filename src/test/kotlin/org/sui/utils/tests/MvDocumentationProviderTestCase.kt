/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.utils.tests

import com.intellij.lang.documentation.DocumentationManager
import com.intellij.lang.documentation.DocumentationProvider
import com.intellij.psi.PsiElement
import org.intellij.lang.annotations.Language

/**
 * Base class for documentation provider tests.
 */
abstract class MvDocumentationProviderTestCase : MvTestBase() {

    protected fun doTest(@Language("Move") code: String, expected: String) {
        InlineFile(myFixture, code)

        val element = getElementAtCaret()
        val doc = generateDocumentation(element)

        check(doc != null) { "No documentation generated" }
        check(normalizeHtml(doc).contains(normalizeHtml(expected))) {
            "Documentation doesn't contain expected text.\nExpected: $expected\nActual: $doc"
        }
    }

    protected fun doTest(
        @Language("Move") code: String,
        @Language("Html") expected: String?,
        block: (PsiElement?, PsiElement?) -> String?
    ) {
        InlineFile(myFixture, code)

        val element = getElementAtCaret()
        val originalElement = element
        val doc = block(element, originalElement)

        if (expected == null) {
            check(doc == null) { "Expected no documentation, but got: $doc" }
        } else {
            check(doc != null) { "No documentation generated" }
            check(normalizeHtml(doc).contains(normalizeHtml(expected))) {
                "Documentation doesn't contain expected text.\nExpected: $expected\nActual: $doc"
            }
        }
    }

    protected fun doTestNoDoc(@Language("Move") code: String) {
        InlineFile(myFixture, code)

        val element = getElementAtCaret()
        val doc = generateDocumentation(element)

        check(doc == null) { "Expected no documentation, but got: $doc" }
    }

    private fun getElementAtCaret(): PsiElement {
        return myFixture.file.findElementAt(myFixture.caretOffset)
            ?: error("No element at caret")
    }

    private fun generateDocumentation(element: PsiElement): String? {
        val provider = DocumentationManager.getProviderFromElement(element)
        return provider?.generateDoc(element, element)
    }

    private fun normalizeHtml(html: String): String {
        return html.trimIndent().replace("\\s+".toRegex(), " ").trim()
    }
}

/**
 * Base class for project-level documentation provider tests.
 */
abstract class MvDocumentationProviderProjectTestCase : MvProjectTestBase() {

    protected fun doTestByFileTree(
        builder: FileTreeBuilder.() -> Unit,
        expectedDoc: String
    ) {
        testProject(builder)

        val element = myFixture.file.findElementAt(myFixture.caretOffset)
            ?: error("No element at caret")

        val provider = DocumentationManager.getProviderFromElement(element)
        val doc = provider?.generateDoc(element, element)

        check(doc != null) { "No documentation generated" }
        check(normalizeHtml(doc).contains(normalizeHtml(expectedDoc))) {
            "Documentation doesn't contain expected text.\nExpected: $expectedDoc\nActual: $doc"
        }
    }

    protected fun doTestByFileTree(
        builder: FileTreeBuilder.() -> Unit,
        expectedDoc: String,
        block: (PsiElement?, PsiElement?) -> String?
    ) {
        testProject(builder)

        val element = myFixture.file.findElementAt(myFixture.caretOffset)
            ?: error("No element at caret")

        val doc = block(element, element)

        check(doc != null) { "No documentation generated" }
        check(normalizeHtml(doc).contains(normalizeHtml(expectedDoc))) {
            "Documentation doesn't contain expected text.\nExpected: $expectedDoc\nActual: $doc"
        }
    }

    private fun normalizeHtml(html: String): String {
        return html.trimIndent().replace("\\s+".toRegex(), " ").trim()
    }
}
