package org.sui.cli.runConfigurations.sui

import com.intellij.execution.configuration.EnvironmentVariablesData
import com.intellij.execution.process.CapturingProcessHandler
import com.intellij.execution.process.ProcessListener
import com.intellij.execution.process.ProcessOutput
import com.intellij.openapi.Disposable
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.vfs.VirtualFile
import org.sui.cli.MvConstants
import org.sui.cli.runConfigurations.SuiCommandLine
import org.sui.cli.settings.moveSettings
import org.sui.openapiext.*
import org.sui.openapiext.common.isUnitTestMode
import org.sui.stdext.MvResult.Err
import org.sui.stdext.MvResult.Ok
import org.sui.stdext.toPath
import java.nio.file.Path
import java.nio.file.Paths

data class Sui(val cliLocation: Path, val parentDisposable: Disposable?) : Disposable {

    init {
        if (parentDisposable != null) {
            Disposer.register(parentDisposable, this)
        }
    }

    fun init(
        rootDirectory: VirtualFile,
        packageName: String
    ): MvProcessResult<VirtualFile> {
        if (!isUnitTestMode) {
            checkIsBackgroundThread()
        }

        val baseDirPath = rootDirectory.path
        val rootDirectoryPath = baseDirPath.removeSuffix("/$packageName")

        val commandLine = SuiCommandLine(
            "move new",
            listOf(packageName),
            workingDirectory = rootDirectoryPath.toPath()
        )
        val result = executeCommandLine(commandLine)
        if (result is Err) {
            println("Sui command execution failed: $result")
            return result
        }

        fullyRefreshDirectory(rootDirectory)

        val manifest =
            checkNotNull(rootDirectory.findChild(MvConstants.MANIFEST_FILE)) { "Can't find the manifest file" }

        return Ok(manifest)
    }

    fun fetchPackageDependencies(
        project: Project,
        projectDir: Path,
        skipLatest: Boolean,
        processListener: ProcessListener
    ): MvProcessResult<Unit> {
        if (project.moveSettings.fetchSuiDeps) {
            val commandLine =
                SuiCommandLine(
                    subCommand = "move build",
                    arguments = listOfNotNull(
                        "--skip-fetch-latest-git-deps".takeIf { skipLatest }
                    ),
                    workingDirectory = projectDir
                )
            // Sui does not yet support fetching dependencies without compiling, so errors are ignored here.
            executeCommandLine(commandLine, listener = processListener)
        }
        return Ok(Unit)
    }

    fun downloadPackage(
        project: Project,
        accountAddress: String,
        packageName: String,
        outputDir: String,
        profile: String? = null,
        networkUrl: String? = null,
        connectionTimeoutSecs: Int = -1,
        nodeApiKey: String? = null,
        runner: CapturingProcessHandler.() -> ProcessOutput = {
            runProcessWithGlobalProgress(
                timeoutInMilliseconds = null
            )
        }
    ): MvProcessResult<ProcessOutput> {
        val commandLine = SuiCommandLine(
            subCommand = "move download",
            arguments = buildList {
                add("--account"); add(accountAddress)
                add("--package"); add(packageName)
                add("--bytecode")
                add("--output-dir"); add(outputDir)
                if (!profile.isNullOrBlank()) {
                    add("--profile"); add(profile)
                }
                if (!networkUrl.isNullOrBlank()) {
                    add("--url"); add(networkUrl)
                }
                if (!nodeApiKey.isNullOrBlank()) {
                    add("--node-api-key"); add(nodeApiKey)
                }
                if (connectionTimeoutSecs != -1) {
                    add("--connection-timeout-secs"); add(connectionTimeoutSecs.toString())
                }
            },
            workingDirectory = project.basePath?.let { Path.of(it) },
            environmentVariables = EnvironmentVariablesData.DEFAULT
        )
        return executeCommandLine(commandLine, runner = runner)
    }

    fun decompileDownloadedPackage(downloadedPackagePath: Path): MvProcessResult<ProcessOutput> {
        val bytecodeModulesPath =
            downloadedPackagePath.resolve("bytecode_modules").toAbsolutePath().toString()
        val commandLine = SuiCommandLine(
            subCommand = "move decompile",
            arguments = buildList {
                add("--package-path"); add(bytecodeModulesPath)
                add("--assume-yes")
            },
            workingDirectory = downloadedPackagePath
        )
        return executeCommandLine(commandLine)
    }

    fun decompileFile(
        bytecodeFilePath: String,
        outputDir: String?,
    ): MvProcessResult<ProcessOutput> {
        val fileRoot = Paths.get(bytecodeFilePath).parent
        val commandLine = SuiCommandLine(
            subCommand = "move decompile",
            arguments = buildList {
                add("--bytecode-path"); add(bytecodeFilePath)
                if (outputDir != null) {
                    add("--output-dir"); add(outputDir)
                }
                add("--assume-yes")
            },
            workingDirectory = fileRoot
        )
        // only one second is allowed to run decompiler, otherwise fails with timeout
        return executeCommandLine(commandLine)
    }

    private fun executeCommandLine(
        commandLine: SuiCommandLine,
        listener: ProcessListener? = null,
        runner: CapturingProcessHandler.() -> ProcessOutput = {
            runProcessWithGlobalProgress(
                timeoutInMilliseconds = null
            )
        }
    ): MvProcessResult<ProcessOutput> {
        return commandLine
            .toGeneralCommandLine(this.cliLocation)
            .execute(this, stdIn = null, listener = listener, runner = runner)
    }

    override fun dispose() {}
}
