package org.sui.lang

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.CachedValuesManager.getProjectPsiDependentCache
import org.sui.cli.MoveProject
import org.sui.cli.moveProjectsService
import org.sui.lang.core.psi.*
import org.sui.lang.core.psi.ext.childrenOfType
import org.sui.lang.core.psi.ext.modules
import org.sui.openapiext.toPsiFile
import org.sui.stdext.chain
import org.toml.lang.psi.TomlFile
import java.nio.file.Path

// requires ReadAccess
val PsiElement.moveProject: MoveProject? get() {
    return project.moveProjectsService.findMoveProjectForPsiElement(this)
}

fun VirtualFile.toNioPathOrNull() = fileSystem.getNioPath(this)

fun PsiFile.toNioPathOrNull(): Path? {
    return this.originalFile.virtualFile?.toNioPathOrNull()
}

class MoveFile(fileViewProvider: FileViewProvider) : PsiFileBase(fileViewProvider, MoveLanguage) {
    override fun getFileType(): FileType = MoveFileType

    fun modules(): Sequence<MvModule> {
        return getProjectPsiDependentCache(this) {
            it.childrenOfType<MvModule>()
                .chain(it.childrenOfType<MvAddressDef>().flatMap { a -> a.modules() })
        }
    }
}

val VirtualFile.isMoveFile: Boolean get() = fileType == MoveFileType

val VirtualFile.isMoveTomlManifestFile: Boolean get() = name == "Move.toml"

fun VirtualFile.toMoveFile(project: Project): MoveFile? = this.toPsiFile(project) as? MoveFile

fun VirtualFile.toTomlFile(project: Project): TomlFile? = this.toPsiFile(project) as? TomlFile
