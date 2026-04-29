package org.sui.lang.index

import com.intellij.openapi.project.Project
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.stubs.StringStubIndexExtension
import com.intellij.psi.stubs.StubIndex
import com.intellij.psi.stubs.StubIndexKey
import org.sui.lang.core.psi.MvNamedElement
import org.sui.lang.core.stubs.impl.MvFileStub
import org.sui.openapiext.checkCommitIsNotInProgress

class MvNamedElementIndex : StringStubIndexExtension<MvNamedElement>() {
    override fun getVersion(): Int = MvFileStub.Type.stubVersion
    override fun getKey(): StubIndexKey<String, MvNamedElement> = KEY

    companion object {
        val KEY: StubIndexKey<String, MvNamedElement> =
            StubIndexKey.createIndexKey("org.sui.index.NamedElementIndex")

        fun getElementsByName(
            project: Project,
            name: String,
            scope: GlobalSearchScope,
        ): Collection<MvNamedElement> {
            checkCommitIsNotInProgress(project)
            return StubIndex.getElements(KEY, name, project, scope, MvNamedElement::class.java)
        }
    }
}
