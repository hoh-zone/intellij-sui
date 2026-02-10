package org.sui.lang.core.psi.ext

import org.sui.lang.core.psi.MvExpr
import org.sui.lang.core.psi.MvLambdaExpr
import org.sui.lang.core.psi.MvPatBinding

/** List of parameter bindings (from LambdaParam children). */
val MvLambdaExpr.patBindingList: List<MvPatBinding>
    get() = lambdaParamList.flatMap { param -> param.matchPat.bindings }

/** Number of lambda parameters, including destructuring patterns. */
val MvLambdaExpr.paramCount: Int
    get() = lambdaParamList.size

/** Body expression: either the single expr or the block's trailing expr. */
val MvLambdaExpr.bodyExpr: MvExpr?
    get() = expr ?: lambdaExprBlock?.codeBlock?.returningExpr
