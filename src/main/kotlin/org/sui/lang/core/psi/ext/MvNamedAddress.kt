package org.sui.lang.core.psi.ext

import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry
import org.sui.lang.core.psi.MvElementImpl
import org.sui.lang.core.psi.MvNamedAddress
import org.sui.lang.core.resolve.ref.NamedAddressReference
import org.sui.openapiext.parentTable
import org.toml.lang.psi.TomlKey
import org.toml.lang.psi.TomlKeySegment
import org.toml.lang.psi.TomlKeyValue
import org.toml.lang.psi.TomlTableHeader

val TomlTableHeader.isAddressesHeader: Boolean get() = text in setOf("[addresses]")

fun TomlKeySegment.isNamedAddressDef(): Boolean {
    val key = this.parent as? TomlKey ?: return false
    val tomlTable = (key.parent as? TomlKeyValue)?.parentTable ?: return false
    return tomlTable.header.isAddressesHeader
}

abstract class MvNamedAddressMixin(node: ASTNode) : MvElementImpl(node),
                                                    MvNamedAddress {

    override fun getIcon(flags: Int) = AllIcons.Nodes.Annotationtype

    override fun getReferences(): Array<PsiReference> {
        return ReferenceProvidersRegistry.getReferencesFromProviders(this)
    }

    override fun getReference(): NamedAddressReference = references.last() as NamedAddressReference
}
