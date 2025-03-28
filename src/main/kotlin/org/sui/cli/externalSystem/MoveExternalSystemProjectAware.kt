package org.sui.cli.externalSystem

import com.intellij.openapi.Disposable
import com.intellij.openapi.externalSystem.autoimport.*
import com.intellij.openapi.externalSystem.model.ProjectSystemId
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import org.sui.cli.MoveProjectsService
import org.sui.cli.MoveProjectsService.MoveRefreshStatus
import org.sui.cli.moveProjectsService

class MoveExternalSystemProjectAware(
    private val project: Project
) :
    ExternalSystemProjectAware {

    override val projectId: ExternalSystemProjectId = ExternalSystemProjectId(MOVE_SYSTEM_ID, project.name)

    override val settingsFiles: Set<String>
        get() {
            val settingsFilesService = MoveSettingsFilesService.getInstance(project)
            // Always collect fresh settings files
            val settingsFiles = settingsFilesService.collectSettingsFiles()
            return settingsFiles
        }

    override fun reloadProject(context: ExternalSystemProjectReloadContext) {
        FileDocumentManager.getInstance().saveAllDocuments()
        project.moveProjectsService.scheduleProjectsRefresh(
            "from project aware, explicit=${context.isExplicitReload}"
        )
    }

    override fun subscribe(listener: ExternalSystemProjectListener, parentDisposable: Disposable) {
        project.messageBus.connect(parentDisposable).subscribe(
            MoveProjectsService.MOVE_PROJECTS_REFRESH_TOPIC,
            object : MoveProjectsService.MoveProjectsRefreshListener {
                override fun onRefreshStarted() {
                    listener.onProjectReloadStart()
                }

                override fun onRefreshFinished(status: MoveRefreshStatus) {
                    val externalStatus = when (status) {
                        MoveRefreshStatus.SUCCESS -> ExternalSystemRefreshStatus.SUCCESS
                        MoveRefreshStatus.FAILURE -> ExternalSystemRefreshStatus.FAILURE
                        MoveRefreshStatus.CANCEL -> ExternalSystemRefreshStatus.CANCEL
                    }
                    listener.onProjectReloadFinish(externalStatus)
                }
            }
        )
    }

    companion object {
        val MOVE_SYSTEM_ID: ProjectSystemId = ProjectSystemId("Move")
    }
}
