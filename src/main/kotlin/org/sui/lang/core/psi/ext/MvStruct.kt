package org.sui.lang.core.psi.ext

import com.intellij.ide.projectView.PresentationData
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.stubs.IStubElementType
import org.sui.ide.MoveIcons
import org.sui.lang.core.psi.MvNamedFieldDecl
import org.sui.lang.core.psi.MvStruct
import org.sui.lang.core.stubs.MvStructStub
import org.sui.lang.core.stubs.MvStubbedNamedElementImpl
import org.sui.lang.core.types.ItemQualName
import org.sui.lang.core.types.ty.Ability
import javax.swing.Icon

val MvStruct.fields: List<MvNamedFieldDecl>
    get() = blockFields?.namedFieldDeclList.orEmpty()

val MvStruct.requiredAbilitiesForTypeParam: Set<Ability>
    get() =
        this.abilities.map { it.requires() }.toSet()

abstract class MvStructMixin: MvStubbedNamedElementImpl<MvStructStub>,
                              MvStruct {

    constructor(node: ASTNode): super(node)

    constructor(stub: MvStructStub, nodeType: IStubElementType<*, *>): super(stub, nodeType)

    override val qualName: ItemQualName?
        get() {
            val itemName = this.name ?: return null
            val moduleFQName = this.module.qualName ?: return null
            return ItemQualName(this, moduleFQName.address, moduleFQName.itemName, itemName)
        }

    override fun getIcon(flags: Int): Icon = MoveIcons.STRUCT

    override fun getPresentation(): ItemPresentation? {
        val structName = this.name ?: return null
        return PresentationData(
            structName,
            this.locationString(true),
            MoveIcons.STRUCT,
            null
        )
    }
}
