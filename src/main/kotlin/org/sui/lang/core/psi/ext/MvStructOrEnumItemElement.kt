package org.sui.lang.core.psi.ext

import com.intellij.psi.StubBasedPsiElement
import org.sui.lang.core.psi.*
import org.sui.lang.core.stubs.MvModuleStub
import org.sui.lang.core.types.ty.Ability
import org.sui.lang.core.types.ty.TyAdt

interface MvStructOrEnumItemElement: MvQualNamedElement,
                                     MvItemElement,
                                     MvTypeParametersOwner {

    val abilitiesList: MvAbilitiesList?

    override fun declaredType(msl: Boolean): TyAdt = TyAdt(this, this.tyTypeParams, this.generics)
}

val MvStructOrEnumItemElement.abilities: Set<Ability>
    get() = this.abilitiesList?.abilityList.orEmpty().mapNotNull { it.ability }.toSet()

val MvStructOrEnumItemElement.module: MvModule
    get() {
        if (this is StubBasedPsiElement<*>) {
            val moduleStub = greenStub?.parentStub as? MvModuleStub
            if (moduleStub != null) {
                return moduleStub.psi
            }
        }
        return this.parent as MvModule
    }
