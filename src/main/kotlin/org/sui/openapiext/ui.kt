package org.sui.openapiext

import com.intellij.openapi.Disposable
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.application.invokeLater
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.ScrollType
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.util.NlsContexts.DialogTitle
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.ui.DocumentAdapter
import com.intellij.ui.components.JBTextField
import com.intellij.ui.dsl.builder.AlignX
import com.intellij.ui.dsl.builder.Cell
import com.intellij.ui.dsl.builder.Row
import com.intellij.util.Alarm
import org.sui.lang.core.psi.MvElement
import javax.swing.JComponent
import javax.swing.JTextField
import javax.swing.event.DocumentEvent

class UiDebouncer(
    parentDisposable: Disposable,
    private val delayMillis: Int = 200
) : Disposable {
    private val alarm = Alarm(Alarm.ThreadToUse.POOLED_THREAD, parentDisposable)
    
    @Volatile
    private var disposed = false

    init {
        Disposer.register(parentDisposable) { dispose() }
    }

    val isDisposed: Boolean get() = disposed

    override fun dispose() {
        disposed = true
        alarm.cancelAllRequests()
    }

    /**
     * @param onUiThread: callback to be executed in EDT with **any** modality state.
     * Use it only for UI updates
     */
    fun <T> update(onPooledThread: () -> T, onUiThread: (T) -> Unit) {
        if (isDisposed) return
        alarm.cancelAllRequests()
        alarm.addRequest({
            val r = onPooledThread()
            invokeLater(ModalityState.any()) {
                if (!isDisposed) {
                    onUiThread(r)
                }
            }
        }, delayMillis)
    }
}

fun pathField(
    fileChooserDescriptor: FileChooserDescriptor,
    disposable: Disposable,
    @DialogTitle dialogTitle: String,
    onTextChanged: (String) -> Unit = {}
): TextFieldWithBrowseButton {
    val component = TextFieldWithBrowseButton(null, disposable)
    fileChooserDescriptor.title = dialogTitle
    component.addActionListener {
        val toSelect = component.text
            .takeIf { it.isNotBlank() }
            ?.let { LocalFileSystem.getInstance().findFileByPath(it) }
        FileChooser.chooseFile(fileChooserDescriptor, null, component, toSelect) {
            component.text = it.path
        }
    }
    component.childComponent.addTextChangeListener {
        val documentText = it.document.getText(0, it.document.length)
        onTextChanged(documentText)
    }
    return component
}

fun JTextField.addTextChangeListener(listener: (DocumentEvent) -> Unit) {
    document.addDocumentListener(
        object : DocumentAdapter() {
            override fun textChanged(e: DocumentEvent) {
                listener(e)
            }
        }
    )
}

fun <T : JComponent> Row.fullWidthCell(component: T): Cell<T> {
    return cell(component).align(AlignX.FILL)
}
