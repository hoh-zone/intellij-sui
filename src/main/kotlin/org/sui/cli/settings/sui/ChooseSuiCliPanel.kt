package org.sui.cli.settings.sui

import com.intellij.ide.DataManager
import com.intellij.openapi.Disposable
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.components.service
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.openapi.ui.popup.JBPopupFactory.ActionSelectionAid.SPEEDSEARCH
import com.intellij.openapi.util.Disposer
import com.intellij.ui.components.DropDownLink
import com.intellij.ui.dsl.builder.AlignX
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.Row
import org.sui.cli.sdks.sdksService
import org.sui.cli.settings.MvProjectSettingsService
import org.sui.cli.settings.VersionLabel
import org.sui.cli.settings.isValidExecutable
import org.sui.ide.actions.DownloadSuiSDKAction
import org.sui.ide.notifications.logOrShowBalloon
import org.sui.openapiext.pathField
import org.sui.stdext.blankToNull
import org.sui.stdext.toPathOrNull
import java.nio.file.Path

object SuiExecType {
    fun suiExecPath(localSuiPath: String?): Path? {
        val pathCandidate = localSuiPath?.blankToNull()?.toPathOrNull()
        return pathCandidate?.takeIf { it.isValidExecutable() }
    }
}

class ChooseSuiCliPanel(versionUpdateListener: (() -> Unit)?) : Disposable {

    data class Data(
        val localSuiPath: String?
    )

    var data: Data
        get() = Data(localSuiPath = localPathField.text.blankToNull())
        set(value) {
            localPathField.text = value.localSuiPath ?: ""
            updateVersion()
        }

    private val localPathField =
        pathField(
            FileChooserDescriptorFactory.createSingleFileOrExecutableAppDescriptor(),
            this,
            "Choose Sui CLI",
            onTextChanged = { _ ->
                updateVersion()
            })
    private val versionLabel = VersionLabel(this, versionUpdateListener)

    private val downloadPrecompiledBinaryAction = DownloadSuiSDKAction().also {
        it.onFinish = { sdk ->
            localPathField.text = sdk.targetFile.toString()
            updateVersion()
        }
    }
    private val popupActionGroup = DefaultActionGroup(downloadPrecompiledBinaryAction)
    private val getSuiActionLink =
        DropDownLink("Get Sui") { dropDownLink ->
            val dataContext = DataManager.getInstance().getDataContext(dropDownLink)
            JBPopupFactory.getInstance().createActionGroupPopup(
                null,
                popupActionGroup,
                dataContext,
                SPEEDSEARCH,
                false,
                null,
                -1,
                { _ -> false },
                null
            )
        }


    fun attachToLayout(layout: Panel): Row {
        val resultRow = with(layout) {
            group("Sui CLI") {
                buttonsGroup {
                    row {
                        cell(localPathField)
                            .align(AlignX.FILL)
                            .resizableColumn()
                        if (popupActionGroup.childrenCount != 0) {
                            cell(getSuiActionLink)
                        }
                    }
                    row("--version :") { cell(versionLabel) }
                }
            }
        }
        updateVersion()
        return resultRow
    }

    private fun updateVersion() {
        val suiPath = localPathField.text.toPathOrNull()
        versionLabel.updateAndNotifyListeners(suiPath)
    }

    fun updateSuiSdks(sdkPath: String) {
        if (sdkPath == "") return

        // do not save if the executable has no `--version`
        if (versionLabel.isError()) return

        // do not save if it's not an sui
        if ("sui" !in versionLabel.text) return

        val settingsService = sdksService()
        if (sdkPath in settingsService.state.suiSdkPaths) return

        settingsService.state.suiSdkPaths.add(sdkPath)
        // update default path
        ProjectManager.getInstance().defaultProject.service<MvProjectSettingsService>().modify {
            it.localSuiPath = sdkPath
        }

        LOG.logOrShowBalloon("Sui SDK saved: $sdkPath")
    }

    override fun dispose() {
        Disposer.dispose(localPathField)
    }

    companion object {
        private val LOG = logger<ChooseSuiCliPanel>()
    }
}
