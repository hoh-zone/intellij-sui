package org.sui.lang.core.psi.ext

import org.sui.lang.core.psi.MvLambdaExpr
import org.sui.lang.core.psi.MvPatBinding

val MvLambdaExpr.patBindingList: List<MvPatBinding>
    get() = lambdaParamList.flatMap { param -> param.matchPat.bindings }
