package org.sui.ide.notifications

import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationGroupManager

object MvNotifications {
    fun buildLogGroup(): NotificationGroup {
        return NotificationGroupManager.getInstance().getNotificationGroup("Move Compile Log")
    }

    fun pluginNotifications(): NotificationGroup {
        return NotificationGroupManager.getInstance().getNotificationGroup("MOVE_ON_SUI")
    }
}
