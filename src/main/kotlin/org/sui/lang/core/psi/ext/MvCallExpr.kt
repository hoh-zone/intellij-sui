package org.sui.lang.core.psi.ext

import org.sui.lang.core.psi.MvElement

/**
 * Marker interface implemented by PSI nodes that represent call-like expressions:
 * `MvCallExpr`, `MvMethodCall`, and `MvAssertMacroExpr`.
 *
 * Referenced from the BNF grammar (see `MoveParser.bnf`), so the interface must exist
 * even if no Kotlin code currently consumes it.
 */
interface MvCallable : MvElement
