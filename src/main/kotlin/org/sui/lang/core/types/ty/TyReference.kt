/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.lang.core.types.ty

import org.sui.ide.presentation.tyToString

import org.sui.lang.core.types.infer.TypeFolder
import org.sui.lang.core.types.infer.TypeVisitor

data class TyReference(
    val referenced: Ty,
    val mutability: Mutability,
    val msl: Boolean
): Ty(referenced.flags) {

    override fun abilities() = setOf(Ability.COPY, Ability.DROP)

    override fun innerFoldWith(folder: TypeFolder): Ty =
        TyReference(referenced.foldWith(folder), mutability, msl)

    override fun innerVisitWith(visitor: TypeVisitor): Boolean =
        referenced.visitWith(visitor)

    override fun toString(): String = tyToString(this)
}

enum class Mutability {
    MUTABLE,
    IMMUTABLE;

    val isMut: Boolean get() = this == MUTABLE

    companion object {
        fun valueOf(mutable: Boolean): Mutability = if (mutable) MUTABLE else IMMUTABLE
    }
}
