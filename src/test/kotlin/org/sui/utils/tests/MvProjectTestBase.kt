/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.utils.tests

import com.intellij.openapi.application.runWriteAction
import com.intellij.ide.bookmarks.BookmarkManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiManager
import com.intellij.openapi.util.Disposer
import com.intellij.testFramework.HeavyPlatformTestCase
import com.intellij.testFramework.PsiTestUtil
import com.intellij.testFramework.fixtures.CodeInsightTestFixture
import com.intellij.testFramework.fixtures.IdeaTestFixtureFactory
import org.sui.cli.moveProjectsService
import org.sui.openapiext.fullyRefreshDirectory

/**
 * Base class for project-level tests that require full project setup.
 * Use this when you need to test functionality that requires a real project structure
 * with multiple files and directories.
 */
abstract class MvProjectTestBase : HeavyPlatformTestCase() {

    protected lateinit var myFixture: CodeInsightTestFixture
    protected lateinit var rootDirectory: VirtualFile
    protected var lastTestProject: TestProject? = null

    override fun setUp() {
        super.setUp()

        val factory = IdeaTestFixtureFactory.getFixtureFactory()
        val projectBuilder = factory.createFixtureBuilder(name)
        myFixture = factory.createCodeInsightFixture(projectBuilder.fixture)
        myFixture.setUp()

        rootDirectory = myFixture.tempDirFixture.getFile(".")!!
        // Ensure temp directory is a real module content root so project-scoped services
        // (like MoveProjectsService) can discover Move.toml files via Project.contentRoots.
        try {
            PsiTestUtil.addContentRoot(myFixture.module, rootDirectory)
        } catch (_: IllegalArgumentException) {
            // In some platform versions / fixture setups the temp dir is already a content root.
        }

        handleCompilerV2Annotations(project)
        handleNamedAddressAnnotations(project)
    }

    override fun tearDown() {
        try {
            // Workaround for occasional document listener leaks in headless tests.
            // BookmarkManager registers document listeners and may outlive the fixture teardown checks.
            runCatching {
                (BookmarkManager.getInstance(project) as? com.intellij.openapi.Disposable)?.let { Disposer.dispose(it) }
            }
            myFixture.tearDown()
        } catch (e: Throwable) {
            addSuppressedException(e)
        } finally {
            super.tearDown()
        }
    }

    protected fun testProject(code: String): TestProject {
        val fileTree = fileTreeFromText(code)
        val testProject = fileTree.create(project, rootDirectory)
        lastTestProject = testProject
        fullyRefreshDirectory(rootDirectory)
        // Ensure Move projects model is initialized for project-level tests
        project.moveProjectsService.scheduleProjectsRefreshSync("testProject(code)")
        // Avoid auto-opening editors in project-level tests (can cause listener leaks in headless).
        return testProject
    }

    protected fun testProject(builder: FileTreeBuilder.() -> Unit): TestProject {
        val fileTree = fileTree(builder)
        val testProject = fileTree.create(project, rootDirectory)
        lastTestProject = testProject
        fullyRefreshDirectory(rootDirectory)
        // Ensure Move projects model is initialized for project-level tests
        project.moveProjectsService.scheduleProjectsRefreshSync("testProject(builder)")
        // Avoid auto-opening editors in project-level tests (can cause listener leaks in headless).
        return testProject
    }

    private fun configureByFileWithCaret(testProject: TestProject) {
        try {
            val filePath = testProject.fileWithCaret
            val vFile = testProject.rootDirectory.findFileByRelativePath(filePath)
            if (vFile != null) {
                myFixture.configureFromExistingVirtualFile(vFile)
            }
        } catch (e: Exception) {
            // No file with caret marker, which is fine
        }
    }

    protected fun findPsiFile(path: String): PsiFile? {
        val vFile = rootDirectory.findFileByRelativePath(path) ?: return null
        return PsiManager.getInstance(project).findFile(vFile)
    }

    protected fun findPsiDirectory(path: String): PsiDirectory? {
        val vFile = rootDirectory.findFileByRelativePath(path) ?: return null
        return PsiManager.getInstance(project).findDirectory(vFile)
    }
}

/**
 * Extension function to configure the test fixture from a file with a caret marker.
 */
fun CodeInsightTestFixture.configureFromFileWithCaret(testProject: TestProject) {
    val filePath = testProject.fileWithCaret
    val vFile = testProject.rootDirectory.findFileByRelativePath(filePath)
        ?: error("Cannot find file: $filePath")
    configureFromExistingVirtualFile(vFile)
}
