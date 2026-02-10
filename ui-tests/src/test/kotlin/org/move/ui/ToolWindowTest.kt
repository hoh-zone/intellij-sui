package org.sui.ui

import com.intellij.remoterobot.RemoteRobot
import com.intellij.remoterobot.data.RemoteComponent
import com.intellij.remoterobot.fixtures.ComponentFixture
import com.intellij.remoterobot.fixtures.ContainerFixture
import com.intellij.remoterobot.fixtures.JTreeFixture
import com.intellij.remoterobot.search.locators.byXpath
import org.junit.jupiter.api.Test
import org.sui.ui.fixtures.*

class ProjectsTreeFixture(
    remoteRobot: RemoteRobot,
    remoteComponent: RemoteComponent
) :
    JTreeFixture(remoteRobot, remoteComponent)

class ToolWindowTest : UiTestBase() {
    @Test
    fun `sui tool window not available if move project has no manifest`(robot: RemoteRobot) = with(robot) {
        val projectPath =
            copyExamplePackageToTempFolder("empty_move_package")
        openOrImportProject(projectPath)

        // TODO: change into proper JS call to ToolWindowManager
        ideaFrame {
            val rightStripe =
                find<ContainerFixture>(byXpath("//div[@accessiblename='Right Stripe']"))
            assert(rightStripe.findIsNotVisible(byXpath("//div[@text='Sui']")))
        }
    }

    @Test
    fun `sui tool window for sui project`(robot: RemoteRobot) = with(robot) {
        val projectPath =
            copyExamplePackageToTempFolder("sui_package")
        openOrImportProject(projectPath)

        // TODO: change into proper JS call to ToolWindowManager
        ideaFrame {
            val rightStripe =
                find<ContainerFixture>(byXpath("//div[@accessiblename='Right Stripe']"))
            assert(rightStripe.findIsVisible(byXpath("//div[@text='Sui']")))
        }
    }
}
