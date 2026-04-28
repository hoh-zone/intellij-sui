package org.sui.lang.core.resolve2

import org.sui.lang.core.psi.*
import org.sui.lang.core.psi.ext.*
import org.sui.lang.core.resolve.*
import org.sui.lang.core.resolve.ref.Namespace
import org.sui.lang.core.types.infer.deepFoldTyTypeParameterWith
import org.sui.lang.core.types.ty.Ty
import org.sui.lang.core.types.ty.TyInfer
import org.sui.lang.core.types.ty.TyReference
import org.sui.lang.moveProject

val MvNamedElement.namespace
    get() = when (this) {
        is MvFunctionLike -> Namespace.FUNCTION
        is MvStruct -> Namespace.TYPE
        is MvEnum -> Namespace.ENUM
        is MvConst -> Namespace.NAME
        is MvModule -> Namespace.MODULE
        else -> error("when should be exhaustive, $this is not covered")
    }

fun processMethodResolveVariants(
    methodOrField: MvMethodOrField,
    receiverTy: Ty,
    msl: Boolean,
    processor: MvResolveProcessor
): Boolean {
    val moveProject = methodOrField.moveProject ?: return false
    val itemModule = receiverTy.itemModule(moveProject) ?: return false
    return processor
        .wrapWithFilter { e ->
            val function = e.element as? MvFunction ?: return@wrapWithFilter false
            val selfTy = function.selfParamTy(msl) ?: return@wrapWithFilter false
            val selfTyWithTyVars =
                selfTy.deepFoldTyTypeParameterWith { tp -> TyInfer.TyVar(tp) }
            TyReference.isCompatibleWithAutoborrow(receiverTy, selfTyWithTyVars, msl)
        }
        .processAllItems(setOf(Namespace.FUNCTION), itemModule.allNonTestFunctions())
}

fun processItemDeclarations(
    itemsOwner: MvItemsOwner,
    ns: Set<Namespace>,
    processor: MvResolveProcessor
): Boolean {
    val items = itemsOwner.itemElements

    for (item in items) {
        val name = item.name ?: continue
        val namespace = item.namespace
        if (namespace !in ns) continue

        if (processor.process(name, item, setOf(namespace))) return true
    }

    return false
}
