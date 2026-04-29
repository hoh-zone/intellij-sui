package org.sui.lang.core.types.ty

import org.sui.ide.presentation.tyToString
import org.sui.lang.core.psi.MvFunctionLike
import org.sui.lang.core.types.infer.*

data class TyFunction(
    override val item: MvFunctionLike,
    override val substitution: Substitution,
    override val paramTypes: List<Ty>,
    override val retType: Ty,
) : TyCallable, GenericTy(
    item,
    substitution,
    mergeFlags(paramTypes) or retType.flags
) {

    override fun innerFoldWith(folder: TypeFolder): Ty {
        return TyFunction(
            item,
            substitution.foldValues(folder),
            paramTypes.map { it.foldWith(folder) },
            retType.foldWith(folder),
        )
    }

    override fun innerVisitWith(visitor: TypeVisitor): Boolean =
        substitution.visitValues(visitor)
                || paramTypes.any { it.visitWith(visitor) }
                || retType.visitWith(visitor)

    override fun abilities(): Set<Ability> = Ability.all()

    override fun toString(): String = tyToString(this)
}
