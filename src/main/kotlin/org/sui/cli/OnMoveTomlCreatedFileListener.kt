package org.sui.cli

import com.intellij.openapi.vfs.newvfs.BulkFileListener
import com.intellij.openapi.vfs.newvfs.events.VFileCreateEvent
import com.intellij.openapi.vfs.newvfs.events.VFileEvent
import com.intellij.openapi.vfs.newvfs.events.VFilePropertyChangeEvent

class OnMoveTomlCreatedFileListener(
    private val onMoveTomlAdded: () -> Unit
) : BulkFileListener {

    override fun after(events: List<VFileEvent>) {
        if (events.any { isInterestingEvent(it) }) onMoveTomlAdded()
    }

    private fun isInterestingEvent(event: VFileEvent): Boolean {
        return event is VFileCreateEvent && event.path.endsWith(MvConstants.MANIFEST_FILE)
                || event is VFilePropertyChangeEvent && event.newPath.endsWith(MvConstants.MANIFEST_FILE)
    }
}
