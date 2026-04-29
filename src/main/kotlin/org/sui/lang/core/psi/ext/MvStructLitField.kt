package org.sui.lang.core.psi.ext

import com.intellij.lang.ASTNode
import org.sui.lang.core.psi.*
import org.sui.lang.core.resolve.ref.MvPolyVariantReference
import org.sui.lang.core.resolve2.ref.MvStructLitFieldReferenceImpl

val MvStructLitField.structLitExpr: MvStructLitExpr
    get() = ancestorStrict()!!

abstract class MvStructLitFieldMixin(node: ASTNode) : MvElementImpl(node),
                                                      MvStructLitField {

    override fun getReference(): MvPolyVariantReference = MvStructLitFieldReferenceImpl(this)
}
