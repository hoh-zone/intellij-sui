package org.sui.lang.core.psi.ext

import com.intellij.psi.PsiFileSystemItem
import org.sui.cli.MoveProject
import org.sui.cli.moveProjectsService
import org.sui.lang.MoveFile
import org.sui.lang.moveProject
import org.sui.lang.toNioPathOrNull

fun PsiFileSystemItem.findMoveProject(): MoveProject? {
    if (this is MoveFile) return this.moveProject
    val path = virtualFile.toNioPathOrNull() ?: return null
    return project.moveProjectsService.findMoveProjectForPath(path)
}
