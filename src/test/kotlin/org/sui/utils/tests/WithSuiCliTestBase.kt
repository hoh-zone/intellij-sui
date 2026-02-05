package org.sui.utils.tests

import com.intellij.openapi.vfs.VirtualFile
import org.sui.cli.settings.moveSettings
import org.sui.cli.settings.sui.SuiExecType
import org.sui.stdext.toPath
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.attribute.PosixFilePermission
import java.nio.file.attribute.PosixFilePermissions

/**
 * Base class for tests that require Sui CLI to be available.
 * It sets up the project to use a local Sui CLI path.
 */
abstract class WithSuiCliTestBase : MvProjectTestBase() {

    protected val cargoProjectDirectory: VirtualFile get() = rootDirectory

    override fun setUp() {
        super.setUp()
        
        // Try to find sui CLI in system path
        val suiPath = findSuiCli() ?: createFakeSuiCli()
        if (suiPath != null) {
            project.moveSettings.modify {
                it.suiExecType = SuiExecType.LOCAL
                it.localSuiPath = suiPath
            }
        }
    }

    private fun findSuiCli(): String? {
        // Check common locations for sui CLI
        val possiblePaths = listOf(
            System.getenv("HOME")?.let { "$it/.cargo/bin/sui" },
            System.getenv("HOME")?.let { "$it/.sui/bin/sui" },
            "/usr/local/bin/sui",
            "/usr/bin/sui"
        ).filterNotNull()

        for (path in possiblePaths) {
            val pathObj = path.toPath()
            if (pathObj.toFile().exists() && pathObj.toFile().canExecute()) {
                return path
            }
        }

        // Try to find in PATH
        val pathEnv = System.getenv("PATH") ?: return null
        for (dir in pathEnv.split(":")) {
            val suiPath = "$dir/sui".toPath()
            if (suiPath.toFile().exists() && suiPath.toFile().canExecute()) {
                return suiPath.toString()
            }
        }

        return null
    }

    /**
     * Creates a minimal fake `sui` executable for unit tests.
     *
     * Many tests only need the CLI to exist (command generation / non-crashing paths).
     * For compilation, we return success by default.
     */
    private fun createFakeSuiCli(): String? {
        return try {
            val binDir: Path = myFixture.tempDirFixture.getFile(".")!!.path.toPath().resolve(".sui_test_bin")
            Files.createDirectories(binDir)
            val suiPath = binDir.resolve("sui")
            val script = """
                |#!/usr/bin/env bash
                |set -euo pipefail
                |
                |# Fake Sui CLI for plugin tests.
                |# Supported commands: `sui move build`, `sui move new`, `sui move test` (no-op).
                |
                |if [[ "${'$'}#" -ge 2 && "${'$'}1 ${'$'}2" == "move new" ]]; then
                |  # Create a minimal Move package structure.
                |  pkg="${'$'}{3:-MyPackage}"
                |  mkdir -p "${'$'}pkg/sources"
                |  cat > "${'$'}pkg/Move.toml" <<'EOF'
                |[package]
                |name = "MyPackage"
                |version = "0.1.0"
                |EOF
                |  exit 0
                |fi
                |
                |# Default: succeed silently.
                |exit 0
            """.trimMargin()
            Files.writeString(suiPath, script)
            try {
                Files.setPosixFilePermissions(
                    suiPath,
                    PosixFilePermissions.fromString("rwxr-xr-x")
                )
            } catch (_: Throwable) {
                // Non-POSIX FS; ignore
                suiPath.toFile().setExecutable(true)
            }
            suiPath.toString()
        } catch (_: Throwable) {
            null
        }
    }
}
