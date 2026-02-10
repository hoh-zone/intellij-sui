package org.sui.lang.core.psi.ext

import com.intellij.openapi.util.Key
import com.intellij.psi.util.CachedValue
import com.intellij.psi.util.CachedValueProvider
import com.intellij.psi.util.PsiModificationTracker
import org.sui.lang.core.psi.MvElement
import org.sui.lang.core.psi.MvItemSpecRef
import org.sui.lang.core.psi.MvSpecCodeBlock
import org.sui.lang.core.psi.NamedItemScope
import org.sui.lang.core.psi.NamedItemScope.MAIN
import org.sui.lang.core.psi.NamedItemScope.VERIFY
import org.sui.lang.core.psi.itemScopeFromAttributes
import org.sui.utils.cache
import org.sui.utils.cacheManager
import org.sui.utils.cacheResult

private val USAGE_SCOPE_KEY: Key<CachedValue<NamedItemScope>> = Key.create("USAGE_SCOPE_KEY")

private class UsageScopeProvider(val scopeElement: MvElement) : CachedValueProvider<NamedItemScope> {
    override fun compute(): CachedValueProvider.Result<NamedItemScope> {
        var scope = MAIN
        for (ancestor in scopeElement.ancestorsOfType<MvDocAndAttributeOwner>()) {
            if (scopeElement is MvSpecCodeBlock || scopeElement is MvItemSpecRef) {
                scope = VERIFY
                break
            }
            val attributeScope = ancestor.itemScopeFromAttributes()
            if (attributeScope != null) {
                scope = attributeScope
                break
            }
        }
        return scopeElement.cacheResult(scope, listOf(PsiModificationTracker.MODIFICATION_COUNT))
    }
}

val MvElement.usageScope: NamedItemScope
    get() = project.cacheManager.cache(this, USAGE_SCOPE_KEY, UsageScopeProvider(this))
