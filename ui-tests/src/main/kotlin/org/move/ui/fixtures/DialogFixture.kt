// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.sui.ui.fixtures

import com.intellij.remoterobot.RemoteRobot
import com.intellij.remoterobot.data.RemoteComponent
import com.intellij.remoterobot.fixtures.CommonContainerFixture
import com.intellij.remoterobot.fixtures.ContainerFixture
import com.intellij.remoterobot.fixtures.FixtureName
import com.intellij.remoterobot.search.locators.byXpath
import com.intellij.remoterobot.stepsProcessing.step
import org.sui.ui.fixtures.DialogFixture.Companion
import java.time.Duration

fun ContainerFixture.dialog(
    title: String,
    timeout: Duration = Duration.ofSeconds(20),
    function: DialogFixture.() -> Unit = {}
): DialogFixture = step("Search for dialog with title $title") {
    find<DialogFixture>(Companion.byTitle(title), timeout).apply(function)
}

@FixtureName("Dialog")
open class DialogFixture(
    remoteRobot: RemoteRobot,
    remoteComponent: RemoteComponent
) : CommonContainerFixture(remoteRobot, remoteComponent) {

    companion object {
        @JvmStatic
        fun byTitle(title: String) = byXpath("title $title", "//div[@title='$title' and @class='MyDialog']")
    }

    val title: String
        get() = callJs("component.getTitle();")

    // component is DialogWrapperPeerImpl.MyDialog
    fun doOKAction() = runJs("""component.getDialogWrapper().performOKAction(); """, runInEdt = true)
    fun doCancelAction() = runJs("""component.getDialogWrapper().doCancelAction(); """, runInEdt = true)
}

class SettingsDialogFixture(
    remoteRobot: RemoteRobot,
    remoteComponent: RemoteComponent
) :
    DialogFixture(remoteRobot, remoteComponent) {

    fun configurableEditor(
        timeout: Duration = Duration.ofSeconds(20),
        function: CommonContainerFixture.() -> Unit = {}
    ) =
        find<CommonContainerFixture>(byXpath("//div[@class='ConfigurableEditor']"), timeout).apply(function)
}