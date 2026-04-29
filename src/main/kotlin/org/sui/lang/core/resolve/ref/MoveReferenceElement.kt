package org.sui.lang.core.resolve.ref

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import org.sui.lang.core.psi.MvElement

interface PsiReferenceElement : PsiElement {
    val identifier: PsiElement?

    val referenceNameElement: PsiElement?
        get() = identifier

    val referenceName: String?
        get() = identifier?.text

    override fun getReference(): PsiReference?
}

interface PsiMandatoryReferenceElement : PsiElement {
    val identifier: PsiElement

    val referenceNameElement: PsiElement
        get() = identifier

    val referenceName: String
        get() = identifier.text

    override fun getReference(): PsiReference
}

interface NamedAddressReferenceElement : PsiMandatoryReferenceElement {

    override fun getReference(): NamedAddressReference
}

interface MvReferenceElement : PsiReferenceElement, MvElement {

    override fun getReference(): MvPolyVariantReference?
}

interface MvMandatoryReferenceElement : MvReferenceElement {
    override val identifier: PsiElement

    override val referenceNameElement: PsiElement get() = identifier

    // referenceNameElement can be null at runtime (e.g. MvStructDotField.getIdentifier() is @Nullable)
    override val referenceName: String get() = referenceNameElement?.text ?: ""

    override fun getReference(): MvPolyVariantReference
}

interface MvNameAccessChainReferenceElement : MvReferenceElement {
    override fun getReference(): MvPath2Reference?
}
