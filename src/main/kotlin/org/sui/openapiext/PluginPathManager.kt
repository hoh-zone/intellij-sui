package org.sui.openapiext

import com.intellij.openapi.util.SystemInfo

object PluginPathManager {
    fun getCurrentOS(): String {
        return when {
            SystemInfo.isMac -> "macos"
            SystemInfo.isWindows -> "windows"
            SystemInfo.isLinux -> "ubuntu"
            else -> "ubuntu"
        }
    }
}
