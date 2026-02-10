package org.sui.lang.core.psi.ext

import org.sui.lang.core.psi.MvMatchArm
import org.sui.lang.core.psi.MvMatchBody
import org.sui.lang.core.psi.MvMatchExpr
import org.sui.lang.core.psi.MvPat
import org.sui.lang.core.psi.MvPatBinding

val MvMatchArm.matchBody: MvMatchBody get() = this.parent as MvMatchBody
val MvMatchArm.matchExpr: MvMatchExpr get() = this.matchBody.parent as MvMatchExpr

val MvMatchArm.pats: List<MvPat>
    get() = matchPatList.flatMap { it.pats }

val MvMatchArm.bindings: List<MvPatBinding>
    get() = matchPatList.flatMap { it.bindings }

val MvMatchArm.primaryPat: MvPat?
    get() = pats.firstOrNull()
