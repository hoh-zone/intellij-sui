package org.sui.lang.core.stubs

import com.intellij.psi.stubs.IndexSink
import org.sui.lang.index.MvModuleIndex
import org.sui.lang.index.MvNamedElementIndex

fun IndexSink.indexModuleStub(stub: MvModuleStub) {
    stub.name?.let {
        occurrence(MvModuleIndex.KEY, it)
    }
    indexNamedStub(stub)
}

fun IndexSink.indexFunctionStub(stub: MvFunctionStub) {
    indexNamedStub(stub)
}

fun IndexSink.indexStructStub(stub: MvStructStub) {
    indexNamedStub(stub)
}

fun IndexSink.indexEnumStub(stub: MvEnumStub) {
    indexNamedStub(stub)
}

fun IndexSink.indexEnumVariantStub(stub: MvEnumVariantStub) {
    indexNamedStub(stub)
}

fun IndexSink.indexConstStub(stub: MvConstStub) {
    indexNamedStub(stub)
}

private fun IndexSink.indexNamedStub(stub: MvNamedStub) {
    stub.name?.let {
        occurrence(MvNamedElementIndex.KEY, it)
    }
}
