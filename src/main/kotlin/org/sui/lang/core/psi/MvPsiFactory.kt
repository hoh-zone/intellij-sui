package org.sui.lang.core.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFileFactory
import org.intellij.lang.annotations.Language
import org.sui.lang.MoveFile
import org.sui.lang.MoveFileType
import org.sui.lang.core.psi.ext.childOfType
import org.sui.lang.core.psi.ext.descendantOfTypeStrict

val Project.psiFactory get() = MvPsiFactory(this)

class MvPsiFactory(val project: Project) {
    fun fieldPatFull(fieldName: String, binding: String): MvPatFieldFull =
        createFromText("module 0x1::_M { fun m() { let S { $fieldName: $binding } = 1; }}")
            ?: error("Failed to create MvFieldPat")

    fun identifier(text: String): PsiElement =
        createFromText<MvModule>("module $text {}")?.nameIdentifier
            ?: error("Failed to create identifier: `$text`")

    fun functions(text: String, moduleName: String = "_Dummy"): List<MvFunction> {
        val dummyFile = PsiFileFactory.getInstance(project)
            .createFileFromText(
                "$moduleName.move",
                MoveFileType,
                "module 0x0::$moduleName { $text }"
            ) as MoveFile
        val functions = dummyFile.childOfType<MvModule>()?.functionList.orEmpty()
        return functions
    }

    inline fun <reified T : MvElement> createFromText(@Language("Sui Move") code: CharSequence): T? {
        val dummyFile = PsiFileFactory.getInstance(project)
            .createFileFromText(
                "DUMMY.move",
                MoveFileType,
                code
            ) as MoveFile
        return dummyFile.descendantOfTypeStrict()
    }
}
