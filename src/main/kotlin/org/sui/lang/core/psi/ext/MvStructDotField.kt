package org.sui.lang.core.psi.ext

import com.intellij.lang.ASTNode
import com.intellij.psi.search.GlobalSearchScope
import org.sui.lang.core.psi.*
import org.sui.lang.index.MvNamedElementIndex
import org.sui.lang.moveProject
import org.sui.lang.core.resolve.ref.MvPolyVariantReference
import org.sui.lang.core.resolve.ref.MvPolyVariantReferenceBase

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
            owner.namedFields
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
