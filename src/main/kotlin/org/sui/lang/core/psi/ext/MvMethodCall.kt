package org.sui.lang.core.psi.ext

import com.intellij.lang.ASTNode
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.PsiElement
import org.sui.lang.core.psi.*
import org.sui.lang.index.MvNamedElementIndex
import org.sui.lang.moveProject
import org.sui.lang.core.resolve.ref.MvPolyVariantReference
import org.sui.lang.core.resolve.ref.MvPolyVariantReferenceBase

class MvMethodCallReferenceImpl(
    element: MvMethodCall
):
    MvPolyVariantReferenceBase<MvMethodCall>(element) {

    override fun multiResolve(): List<MvNamedElement> {
        val refName = element.referenceName
        val results = linkedSetOf<MvNamedElement>()

        // Prefer local module candidates first.
        val containingModule = element.containingModule
        if (containingModule != null) {
            containingModule.allFunctions()
                .filterTo(results) { it.name == refName }
        }

        // Fallback to index-based search to keep Ctrl/Cmd-hover clickable.
        val project = element.project
        val scope = element.moveProject?.searchScope() ?: GlobalSearchScope.allScope(project)
        MvNamedElementIndex
            .getElementsByName(project, refName, scope)
            .filterIsInstance<MvFunction>()
            .forEach { results.add(it) }

        return results.toList()
    }

    override fun isReferenceTo(element: PsiElement): Boolean =
        element is MvFunction && super.isReferenceTo(element)
}

abstract class MvMethodCallMixin(node: ASTNode): MvElementImpl(node),
                                                 MvMethodCall {

    override fun getReference(): MvPolyVariantReference = MvMethodCallReferenceImpl(this)
}
