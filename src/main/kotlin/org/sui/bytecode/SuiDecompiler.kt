package org.sui.bytecode

import com.intellij.openapi.Disposable
import com.intellij.openapi.fileEditor.impl.LoadTextUtil
import com.intellij.openapi.fileTypes.BinaryFileDecompiler
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.util.io.FileUtil
import com.intellij.openapi.util.text.StringUtil
import com.intellij.openapi.vfs.*
import com.intellij.openapi.vfs.newvfs.BulkFileListener
import com.intellij.openapi.vfs.newvfs.events.VFileEvent
import org.sui.cli.runConfigurations.sui.Sui
import org.sui.openapiext.pathAsPath
import org.sui.openapiext.rootPluginDisposable
import org.sui.stdext.MvResult
import org.sui.stdext.unwrapOrElse
import java.io.File

class SuiBytecodeDecompiler : BinaryFileDecompiler {
    override fun decompile(file: VirtualFile): CharSequence {
        val fileText = file.readText()
        try {
            StringUtil.assertValidSeparators(fileText)
            return fileText
        } catch (e: AssertionError) {
            val bytes = file.readBytes()
            return LoadTextUtil.getTextByBinaryPresentation(bytes, file)
        }
    }

    fun decompileFileToTheSameDir(sui: Sui, file: VirtualFile): MvResult<VirtualFile, String> {
        sui.decompileFile(file.path, outputDir = null)
            .unwrapOrElse {
                return MvResult.Err("`sui move decompile` failed")
            }
        val decompiledFilePath = file.parent.pathAsPath.resolve(sourceFileName(file))
        val decompiledFile = VirtualFileManager.getInstance().refreshAndFindFileByNioPath(decompiledFilePath)
            ?: run {
                return MvResult.Err("Expected decompiled file $decompiledFilePath does not exist")
            }
        return MvResult.Ok(decompiledFile)
    }

    fun getArtifactsDir(): File {
        return File(FileUtil.getTempDirectory(), "intellij-move-decompiled-artifacts")
    }

    fun sourceFileName(file: VirtualFile): String {
        val fileName = file.name
        return "$fileName.move"
    }
}

fun Project.createDisposableOnFileChange(file: VirtualFile): Disposable {
    val filePath = file.path
    val disposable = Disposer.newDisposable("Dispose on any change to the ${file.name} file")
    messageBus.connect(disposable).subscribe(
        VirtualFileManager.VFS_CHANGES,
        object : BulkFileListener {
            override fun after(events: MutableList<out VFileEvent>) {
                if (events.any { it.path == filePath }) {
                    Disposer.dispose(disposable)
                }
            }
        }
    )
    Disposer.register(this.rootPluginDisposable, disposable)
    return disposable
}
