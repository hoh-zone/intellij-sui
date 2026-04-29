/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.openapiext

import com.intellij.openapi.Disposable
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.Service.Level.PROJECT
import com.intellij.openapi.components.service
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.module.ModuleManager
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ModuleRootManager
import com.intellij.openapi.util.NlsContexts
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiManager
import com.intellij.psi.impl.PsiDocumentManagerBase
import org.sui.lang.toNioPathOrNull
import org.sui.openapiext.common.isUnitTestMode
import java.nio.file.Path
import java.nio.file.Paths

fun fullyRefreshDirectory(directory: VirtualFile) {
    VfsUtil.markDirtyAndRefresh(
        false,
        true,
        true,
        directory
    )
}

val VirtualFile.pathAsPath: Path get() = Paths.get(path)

fun VirtualFile.toPsiFile(project: Project): PsiFile? =
    PsiManager.getInstance(project).findFile(this)

fun saveAllDocuments() = FileDocumentManager.getInstance().saveAllDocuments()

val Project.contentRoots: Sequence<VirtualFile>
    get() = ModuleManager.getInstance(this).modules.asSequence()
        .flatMap { ModuleRootManager.getInstance(it).contentRoots.asSequence() }

val Project.rootPath: Path? get() = contentRoots.firstOrNull()?.toNioPathOrNull()

val Project.contentRoot: VirtualFile? get() = contentRoots.firstOrNull()

fun <T> Project.computeWithCancelableProgress(
    @NlsContexts.ProgressTitle title: String,
    supplier: () -> T
): T {
    if (isUnitTestMode) {
        return supplier()
    }
    return ProgressManager.getInstance()
        .runProcessWithProgressSynchronously<T, Exception>(supplier, title, true, this)
}

fun checkIsBackgroundThread() {
    check(!ApplicationManager.getApplication().isDispatchThread) {
        "Long running operation invoked on UI thread"
    }
}

fun checkIsDispatchThread() {
    check(ApplicationManager.getApplication().isDispatchThread) {
        "Should be invoked on the Swing dispatch thread"
    }
}

fun checkReadAccessAllowed() {
    check(ApplicationManager.getApplication().isReadAccessAllowed) {
        "Needs read action"
    }
}

@Service(PROJECT)
class RootPluginDisposable : Disposable {
    override fun dispose() {}
}

val Project.rootPluginDisposable get() = this.service<RootPluginDisposable>()

fun checkCommitIsNotInProgress(project: Project) {
    val app = ApplicationManager.getApplication()
    if ((app.isUnitTestMode || app.isInternal) && app.isDispatchThread) {
        if ((PsiDocumentManager.getInstance(project) as PsiDocumentManagerBase).isCommitInProgress) {
            error("Accessing indices during PSI event processing can lead to typing performance issues")
        }
    }
}
