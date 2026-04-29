package org.sui.lang.core.psi.ext

import org.sui.lang.core.psi.*
import org.sui.lang.core.resolve2.ref.InferenceCachedPathElement

val MvIndexExpr.receiverExpr: MvExpr get() = exprList.first()

val MvExpr.declaration: MvElement?
    get() = when (this) {
        is InferenceCachedPathElement -> path.reference?.resolve()
        is MvDotExpr -> expr.declaration
        is MvIndexExpr -> receiverExpr.declaration
        else -> null
    }
