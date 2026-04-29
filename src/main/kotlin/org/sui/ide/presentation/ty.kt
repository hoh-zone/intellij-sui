package org.sui.ide.presentation

import org.sui.lang.core.types.ty.*

fun tyToString(ty: Ty) = render(ty, Int.MAX_VALUE)

private const val UNKNOWN = "<unknown>"
private const val ANONYMOUS = "<anonymous>"
private const val INTEGER = "integer"

private fun render(ty: Ty, level: Int): String {
    check(level >= 0)
    if (ty is TyUnknown) return UNKNOWN
    if (ty is TyPrimitive) {
        return when (ty) {
            is TyBool -> "bool"
            is TyAddress -> "address"
            is TySigner -> "signer"
            is TyUnit -> "()"
            is TyInteger -> {
                if (ty.kind == TyInteger.DEFAULT_KIND) {
                    INTEGER
                } else {
                    ty.kind.toString()
                }
            }
            else -> error("unreachable")
        }
    }

    if (level == 0) return "_"

    val r = { subTy: Ty -> render(subTy, level - 1) }

    return when (ty) {
        is TyFunction -> {
            val params = ty.paramTypes.joinToString(", ", "fn(", ")", transform = r)
            if (ty.retType is TyUnit) params else "$params -> ${r(ty.retType)}"
        }
        is TyTuple -> {
            val inner = ty.types.joinToString(", ", transform = r)
            if (ty.types.size == 1) "($inner,)" else "($inner)"
        }
        is TyVector -> "vector<${r(ty.item)}>"
        is TyReference -> {
            val prefix = if (ty.mutability.isMut) "&mut " else "&"
            "$prefix${r(ty.referenced)}"
        }
        is TyTypeParameter -> ty.name ?: ANONYMOUS
        is TyAdt -> {
            val name = ty.item.name ?: ANONYMOUS
            val args =
                if (ty.typeArguments.isEmpty()) ""
                else ty.typeArguments.joinToString(", ", "<", ">", transform = r)
            name + args
        }
        is TyInfer -> when (ty) {
            is TyInfer.TyVar -> "?${ty.origin?.name ?: "_"}"
            is TyInfer.IntVar -> INTEGER
        }
        is TyLambda -> {
            val params = ty.paramTypes.joinToString(",", "|", "|", transform = r)
            val retType = if (ty.retType is TyUnit)
                "()"
            else
                r(ty.retType)
            "$params -> $retType"
        }
        else -> error("unimplemented for type ${ty.javaClass.name}")
    }
}
