/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.utils.tests

import com.intellij.execution.filters.Filter
import com.intellij.openapi.vfs.VirtualFile

/**
 * Base class for testing console highlight filters.
 */
abstract class HighlightFilterTestBase : MvProjectTestBase() {

    protected val projectDir: VirtualFile
        get() = rootDirectory

    protected fun checkHighlights(
        filter: Filter,
        setup: FileTreeBuilder.() -> Unit,
        input: String,
        expectedOutput: String
    ) {
        testProject(setup)

        val result = filter.applyFilter(input, input.length)

        if (result == null) {
            check(input == expectedOutput) {
                "Expected highlights but got none.\nInput: $input\nExpected: $expectedOutput"
            }
            return
        }

        // Build the output with highlight markers
        val items = result.resultItems
        if (items.isEmpty()) {
            check(input == expectedOutput) {
                "Expected highlights but got none.\nInput: $input\nExpected: $expectedOutput"
            }
            return
        }

        val sb = StringBuilder()
        var lastEnd = 0

        for (item in items.sortedBy { it.highlightStartOffset }) {
            sb.append(input.substring(lastEnd, item.highlightStartOffset))
            sb.append("[")
            sb.append(input.substring(item.highlightStartOffset, item.highlightEndOffset))
            val info = item.hyperlinkInfo
            if (info != null) {
                sb.append(" -> ")
                sb.append(info.toString().substringAfterLast("/"))
            }
            sb.append("]")
            lastEnd = item.highlightEndOffset
        }
        sb.append(input.substring(lastEnd))

        val actualOutput = sb.toString()
        check(actualOutput.trim() == expectedOutput.trim()) {
            "Highlight mismatch.\nExpected:\n$expectedOutput\nActual:\n$actualOutput"
        }
    }
}
