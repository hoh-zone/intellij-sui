package org.sui.lang.core.psi.ext

import com.intellij.extapi.psi.StubBasedPsiElementBase
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.StubBasedPsiElement
import com.intellij.psi.stubs.StubElement
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.PsiUtilCore
import org.sui.lang.MoveFile
import org.sui.lang.core.stubs.impl.MvFileStub
import org.sui.lang.toNioPathOrNull
import org.sui.openapiext.rootPath
import java.nio.file.Path

fun PsiElement.hasChild(tokenType: IElementType): Boolean = childrenByType(tokenType).toList().isNotEmpty()

inline fun <reified T : PsiElement> PsiElement.childOfType(): T? =
    PsiTreeUtil.getChildOfType(this, T::class.java)

inline fun <reified T : PsiElement> PsiElement.childrenOfType(): List<T> =
    PsiTreeUtil.getChildrenOfTypeAsList(this, T::class.java)

inline fun <reified T : PsiElement> PsiElement.descendantOfTypeStrict(): T? =
    PsiTreeUtil.findChildOfType(this, T::class.java, /* strict */ true)

val PsiElement.ancestors: Sequence<PsiElement>
    get() = generateSequence(this) {
        if (it is PsiFile)
            null
        else it.parent
    }

inline fun <reified T : PsiElement> PsiElement.ancestorsOfType(): Sequence<T> {
    return this.ancestors.filterIsInstance<T>()
}

inline fun <reified T : PsiElement> PsiElement.ancestorStrict(): T? =
    PsiTreeUtil.getParentOfType(this, T::class.java, true)

inline fun <reified T : PsiElement> PsiElement.hasAncestor(): Boolean =
    ancestorStrict<T>() != null

/**
 * Extracts node's element type
 */
val PsiElement.elementType: IElementType
    // XXX: be careful not to switch to AST
    get() = if (this is MoveFile) MvFileStub.Type else PsiUtilCore.getElementType(this)

val PsiElement.childrenWithLeaves: Sequence<PsiElement>
    get() = generateSequence(this.firstChild) { it.nextSibling }

fun PsiElement.childrenByType(type: IElementType): Sequence<PsiElement> =
    childrenWithLeaves.filter { it.elementType == type }

fun PsiElement.findLastChildByType(type: IElementType): PsiElement? =
    childrenByType(type).lastOrNull()

fun PsiElement.isChildExists(type: IElementType): Boolean =
    childrenByType(type).firstOrNull() != null

fun PsiElement.isAncestorOf(child: PsiElement): Boolean =
    child.ancestors.contains(this)

val PsiElement.nextNonWsSibling: PsiElement?
    get() = PsiTreeUtil.skipWhitespacesForward(this)

fun PsiElement.cameBefore(element: PsiElement) =
    PsiUtilCore.compareElementsByPosition(this, element) <= 0

@Suppress("UNCHECKED_CAST")
inline val <T : StubElement<*>> StubBasedPsiElement<T>.greenStub: T?
    get() = (this as? StubBasedPsiElementBase<T>)?.greenStub

fun PsiElement.locationPath(tryRelative: Boolean): Path? {
    val containingFilePath = this.containingFile.toNioPathOrNull() ?: return null
    if (tryRelative) {
        val rootPath = this.project.rootPath
        if (rootPath != null) {
            return rootPath.relativize(containingFilePath)
        }
    }
    return containingFilePath
}

fun PsiElement.locationString(tryRelative: Boolean): String? =
    locationPath(tryRelative)?.toString()
