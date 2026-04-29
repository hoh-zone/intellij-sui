package org.sui.lang.core.psi

import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import org.sui.lang.MvElementTypes.IDENTIFIER
import org.sui.lang.core.psi.ext.findLastChildByType
import org.sui.lang.core.types.ItemQualName

interface MvNamedElement : MvElement,
                           PsiNamedElement,
                           NavigatablePsiElement {

    val nameElement: PsiElement? get() = this.findLastChildByType(IDENTIFIER)
}

interface MvMandatoryNamedElement : MvNamedElement {
    val identifier: PsiElement

    override val nameElement: PsiElement get() = identifier

    override fun getName(): String
}

interface MvQualNamedElement : MvNamedElement {
    val qualName: ItemQualName?
}
