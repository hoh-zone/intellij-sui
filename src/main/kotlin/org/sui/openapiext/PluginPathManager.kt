package org.sui.openapiext

import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.openapi.extensions.PluginId
import com.intellij.openapi.util.SystemInfo
import java.nio.file.Path

object PluginPathManager {
    private const val PLUGIN_ID = "org.sui.lang"

    private fun pluginDir(): Path? {
        val pluginId = PluginId.getId(PLUGIN_ID)
        val plugin = PluginManagerCore.getPlugin(pluginId) ?: return null
        return plugin.pluginPath
    }

    fun getCurrentOS(): String {
        return when {
            SystemInfo.isMac -> "macos"
            SystemInfo.isWindows -> "windows"
            SystemInfo.isLinux -> "ubuntu"
            else -> "ubuntu"
        }
    }

    val bundledSuiCli: String?
        get() {
            return null
        }
}
