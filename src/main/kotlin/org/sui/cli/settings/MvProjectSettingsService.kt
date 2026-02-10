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

    val suiExecType: SuiExecType get() = state.suiExecType
    val localSuiPath: String? get() = state.localSuiPath
    val fetchSuiDeps: Boolean get() = state.fetchSuiDeps

    val disableTelemetry: Boolean get() = state.disableTelemetry
    val skipFetchLatestGitDeps: Boolean get() = state.skipFetchLatestGitDeps
    val dumpStateOnTestFailure: Boolean get() = state.dumpStateOnTestFailure

    val enableReceiverStyleFunctions: Boolean get() = state.enableReceiverStyleFunctions
    val enableResourceAccessControl: Boolean get() = state.enableResourceAccessControl
    val enableIndexExpr: Boolean get() = state.enableIndexExpr
    val enablePublicPackage: Boolean get() = state.enablePublicPackage
    val addCompilerV2CLIFlags: Boolean get() = state.addCompilerV2CLIFlags
    val moveAnalyzerPath: String? get() = state.moveAnalyzerPath

    // default values for settings
    class MoveProjectSettings : MvProjectSettingsBase<MoveProjectSettings>() {
        @AffectsMoveProjectsMetadata
        var suiExecType: SuiExecType by enum(defaultSuiExecType)

        @AffectsMoveProjectsMetadata
        var localSuiPath: String? by string()

        @AffectsHighlighting
        var enableReceiverStyleFunctions: Boolean by property(true)

        @AffectsParseTree
        var enableResourceAccessControl: Boolean by property(false)

        @AffectsHighlighting
        var enableIndexExpr: Boolean by property(true)

        @AffectsHighlighting
        var enablePublicPackage: Boolean by property(true)

        @AffectsMoveProjectsMetadata
        var fetchSuiDeps: Boolean by property(false)

        var disableTelemetry: Boolean by property(true)

        // change to true here to not annoy the users with constant updates
        var skipFetchLatestGitDeps: Boolean by property(true)
        var dumpStateOnTestFailure: Boolean by property(false)

        var addCompilerV2CLIFlags: Boolean by property(false)

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

    companion object {
        private val defaultSuiExecType
            get() = SuiExecType.LOCAL
    }
}

val Project.moveSettings: MvProjectSettingsService get() = service()

fun Project.getSuiCli(parentDisposable: Disposable? = null): Sui? {
    val suiExecPath =
        SuiExecType.suiExecPath(
            this.moveSettings.suiExecType,
            this.moveSettings.localSuiPath
        )
    val sui = suiExecPath?.let { Sui(it, parentDisposable) }
    return sui
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
fun isTypeUnknownAsError(): Boolean = Registry.`is`("org.sui.types.highlight.unknown.as.error")

fun debugError(message: String) {
    if (isDebugModeEnabled()) {
        error(message)
    }
}

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
