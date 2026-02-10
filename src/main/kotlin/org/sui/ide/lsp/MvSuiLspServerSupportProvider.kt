package org.sui.ide.lsp

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.ide.BrowserUtil
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.LspServerDescriptor
import com.intellij.platform.lsp.api.LspServerManager
import com.intellij.platform.lsp.api.LspServerSupportProvider
import org.sui.cli.settings.isValidExecutable
import org.sui.cli.settings.moveSettings
import org.sui.lang.MoveFileType
import org.sui.openapiext.common.isUnitTestMode
import org.sui.openapiext.showSettingsDialog
import org.sui.stdext.toPathOrNull
import java.nio.file.Path
import java.util.concurrent.ConcurrentHashMap

class MvSuiLspServerSupportProvider : LspServerSupportProvider {
    override fun fileOpened(
        project: Project,
        file: VirtualFile,
        serverStarter: LspServerSupportProvider.LspServerStarter
    ) {
        if (isUnitTestMode) return
        if (file.extension != MoveFileType.defaultExtension) return

        val moveAnalyzerPath = resolveMoveAnalyzerPath(project)
        if (moveAnalyzerPath == null) {
            remindInstallMoveAnalyzer(project)
            return
        }

        serverStarter.ensureServerStarted(MvSuiLspServerDescriptor(project, moveAnalyzerPath))
    }

    companion object {
        private val promptedProjects = ConcurrentHashMap.newKeySet<String>()

        fun resolveMoveAnalyzerPath(project: Project): Path? {
            val configuredPath = project.moveSettings.moveAnalyzerPath
                ?.toPathOrNull()
            return configuredPath?.takeIf { it.isValidExecutable() }
        }

        private fun remindInstallMoveAnalyzer(project: Project) {
            if (project.isDisposed) return
            if (!promptedProjects.add(project.locationHash)) return

            ApplicationManager.getApplication().invokeLater {
                if (project.isDisposed) return@invokeLater
                val title = "move-analyzer not found"
                val message =
                    "LSP-only mode requires configured `move-analyzer` path.\n\n" +
                            "Set it in plugin settings, then reopen Move files.\n" +
                            "Recommended install command:\n" +
                            "suiup install move-analyzer"
                val choice = Messages.showDialog(
                    project,
                    message,
                    title,
                    arrayOf("Open Install Guide", "Open Settings"),
                    1,
                    Messages.getWarningIcon()
                )
                if (choice == 0) {
                    BrowserUtil.browse("https://docs.sui.io/references/cli/suiup")
                    project.showSettingsDialog<org.sui.cli.settings.PerProjectSuiConfigurable>()
                } else {
                    project.showSettingsDialog<org.sui.cli.settings.PerProjectSuiConfigurable>()
                }
            }
        }
    }
}

class MvSuiLspServerDescriptor(
    project: Project,
    private val moveAnalyzerPath: Path
) : LspServerDescriptor(project, "move") {
    override fun isSupportedFile(file: VirtualFile): Boolean =
        file.extension == MoveFileType.defaultExtension

    override fun createCommandLine(): GeneralCommandLine =
        GeneralCommandLine(moveAnalyzerPath.toString())
}

fun restartMoveAnalyzerServerAsync(project: Project) {
    if (isUnitTestMode) return
    ApplicationManager.getApplication().invokeLater {
        if (project.isDisposed) return@invokeLater
        LspServerManager.getInstance(project).stopAndRestartIfNeeded(MvSuiLspServerSupportProvider::class.java)
    }
}
