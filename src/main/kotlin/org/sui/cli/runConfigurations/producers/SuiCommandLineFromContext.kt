package org.sui.cli.runConfigurations.producers

import org.sui.cli.runConfigurations.SuiCommandLine

data class SuiCommandLineFromContext(
    val configurationName: String,
    val commandLine: SuiCommandLine
)
