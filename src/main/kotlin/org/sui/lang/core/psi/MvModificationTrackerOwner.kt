/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.lang.core.psi

import com.intellij.openapi.util.SimpleModificationTracker
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile

class MvModificationTracker(val owner: MvModificationTrackerOwner): SimpleModificationTracker()

/**
 * A PSI element that holds modification tracker for some reason.
 * This is mostly used to invalidate cached type inference results.
 */
interface MvModificationTrackerOwner : MvElement {
    val modificationTracker: MvModificationTracker

    /**
     * Increments local modification counter if needed.
     *
     * If and only if false returned,
     * [MvPsiManager.moveStructureModificationTracker]
     * will be incremented.
     */
    fun incModificationCount(element: PsiElement): Boolean
}

fun PsiElement.findModificationTrackerOwner(strict: Boolean): MvModificationTrackerOwner? {
    var element = if (strict) this.parent else this
    while (element != null && element !is PsiFile) {
        if (element is MvModule) return null
        if (element is MvModificationTrackerOwner) return element
        element = element.parent
    }
    return null
}
