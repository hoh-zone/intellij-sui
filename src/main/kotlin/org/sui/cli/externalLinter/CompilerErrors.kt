package org.sui.cli.externalLinter

import org.sui.ide.annotator.CompilerMessage
import org.sui.ide.annotator.CompilerSpan

fun parseCompilerErrors(outputLines: List<String>): List<CompilerMessage> {
    val errorLists = splitErrors(outputLines)
    val messages = errorLists.map(::errorLinesToCompilerMessage)
    return messages
}

private val ERROR_START_RE = Regex("^error(\\[E\\d+\\])?:\\s+(.+)")

private fun splitErrors(outputLines: List<String>): List<List<String>> {
    val errorLists = mutableListOf<List<String>>()
    var thisErrorLines: MutableList<String>? = null
    for (line in outputLines) {
        if (line.startsWith("{")) {
            break
        }
        if (ERROR_START_RE.find(line) != null) {
//        if (line.startsWith("error:")) {
            // flush
            thisErrorLines?.let { errorLists.add(it) }
            thisErrorLines = mutableListOf()
        }
        thisErrorLines?.add(line)
    }
    // flush
    thisErrorLines?.let { errorLists.add(it) }
    return errorLists
}

private fun errorLinesToCompilerMessage(errorLines: List<String>): CompilerMessage {
    val messageLine = errorLines.first()
    val matchResult = ERROR_START_RE.find(messageLine)
        ?: error("Expected error line to match ERROR_START_RE: $messageLine")
    val (_, message) = matchResult.destructured
    val spans = splitSpans(errorLines)
    return CompilerMessage(message, "error", spans)
}

private val FILE_POSITION_RE =
    Regex("""┌─ (?<file>(?:\p{Alpha}:)?[0-9a-z_A-Z\-\\./]+):(?<line>[0-9]+):(?<column>[0-9]+)""")
private val ERROR_UNDERLINE_RE =
    Regex("""^\s*│[^\^]*(\^{2,})""")

private fun splitSpans(errorLines: List<String>): List<CompilerSpan> {
    val filePositionMatch =
        errorLines.firstNotNullOfOrNull { FILE_POSITION_RE.find(it) } ?: return emptyList()
    val (fileName, lineStart, columnStart) = filePositionMatch.destructured
    val columnSpan = errorLines
        .firstNotNullOfOrNull { ERROR_UNDERLINE_RE.find(it) }
        ?.groupValues?.get(1)
        ?.length ?: 1
    return listOf(
        CompilerSpan(
            fileName,
            lineStart = lineStart.toInt(),
            lineEnd = lineStart.toInt(),
            columnStart = columnStart.toInt(),
            columnEnd = columnStart.toInt() + columnSpan,
            isPrimary = true,
            label = null
        )
    )
}