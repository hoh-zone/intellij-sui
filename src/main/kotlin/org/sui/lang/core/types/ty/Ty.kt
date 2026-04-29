package org.sui.lang.core.types.ty

import org.sui.lang.core.psi.MvTypeParametersOwner
import org.sui.lang.core.types.infer.*
import org.sui.lang.core.types.infer.HasTypeFlagVisitor.Companion.NEEDS_SUBST

enum class Ability {
    DROP, COPY, STORE, KEY;

    fun requires(): Ability {
        return when (this) {
            DROP -> DROP
            COPY -> COPY
            KEY, STORE -> STORE
        }
    }

    override fun toString(): String {
        return super.toString().lowercase()
    }

    companion object {
        fun none(): Set<Ability> = setOf()
        fun all(): Set<Ability> = setOf(DROP, COPY, STORE, KEY)
    }
}

val TypeFoldable<*>.needsSubst get(): Boolean = visitWith(NEEDS_SUBST)

abstract class Ty(val flags: TypeFlags = 0) : TypeFoldable<Ty> {

    override fun foldWith(folder: TypeFolder): Ty = folder(this)

    override fun innerFoldWith(folder: TypeFolder): Ty = this

    override fun visitWith(visitor: TypeVisitor): Boolean = visitor(this)

    override fun innerVisitWith(visitor: TypeVisitor): Boolean = false

    /**
     * User visible string representation of a type
     */
    abstract override fun toString(): String

    abstract fun abilities(): Set<Ability>
}


abstract class GenericTy(
    open val item: MvTypeParametersOwner,
    open val substitution: Substitution,
    flags: TypeFlags,
) : Ty(mergeFlags(substitution.types) or flags)
