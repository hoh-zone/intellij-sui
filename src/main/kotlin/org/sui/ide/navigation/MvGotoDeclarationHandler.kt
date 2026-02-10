package org.sui.ide.navigation

import com.intellij.codeInsight.navigation.actions.GotoDeclarationHandler
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiPolyVariantReference
import com.intellij.psi.util.PsiTreeUtil
import org.sui.lang.MoveLanguage
import org.sui.lang.core.psi.MvPath
import org.sui.lang.core.resolve.ref.MvPolyVariantReference

class MvGotoDeclarationHandler : GotoDeclarationHandler {
    override fun getGotoDeclarationTargets(
        sourceElement: PsiElement?,
        offset: Int,
        editor: Editor
    ): Array<PsiElement>? {
        if (sourceElement == null) return null
        if (!sourceElement.language.isKindOf(MoveLanguage)) return null

        // Prefer explicit path resolve (with alias unwrapping) for stable Cmd+Hover behavior.
        val path = PsiTreeUtil.getParentOfType(sourceElement, MvPath::class.java, false)
        if (path != null) {
            val pathRef = path.reference as? MvPolyVariantReference
            val resolved = pathRef?.resolveFollowingAliases()
            if (resolved != null) return arrayOf(resolved)
        }

        // Fallback: walk up to nearest resolvable reference host.
        var current: PsiElement? = sourceElement
        while (current != null && current !is PsiFile) {
            val ref = current.reference
            val targets = resolveReferenceTargets(ref)
            if (targets.isNotEmpty()) return targets.toTypedArray()
            current = current.parent
        }
        return null
    }

    override fun getActionText(context: com.intellij.openapi.actionSystem.DataContext): String? = null

    private fun resolveReferenceTargets(ref: com.intellij.psi.PsiReference?): List<PsiElement> {
        if (ref == null) return emptyList()
        val single = ref.resolve()
        if (single != null) return listOf(single)
        if (ref is PsiPolyVariantReference) {
            return ref.multiResolve(false).mapNotNull { it.element }
        }
        return emptyList()
    }
}
