package org.sui.lang.core.psi.ext

import org.sui.lang.core.psi.MvDotExpr
import org.sui.lang.core.psi.MvExpr
import org.sui.lang.core.resolve.ref.MvMandatoryReferenceElement

interface MvMethodOrField : MvMandatoryReferenceElement

val MvMethodOrField.dotExpr: MvDotExpr get() = parent as MvDotExpr
val MvMethodOrField.receiverExpr: MvExpr get() = dotExpr.expr
