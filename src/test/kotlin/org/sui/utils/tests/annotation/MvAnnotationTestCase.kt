package org.sui.utils.tests.annotation

import org.intellij.lang.annotations.Language
import org.sui.utils.tests.MvTestBase

abstract class MvAnnotationTestCase : MvTestBase() {
    protected lateinit var annotationFixture: MvAnnotationTestFixture

    override fun setUp() {
        super.setUp()
        annotationFixture = createAnnotationFixture()
        annotationFixture.setUp()
    }

    override fun tearDown() {
        annotationFixture.tearDown()
        super.tearDown()
    }

    protected abstract fun createAnnotationFixture(): MvAnnotationTestFixture

    fun checkHighlighting(@Language("Move") text: String) = annotationFixture.checkHighlighting(text)
    fun checkErrors(@Language("Move") text: String) = annotationFixture.checkErrors(text)
    fun checkWarnings(@Language("Move") text: String) = annotationFixture.checkWarnings(text)

    fun checkMoveTomlWarnings(@Language("TOML") text: String) =
        annotationFixture.check(text,
            configure = { tomlText ->
                annotationFixture.codeInsightFixture
                    .configureByText("Move.toml", tomlText)
            })

    protected fun checkByText(
        @Language("Move") text: String,
        checkWarn: Boolean = true,
        checkInfo: Boolean = false,
        checkWeakWarn: Boolean = false,
        ignoreExtraHighlighting: Boolean = false,
    ) = annotationFixture.checkByText(
        text,
        checkWarn,
        checkInfo,
        checkWeakWarn,
        ignoreExtraHighlighting,
    )
}
