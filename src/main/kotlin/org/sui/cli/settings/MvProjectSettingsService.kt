package org.sui.cli.settings

import com.intellij.openapi.Disposable
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.StoragePathMacros
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.registry.Registry
import com.intellij.openapi.vfs.VirtualFile
import org.sui.bytecode.createDisposableOnFileChange
import org.sui.cli.runConfigurations.sui.Sui
import org.sui.cli.settings.MvProjectSettingsService.MoveProjectSettings
import org.sui.cli.settings.sui.SuiExecType
import org.sui.stdext.exists
import org.sui.stdext.isExecutableFile
import java.nio.file.Path

private const val SERVICE_NAME: String = "SuiMoveProjectSettingsService_1"

@State(
    name = SERVICE_NAME,
    storages = [Storage(StoragePathMacros.WORKSPACE_FILE)]
)
class MvProjectSettingsService(
    project: Project
) :
    MvProjectSettingsServiceBase<MoveProjectSettings>(project, MoveProjectSettings()) {

    val localSuiPath: String? get() = state.localSuiPath
    val fetchSuiDeps: Boolean get() = state.fetchSuiDeps

    val skipFetchLatestGitDeps: Boolean get() = state.skipFetchLatestGitDeps

    val enablePublicPackage: Boolean get() = state.enablePublicPackage
    val moveAnalyzerPath: String? get() = state.moveAnalyzerPath

    // default values for settings
    class MoveProjectSettings : MvProjectSettingsBase<MoveProjectSettings>() {
        @AffectsMoveProjectsMetadata
        var localSuiPath: String? by string()

        @AffectsHighlighting
        var enablePublicPackage: Boolean by property(true)

        @AffectsMoveProjectsMetadata
        var fetchSuiDeps: Boolean by property(false)

        // change to true here to not annoy the users with constant updates
        var skipFetchLatestGitDeps: Boolean by property(true)

        @AffectsHighlighting
        var moveAnalyzerPath: String? by string()

        override fun copy(): MoveProjectSettings {
            val state = MoveProjectSettings()
            state.copyFrom(this)
            return state
        }
    }

    override fun createSettingsChangedEvent(
        oldEvent: MoveProjectSettings,
        newEvent: MoveProjectSettings
    ): SettingsChangedEvent = SettingsChangedEvent(oldEvent, newEvent)

    class SettingsChangedEvent(
        oldState: MoveProjectSettings,
        newState: MoveProjectSettings
    ) : SettingsChangedEventBase<MoveProjectSettings>(oldState, newState)
}

val Project.moveSettings: MvProjectSettingsService get() = service()

fun Project.getSuiCli(parentDisposable: Disposable? = null): Sui? {
    val suiExecPath = SuiExecType.suiExecPath(this.moveSettings.localSuiPath)
    return suiExecPath?.let { Sui(it, parentDisposable) }
}

val Project.isSuiConfigured: Boolean get() = this.getSuiCli() != null

fun Project.getSuiCliDisposedOnFileChange(file: VirtualFile): Sui? {
    val anyChangeDisposable = this.createDisposableOnFileChange(file)
    return this.getSuiCli(anyChangeDisposable)
}

val Project.suiExecPath: Path? get() = this.getSuiCli()?.cliLocation

fun Path?.isValidExecutable(): Boolean {
    return this != null
            && this.toString().isNotBlank()
            && this.exists()
            && this.isExecutableFile()
}

fun isDebugModeEnabled(): Boolean = Registry.`is`("org.sui.debug.enabled")

fun <T> debugErrorOrFallback(message: String, fallback: T): T {
    if (isDebugModeEnabled()) {
        error(message)
    }
    return fallback
}

fun <T> debugErrorOrFallback(message: String, cause: Throwable?, fallback: () -> T): T {
    if (isDebugModeEnabled()) {
        throw IllegalStateException(message, cause)
    }
    return fallback()
}
