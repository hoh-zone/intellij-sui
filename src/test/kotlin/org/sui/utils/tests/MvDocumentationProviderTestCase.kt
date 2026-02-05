/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.utils.tests

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiWhiteSpace
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.codeInsight.documentation.DocumentationManager
import org.intellij.lang.annotations.Language

/**
 * Base class for documentation provider tests.
 */
abstract class MvDocumentationProviderTestCase : MvTestBase() {

    protected fun doTest(@Language("Move") code: String, expected: String) {
        InlineFile(myFixture, code)

        val element = getElementAtCaretOrMarker()
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

        val element = getElementAtCaretOrMarker()
        val originalElement = element
        // Some providers expect a higher-level PSI node than a leaf at caret.
        // Walk up the parent chain and return the first non-null documentation.
        var doc: String? = null
        var cur: PsiElement? = element
        while (cur != null && doc == null) {
            doc = block(cur, originalElement)
            cur = cur.parent
        }

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

        val element = getElementAtCaretOrMarker()
        val doc = generateDocumentation(element)

        check(doc == null) { "Expected no documentation, but got: $doc" }
    }

    private fun getElementAtCaretOrMarker(): PsiElement {
        // Back-compat: use `//^` marker (caret points to previous line at same column as `^`)
        val text = myFixture.file.text
        val markerIndex = text.indexOf("//^")
        if (markerIndex != -1) {
            val doc = myFixture.editor.document
            val markerLine = doc.getLineNumber(markerIndex)
            val markerLineStart = doc.getLineStartOffset(markerLine)
            val caretColumn = (markerIndex - markerLineStart) + 2 // column of '^' in "//^"
            val targetLine = (markerLine - 1).coerceAtLeast(0)
            val targetLineStart = doc.getLineStartOffset(targetLine)
            val targetLineEnd = doc.getLineEndOffset(targetLine)
            var offset = (targetLineStart + caretColumn).coerceIn(targetLineStart, targetLineEnd)
            // If we landed on whitespace, walk left to nearest token on the line.
            while (offset > targetLineStart) {
                val e = myFixture.file.findElementAt(offset)
                if (e != null && e !is PsiWhiteSpace) break
                offset--
            }
            myFixture.editor.caretModel.moveToOffset(offset)
            return myFixture.file.findElementAt(offset) ?: error("No element at //^ marker")
        }

        // Fallback: real caret marker in text
        return myFixture.file.findElementAt(myFixture.caretOffset)
            ?: error("No element at caret and no //^ marker")
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

        val leaf = myFixture.file.findElementAt(myFixture.caretOffset)
            ?: error("No element at caret")
        val element = PsiTreeUtil.getParentOfType(leaf, PsiElement::class.java, false) ?: leaf

        var doc: String? = null
        var cur: PsiElement? = element
        while (cur != null && doc == null) {
            doc = block(cur, cur)
            cur = cur.parent
        }

        check(doc != null) { "No documentation generated" }
        check(normalizeHtml(doc).contains(normalizeHtml(expectedDoc))) {
            "Documentation doesn't contain expected text.\nExpected: $expectedDoc\nActual: $doc"
        }
    }

    private fun normalizeHtml(html: String): String {
        return html.trimIndent().replace("\\s+".toRegex(), " ").trim()
    }
}
