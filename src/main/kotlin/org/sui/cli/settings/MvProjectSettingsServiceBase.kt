package org.sui.cli.settings

import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer
import com.intellij.openapi.components.BaseState
import com.intellij.openapi.components.SimplePersistentStateComponent
import com.intellij.openapi.project.Project
import com.intellij.util.messages.Topic
import org.sui.cli.settings.MvProjectSettingsServiceBase.MvProjectSettingsBase
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

abstract class MvProjectSettingsServiceBase<T : MvProjectSettingsBase<T>>(
    val project: Project,
    state: T
) : SimplePersistentStateComponent<T>(state) {

    abstract class MvProjectSettingsBase<T : MvProjectSettingsBase<T>> : BaseState() {
        abstract fun copy(): T
    }

    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.PROPERTY)
    protected annotation class AffectsMoveProjectsMetadata

    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.PROPERTY)
    protected annotation class AffectsHighlighting

    fun modify(modifySettings: (T) -> Unit) {
        val oldState = this.state.copy()
        // assigns new values to `this.state`
        val newState = this.state.also(modifySettings)

        val event = createSettingsChangedEvent(oldState, newState)
        notifySettingsChanged(event)
    }

    companion object {
        val MOVE_SETTINGS_TOPIC: Topic<MoveSettingsListener> = Topic.create(
            "move settings changes",
            MoveSettingsListener::class.java,
        )
    }

    interface MoveSettingsListener {
        fun <T : MvProjectSettingsBase<T>> settingsChanged(e: SettingsChangedEventBase<T>)
    }

    protected abstract fun createSettingsChangedEvent(oldEvent: T, newEvent: T): SettingsChangedEventBase<T>

    protected open fun notifySettingsChanged(event: SettingsChangedEventBase<T>) {
        project.messageBus.syncPublisher(MOVE_SETTINGS_TOPIC).settingsChanged(event)

        if (event.affectsHighlighting) {
            DaemonCodeAnalyzer.getInstance(project).settingsChanged()
        }
    }

    abstract class SettingsChangedEventBase<T : MvProjectSettingsBase<T>>(val oldState: T, val newState: T) {
        private val moveProjectsMetadataAffectingProps: List<KProperty1<T, *>> =
            oldState.javaClass.kotlin.memberProperties.filter { it.findAnnotation<AffectsMoveProjectsMetadata>() != null }

        private val highlightingAffectingProps: List<KProperty1<T, *>> =
            oldState.javaClass.kotlin.memberProperties.filter { it.findAnnotation<AffectsHighlighting>() != null }

        val affectsMoveProjectsMetadata: Boolean
            get() = moveProjectsMetadataAffectingProps.any(::isChanged)

        val affectsHighlighting: Boolean
            get() = highlightingAffectingProps.any(::isChanged)

        private fun isChanged(prop: KProperty1<T, *>): Boolean = prop.get(oldState) != prop.get(newState)
    }
}
