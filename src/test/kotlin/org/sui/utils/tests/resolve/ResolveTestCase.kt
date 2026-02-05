/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.utils.tests.resolve

import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import org.intellij.lang.annotations.Language
import org.sui.lang.core.psi.MvNamedElement
import org.sui.lang.core.resolve.ref.MvReferenceElement
import org.sui.utils.tests.InlineFile
import org.sui.utils.tests.MvTestBase

/**
 * Base class for resolve tests.
 * Uses //^ marker for the reference to resolve and //X marker for the expected target.
 */
abstract class ResolveTestCase : MvTestBase() {

    /**
     * Test resolution using code with markers.
     * //^ marks the reference to resolve
     * //X marks the expected resolution target
     */
    protected fun checkByCode(@Language("Move") code: String) {
        InlineFile(myFixture, code)

        val (refElement, expectedTarget) = findReferenceAndTarget()

        val resolved = refElement.reference?.resolve()

        if (expectedTarget == null) {
            check(resolved == null) {
                "Expected no resolution, but resolved to: ${resolved?.text}"
            }
        } else {
            check(resolved != null) {
                "Failed to resolve reference '${refElement.text}'"
            }
            check(resolved == expectedTarget || isEquivalent(resolved, expectedTarget)) {
                "Resolved to wrong element.\nExpected: ${expectedTarget.text}\nActual: ${resolved.text}"
            }
        }
    }

    /**
     * Test that reference does not resolve.
     */
    protected fun checkByCodeNoResolve(@Language("Move") code: String) {
        InlineFile(myFixture, code)

        val text = myFixture.file.text
        val refMarkerIndex = text.indexOf("//^")
        check(refMarkerIndex != -1) { "No //^ marker in test code" }

        val refOffset = findElementOffset(text, refMarkerIndex)
        val element = myFixture.file.findElementAt(refOffset)
        val refElement = PsiTreeUtil.getParentOfType(element, MvReferenceElement::class.java, false)
            ?: error("No reference element at //^ marker")

        val resolved = refElement.reference?.resolve()
        check(resolved == null) {
            "Expected no resolution, but resolved to: ${resolved?.text}"
        }
    }

    private fun findReferenceAndTarget(): Pair<MvReferenceElement, MvNamedElement?> {
        val text = myFixture.file.text

        // Find reference marker //^
        val refMarkerIndex = text.indexOf("//^")
        check(refMarkerIndex != -1) { "No //^ marker in test code" }

        val refOffset = findElementOffset(text, refMarkerIndex)
        val refElement = myFixture.file.findElementAt(refOffset)
        val reference = PsiTreeUtil.getParentOfType(refElement, MvReferenceElement::class.java, false)
            ?: error("No reference element at //^ marker")

        // Find target marker //X
        val targetMarkerIndex = text.indexOf("//X")
        if (targetMarkerIndex == -1) {
            return reference to null
        }

        val targetOffset = findElementOffset(text, targetMarkerIndex)
        val targetElement = myFixture.file.findElementAt(targetOffset)
        val target = PsiTreeUtil.getParentOfType(targetElement, MvNamedElement::class.java, false)
            ?: error("No named element at //X marker")

        return reference to target
    }

    private fun findElementOffset(text: String, markerIndex: Int): Int {
        val lineStart = text.lastIndexOf('\n', markerIndex) + 1
        val markerColumn = markerIndex - lineStart + 2 // Position of ^

        val prevLineEnd = text.lastIndexOf('\n', lineStart - 1)
        val prevLineStart = if (prevLineEnd == -1) 0 else text.lastIndexOf('\n', prevLineEnd - 1) + 1

        return prevLineStart + markerColumn
    }

    private fun isEquivalent(a: PsiElement, b: PsiElement): Boolean {
        return a.textRange == b.textRange && a.containingFile == b.containingFile
    }
}

/**
 * Base class for project-level resolve tests.
 */
abstract class ResolveProjectTestCase : org.sui.utils.tests.MvProjectTestBase() {

    protected open fun checkByFileTree(fileTree: org.sui.utils.tests.FileTreeBuilder.() -> Unit) {
        val testProject = testProject(fileTree)
        // Resolve testing logic would go here
    }
}
