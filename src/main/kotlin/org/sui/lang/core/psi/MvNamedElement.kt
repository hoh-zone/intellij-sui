package org.sui.lang.core.psi

import com.intellij.psi.*
import com.intellij.psi.search.PsiSearchHelper
import com.intellij.psi.search.searches.ReferencesSearch
import com.intellij.refactoring.listeners.RefactoringElementListener
import com.intellij.refactoring.rename.RenameUtil
import com.intellij.util.Query
import org.sui.lang.MvElementTypes.IDENTIFIER
import org.sui.lang.core.psi.ext.findLastChildByType
import org.sui.lang.core.types.ItemQualName

// 定义一个接口MvNamedElement，继承自MvElement、PsiNamedElement和NavigatablePsiElement
interface MvNamedElement : MvElement,
                           PsiNamedElement,
                           NavigatablePsiElement {

    // 定义一个属性nameElement，类型为PsiElement，返回值为this.findLastChildByType(IDENTIFIER)
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

fun MvNamedElement.searchReferences(): Query<PsiReference> =
    ReferencesSearch.search(
        this,
        PsiSearchHelper.getInstance(this.project).getUseScope(this)
    )

// 为MvNamedElement类添加重命名方法
fun MvNamedElement.rename(newName: String) {
    // 查找重命名后的使用信息
    val usageInfos = RenameUtil.findUsages(
        this,
        newName,
        false,
        false,
        emptyMap()
    )
    // 执行重命名操作
    RenameUtil.doRename(
        this,
        newName,
        usageInfos,
        this.project,
        RefactoringElementListener.DEAF
    )
    // 提交所有文档
    PsiDocumentManager.getInstance(project).commitAllDocuments()
}
