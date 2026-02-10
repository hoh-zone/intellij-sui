package org.sui.cli.runConfigurations.sui

import com.intellij.psi.PsiElement
import org.sui.cli.moveProjectsService
import org.sui.cli.MoveProject
import org.sui.cli.runConfigurations.SuiCommandLine
import org.sui.cli.runConfigurations.producers.CommandConfigurationProducerBase
import org.sui.cli.runConfigurations.producers.SuiCommandLineFromContext
import org.sui.lang.core.psi.MvFunction
import org.sui.lang.core.psi.MvFunctionParameter
import org.sui.lang.core.psi.ext.functionId
import org.sui.lang.core.psi.ext.allFunctions
import org.sui.lang.core.psi.typeParameters
import org.sui.lang.moveProject
import org.sui.stdext.MvResult

abstract class CommandConfigurationHandler {

    abstract val subCommand: String

    abstract fun functionPredicate(function: MvFunction): Boolean

    abstract fun configurationName(functionId: String): String

    fun configurationFromLocation(location: PsiElement): SuiCommandLineFromContext? {
        val function =
            CommandConfigurationProducerBase.findElement<MvFunction>(location, true)
                ?.takeIf(this::functionPredicate)
                ?: return null
        val moveProject = function.moveProject ?: return null

        val functionId = function.functionId(moveProject) ?: return null
        val profileName = moveProject.profiles.firstOrNull()
        val workingDirectory = moveProject.contentRootPath

        val arguments = mutableListOf<String>()

        val functionQualList = functionId.split("::")
        val moduleName = functionQualList[1]
        val functionName = functionQualList[2]

        // arguments.addAll(listOf("--package", functionId))
        arguments.addAll(listOf("--module", moduleName))
        arguments.addAll(listOf("--function", functionId))

        val commandLine = SuiCommandLine(subCommand, arguments, workingDirectory)
        return SuiCommandLineFromContext(
            function,
            configurationName(functionId),
            commandLine
        )
    }

    abstract fun getFunctionCompletionVariants(moveProject: MoveProject): Collection<String>

    abstract fun getFunctionItem(moveProject: MoveProject, functionQualName: String): MvFunction?

    abstract fun getFunctionByCmdName(moveProject: MoveProject, functionCmdName: String): MvFunction?

    abstract fun getFunctionParameters(function: MvFunction): List<MvFunctionParameter>

    fun generateCommand(
        moveProject: MoveProject,
        functionCall: FunctionCall,
        signerAccount: String?,
    ): MvResult<String, String> {
        val functionId = functionCall.functionId(moveProject) ?: return MvResult.Err("FunctionId is null")

        val typeParams = functionCall.typeParams
            .mapNotNull { it.value }.flatMap { listOf("--type-args", it) }
        val params = functionCall.valueParams
            .mapNotNull { it.value?.cmdText() }.flatMap { listOf("--args", it) }

        val profileArguments = signerAccount?.takeIf { it.isNotBlank() }?.let { listOf("--profile", it) }
        val gasArguments = functionCall.gasId?.takeIf { it.isNotBlank() }?.let { listOf("--gas", it) }
        val gasBudgetArguments = functionCall.gasBudget?.takeIf { it.isNotBlank() }?.let { listOf("--gas-budget", it) }

        val commandArguments = listOf(
            subCommand.split(' '),
            profileArguments ?: emptyList(),
            listOf("--package", functionCall.packageId),
            listOf("--function", functionId),
            gasArguments ?: emptyList(),
            gasBudgetArguments ?: emptyList(),
            typeParams,
            params
        )

        val command = commandArguments.flatten().joinToString(" ")
        return MvResult.Ok(command)
    }

