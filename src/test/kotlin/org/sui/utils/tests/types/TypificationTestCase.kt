/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.utils.tests.types

import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import org.intellij.lang.annotations.Language
import org.sui.lang.core.psi.MvExpr
import org.sui.lang.core.psi.MvPatBinding
import org.sui.lang.core.psi.MvTypeArgument
import org.sui.lang.core.types.infer.MvInferenceContextOwner
import org.sui.lang.core.types.infer.inferExpectedTy
import org.sui.lang.core.types.infer.inference
import org.sui.utils.tests.InlineFile
import org.sui.utils.tests.MvTestBase

/**
 * Base class for type inference tests.
 * Provides utility methods for testing expression types in Move code.
 */
abstract class TypificationTestCase : MvTestBase() {

    // Back-compat helpers used by existing tests in this repo
    protected fun testExpectedTyExpr(@Language("Move") code: String) = testExpectedTyAtMarker(code)

    protected inline fun <reified T : PsiElement> testExpectedType(@Language("Move") code: String) =
        testExpectedTyAtMarker(code, T::class.java)

    protected fun testExpectedTyType(@Language("Move") code: String) = testExpectedTyAtMarker(code)

    /**
     * Test the type of an expression marked with //^ marker.
     * The expected type should be specified after the ^ marker.
     *
     * Example:
     * ```
     * let a = 1u8;
     *       //^ u8
     * ```
     */
    protected fun testExpr(@Language("Move") code: String, allowErrors: Boolean = true) {
        InlineFile(myFixture, code)
        val (expr, expectedType) = findElementAndExpectedType<MvExpr>()
        val actualType = expr.inference(false)?.getExprType(expr)
        val actualTypeText = actualType?.toString() ?: "<unknown>"

        check(expectedType == actualTypeText) {
            "Type mismatch. Expected: $expectedType, Actual: $actualTypeText"
        }
    }

    /**
     * Test types of multiple expressions marked with //^ markers.
     * Each marker should have its expected type.
     */
    protected fun testExprsTypified(@Language("Move") code: String) {
        InlineFile(myFixture, code)
        val markers = findAllElementsAndExpectedTypes<MvExpr>()

        for ((expr, expectedType) in markers) {
            val actualType = expr.inference(false)?.getExprType(expr)
            val actualTypeText = actualType?.toString() ?: "<unknown>"

            check(expectedType == actualTypeText) {
                "Type mismatch for '${expr.text}'. Expected: $expectedType, Actual: $actualTypeText"
            }
        }
    }

    /**
     * Test the type of a binding pattern marked with //^ marker.
     * The expected type should be specified after the ^ marker.
     *
     * Example:
     * ```
     * let a: u64 = 1;
     *     //^ u64
     * ```
     */
    protected fun testBinding(@Language("Move") code: String) {
        InlineFile(myFixture, code)
        val (binding, expectedType) = findElementAndExpectedType<MvPatBinding>()
        val actualType = binding.inference(false)?.getBindingType(binding)
        val actualTypeText = actualType?.toString() ?: "<unknown>"

        check(expectedType == actualTypeText) {
            "Type mismatch. Expected: $expectedType, Actual: $actualTypeText"
        }
    }

    @PublishedApi
    internal fun testExpectedTyAtMarker(
        @Language("Move") code: String,
        expectedPsiClass: Class<out PsiElement>? = null,
    ) {
        InlineFile(myFixture, code)
        val (elementAtMarker, expectedTypeText) = findElementAndExpectedType<PsiElement>()

        val element =
            when {
                expectedPsiClass != null ->
                    PsiTreeUtil.getParentOfType(elementAtMarker, expectedPsiClass, false)
                        ?: error("No ${expectedPsiClass.simpleName} found at marker position")
                else -> elementAtMarker
            }

        val inferenceOwner =
            PsiTreeUtil.getParentOfType(element, MvInferenceContextOwner::class.java, false)
                ?: error("No inference context owner for `${element.text}`")
        val inference = inferenceOwner.inference(false)
        val expectedTy = inferExpectedTy(element, inference)
        val actual = expectedTy?.toString() ?: "<unknown>"

        check(expectedTypeText == actual) {
            "Expected type mismatch. Expected: $expectedTypeText, Actual: $actual"
        }
    }

    private inline fun <reified T : com.intellij.psi.PsiElement> findElementAndExpectedType(): Pair<T, String> {
        val text = myFixture.file.text
        val markerIndex = text.indexOf("//^")
        check(markerIndex != -1) { "No //^ marker found in test code" }

        val expectedType = text.substring(markerIndex + 3).takeWhile { it != '\n' }.trim()
        check(expectedType.isNotEmpty()) { "Expected type not specified after //^ marker" }

        val elementOffset = findElementOffset(text, markerIndex)
        val element = myFixture.file.findElementAt(elementOffset)
        val typedElement = PsiTreeUtil.getParentOfType(element, T::class.java, false)
            ?: error("No ${T::class.simpleName} found at marker position")

        return typedElement to expectedType
    }

    private inline fun <reified T : com.intellij.psi.PsiElement> findAllElementsAndExpectedTypes(): List<Pair<T, String>> {
        val text = myFixture.file.text
        val result = mutableListOf<Pair<T, String>>()

        var searchFrom = 0
        while (true) {
            val markerIndex = text.indexOf("//^", searchFrom)
            if (markerIndex == -1) break

            val expectedType = text.substring(markerIndex + 3).takeWhile { it != '\n' }.trim()
            if (expectedType.isNotEmpty()) {
                val elementOffset = findElementOffset(text, markerIndex)
                val element = myFixture.file.findElementAt(elementOffset)
                val typedElement = PsiTreeUtil.getParentOfType(element, T::class.java, false)
                if (typedElement != null) {
                    result.add(typedElement to expectedType)
                }
            }

            searchFrom = markerIndex + 3
        }

        return result
    }

    private fun findElementOffset(text: String, markerIndex: Int): Int {
        // Find the column of the ^ marker (position within the line)
        val lineStart = text.lastIndexOf('\n', markerIndex) + 1
        val markerColumn = markerIndex - lineStart + 3 // +3 to account for "//^" -> point at ^

        // Find the previous line
        val prevLineEnd = text.lastIndexOf('\n', lineStart - 1)
        val prevLineStart = if (prevLineEnd == -1) 0 else text.lastIndexOf('\n', prevLineEnd - 1) + 1

        // Return offset at the same column in the previous line
        return prevLineStart + markerColumn - 3 // -3 because we don't have "//^" in the previous line
    }
}
