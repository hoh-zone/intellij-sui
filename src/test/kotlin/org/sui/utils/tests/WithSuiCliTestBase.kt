package org.sui.utils.tests

import com.intellij.openapi.vfs.VirtualFile
import org.sui.cli.settings.moveSettings
import org.sui.cli.settings.sui.SuiExecType
import org.sui.openapiext.pathAsPath
import org.sui.stdext.toPath

/**
 * Base class for tests that require Sui CLI to be available.
 * It sets up the project to use a local Sui CLI path.
 */
abstract class WithSuiCliTestBase : MvProjectTestBase() {

    protected val cargoProjectDirectory: VirtualFile get() = rootDirectory

    override fun setUp() {
        super.setUp()
        
        // Try to find sui CLI in system path
        val suiPath = findSuiCli()
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
}
