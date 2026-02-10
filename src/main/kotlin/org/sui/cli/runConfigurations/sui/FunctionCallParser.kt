package org.sui.cli.runConfigurations.sui

import com.intellij.util.execution.ParametersListUtil
import org.sui.stdext.MvResult

data class FunctionCallParser(
    val functionId: String,
    val typeArgs: List<String>,
    val args: List<String>,
    val profile: String,
    val packageId: String,
    val moduleName: String?,
    val gasId: String,
    val gasBudget: String,
) {
    companion object {
        fun parse(rawCommand: String, subcommand: String): MvResult<FunctionCallParser, String> {
            val tokens = ParametersListUtil.parse(rawCommand)
            val subcommandTokens = ParametersListUtil.parse(subcommand)
            if (!tokens.take(subcommandTokens.size).equals(subcommandTokens))
                return MvResult.Err("does not start with '$subcommand'")

            val arguments = tokens.drop(subcommandTokens.size)
            var functionId: String? = null
            val typeArgs = mutableListOf<String>()
            val args = mutableListOf<String>()
            var profile: String? = null
            var packageId: String? = null
            var moduleName: String? = null
            var gasId: String? = null
            var gasBudget: String? = null

            var index = 0
            while (index < arguments.size) {
                val option = arguments[index]
                fun readValue(): String {
                    if (index + 1 >= arguments.size) error("missing value for '$option'")
                    index += 1
                    return arguments[index]
                }

                try {
                    when (option) {
                        "--function" -> functionId = readValue()
                        "--type-args" -> typeArgs += readValue()
                        "--args" -> args += readValue()
                        "--profile" -> profile = readValue()
                        "--package" -> packageId = readValue()
                        "--module" -> moduleName = readValue()
                        "--gas" -> gasId = readValue()
                        "--gas-budget" -> gasBudget = readValue()
                        else -> return MvResult.Err("unknown option '$option'")
                    }
                } catch (e: IllegalStateException) {
                    return MvResult.Err(e.message ?: "malformed command")
                }

                index += 1
            }

            val resolvedFunctionId = functionId ?: return MvResult.Err("missing option '--function'")
            val resolvedProfile = profile ?: "default"
            val resolvedPackageId = packageId ?: ""
            val resolvedGasId = gasId ?: ""
            val resolvedGasBudget = gasBudget ?: ""

            val parser = FunctionCallParser(
                resolvedFunctionId, typeArgs, args, resolvedProfile,
                resolvedPackageId, moduleName, resolvedGasId, resolvedGasBudget
            )
            return MvResult.Ok(parser)
        }
    }
}
