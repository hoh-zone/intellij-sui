/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.utils.tests.annotation

import com.intellij.codeInsight.daemon.impl.SeveritiesProvider
import com.intellij.codeInspection.InspectionProfileEntry
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.intellij.testFramework.InspectionTestUtil
import com.intellij.testFramework.PsiTestUtil
import com.intellij.testFramework.fixtures.CodeInsightTestFixture
import com.intellij.testFramework.fixtures.impl.BaseFixture
import junit.framework.TestCase
import org.intellij.lang.annotations.Language
import org.sui.ide.annotator.MvAnnotatorBase
import kotlin.reflect.KClass

class MvAnnotationTestFixture(
    private val testCase: TestCase,
    val codeInsightFixture: CodeInsightTestFixture,
    private var annotatorClasses: List<KClass<out MvAnnotatorBase>> = emptyList(),
    private var inspectionClasses: List<KClass<out InspectionProfileEntry>> = emptyList(),
) : BaseFixture() {
    val project: Project get() = codeInsightFixture.project
    lateinit var enabledInspections: List<InspectionProfileEntry>

    override fun setUp() {
        super.setUp()

//        val inspectionToEnable = testCase.findAnnotationInstance<WithEnabledInspections>()?.inspectionClass
//        if (inspectionToEnable != null) {
//            inspectionClasses = inspectionClasses.withAdded(inspectionToEnable)
//        }

        annotatorClasses.forEach {
            MvAnnotatorBase.enableAnnotator(
                it.java,
                testRootDisposable
            )
        }
        enabledInspections = InspectionTestUtil.instantiateTools(inspectionClasses.map { it.java })
        codeInsightFixture.enableInspections(*enabledInspections.toTypedArray())
    }

    private fun replaceCaretMarker(text: String) = text.replace("/*caret*/", "<caret>")

    fun checkHighlighting(text: String) = checkByText(
        text,
        checkWarn = false,
        checkWeakWarn = false,
        checkInfo = false,
        ignoreExtraHighlighting = true
    )

    fun checkWarnings(text: String) =
        checkByText(text, checkWarn = true, checkWeakWarn = true, checkInfo = false)

    fun checkErrors(text: String) =
        checkByText(text, checkWarn = false, checkWeakWarn = false, checkInfo = false)

    private fun configureByText(text: String): PsiFile {
        val psiFile = codeInsightFixture.configureByText("main.move", replaceCaretMarker(text.trimIndent()))
        // build indexes
//        IndexingTestUtil.waitUntilIndexesAreReady(codeInsightFixture.project)
        return psiFile
    }

    fun checkByText(
        text: String,
        checkWarn: Boolean = true,
        checkInfo: Boolean = false,
        checkWeakWarn: Boolean = false,
        ignoreExtraHighlighting: Boolean = false,
    ) = check(
        text,
        checkWarn = checkWarn,
        checkInfo = checkInfo,
        checkWeakWarn = checkWeakWarn,
        ignoreExtraHighlighting = ignoreExtraHighlighting,
        configure = this::configureByText
    )

    fun check(
        text: String,
        checkWarn: Boolean = true,
        checkInfo: Boolean = false,
        checkWeakWarn: Boolean = false,
        ignoreExtraHighlighting: Boolean = false,
        configure: (String) -> Unit
    ) {
        configure(text)
        codeInsightFixture.checkHighlighting(checkWarn, checkInfo, checkWeakWarn, ignoreExtraHighlighting)
    }

    fun registerSeverities(severities: List<HighlightSeverity>) {
        val testSeverityProvider = TestSeverityProvider(severities)
        SeveritiesProvider.EP_NAME.point.registerExtension(testSeverityProvider, testRootDisposable)
    }

    fun checkFixIsUnavailable(
        fixName: String,
        @Language("Move") text: String,
        checkWarn: Boolean,
        checkInfo: Boolean,
        checkWeakWarn: Boolean,
        ignoreExtraHighlighting: Boolean,
    ) {
        check(text, checkWarn, checkInfo, checkWeakWarn, ignoreExtraHighlighting, this::configureByText)
        check(
            codeInsightFixture.filterAvailableIntentions(fixName).isEmpty()
        ) {
            "Fix $fixName should not be possible to apply."
        }
    }

    fun applyQuickFix(name: String) {
        val action = codeInsightFixture.findSingleIntention(name)
        codeInsightFixture.launchAction(action)
    }

    fun checkFixByText(
        fixName: String,
        before: String,
        after: String,
        checkWarn: Boolean = true,
        checkInfo: Boolean = false,
        checkWeakWarn: Boolean = false,
    ) {
        check(before.contains("/*caret*/")) {
            "No /*caret*/ comment, add it to the place where fix is expected"
        }
        val file = configureByText(before)

        codeInsightFixture.checkHighlighting(checkWarn, checkInfo, checkWeakWarn)
        applyQuickFix(fixName)
        codeInsightFixture.checkResult(replaceCaretMarker(after.trimIndent()))
        PsiTestUtil.checkFileStructure(file)
    }

    fun checkAvailableFixes(
        fixPrefix: String,
        fixes: List<String>,
        @Language("Move") code: String,
        checkWarn: Boolean = true,
        checkInfo: Boolean = false,
        checkWeakWarn: Boolean = false,
    ) {
        check(code.contains("/*caret*/")) {
            "No /*caret*/ comment, add it to the place where fix is expected"
        }
        configureByText(code)
        codeInsightFixture.checkHighlighting(checkWarn, checkInfo, checkWeakWarn)

        val fixNames = codeInsightFixture.filterAvailableIntentions(fixPrefix).map { it.text }
        check(fixes == fixNames) {
            "Cannot find fixes ${fixNamesToString(fixes)} in ${fixNamesToString(fixNames)}"
        }
    }

    companion object {
        private fun fixNamesToString(intentionNames: List<String>): String {
            return intentionNames.joinToString(
                prefix = "[",
                postfix = "]",
                transform = { name -> "\"$name\"" }
            )
        }
    }
}
