package org.sui.lang.core.psi.ext

import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.stubs.IStubElementType
import org.sui.ide.MoveIcons
import org.sui.lang.core.psi.MvModule
import org.sui.lang.core.psi.MvSpecFunction
import org.sui.lang.core.psi.MvSpecInlineFunction
import org.sui.lang.core.psi.functionItemPresentation
import org.sui.lang.core.psi.impl.MvNameIdentifierOwnerImpl
import org.sui.lang.core.stubs.MvSpecFunctionStub
import org.sui.lang.core.stubs.MvStubbedNamedElementImpl
import org.sui.lang.core.types.ItemQualName
import javax.swing.Icon

val MvSpecFunction.module: MvModule? get() = this.parent as? MvModule

abstract class MvSpecFunctionMixin : MvStubbedNamedElementImpl<MvSpecFunctionStub>,
                                     MvSpecFunction {

    constructor(node: ASTNode) : super(node)

    constructor(stub: MvSpecFunctionStub, nodeType: IStubElementType<*, *>) : super(stub, nodeType)

    override val qualName: ItemQualName?
        get() {
            val itemName = this.name ?: return null
            val moduleFQName = this.module?.qualName ?: return null
            return ItemQualName(this, moduleFQName.address, moduleFQName.itemName, itemName)
        }

    override fun getIcon(flags: Int): Icon = MoveIcons.FUNCTION

    override fun getPresentation(): ItemPresentation? = this.functionItemPresentation

}

abstract class MvSpecInlineFunctionMixin(node: ASTNode) : MvNameIdentifierOwnerImpl(node),
                                                          MvSpecInlineFunction {

    override fun getIcon(flags: Int): Icon = MoveIcons.FUNCTION

    override fun getPresentation(): ItemPresentation? = this.functionItemPresentation
}
