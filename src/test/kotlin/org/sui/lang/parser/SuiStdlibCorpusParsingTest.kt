package org.sui.lang.parser

import com.intellij.psi.PsiErrorElement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.PsiErrorElementUtil
import org.junit.Assume.assumeTrue
import org.sui.utils.tests.MvTestBase
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.isDirectory
import kotlin.io.path.name

/**
 * Parses the full Sui framework corpus to ensure the plugin supports the syntax used by:
 * - move-stdlib
 * - sui-framework
 * - sui-system
 *
 * The corpus is expected to be checked out locally at:
 * `/Users/mac/work/sui/sui/crates/sui-framework/packages`
 *
 * In CI (or on developer machines without the repo), the test is skipped.
 */
class SuiStdlibCorpusParsingTest : MvTestBase() {

    fun `test parse all files from move-stdlib sui-framework sui-system`() {
        val root = Paths.get("/Users/mac/work/sui/sui/crates/sui-framework/packages")
        assumeTrue("Sui packages directory not found: $root", Files.isDirectory(root))

        val packages = listOf("move-stdlib", "sui-framework", "sui-system")
        val moveFiles = packages
            .map { root.resolve(it) }
            .filter { it.isDirectory() }
            .flatMap { pkgRoot -> collectMoveFiles(pkgRoot) }
            .sortedBy { it.toString() }

        assumeTrue("No .move files found under $root", moveFiles.isNotEmpty())

        val failures = mutableListOf<String>()
        for (path in moveFiles) {
            val text = runCatching { Files.readString(path, StandardCharsets.UTF_8) }
                .getOrElse { e ->
                    failures += "${path}: failed to read (${e.javaClass.simpleName}: ${e.message})"
                    continue
                }

            val psiFile = myFixture.configureByText("${path.name}.move", text)
            val hasErrors = PsiErrorElementUtil.hasErrors(project, psiFile.virtualFile)
            val firstError: PsiErrorElement? = PsiTreeUtil.findChildOfType(psiFile, PsiErrorElement::class.java)
            if (hasErrors || firstError != null) {
                val err = firstError
                val details =
                    if (err != null) {
                        val start = err.textRange.startOffset
                        val (line, col, lineText) = lineAndColumn(text, start)
                        " | firstError=${err.errorDescription} @ ${line}:${col}\n    $lineText"
                    } else ""
                failures += "${path}: parse errors detected$details"
            }
        }

        check(failures.isEmpty()) {
            "Parser errors in Sui corpus (${failures.size}/${moveFiles.size} files):\n" +
                failures.joinToString(separator = "\n")
        }
    }

    private fun collectMoveFiles(pkgRoot: Path): List<Path> {
        if (!Files.isDirectory(pkgRoot)) return emptyList()
        Files.newDirectoryStream(pkgRoot).use { /* touch to fail fast on perms */ }
        return Files.walk(pkgRoot).use { stream ->
            stream
                .filter { p -> Files.isRegularFile(p) && p.toString().endsWith(".move") }
                .toList()
        }
    }

    private fun lineAndColumn(text: String, offset: Int): Triple<Int, Int, String> {
        val safeOffset = offset.coerceIn(0, text.length)
        var line = 1
        var col = 1
        var i = 0
        var lineStart = 0
        while (i < safeOffset) {
            val ch = text[i]
            if (ch == '\n') {
                line++
                col = 1
                lineStart = i + 1
            } else {
                col++
            }
            i++
        }
        val lineEnd = text.indexOf('\n', lineStart).let { if (it == -1) text.length else it }
        val lineText = text.substring(lineStart, lineEnd).replace('\t', ' ')
        return Triple(line, col, lineText)
    }
}

