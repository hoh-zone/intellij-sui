package org.sui.lang.core.psi

import org.sui.lang.core.psi.ext.*

enum class NamedItemScope {
    MAIN,
    TEST;

    val isTest get() = this == TEST

    fun shrinkScope(adjustmentScope: NamedItemScope): NamedItemScope {
        if (this == MAIN) {
            return adjustmentScope
        }
        return this
    }
}

fun MvDocAndAttributeOwner.itemScopeFromAttributes(): NamedItemScope? =
    when (this) {
        is MvFunction ->
            when {
                this.hasTestOnlyAttr || this.hasTestAttr -> NamedItemScope.TEST
                else ->
                    this.containingModule?.itemScopeFromAttributes() ?: NamedItemScope.MAIN
            }

        is MvStruct ->
            when {
                this.hasTestOnlyAttr -> NamedItemScope.TEST
                else -> this.module.itemScopeFromAttributes() ?: NamedItemScope.MAIN
            }

        else ->
            when {
                this.hasTestOnlyAttr -> NamedItemScope.TEST
                else -> null
            }
    }

