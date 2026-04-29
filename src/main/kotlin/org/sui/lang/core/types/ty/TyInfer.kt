package org.sui.lang.core.types.ty

import org.sui.ide.presentation.tyToString
import org.sui.lang.core.types.infer.HAS_TY_INFER_MASK

sealed class TyInfer : Ty(HAS_TY_INFER_MASK) {
    // Note these classes must NOT be `data` classes and must provide equality by identity
    class TyVar(val origin: TyTypeParameter? = null) : TyInfer() {
        override fun abilities(): Set<Ability> = origin?.abilities() ?: Ability.none()
    }

    class IntVar : TyInfer() {
        override fun abilities(): Set<Ability> = Ability.all()
    }

    override fun toString(): String = tyToString(this)
}
