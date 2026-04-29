package org.sui.lang.core.resolve2.ref

import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveResult
import org.sui.lang.core.psi.MvElement

data class MvPathResolveResult<T : MvElement>(
    val element: T,
    val isVisible: Boolean,
) : ResolveResult {
    override fun getElement(): PsiElement = element

    override fun isValidResult(): Boolean = true
}
