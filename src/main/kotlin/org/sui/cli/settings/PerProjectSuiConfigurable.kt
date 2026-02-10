package org.sui.cli.settings

import com.intellij.openapi.options.BoundConfigurable
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.util.Disposer
import com.intellij.ui.dsl.builder.AlignX
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.panel
import org.sui.ide.lsp.restartMoveAnalyzerServerAsync
import org.sui.cli.settings.sui.ChooseSuiCliPanel
import org.sui.openapiext.showSettingsDialog
import org.sui.stdext.getCliFromPATH

// panels needs not to be bound to the Configurable itself, as it's sometimes created without calling the `createPanel()`
class PerProjectSuiConfigurable(val project: Project) : BoundConfigurable("Sui") {

    override fun createPanel(): DialogPanel {
        val chooseSuiCliPanel = ChooseSuiCliPanel(versionUpdateListener = null)
        this.disposable?.let {
            Disposer.register(it, chooseSuiCliPanel)
        }
        return panel {
            val settings = project.moveSettings
            val state = settings.state.copy()

            chooseSuiCliPanel.attachToLayout(this)

            group {
//                row {
//                    checkBox("Fetch external packages on project reload")
//                        .bindSelected(state::fetchSuiDeps)
//                    link("Configure project reload schedule") {
//                        ProjectManager.getInstance().defaultProject.showSettingsDialog<ExternalSystemGroupConfigurable>()
//                    }
//                        .align(AlignX.RIGHT)
//                }
                group("Compiler 2024") {
//                    row {
//                        checkBox("Set Compiler V2 flags for CLI")
//                            .comment(
//                                "Adds `--compiler-version v2 --language-version 2.0` " +
//                                        "to all generated Sui CLI commands"
//                            )
//                            .bindSelected(state::addCompilerV2CLIFlags)
//                    }
                    group("Language features") {
                        row {
                            checkBox("Receiver-Style functions")
                                .comment(
                                    "Allows calling functions with special " +
                                            "first <b><code>self</code></b> parameter as a methods through dot expression."
                                )
                                .bindSelected(state::enableReceiverStyleFunctions)
                        }
//                        row {
//                            checkBox("Resource-Access control")
//                                .comment(
//                                    "Allows specifying resource access attributes " +
//                                            "(<code>reads, writes, pure</code> for functions). " +
//                                            "Requires re-parsing of all Move files in the project, can be slow."
//                                )
//                                .bindSelected(state::enableResourceAccessControl)
//                        }
                        row {
                            checkBox("Index notation")
                                .comment(
                                    "Allows resource (<code>R[@0x1]</code>) and vector (<code>v[0]</code>) index operators."
                                )
                                .bindSelected(state::enableIndexExpr)
                        }
                        row {
                            checkBox("public(package) visibility modifier")
                                .comment(
                                    "Allows <code>public(package)</code> visibility modifier " +
                                            "to specify functions accessible to any module of the same package."
                                )
                                .bindSelected(state::enablePublicPackage)
                        }
                    }
                }
                group("Move Analyzer (LSP)") {
                    row { comment("Plugin works in LSP-only mode and requires configured <code>move-analyzer</code>.") }
                    row("move-analyzer path:") {
                        val pathField = textField()
                            .bindText(
                                { state.moveAnalyzerPath.orEmpty() },
                                { state.moveAnalyzerPath = it.ifBlank { null } }
                            )
                            .align(AlignX.FILL)
                            .resizableColumn()
                        button("Auto-detect") {
                            pathField.component.text = getCliFromPATH("move-analyzer")?.toString().orEmpty()
                        }
                    }
                    row {
                        comment("Install command: <code>suiup install move-analyzer</code>")
                    }
                }
                group("Command Line Options") {
//                    row {
//                        checkBox("Disable telemetry for new Run Configurations")
//                            .comment(
//                                "Adds SUI_DISABLE_TELEMETRY=true to every generated Sui command."
//                            )
//                            .bindSelected(state::disableTelemetry)
//                    }
                    row {
                        checkBox("Skip updating to the latest git dependencies")
                            .comment(
                                "Adds --skip-fetch-latest-git-deps to the sync and test runs."
                            )
                            .bindSelected(state::skipFetchLatestGitDeps)

                    }
//                    row {
//                        checkBox("Dump storage to console on test failures")
//                            .comment(
//                                "Adds --dump to generated test runs."
//                            )
//                            .bindSelected(state::dumpStateOnTestFailure)
//                    }
                }
            }

            if (!project.isDefault) {
                row {
                    link("Set default project settings") {
                        ProjectManager.getInstance().defaultProject.showSettingsDialog<PerProjectSuiConfigurable>()
                    }
                        .align(AlignX.RIGHT)
                }
            }

            // saves values from Swing form back to configurable (OK / Apply)
            onApply {
                val oldState = settings.state.copy()
                settings.modify {
                    it.suiExecType = chooseSuiCliPanel.data.suiExecType

                    val localSuiSdkPath = chooseSuiCliPanel.data.localSuiPath
                    if (localSuiSdkPath != null) {
                        chooseSuiCliPanel.updateSuiSdks(localSuiSdkPath)
                    }
                    it.localSuiPath = localSuiSdkPath

                    it.disableTelemetry = state.disableTelemetry
                    it.skipFetchLatestGitDeps = state.skipFetchLatestGitDeps
                    it.dumpStateOnTestFailure = state.dumpStateOnTestFailure
                    it.enableReceiverStyleFunctions = state.enableReceiverStyleFunctions
                    it.enableResourceAccessControl = state.enableResourceAccessControl
                    it.enableIndexExpr = state.enableIndexExpr
                    it.enablePublicPackage = state.enablePublicPackage
                    it.addCompilerV2CLIFlags = state.addCompilerV2CLIFlags
                    it.fetchSuiDeps = state.fetchSuiDeps
                    it.moveAnalyzerPath = state.moveAnalyzerPath
                }
                val lspConfigChanged =
                    oldState.moveAnalyzerPath != state.moveAnalyzerPath
                if (lspConfigChanged) {
                    restartMoveAnalyzerServerAsync(project)
                }
            }

            // loads settings from configurable to swing form
            onReset {
                state.copyFrom(settings.state)
                chooseSuiCliPanel.data =
                    ChooseSuiCliPanel.Data(state.suiExecType, state.localSuiPath)
            }

            /// checks whether any settings are modified (should be fast)
            onIsModified {
                val current = settings.state
                val suiPanelData = chooseSuiCliPanel.data
                suiPanelData.suiExecType != settings.suiExecType
                        || suiPanelData.localSuiPath != settings.localSuiPath
                        || state.disableTelemetry != current.disableTelemetry
                        || state.skipFetchLatestGitDeps != current.skipFetchLatestGitDeps
                        || state.dumpStateOnTestFailure != current.dumpStateOnTestFailure
                        || state.enableReceiverStyleFunctions != current.enableReceiverStyleFunctions
                        || state.enableResourceAccessControl != current.enableResourceAccessControl
                        || state.enableIndexExpr != current.enableIndexExpr
                        || state.enablePublicPackage != current.enablePublicPackage
                        || state.addCompilerV2CLIFlags != current.addCompilerV2CLIFlags
                        || state.fetchSuiDeps != current.fetchSuiDeps
                        || state.moveAnalyzerPath != current.moveAnalyzerPath
            }
        }
    }

//    override fun disposeUIResources() {
//        super.disposeUIResources()
//        Disposer.dispose(chooseSuiCliPanel)
//    }
}
