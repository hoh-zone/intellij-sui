package org.sui.lang.core.psi.ext

import org.sui.lang.core.psi.*

interface MvFieldsOwner : MvNameIdentifierOwner {
    val blockFields: MvBlockFields?
}

val MvFieldsOwner.namedFields: List<MvNamedFieldDecl>
    get() = blockFields?.namedFieldDeclList.orEmpty()

/**
 * True for:
 * ```
 * struct S;
 * enum E { A }
 * ```
 * but false for:
 * ```
 * struct S {}
 * struct S();
 * ```
 */
val MvFieldsOwner.isFieldless: Boolean
    get() = blockFields == null
