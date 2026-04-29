package org.sui.openapiext

import com.intellij.execution.RunManager
import com.intellij.execution.RunnerAndConfigurationSettings
import com.intellij.ide.util.PsiNavigationSupport
import com.intellij.openapi.application.invokeLater
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.options.ShowSettingsUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import org.sui.cli.runConfigurations.sui.cmd.SuiCommandConfiguration
import org.sui.openapiext.common.isHeadlessEnvironment

val Project.runManager: RunManager get() = RunManager.getInstance(this)

fun Project.suiCommandConfigurationsSettings(): List<RunnerAndConfigurationSettings> =
    runManager.allSettings
        .filter { it.configuration is SuiCommandConfiguration }


inline fun <reified T : Configurable> Project.showSettingsDialog() {
    ShowSettingsUtil.getInstance().showSettingsDialog(this, T::class.java)
}

fun Project.addRunConfiguration(
    isSelected: Boolean = false,
    configurationFactory: (RunManager, Project) -> RunnerAndConfigurationSettings,
): RunnerAndConfigurationSettings {
    val runManager = RunManager.getInstance(this)
    val runnerAndConfigurationSettings = configurationFactory(runManager, this)
    runManager.addConfiguration(runnerAndConfigurationSettings)
    if (isSelected) {
        runManager.selectedConfiguration = runnerAndConfigurationSettings
    }
    return runnerAndConfigurationSettings
}

fun Project.openFile(file: VirtualFile) = invokeLater {
    if (!isHeadlessEnvironment) {
        PsiNavigationSupport.getInstance()
            .createNavigatable(this, file, -1)
            .navigate(false)
    }
}

