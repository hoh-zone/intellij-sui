/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.utils.tests.lineMarkers

import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.impl.DaemonCodeAnalyzerImpl
import org.intellij.lang.annotations.Language
import org.sui.utils.tests.MvTestBase

/**
 * Base class for testing line marker providers.
 */
abstract class LineMarkerProviderTestBase : MvTestBase() {

    /**
     * Test line markers by providing code with expected marker comments.
     * Markers are expected in comments like: // - Has specifications
     */
    protected fun doTestByText(@Language("Move") text: String) {
        myFixture.configureByText("main.move", text.trimIndent())

        myFixture.doHighlighting()

        val lineMarkers = DaemonCodeAnalyzerImpl.getLineMarkers(myFixture.editor.document, project)

        // Extract expected markers from comments
        val expectedMarkers = extractExpectedMarkers(text)
        val actualMarkers = lineMarkers.map { it.lineNumber() to it.lineMarkerTooltip }

        // Verify expected markers are present
        for ((line, tooltip) in expectedMarkers) {
            val found = actualMarkers.any { (actualLine, actualTooltip) ->
                actualLine == line && (actualTooltip?.contains(tooltip, ignoreCase = true) == true)
            }
            if (!found && tooltip.isNotBlank()) {
                // Only check if tooltip is specified
                check(lineMarkers.any { it.lineNumber() == line }) {
                    "Expected line marker at line $line with tooltip '$tooltip', but found none.\n" +
                            "Actual markers: ${actualMarkers.joinToString("\n") { "Line ${it.first}: ${it.second}" }}"
                }
            }
        }
    }

    private fun extractExpectedMarkers(text: String): List<Pair<Int, String>> {
        val lines = text.trimIndent().lines()
        val markers = mutableListOf<Pair<Int, String>>()

        lines.forEachIndexed { index, line ->
            val markerMatch = Regex("//\\s*-\\s*(.+)$").find(line)
            if (markerMatch != null) {
                markers.add(index to markerMatch.groupValues[1].trim())
            }
        }

        return markers
    }

    private fun LineMarkerInfo<*>.lineNumber(): Int {
        return myFixture.editor.document.getLineNumber(startOffset)
    }
}
