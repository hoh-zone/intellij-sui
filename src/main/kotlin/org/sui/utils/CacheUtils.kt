package org.sui.utils

import com.intellij.injected.editor.VirtualFileWindow
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Key
import com.intellij.openapi.util.UserDataHolder
import com.intellij.psi.util.CachedValue
import com.intellij.psi.util.CachedValueProvider
import com.intellij.psi.util.CachedValuesManager
import com.intellij.psi.util.PsiModificationTracker
import org.sui.lang.core.psi.MvElement

val Project.cacheManager: CachedValuesManager get() = CachedValuesManager.getManager(this)

fun <T> CachedValuesManager.cache(
    dataHolder: UserDataHolder,
    key: Key<CachedValue<T>>,
    provider: CachedValueProvider<T>
): T {
    return getCachedValue(dataHolder, key, provider, false)
}

fun <T> MvElement.psiCacheResult(value: T): CachedValueProvider.Result<T> =
    this.cacheResult(value, listOf(PsiModificationTracker.MODIFICATION_COUNT))

fun <T> MvElement.cacheResult(value: T, dependencies: List<Any>): CachedValueProvider.Result<T> {
    return when {
        // Injected PSI don't have its own event system, so we have to invalidate the cached value
        // on any PSI change.
        containingFile.virtualFile is VirtualFileWindow -> {
            CachedValueProvider.Result.create(
                value,
                PsiModificationTracker.MODIFICATION_COUNT
            )
        }

        else -> CachedValueProvider.Result.create(value, dependencies)
    }
}

