package org.sui.lang.core.psi.ext

import com.intellij.extapi.psi.StubBasedPsiElementBase
import com.intellij.psi.NavigatablePsiElement
import org.sui.lang.core.psi.MvAttr
import org.sui.lang.core.psi.MvAttrItem
import org.sui.lang.core.psi.MvElement
import org.sui.lang.core.stubs.MvAttributeOwnerStub

interface MvDocAndAttributeOwner : MvElement, NavigatablePsiElement {
    val attrList: List<MvAttr>
}

val MvDocAndAttributeOwner.hasTestOnlyAttr: Boolean
    get() {
        val stub = attributeStub
        return stub?.isTestOnly ?: queryAttributes.isTestOnly
    }

inline val MvDocAndAttributeOwner.attributeStub: MvAttributeOwnerStub?
    get() = (this as? StubBasedPsiElementBase<*>)?.greenStub as? MvAttributeOwnerStub

/**
 * Returns [QueryAttributes] for given PSI element.
 */
val MvDocAndAttributeOwner.queryAttributes: QueryAttributes
    get() {
        val stub = attributeStub
        return if (stub?.hasAttrs == false) {
            QueryAttributes.EMPTY
        } else {
            QueryAttributes(this.attrList.asSequence())
        }
    }

/**
 * Allows for easy querying [MvDocAndAttributeOwner] for specific attributes.
 *
 * **Do not instantiate directly**, use [MvDocAndAttributeOwner.queryAttributes] instead.
 */
class QueryAttributes(
    private val attributes: Sequence<MvAttr>
) {
    val isTestOnly: Boolean get() = hasAttrItem("test_only")

    fun hasAttrItem(attributeName: String): Boolean = getAttrItem(attributeName) != null

    fun getAttrItem(attributeName: String): MvAttrItem? {
        return this.attrItems.find { it.referenceName == attributeName }
    }

    val attrItems: Sequence<MvAttrItem> get() = this.attributes.flatMap { it.attrItemList }

    override fun toString(): String =
        "QueryAttributes(${attributes.joinToString { it.text }})"

    companion object {
        val EMPTY: QueryAttributes = QueryAttributes(emptySequence())
    }
}