    fun parseTransactionCommand(
        moveProject: MoveProject,
        command: String
    ): MvResult<Pair<String, FunctionCall>, String> {
        val res = FunctionCallParser.parse(command, subCommand)
        val callArgs = when (res) {
            is MvResult.Ok -> res.ok
            is MvResult.Err -> return MvResult.Err("malformed command error '${res.err}'")
        }
        val profileName = callArgs.profile

        val functionId = callArgs.functionId
        val function = resolveFunction(moveProject, callArgs)
            ?: moveProject.project.moveProjectsService.allProjects
                .asSequence()
                .mapNotNull { project -> resolveFunction(project, callArgs) }
                .firstOrNull()
            ?: return MvResult.Err("function with this functionId does not exist in the current project")

        val transaction = FunctionCall.template(
            function,
            callArgs.packageId,
            callArgs.gasId.takeIf { it.isNotBlank() },
            callArgs.gasBudget.takeIf { it.isNotBlank() }
        )

        val typeParameterNames = function.typeParameters.mapNotNull { it.name }
        for ((name, value) in typeParameterNames.zip(callArgs.typeArgs)) {
            transaction.typeParams[name] = value
        }

        val parameters = getFunctionParameters(function)
        for ((parameter, valueWithType) in parameters.zip(callArgs.args)) {
            val name = parameter.patBinding.name
            val split = valueWithType.split(':', limit = 2)
            val value = split.getOrElse(1) { "" }
            val tyName = parameter.typeAnnotation?.type?.text ?: split.firstOrNull().orEmpty()
            transaction.valueParams[name] = FunctionCallParam(value, tyName)
        }

        return MvResult.Ok(Pair(profileName, transaction))
    }

    private fun resolveFunction(
        moveProject: MoveProject,
        callArgs: FunctionCallParser
    ): MvFunction? {
        val candidates = linkedSetOf<String>()
        val functionId = callArgs.functionId

        fun addressVariants(address: String): List<String> {
            val trimmed = address.removePrefix("0x")
            if (trimmed.isEmpty()) return listOf(address)
            return listOf("0x$trimmed", trimmed)
        }

        candidates += functionId
        val explicitPackage = callArgs.packageId.takeIf { it.isNotBlank() }
        val explicitModule = callArgs.moduleName

        val parts = functionId.split("::")
        when (parts.size) {
            1 -> {
                if (explicitPackage != null && explicitModule != null) {
                    for (pkg in addressVariants(explicitPackage)) {
                        candidates += "$pkg::$explicitModule::$functionId"
                    }
                }
            }

            2 -> {
                val module = parts[0]
                val function = parts[1]
                if (explicitPackage != null) {
                    for (pkg in addressVariants(explicitPackage)) {
                        candidates += "$pkg::$module::$function"
                    }
                }
            }

            else -> {
                val address = parts[0]
                val module = parts[1]
                val function = parts[2]
                for (addr in addressVariants(address)) {
                    candidates += "$addr::$module::$function"
                }
                candidates += "$module::$function"
                candidates += function
            }
        }

        for (candidate in candidates) {
            val function = getFunctionByCmdName(moveProject, candidate)
            if (function != null) return function
        }
        return resolveFunctionByPsiScan(moveProject, callArgs, candidates)
    }

    private fun resolveFunctionByPsiScan(
        moveProject: MoveProject,
        callArgs: FunctionCallParser,
        candidateIds: Set<String>
    ): MvFunction? {
        val explicitModule = callArgs.moduleName
        val simpleName = callArgs.functionId.substringAfterLast("::")

        val matches = mutableListOf<MvFunction>()
        var exactMatch: MvFunction? = null
        moveProject.processMoveFiles { moveFile ->
            for (module in moveFile.modules()) {
                for (function in module.allFunctions()) {
                    if (function.name != simpleName) continue
                    if (explicitModule != null && function.qualName?.moduleName != explicitModule) continue

                    val fid = function.functionId(moveProject)
                    if (fid != null && fid in candidateIds) {
                        exactMatch = function
                        return@processMoveFiles false
                    }
                    matches += function
                }
            }
            true
        }
        if (exactMatch != null) return exactMatch
        return matches.singleOrNull()
    }
}
