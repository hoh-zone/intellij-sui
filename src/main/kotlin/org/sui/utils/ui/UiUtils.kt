package org.sui.utils.ui

import com.intellij.execution.ExecutionBundle
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.ui.LabeledComponent
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.util.text.nullize
import java.nio.file.Path
import java.nio.file.Paths

class WorkingDirectoryField : LabeledComponent<TextFieldWithBrowseButton>() {
    init {
        component = TextFieldWithBrowseButton().apply {
            val fileChooser = FileChooserDescriptorFactory.createSingleFolderDescriptor().apply {
                title = ExecutionBundle.message("select.working.directory.message")
            }
            addActionListener {
                val toSelect = text
                    .takeIf { it.isNotBlank() }
                    ?.let { LocalFileSystem.getInstance().findFileByPath(it) }
                FileChooser.chooseFile(fileChooser, null, this, toSelect) {
                    text = it.path
                }
            }
        }
        text = ExecutionBundle.message("run.configuration.working.directory.label")
    }

    fun toPath(): Path? = this.component.text.nullize()?.let { Paths.get(it) }
}
