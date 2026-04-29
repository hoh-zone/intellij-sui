package org.sui.lang.core.stubs.impl

import com.intellij.psi.PsiFile
import com.intellij.psi.StubBuilder
import com.intellij.psi.impl.source.tree.TreeUtil
import com.intellij.psi.stubs.DefaultStubBuilder
import com.intellij.psi.stubs.PsiFileStubImpl
import com.intellij.psi.stubs.StubElement
import com.intellij.psi.stubs.StubInputStream
import com.intellij.psi.tree.IStubFileElementType
import org.sui.lang.MoveFile
import org.sui.lang.MoveLanguage
import org.sui.lang.MoveParserDefinition

class MvFileStub(file: MoveFile?) : PsiFileStubImpl<MoveFile>(file) {

    override fun getType() = Type

    object Type : IStubFileElementType<MvFileStub>(MoveLanguage) {
        private const val STUB_VERSION = 29

        // Bump this number if Stub structure changes
        override fun getStubVersion(): Int = MoveParserDefinition.PARSER_VERSION + STUB_VERSION

        override fun getBuilder(): StubBuilder = object : DefaultStubBuilder() {
            override fun createStubForFile(file: PsiFile): StubElement<*> {
                TreeUtil.ensureParsed(file.node) // profiler hint
                check(file is MoveFile)
                return MvFileStub(file)
            }
        }

        override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>?) = MvFileStub(null)

        override fun getExternalId(): String = "Move.file"
    }
}
