package org.sui.cli.module

import com.intellij.openapi.vfs.VirtualFile
import com.intellij.projectImport.ProjectOpenProcessor
import org.sui.ide.newProject.MoveLangProjectOpenProcessor
import org.sui.utils.tests.MvProjectTestBase

class MoveLangTreeProjectOpenProcessorTest : MvProjectTestBase() {
    fun `test open project via directory with Move toml file`() {
        val testProject = testProject {
            dir("package") {
                namedMoveToml("MyPackage")
                sources {
                    main(
                        """
                    module 0x1::M {/*caret*/}    
                    """
                    )
                }
            }
        }
        val packageDir = testProject.file("package")
        checkFileCanBeOpenedAsProject(packageDir)
    }

    private fun checkFileCanBeOpenedAsProject(file: VirtualFile) {
        val processor =
            ProjectOpenProcessor.EXTENSION_POINT_NAME.extensionList.find { it is MoveLangProjectOpenProcessor }
                ?: error("MoveProjectOpenProcessor is not registered in plugin.xml")
        check(processor.canOpenProject(file)) {
            "Move project cannot be opened via `$file`"
        }
    }
}
