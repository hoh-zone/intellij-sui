package org.sui.ide.newProject

import com.intellij.openapi.application.invokeLater
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.io.FileUtil
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.PlatformProjectOpenProcessor
import com.intellij.projectImport.ProjectOpenProcessor
import org.sui.cli.Consts
import org.sui.ide.MoveIcons
import javax.swing.Icon

/** Called only when IDE opens a project from existing sources */
class MoveLangProjectOpenProcessor : ProjectOpenProcessor() {
    override val name: String get() = "Sui Move"
    override val icon: Icon get() = MoveIcons.SUI_LOGO

    override fun canOpenProject(file: VirtualFile): Boolean {
        return FileUtil.namesEqual(file.name, Consts.MANIFEST_FILE)
                || (file.isDirectory && file.findChild(Consts.MANIFEST_FILE) != null)
    }

    override suspend fun openProjectAsync(
        virtualFile: VirtualFile,
        projectToClose: Project?,
        forceOpenInNewFrame: Boolean,
    ): Project? {
        val platformOpenProcessor = PlatformProjectOpenProcessor.getInstance()
        val basedir = if (virtualFile.isDirectory) virtualFile else virtualFile.parent
        return platformOpenProcessor.openProjectAsync(basedir, projectToClose, forceOpenInNewFrame)
            ?.also { project ->
                invokeLater {
                    if (project.isDisposed) return@invokeLater
                    ProjectInitializationSteps.createDefaultCompileConfigurationIfNotExists(project)
                    // NOTE:
                    // this cannot be moved to a ProjectActivity, as Move.toml files
                    // are not created by the time those activities are executed
                    ProjectInitializationSteps.openMoveTomlInEditor(project)
                }
            }
    }
}
