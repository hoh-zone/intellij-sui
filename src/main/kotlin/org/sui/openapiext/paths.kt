package org.sui.openapiext

import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.VirtualFileManager
import org.sui.stdext.exists
import java.nio.file.Path
import java.nio.file.Paths

fun Path.resolveExisting(other: String): Path? {
    val path = resolve(other)
    return if (path.exists()) path else null
}

fun Path.resolveAbsPath(other: String): Path? {
    val rawPath = Paths.get(other)
    if (rawPath.isAbsolute && rawPath.exists()) return rawPath

    val joined = this.resolve(other)
    if (!joined.exists()) {
        return null
    } else {
        return joined.toRealPath()
    }
}

fun Path.toVirtualFile(): VirtualFile? =
    VirtualFileManager.getInstance().findFileByNioPath(this)
