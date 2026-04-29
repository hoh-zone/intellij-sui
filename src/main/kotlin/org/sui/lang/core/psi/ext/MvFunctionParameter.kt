package org.sui.lang.core.psi.ext

import com.intellij.lang.ASTNode
import org.sui.lang.core.psi.MvElementImpl
import org.sui.lang.core.psi.MvFunctionParameter
import org.sui.lang.core.psi.MvFunctionParameterList

val MvFunctionParameter.isSelfParam: Boolean
    get() {
        if (this.patBinding.name != "self") return false
        val parameters = (this.parent as MvFunctionParameterList).functionParameterList
        return parameters.indexOf(this) == 0
    }

abstract class MvFunctionParameterMixin(node: ASTNode) : MvElementImpl(node), MvFunctionParameter
