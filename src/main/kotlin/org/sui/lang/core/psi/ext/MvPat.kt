package org.sui.lang.core.psi.ext

import com.intellij.psi.util.descendantsOfType
import org.sui.lang.core.psi.MvNamedElement
import org.sui.lang.core.psi.MvPat
import org.sui.lang.core.psi.MvPatBinding

val MvPat.bindings: List<MvNamedElement> get() = this.descendantsOfType<MvPatBinding>().toList()
