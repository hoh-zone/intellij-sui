package org.sui.lang.core.psi.ext

import com.intellij.lang.ASTNode
import org.sui.lang.core.psi.MvAbility
import org.sui.lang.core.psi.MvTypeParameter
import org.sui.lang.core.psi.impl.MvNameIdentifierOwnerImpl

val MvTypeParameter.abilityBounds: List<MvAbility>
    get() = typeParamBound?.abilityList.orEmpty()

abstract class MvTypeParameterMixin(node: ASTNode) : MvNameIdentifierOwnerImpl(node),
                                                     MvTypeParameter
