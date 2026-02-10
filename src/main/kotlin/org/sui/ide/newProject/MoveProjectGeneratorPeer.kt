/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.ide.newProject

import com.intellij.openapi.Disposable
import com.intellij.openapi.components.service
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.openapi.util.Disposer
import com.intellij.ide.util.projectWizard.SettingsStep
import com.intellij.platform.ProjectGeneratorPeer
import com.intellij.ui.dsl.builder.panel
import org.sui.cli.settings.MvProjectSettingsService
import org.sui.cli.settings.sui.ChooseSuiCliPanel
import org.sui.cli.settings.sui.SuiExecType
import org.sui.stdext.getCliFromPATH

class MoveProjectGeneratorPeer(
    val parentDisposable: Disposable
) : ProjectGeneratorPeer<MoveProjectConfig> {
    private val chooseSuiCliPanel = ChooseSuiCliPanel {}

    init {
        Disposer.register(parentDisposable, chooseSuiCliPanel)

        // set values from the default project settings
        val defaultProjectSettings =
            ProjectManager.getInstance().defaultProject.service<MvProjectSettingsService>()

        val localSuiPath =
            defaultProjectSettings.localSuiPath ?: getCliFromPATH("sui")?.toString()
        chooseSuiCliPanel.data =
            ChooseSuiCliPanel.Data(defaultProjectSettings.suiExecType, localSuiPath)
    }

    override fun buildUI(settingsStep: SettingsStep) {
        settingsStep.addSettingsComponent(
            panel {
                chooseSuiCliPanel.attachToLayout(this)
            }
        )
    }

    override fun getSettings(): MoveProjectConfig {
        val localSuiPath = this.chooseSuiCliPanel.data.localSuiPath
        if (localSuiPath != null) {
            this.chooseSuiCliPanel.updateSuiSdks(localSuiPath)
        }
        return MoveProjectConfig(
            suiExecType = this.chooseSuiCliPanel.data.suiExecType,
            localSuiPath = localSuiPath,
        )
    }

    override fun validate(): ValidationInfo? {
        val panelData = this.chooseSuiCliPanel.data
        val suiExecPath =
            SuiExecType.suiExecPath(panelData.suiExecType, panelData.localSuiPath)
        if (suiExecPath == null) {
            return ValidationInfo("Invalid path to Sui executable")
        }
        return null
    }

    override fun isBackgroundJobRunning(): Boolean = false
}
