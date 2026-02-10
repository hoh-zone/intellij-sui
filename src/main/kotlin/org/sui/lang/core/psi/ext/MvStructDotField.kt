package org.sui.lang.core.psi.ext

import com.intellij.lang.ASTNode
import com.intellij.psi.search.GlobalSearchScope
import org.sui.lang.core.psi.*
import org.sui.lang.index.MvNamedElementIndex
import org.sui.lang.moveProject
import org.sui.lang.core.resolve.MvResolveProcessor
import org.sui.lang.core.resolve.process
import org.sui.lang.core.resolve.processAll
import org.sui.lang.core.resolve.ref.MvPolyVariantReference
import org.sui.lang.core.resolve.ref.MvPolyVariantReferenceBase
import org.sui.lang.core.resolve.ref.NONE
import org.sui.lang.core.types.ty.TyAdt

fun processNamedFieldVariants(
    element: MvMethodOrField,
    receiverTy: TyAdt,
    msl: Boolean,
    processor: MvResolveProcessor
): Boolean {
    val receiverItem = receiverTy.item
    if (!isFieldsAccessible(element, receiverItem, msl)) return false

    return when (receiverItem) {
        is MvStruct -> processor.processAll(NONE, receiverItem.namedFields)
        is MvEnum -> {
            val visitedFields = mutableSetOf<String>()
            for (variant in receiverItem.variants) {
                val visitedVariantFields = mutableSetOf<String>()
                for (namedField in variant.namedFields) {
                    val fieldName = namedField.name
                    if (fieldName in visitedFields) continue
                    if (processor.process(NONE, namedField)) return true
                    // collect all names for the variant
                    visitedVariantFields.add(fieldName)
                }
                // add variant fields to the global fields list to skip them in the next variants
                visitedFields.addAll(visitedVariantFields)
            }
            false
        }
        else -> error("unreachable")
    }
}

// todo: change into VisibilityFilter
private fun isFieldsAccessible(
    element: MvElement,
    item: MvStructOrEnumItemElement,
    msl: Boolean
): Boolean {
    if (!msl) {
        // cannot resolve field if not in the same module as struct definition
        val dotExprModule = element.namespaceModule
        val itemModule = item.containingModule
        // In semicolon module style (stdlib style), one side can be absent in PSI.
        // In that case, don't block field resolution here.
        if (dotExprModule != null && itemModule != null && !areSameModuleContext(itemModule, dotExprModule)) {
            return false
        }
    }
    return true
}

fun areSameModuleContext(left: MvModule?, right: MvModule?): Boolean {
    if (left == null || right == null) return false
    if (left == right) return true
    return left.qualName != null && left.qualName == right.qualName
}

class MvStructDotFieldReferenceImpl(
    element: MvStructDotField
): MvPolyVariantReferenceBase<MvStructDotField>(element) {

    override fun multiResolve(): List<MvNamedElement> {
        val refName = element.referenceName
        val results = linkedSetOf<MvNamedElement>()

        // If receiver directly resolves to a fields owner, use exact field declarations first.
        val decl = element.receiverExpr.declaration
        val owner = when (decl) {
            is MvStruct -> decl
            is MvEnumVariant -> decl
            is MvFieldsOwner -> decl
            else -> null
        }
        if (owner != null) {
            owner.fields
                .filterTo(results) { it.name == refName }
        }

        // Fallback to indexed named fields to keep Cmd-hover marker available.
        val project = element.project
        val scope = element.moveProject?.searchScope() ?: GlobalSearchScope.allScope(project)
        MvNamedElementIndex
            .getElementsByName(project, refName, scope)
            .filterIsInstance<MvNamedFieldDecl>()
            .forEach { results.add(it) }

        return results.toList()
    }
}

abstract class MvStructDotFieldMixin(node: ASTNode): MvElementImpl(node),
                                                     MvStructDotField {
    override fun getReference(): MvPolyVariantReference {
        return MvStructDotFieldReferenceImpl(this)
    }
}
