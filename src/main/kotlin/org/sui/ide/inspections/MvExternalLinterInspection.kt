/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.ide.inspections

import com.intellij.codeInsight.daemon.impl.HighlightInfo
import com.intellij.codeInspection.*
import com.intellij.codeInspection.ex.GlobalInspectionContextEx
import com.intellij.codeInspection.ex.GlobalInspectionContextUtil
import com.intellij.codeInspection.reference.RefElement
import com.intellij.openapi.Disposable
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.util.Key
import com.intellij.profile.codeInspection.InspectionProjectProfileManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.util.containers.ContainerUtil
import org.sui.cli.MoveProject
import org.sui.cli.moveProjectsService
import org.sui.cli.runConfigurations.aptos.AptosCompileArgs
import org.sui.cli.runConfigurations.aptos.workingDirectory
import org.sui.cli.settings.getAptosCli
import org.sui.ide.annotator.RsExternalLinterResult
import org.sui.ide.annotator.RsExternalLinterUtils
import org.sui.ide.annotator.addHighlightsForFile
import org.sui.ide.annotator.createDisposableOnAnyPsiChange
import org.sui.lang.MoveFile
import org.sui.lang.core.psi.ext.ancestorOrSelf
import org.sui.lang.moveProject
import org.sui.openapiext.rootPluginDisposable

class MvExternalLinterInspection : GlobalSimpleInspectionTool() {

    // 在全局上下文中存储已分析的文件集合
    override fun inspectionStarted(
        manager: InspectionManager,
        globalContext: GlobalInspectionContext,
        problemDescriptionsProcessor: ProblemDescriptionsProcessor
    ) {
        globalContext.putUserData(ANALYZED_FILES, ContainerUtil.newConcurrentSet())
    }

    // 检查文件是否为MoveFile类型，如果是，则将其添加到已分析的文件集合中
    override fun checkFile(
        file: PsiFile,
        manager: InspectionManager,
        problemsHolder: ProblemsHolder,
        globalContext: GlobalInspectionContext,
        problemDescriptionsProcessor: ProblemDescriptionsProcessor
    ) {
//        if (file !is MoveFile || file.containingCrate.asNotFake?.origin != PackageOrigin.WORKSPACE) return
        if (file !is MoveFile) return
        val analyzedFiles = globalContext.getUserData(ANALYZED_FILES) ?: return
        analyzedFiles.add(file)
    }

    // 在检查结束时，对已分析的文件进行外部检查，并将结果添加到问题描述处理器中
    override fun inspectionFinished(
        manager: InspectionManager,
        globalContext: GlobalInspectionContext,
        problemDescriptionsProcessor: ProblemDescriptionsProcessor
    ) {
        if (globalContext !is GlobalInspectionContextEx) return
        val analyzedFiles = globalContext.getUserData(ANALYZED_FILES) ?: return

        val project = manager.project
        val currentProfile = InspectionProjectProfileManager.getInstance(project).currentProfile
        val toolWrapper = currentProfile.getInspectionTool(SHORT_NAME, project) ?: return

        while (true) {
            val anyPsiChangeDisposable = project.messageBus.createDisposableOnAnyPsiChange()
                .also { Disposer.register(project.rootPluginDisposable, it) }
            val moveProjects = run {
                val allProjects = project.moveProjectsService.allProjects
                if (allProjects.size == 1) {
                    setOf(allProjects.first())
                } else {
                    runReadAction {
                        analyzedFiles.mapNotNull { it.moveProject }.toSet()
                    }
                }
            }
            val futures = moveProjects.map {
                ApplicationManager.getApplication().executeOnPooledThread<RsExternalLinterResult?> {
                    checkProjectLazily(it, anyPsiChangeDisposable)?.value
                }
            }
            val annotationResults = futures.mapNotNull { it.get() }

            val exit = runReadAction {
                ProgressManager.checkCanceled()
                if (anyPsiChangeDisposable.isDisposed) return@runReadAction false
                if (annotationResults.size < moveProjects.size) return@runReadAction true
                for (annotationResult in annotationResults) {
                    val problemDescriptors = getProblemDescriptors(analyzedFiles, annotationResult)
                    val presentation = globalContext.getPresentation(toolWrapper)
                    presentation.addProblemDescriptors(problemDescriptors, globalContext)
                }
                true
            }

            if (exit) break
        }
    }

    // 返回检查工具的显示名称
    override fun getDisplayName(): String = "External linter"

    // 返回检查工具的短名称
    override fun getShortName(): String = SHORT_NAME

    companion object {
        const val SHORT_NAME: String = "MvExternalLinter"

        // 用于存储已分析的文件的键
        private val ANALYZED_FILES: Key<MutableSet<MoveFile>> = Key.create("ANALYZED_FILES")

        // 懒惰地检查项目
        private fun checkProjectLazily(
            moveProject: MoveProject,
            disposable: Disposable
        ): Lazy<RsExternalLinterResult?>? = runReadAction {
            val project = moveProject.project
            val aptosCli = project.getAptosCli(disposable) ?: return@runReadAction null
            RsExternalLinterUtils.checkLazily(
                aptosCli,
                project,
                moveProject.workingDirectory,
                AptosCompileArgs.forMoveProject(moveProject)
            )
        }

        // 获取问题描述符
        private fun getProblemDescriptors(
            analyzedFiles: Set<MoveFile>,
            annotationResult: RsExternalLinterResult
        ): List<ProblemDescriptor> = buildList {
            for (file in analyzedFiles) {
                if (!file.isValid) continue
                val highlights = mutableListOf<HighlightInfo>()
                highlights.addHighlightsForFile(file, annotationResult)
                highlights.mapNotNull { ProblemDescriptorUtil.toProblemDescriptor(file, it) }.forEach(::add)
            }
        }

        // 添加问题描述符到问题描述处理器中
        private fun InspectionToolResultExporter.addProblemDescriptors(
            descriptors: List<ProblemDescriptor>,
            context: GlobalInspectionContext
        ) {
            if (descriptors.isEmpty()) return
            val problems = hashMapOf<RefElement, MutableList<ProblemDescriptor>>()

            for (descriptor in descriptors) {
                val element = descriptor.psiElement ?: continue
                val refElement = getProblemElement(element, context) ?: continue
                val elementProblems = problems.getOrPut(refElement) { mutableListOf() }
                elementProblems.add(descriptor)
            }

            for ((refElement, problemDescriptors) in problems) {
                val descriptions = problemDescriptors.toTypedArray<CommonProblemDescriptor>()
                addProblemElement(refElement, false, *descriptions)
            }
        }

        // 获取问题元素
        private fun getProblemElement(element: PsiElement, context: GlobalInspectionContext): RefElement? {
            val problemElement = element.ancestorOrSelf<MoveFile>()
            val refElement = context.refManager.getReference(problemElement)
            return if (refElement == null && problemElement != null) {
                GlobalInspectionContextUtil.retrieveRefElement(element, context)
            } else {
                refElement
            }
        }
    }
}
