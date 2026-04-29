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
                group("Compiler 2024") {
                    group("Language features") {
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
                    row {
                        checkBox("Skip updating to the latest git dependencies")
                            .comment(
                                "Adds --skip-fetch-latest-git-deps to the sync and test runs."
                            )
                            .bindSelected(state::skipFetchLatestGitDeps)

                    }
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
                    val localSuiSdkPath = chooseSuiCliPanel.data.localSuiPath
                    if (localSuiSdkPath != null) {
                        chooseSuiCliPanel.updateSuiSdks(localSuiSdkPath)
                    }
                    it.localSuiPath = localSuiSdkPath

                    it.skipFetchLatestGitDeps = state.skipFetchLatestGitDeps
                    it.enablePublicPackage = state.enablePublicPackage
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
                chooseSuiCliPanel.data = ChooseSuiCliPanel.Data(state.localSuiPath)
            }

            /// checks whether any settings are modified (should be fast)
            onIsModified {
                val current = settings.state
                val suiPanelData = chooseSuiCliPanel.data
                suiPanelData.localSuiPath != settings.localSuiPath
                        || state.skipFetchLatestGitDeps != current.skipFetchLatestGitDeps
                        || state.enablePublicPackage != current.enablePublicPackage
                        || state.fetchSuiDeps != current.fetchSuiDeps
                        || state.moveAnalyzerPath != current.moveAnalyzerPath
            }
        }
    }
}
