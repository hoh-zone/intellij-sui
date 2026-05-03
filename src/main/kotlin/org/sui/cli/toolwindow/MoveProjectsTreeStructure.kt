package org.sui.cli.toolwindow

import com.intellij.openapi.Disposable
import com.intellij.openapi.application.ReadAction
import com.intellij.ui.tree.AsyncTreeModel
import com.intellij.ui.tree.StructureTreeModel
import com.intellij.ui.treeStructure.CachingSimpleNode
import com.intellij.ui.treeStructure.SimpleNode
import com.intellij.ui.treeStructure.SimpleTreeStructure
import org.sui.cli.MovePackage
import org.sui.cli.MoveProject
import org.sui.ide.MoveIcons
import org.sui.lang.core.psi.MvFunction
import org.sui.lang.core.psi.MvModule
import org.sui.lang.core.psi.ext.entryFunctions
import org.sui.lang.core.psi.ext.hasTestAttr
import org.sui.lang.core.psi.ext.hasTestOnlyAttr
import org.sui.lang.core.psi.ext.viewFunctions
import org.sui.stdext.iterateMoveFiles

class MoveProjectsTreeStructure(
    tree: MoveProjectsTree,
    parentDisposable: Disposable,
    private var moveProjects: List<MoveProject> = emptyList()
) : SimpleTreeStructure() {

    private val treeModel = StructureTreeModel(this, parentDisposable)
    private var root = MoveSimpleNode.Root(moveProjects)

    init {
        tree.model = AsyncTreeModel(treeModel, parentDisposable)
    }

    override fun getRootElement() = root

    fun updateMoveProjects(moveProjects: List<MoveProject>) {
        this.moveProjects = moveProjects
        this.root = MoveSimpleNode.Root(moveProjects)
        treeModel.invalidateAsync()
    }

    sealed class MoveSimpleNode(parent: SimpleNode?) : CachingSimpleNode(parent) {
        class Root(private val moveProjects: List<MoveProject>) : MoveSimpleNode(null) {
            override fun buildChildren(): Array<SimpleNode> =
                moveProjects.map { TreeProject(it, this) }.sortedBy { it.name }.toTypedArray()

            override fun getName() = ""
        }

        open class Package(val movePackage: MovePackage, parent: SimpleNode) : MoveSimpleNode(parent) {
            init {
                icon = MoveIcons.SUI_LOGO
            }

            override fun buildChildren(): Array<SimpleNode> {
                val modules = mutableListOf<MvModule>()
                val scriptFunctions = mutableListOf<MvFunction>()
                val viewFunctions = mutableListOf<MvFunction>()
                for (folder in movePackage.moveFolders()) {
                    folder.iterateMoveFiles(movePackage.project) {
                        for (module in it.modules()) {
                            if (!module.hasTestOnlyAttr) modules.add(module)
                            scriptFunctions.addAll(
                                module.entryFunctions()
                                    .filter { fn -> !fn.hasTestOnlyAttr && !fn.hasTestAttr })
                            viewFunctions.addAll(
                                module.viewFunctions()
                                    .filter { fn -> !fn.hasTestOnlyAttr && !fn.hasTestAttr })
                        }
                        true
                    }
                }
                return listOfNotNull(
                    modules.takeIf { it.isNotEmpty() }?.let { Modules(it, this) },
                    scriptFunctions.takeIf { it.isNotEmpty() }?.let { Entrypoints(it, this) },
                    viewFunctions.takeIf { it.isNotEmpty() }?.let { Views(it, this) },
                ).toTypedArray()
            }

            override fun getName(): String = movePackage.packageName
        }

        class TreeProject(val moveProject: MoveProject, parent: SimpleNode) :
            Package(moveProject.currentPackage, parent) {

            override fun buildChildren(): Array<SimpleNode> {
                val dependencyPackages = moveProject.dependencies.map { it.first }
                return arrayOf(
                    *super.buildChildren(),
                    DependencyPackages(dependencyPackages, this),
                )
            }

            override fun getName(): String = moveProject.currentPackage.packageName
        }

        class DependencyPackages(val packages: List<MovePackage>, parent: SimpleNode) : MoveSimpleNode(parent) {
            override fun buildChildren(): Array<SimpleNode> {
                return packages.map { Package(it, this) }.toTypedArray()
            }

            override fun getName(): String = "Dependencies"
        }

        class Modules(val modules: List<MvModule>, parent: SimpleNode) : MoveSimpleNode(parent) {
            override fun buildChildren(): Array<SimpleNode> =
                modules.map { Module(it, this) }.toTypedArray()

            override fun getName(): String = "Modules"
        }

        class Module(val module: MvModule, parent: SimpleNode) : MoveSimpleNode(parent) {
            init {
                icon = MoveIcons.MODULE
            }

            override fun buildChildren(): Array<SimpleNode> = emptyArray()
            override fun getName(): String =
                ReadAction.compute<String, RuntimeException> { module.qualName?.editorText() ?: "null" }
        }

        class Entrypoints(val functions: List<MvFunction>, parent: SimpleNode) : MoveSimpleNode(parent) {
            override fun buildChildren(): Array<SimpleNode> {
                return functions.map { Entrypoint(it, this) }.toTypedArray()
            }

            override fun getName(): String = "Entrypoints"
        }

        class Entrypoint(val function: MvFunction, parent: SimpleNode) : MoveSimpleNode(parent) {
            init {
                icon = MoveIcons.FUNCTION
            }

            override fun buildChildren(): Array<SimpleNode> = emptyArray()
            override fun getName(): String =
                ReadAction.compute<String, RuntimeException> { function.qualName?.editorText() ?: "null" }
        }

        class Views(val functions: List<MvFunction>, parent: SimpleNode) : MoveSimpleNode(parent) {
            override fun buildChildren(): Array<SimpleNode> {
                return functions.map { View(it, this) }.toTypedArray()
            }

            override fun getName(): String = "Views"
        }

        class View(val function: MvFunction, parent: SimpleNode) : MoveSimpleNode(parent) {
            init {
                icon = MoveIcons.FUNCTION
            }

            override fun buildChildren(): Array<SimpleNode> = emptyArray()
            override fun getName(): String =
                ReadAction.compute<String, RuntimeException> { function.qualName?.editorText() ?: "null" }
        }
    }
}
