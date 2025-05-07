package org.sui.lang.core.psi.ext

import org.sui.lang.core.psi.MvLambdaType
import org.sui.lang.core.psi.MvType

val MvLambdaType.paramTypes: List<MvType>
    get() = this.lambdaTypeParamList.map { it.type }

val MvLambdaType.returnType: MvType? get() = this.type
