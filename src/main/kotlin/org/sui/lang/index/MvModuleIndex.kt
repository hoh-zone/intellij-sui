package org.sui.lang.index

import com.intellij.openapi.project.Project
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.stubs.StringStubIndexExtension
import com.intellij.psi.stubs.StubIndex
import com.intellij.psi.stubs.StubIndexKey
import org.sui.lang.core.psi.MvModule
import org.sui.lang.core.stubs.impl.MvFileStub
import org.sui.openapiext.checkCommitIsNotInProgress

class MvModuleIndex : StringStubIndexExtension<MvModule>() {
    override fun getVersion(): Int = MvFileStub.Type.stubVersion
    override fun getKey(): StubIndexKey<String, MvModule> = KEY

    companion object {
        val KEY: StubIndexKey<String, MvModule> =
            StubIndexKey.createIndexKey("org.sui.index.ModuleIndex")

        fun getAllModuleNames(project: Project): Collection<String> {
            checkCommitIsNotInProgress(project)
            return StubIndex.getInstance().getAllKeys(KEY, project)
        }

        fun getModulesByName(
            project: Project,
            name: String,
            scope: GlobalSearchScope,
        ): Collection<MvModule> {
            checkCommitIsNotInProgress(project)
            return StubIndex.getElements(KEY, name, project, scope, MvModule::class.java)
        }
    }
}