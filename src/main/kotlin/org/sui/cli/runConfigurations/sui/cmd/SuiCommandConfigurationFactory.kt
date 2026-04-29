package org.sui.cli.runConfigurations.sui.cmd

import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationType
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.openapi.project.Project

class SuiCommandConfigurationFactory(
    configurationType: ConfigurationType
) :
    ConfigurationFactory(configurationType) {

    override fun getId(): String = "SuiCommand"

    override fun getName(): String = "sui command"

    override fun createTemplateConfiguration(project: Project): RunConfiguration {
        return SuiCommandConfiguration(project, this)
    }
}
