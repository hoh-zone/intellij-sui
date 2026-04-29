package org.sui.lang.core.psi.ext

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.search.LocalSearchScope
import com.intellij.psi.search.SearchScope
import com.intellij.psi.util.PsiTreeUtil
import org.sui.ide.MoveIcons
import org.sui.lang.core.psi.*
import org.sui.lang.core.psi.impl.MvMandatoryNameIdentifierOwnerImpl
import org.sui.lang.core.resolve.ref.MvPolyVariantReference
import org.sui.lang.core.resolve2.ref.MvBindingPatReferenceImpl
import javax.swing.Icon

val MvPatBinding.owner: PsiElement?
    get() = PsiTreeUtil.findFirstParent(this) {
        it is MvLetStmt
                || it is MvFunctionParameter
    }

abstract class MvPatBindingMixin(node: ASTNode) : MvMandatoryNameIdentifierOwnerImpl(node),
                                                  MvPatBinding {

    // XXX: RsPatBinding is both a name element and a reference element:
    //
    // ```
    // match Some(82) {
    //     None => { /* None is a reference */ }
    //     Nope => { /* Nope is a named element*/ }
    // }
    // ```
    override fun getReference(): MvPolyVariantReference = MvBindingPatReferenceImpl(this)

    override val referenceNameElement: PsiElement get() = nameIdentifier
    override val referenceName: String get() = name



    override fun getIcon(flags: Int): Icon =
        when (this.owner) {
            is MvFunctionParameter -> MoveIcons.PARAMETER
            is MvConst -> MoveIcons.CONST
            else -> MoveIcons.VARIABLE
        }

    override fun getUseScope(): SearchScope {
        return when (this.owner) {
            is MvFunctionParameter -> {
                val function = this.ancestorStrict<MvFunction>() ?: return super.getUseScope()
                LocalSearchScope(function)
            }
            is MvLetStmt -> {
                val function = this.ancestorStrict<MvFunction>() ?: return super.getUseScope()
                LocalSearchScope(function)
            }
            is MvConst -> super.getUseScope()
            else -> super.getUseScope()
        }
    }
}
