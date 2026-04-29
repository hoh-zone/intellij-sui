package org.sui.lang.core.stubs

import com.intellij.psi.stubs.IStubElementType
import com.intellij.psi.stubs.IndexSink
import com.intellij.psi.stubs.StubElement
import org.sui.lang.MoveLanguage
import org.sui.lang.core.psi.MvElement

abstract class MvStubElementType<StubT : StubElement<*>, PsiT : MvElement>(
    debugName: String,
) : IStubElementType<StubT, PsiT>(debugName, MoveLanguage) {

    final override fun getExternalId(): String = "move.${super.toString()}"

    override fun indexStub(stub: StubT, sink: IndexSink) {}
}
