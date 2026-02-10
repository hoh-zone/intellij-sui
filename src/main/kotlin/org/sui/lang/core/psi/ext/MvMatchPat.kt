package org.sui.lang.core.psi.ext

import org.sui.lang.core.psi.MvMatchPat
import org.sui.lang.core.psi.MvPat
import org.sui.lang.core.psi.MvPatBinding

val MvMatchPat.pats: List<MvPat>
    get() {
        val simplePat = this.pat
        if (simplePat != null) return listOf(simplePat)

        val enumVariantPat = this.enumVariantPat
        if (enumVariantPat != null) return enumVariantPat.patTuple.patList

        return emptyList()
    }

val MvMatchPat.bindings: List<MvPatBinding>
    get() = this.pats.flatMap { pat -> pat.bindings.filterIsInstance<MvPatBinding>() }
