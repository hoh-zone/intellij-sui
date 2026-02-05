package org.sui.cli.runConfigurations.sui

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.CliktError
import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.parameters.options.multiple
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
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
        class Parser : CliktCommand() {
            val functionId: String by option("--function").required()
            val typeParams: List<String> by option("--type-args").multiple()
            val params: List<String> by option("--args").multiple()
            val profile: String? by option("--profile")
            val packageId: String? by option("--package")
            val moduleName: String? by option("--module")
            val gasId: String? by option("--gas")
            val gasBudget: String? by option("--gas-budget")
            override fun run() {}
        }

        fun parse(rawCommand: String, subcommand: String): MvResult<FunctionCallParser, String> {
            val command = ParametersListUtil.parse(rawCommand).joinToString(" ")
            if (!command.startsWith(subcommand))
                return MvResult.Err("does not start with '$subcommand'")

            val arguments =
                command.drop(subcommand.length + 1).let { ParametersListUtil.parse(it) }
            val runParser = Parser()
            try {
                runParser.main(arguments)
            } catch (e: CliktError) {
                return MvResult.Err(e.message ?: "")
            }
            val functionId = runParser.functionId
            val profile = runParser.profile ?: "default"
            val packageId = runParser.packageId ?: ""
            val gasId = runParser.gasId ?: ""
            val gasBudget = runParser.gasBudget ?: ""

            val parser = FunctionCallParser(
                functionId, runParser.typeParams, runParser.params, profile,
                packageId, runParser.moduleName, gasId, gasBudget
            )
            return MvResult.Ok(parser)
        }
    }
}
