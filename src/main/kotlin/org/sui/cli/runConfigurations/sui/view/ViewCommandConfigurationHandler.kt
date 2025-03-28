package org.sui.cli.runConfigurations.sui.view

import org.sui.cli.MoveProject
import org.sui.cli.runConfigurations.sui.CommandConfigurationHandler
import org.sui.lang.core.psi.MvFunction
import org.sui.lang.core.psi.MvFunctionParameter
import org.sui.lang.core.psi.ext.hasTestAttr
import org.sui.lang.core.psi.ext.isView
import org.sui.lang.core.psi.parameters
import org.sui.lang.index.MvViewFunctionIndex

class ViewCommandConfigurationHandler : CommandConfigurationHandler() {

    override val subCommand: String get() = "move view"

    override fun configurationName(functionId: String): String = "View $functionId"

    override fun functionPredicate(function: MvFunction): Boolean = function.isView && !function.hasTestAttr

    override fun getFunctionItem(moveProject: MoveProject, functionQualName: String): MvFunction? {
        return getViewFunction(moveProject, functionQualName)
    }

    override fun getFunctionByCmdName(moveProject: MoveProject, functionCmdName: String): MvFunction? {
        return getViewFunction(moveProject, functionCmdName)
    }

    override fun getFunctionParameters(function: MvFunction): List<MvFunctionParameter> {
        return function.parameters
    }

    override fun getFunctionCompletionVariants(moveProject: MoveProject): Collection<String> {
        return MvViewFunctionIndex.getAllKeys(moveProject.project)
//        val completionVariants = mutableListOf<String>()
//        for (key in keys) {
//            val functions =
//                StubIndex.getElements(MvEntryFunctionIndex.KEY, key, moveProject.project, null, MvFunction::class.java)
//            for (function in functions) {
//                if (function.moveProject != moveProject) continue
//                val qualName = function.qualName?.editorText() ?: continue
//                completionVariants.add(qualName)
//            }
//        }
//        return completionVariants
    }

    companion object {
        fun getViewFunction(moveProject: MoveProject, functionId: String): MvFunction? {
            return MvViewFunctionIndex.getFunctionByFunctionId(
                moveProject,
                functionId,
            )
        }
    }
}
