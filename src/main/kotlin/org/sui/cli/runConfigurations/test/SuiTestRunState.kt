package org.sui.cli.runConfigurations.test

import com.intellij.execution.runners.ExecutionEnvironment
import org.sui.cli.runConfigurations.CommandConfigurationBase
import org.sui.cli.runConfigurations.SuiRunStateBase
import org.sui.cli.runConfigurations.sui.SuiTestConsoleBuilder
import org.sui.cli.runConfigurations.sui.cmd.SuiCommandConfiguration

class SuiTestRunState(
    environment: ExecutionEnvironment,
    runConfiguration: CommandConfigurationBase,
    config: CommandConfigurationBase.CleanConfiguration.Ok
) : SuiRunStateBase(environment, runConfiguration, config) {

    init {
        consoleBuilder =
            SuiTestConsoleBuilder(environment.runProfile as SuiCommandConfiguration, environment.executor)
        createFilters().forEach { consoleBuilder.addFilter(it) }
    }
}
