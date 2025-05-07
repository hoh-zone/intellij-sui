/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.cli.externalLinter

import com.intellij.openapi.components.*
import com.intellij.openapi.components.Service.Level.PROJECT
import com.intellij.openapi.project.Project
import org.sui.cli.externalLinter.MvExternalLinterProjectSettingsService.MvExternalLinterProjectSettings
import org.sui.cli.settings.MvProjectSettingsServiceBase

val Project.externalLinterSettings: MvExternalLinterProjectSettingsService
    get() = service<MvExternalLinterProjectSettingsService>()

private const val SERVICE_NAME: String = "MvExternalLinterProjectSettings"

@State(
    name = SERVICE_NAME,
    storages = [Storage(StoragePathMacros.WORKSPACE_FILE)],
)
@Service(PROJECT)
class MvExternalLinterProjectSettingsService(
    project: Project
) : MvProjectSettingsServiceBase<MvExternalLinterProjectSettings>(project, MvExternalLinterProjectSettings()) {
    val tool: ExternalLinter get() = state.tool
    val additionalArguments: String get() = state.additionalArguments

    val envs: Map<String, String> get() = state.envs
    val runOnTheFly: Boolean get() = state.runOnTheFly
    val skipErrorsKnownToIde: Boolean get() = state.skipErrorsKnownToIde

    class MvExternalLinterProjectSettings : MvProjectSettingsBase<MvExternalLinterProjectSettings>() {
        @AffectsHighlighting
        var tool by enum(ExternalLinter.DEFAULT)

        @AffectsHighlighting
        var additionalArguments by property("") { it.isEmpty() }

        @AffectsHighlighting
        var envs by map<String, String>()

        @AffectsHighlighting
        var runOnTheFly by property(false)

        @AffectsHighlighting
        var skipErrorsKnownToIde by property(false)

        override fun copy(): MvExternalLinterProjectSettings {
            val state = MvExternalLinterProjectSettings()
            state.copyFrom(this)
            return state
        }
    }

    override fun createSettingsChangedEvent(
        oldEvent: MvExternalLinterProjectSettings,
        newEvent: MvExternalLinterProjectSettings
    ): SettingsChangedEvent = SettingsChangedEvent(oldEvent, newEvent)

    class SettingsChangedEvent(
        oldState: MvExternalLinterProjectSettings,
        newState: MvExternalLinterProjectSettings
    ) : SettingsChangedEventBase<MvExternalLinterProjectSettings>(oldState, newState)
}
