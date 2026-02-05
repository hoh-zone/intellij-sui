package org.sui.ide.annotator

import com.intellij.openapi.editor.Document

data class CompilerMessage(
    val message: String,
    val severityLevel: String,
    val spans: List<CompilerSpan>
) {
    val mainSpan: CompilerSpan? get() = spans.firstOrNull { it.isPrimary }

    fun toTestString(): String {
        val mainSpan = this.mainSpan
        return if (mainSpan != null) {
            val loc = "[(${mainSpan.lineStart}, ${mainSpan.columnStart}), (${mainSpan.lineEnd}, ${mainSpan.columnEnd})]"
            "$severityLevel: $message\n    at ${mainSpan.filename} $loc"
        } else {
            "$severityLevel: $message"
        }
    }

    companion object {
        fun forTest(
            message: String,
            severityLevel: String,
            filename: String = "",
            location: String = ""
        ): CompilerMessage {
            val spans = if (filename.isNotEmpty() && location.isNotEmpty()) {
                // Parse location like "[(8, 9), (8, 13)]"
                val regex = """\[\((\d+), (\d+)\), \((\d+), (\d+)\)\]""".toRegex()
                val match = regex.find(location)
                if (match != null) {
                    val (lineStart, columnStart, lineEnd, columnEnd) = match.destructured
                    listOf(
                        CompilerSpan(
                            filename = filename,
                            lineStart = lineStart.toInt(),
                            columnStart = columnStart.toInt(),
                            lineEnd = lineEnd.toInt(),
                            columnEnd = columnEnd.toInt(),
                            isPrimary = true,
                            label = null
                        )
                    )
                } else {
                    emptyList()
                }
            } else {
                emptyList()
            }
            return CompilerMessage(message, severityLevel, spans)
        }
    }
}

data class CompilerSpan(
    val filename: String,
    val lineStart: Int,
    val lineEnd: Int,
    val columnStart: Int,
    val columnEnd: Int,
    val isPrimary: Boolean,
    val label: String?
) {
    fun toTextRange(document: Document): com.intellij.openapi.util.TextRange? {
        val startLine = lineStart - 1
        val endLine = lineEnd - 1
        if (startLine < 0 || startLine >= document.lineCount) return null
        if (endLine < 0 || endLine >= document.lineCount) return null

        val lineStartOffset = document.getLineStartOffset(startLine)
        val lineEndOffset = document.getLineEndOffset(endLine)
        
        val startOffset = (lineStartOffset + columnStart - 1).coerceIn(lineStartOffset, lineEndOffset)
        val endOffset = (lineStartOffset + columnEnd - 1).coerceIn(startOffset, lineEndOffset)
        
        return com.intellij.openapi.util.TextRange(startOffset, endOffset)
    }
}
