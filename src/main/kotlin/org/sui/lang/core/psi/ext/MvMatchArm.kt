package org.sui.lang.core.psi.ext

import org.sui.lang.core.psi.MvMatchArm
import org.sui.lang.core.psi.MvPatBinding

val MvMatchArm.bindings: List<MvPatBinding>
    get() = matchPatList.flatMap { it.bindings }
