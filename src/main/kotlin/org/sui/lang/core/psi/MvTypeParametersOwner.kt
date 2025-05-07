package org.sui.lang.core.psi

import org.sui.lang.core.types.infer.Substitution
import org.sui.lang.core.types.infer.toTypeSubst
import org.sui.lang.core.types.ty.Ty
import org.sui.lang.core.types.ty.TyInfer
import org.sui.lang.core.types.ty.TyTypeParameter
import org.sui.lang.core.types.ty.TyUnknown

interface MvTypeParametersOwner : MvElement {
    val typeParameterList: MvTypeParameterList?
    // override for generic items
    fun declaredType(msl: Boolean): Ty = TyUnknown
}

val MvTypeParametersOwner.typeParameters: List<MvTypeParameter>
    get() =
        typeParameterList?.typeParameterList.orEmpty()

val MvTypeParametersOwner.generics: List<TyTypeParameter>
    get() = typeParameters.map { TyTypeParameter(it) }

val MvTypeParametersOwner.tyTypeParams: Substitution get() = Substitution(generics.associateWith { it })

val MvTypeParametersOwner.tyInfers: Substitution
    get() {
        val typeSubst = this
            .generics
            .associateWith { TyInfer.TyVar(it) }
        return typeSubst.toTypeSubst()
    }

val MvTypeParametersOwner.hasTypeParameters: Boolean
    get() =
        typeParameters.isNotEmpty()
