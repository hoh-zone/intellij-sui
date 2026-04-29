package org.sui.lang.core.psi.ext

import org.sui.lang.core.psi.MvUseGroup
import org.sui.lang.core.psi.MvUseSpeck

val MvUseGroup.parentUseSpeck: MvUseSpeck get() = parent as MvUseSpeck

