package org.sui.lang.core.types.ty

import org.sui.ide.presentation.tyToString

import org.sui.lang.core.types.infer.TypeFolder
import org.sui.lang.core.types.infer.TypeVisitor

open class TyVector(val item: Ty) : Ty(item.flags) {
    override fun abilities() = item.abilities()

    override fun innerFoldWith(folder: TypeFolder): Ty {
        folder.depth += 1
        return TyVector(item.foldWith(folder))
    }

    override fun innerVisitWith(visitor: TypeVisitor): Boolean = item.visitWith(visitor)

    override fun toString(): String = tyToString(this)

    override fun equals(other: Any?): Boolean = other is TyVector && item == other.item

    override fun hashCode(): Int = item.hashCode()
}

data class TyByteString(val msl: Boolean) :
    TyVector(TyInteger(if (msl) TyInteger.Kind.num else TyInteger.Kind.u8))

data class TyHexString(val msl: Boolean) :
    TyVector(TyInteger(if (msl) TyInteger.Kind.num else TyInteger.Kind.u8))
