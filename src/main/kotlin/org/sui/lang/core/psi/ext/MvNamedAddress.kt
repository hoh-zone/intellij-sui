package org.sui.lang.core.psi.ext

import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import org.sui.lang.core.psi.MvElementImpl
import org.sui.lang.core.psi.MvNamedAddress
import org.sui.lang.core.resolve.ref.NamedAddressReference

abstract class MvNamedAddressMixin(node: ASTNode) : MvElementImpl(node),
                                                    MvNamedAddress {

    override fun getIcon(flags: Int) = AllIcons.Nodes.Annotationtype

    override fun getReference(): NamedAddressReference = NamedAddressReference(this)
}
