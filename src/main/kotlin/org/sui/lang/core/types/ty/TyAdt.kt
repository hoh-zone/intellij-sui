package org.sui.lang.core.types.ty

import org.sui.ide.presentation.tyToString
import org.sui.lang.core.psi.ext.MvStructOrEnumItemElement
import org.sui.lang.core.psi.ext.abilities
import org.sui.lang.core.types.infer.*

data class TyAdt(
    override val item: MvStructOrEnumItemElement,
    override val substitution: Substitution,
    val typeArguments: List<Ty>,
): GenericTy(item, substitution, mergeFlags(typeArguments) or HAS_TY_ADT_MASK) {

    override fun abilities(): Set<Ability> = this.item.abilities

    override fun innerFoldWith(folder: TypeFolder): Ty {
        return TyAdt(
            item,
            substitution.foldValues(folder),
            typeArguments.map { it.foldWith(folder) }
        )
    }

    override fun toString(): String = tyToString(this)

    override fun innerVisitWith(visitor: TypeVisitor): Boolean {
        return typeArguments.any { it.visitWith(visitor) } || substitution.visitValues(visitor)
    }
}
