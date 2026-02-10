package org.sui.lang.core.psi.ext

import org.sui.lang.core.psi.*
import org.sui.lang.core.resolve2.ref.InferenceCachedPathElement

val MvExpr.isAtomExpr: Boolean get() =
    this is MvAnnotatedExpr
            || this is MvUnitLitExpr
            || this is MvTupleLitExpr
            || this is MvParensExpr
            || this is MvVectorLitExpr
            || this is MvDotExpr
            || this is MvIndexExpr
            || this is MvCallExpr
            || this is MvPathExpr
            || this is MvLambdaExpr
            || this is MvLitExpr
            || this is MvCodeBlockExpr

val MvIndexExpr.receiverExpr: MvExpr get() = exprList.first()

/** First index argument (for single-arg: v[0]). */
val MvIndexExpr.argExpr: MvExpr get() = argExprList.first()

/** All index arguments (for multi-arg: m[i,j], mat[0,0]). */
val MvIndexExpr.argExprList: List<MvExpr> get() = exprList.drop(1)

val MvExpr.declaration: MvElement?
    get() = when (this) {
        is InferenceCachedPathElement -> path.reference?.resolve()
        is MvDotExpr -> expr.declaration
        is MvIndexExpr -> receiverExpr.declaration
        else -> null
    }
