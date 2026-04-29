package org.sui.ide.notifications

import com.intellij.notification.NotificationType
import com.intellij.notification.NotificationType.INFORMATION
import com.intellij.notification.Notifications
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.diagnostic.LogLevel
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.NlsContexts.NotificationContent
import com.intellij.openapi.util.NlsContexts.NotificationTitle
import org.sui.cli.settings.isDebugModeEnabled
import org.sui.openapiext.common.isUnitTestMode
import org.sui.openapiext.log

fun Logger.logOrShowBalloon(@NotificationContent content: String, productionLevel: LogLevel = LogLevel.DEBUG) {
    when {
        isUnitTestMode -> this.warn("BALLOON: $content")
        isDebugModeEnabled() -> {
            this.warn(content)
            showBalloonWithoutProject(content, INFORMATION)
        }

        else -> this.log(content, productionLevel)
    }
}

fun Project.showBalloon(
    @NotificationTitle title: String,
    @NotificationContent content: String,
    type: NotificationType,
    action: AnAction? = null,
) {
    val notification = MvNotifications.pluginNotifications().createNotification(title, content, type)
    if (action != null) {
        notification.addAction(action)
    }
    Notifications.Bus.notify(notification, this)
}

fun Project.showDebugBalloon(
    @NotificationTitle title: String,
    @NotificationContent content: String,
    type: NotificationType,
    action: AnAction? = null,
) {
    if (isDebugModeEnabled()) {
        this.showBalloon(title, content, type, action)
    }
}

fun showBalloonWithoutProject(
    @NotificationContent content: String,
    type: NotificationType
) {
    val notification = MvNotifications.pluginNotifications().createNotification(content, type)
    Notifications.Bus.notify(notification)
}

fun showBalloonWithoutProject(
    title: String,
    @NotificationContent content: String,
    type: NotificationType
) {
    val notification = MvNotifications.pluginNotifications().createNotification(title, content, type)
    Notifications.Bus.notify(notification)
}
