package org.sui.lang.core.types

import org.sui.cli.MoveProject
import org.sui.lang.core.psi.MvQualNamedElement

data class ItemQualName(
    val item: MvQualNamedElement,
    val address: Address,
    val moduleName: String?,
    val itemName: String
) {
    fun editorText(): String {
        val addressText = this.editorAddressText()
        return listOfNotNull(addressText, moduleName, itemName).joinToString("::")
    }

    fun cmdText(moveProject: MoveProject): String {
        val addressText = when (address) {
            is Address.Named -> address.addressLit(moveProject)?.short()
            is Address.Value -> address.addressLit().short()
        }
        return listOfNotNull(addressText, moduleName, itemName).joinToString("::")
    }

    private fun editorAddressText(): String {
        return when (address) {
            is Address.Named -> address.name
            is Address.Value -> address.addressLit().original
        }
    }
}
