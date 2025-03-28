package org.sui.openapiext

import com.intellij.openapi.project.Project
import org.sui.cli.fsDepth
import org.sui.cli.moveProjectsService
import org.sui.lang.MoveFile

fun Project.allMoveFiles(): List<MoveFile> {
    val files = mutableListOf<MoveFile>()
    val visited = mutableSetOf<String>()
    // find Move.toml files in all project roots, remembering depth of those
    // then search all .move children of those files, with biggest depth first
    this.moveProjectsService.allProjects
        .sortedByDescending { it.contentRoot.fsDepth }
        .map { moveProject ->
            moveProject.processMoveFiles {
                // virtualFile can be null
                val filePath = it.virtualFile?.path ?: return@processMoveFiles true

                if (filePath in visited) return@processMoveFiles true
                visited.add(filePath)

                files.add(it)
                true
            }
        }
    return files
}
