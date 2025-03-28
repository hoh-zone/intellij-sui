package org.sui.lang.core.psi.ext

import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.IStubElementType
import com.intellij.psi.util.CachedValuesManager.getProjectPsiDependentCache
import org.sui.cli.MoveProject
import org.sui.ide.MoveIcons
import org.sui.ide.annotator.BUILTIN_FUNCTIONS
import org.sui.lang.MvElementTypes
import org.sui.lang.core.psi.*
import org.sui.lang.core.stubs.MvFunctionStub
import org.sui.lang.core.stubs.MvStubbedNamedElementImpl
import org.sui.lang.core.types.ItemQualName
import org.sui.lang.core.types.infer.loweredType
import org.sui.lang.core.types.ty.Ty
import org.sui.lang.core.types.ty.TyUnit
import org.sui.lang.core.types.ty.TyUnknown
import javax.swing.Icon

val MvFunction.isEntry: Boolean
    get() {
        val stub = greenStub
        return stub?.isEntry ?: this.isChildExists(MvElementTypes.ENTRY)
    }

val MvFunction.isPublicScript: Boolean get() = this.visibilityModifier?.hasScript ?: false

val MvFunction.isInline: Boolean get() = this.isChildExists(MvElementTypes.INLINE)

val MvFunction.isView: Boolean
    get() {
        val stub = greenStub
        return stub?.isView ?: queryAttributes.isView
    }

// is a Macro function
val MvFunction.isMacro :Boolean get() = this.isChildExists(MvElementTypes.MARCO_TYPE)

fun MvFunction.functionId(moveProject: MoveProject): String? = qualName?.cmdText(moveProject)

val MvFunction.testAttrItem: MvAttrItem? get() = queryAttributes.getAttrItem("test")

val MvFunction.hasTestAttr: Boolean
    get() {
        val stub = greenStub
        return stub?.isTest ?: queryAttributes.isTest
    }

val QueryAttributes.isTest: Boolean get() = this.hasAttrItem("test")

val QueryAttributes.isView: Boolean get() = this.hasAttrItem("view")

val MvFunction.outerFileName: String
    get() =
        if (this.name in BUILTIN_FUNCTIONS) {
            "builtins"
        } else {
            this.containingFile?.name.orEmpty()
        }

fun MvFunction.innerItemSpecs(): List<MvItemSpec> {
    val functionName = this.name ?: return emptyList()
    val itemSpecs = this.module?.itemSpecList.orEmpty()
    return itemSpecs
        .filter { it.itemSpecRef?.referenceName == functionName }
}

fun MvFunction.outerItemSpecs(): List<MvItemSpec> {
    val functionName = this.name ?: return emptyList()
    val moduleSpecs = this.module?.allModuleSpecs().orEmpty()
    return moduleSpecs
        .flatMap { it.moduleSpecBlock?.itemSpecList.orEmpty() }
        .filter { it.itemSpecRef?.referenceName == functionName }
}

val MvFunction.transactionParameters: List<MvFunctionParameter> get() = this.parameters.drop(1)

//fun MvFunctionLike.declaredType(msl: Boolean): TyFunction2 {
//    val subst = typeParameters ?: this.instantiateTypeParameters()
//    val paramTypes = parameters.map { it.type?.loweredType(msl) ?: TyUnknown }
//    val acquiresTypes = this.acquiresPathTypes.map { it.loweredType(msl) }
//    val retType = rawReturnType(msl)
//    return TyFunction2(subst, paramTypes, acquiresTypes, retType)
//}

fun MvFunctionLike.rawReturnType(msl: Boolean): Ty {
    val retType = returnType ?: return TyUnit
    return retType.type?.loweredType(msl) ?: TyUnknown
}

val MvFunction.specFunctionResultParameters: List<MvFunctionParameter>
    get() {
        return getProjectPsiDependentCache(this) {
            val retType = it.returnType
            val psiFactory = it.project.psiFactory
            if (retType == null) {
                emptyList()
            } else {
                val retTypeType = retType.type
                when (retTypeType) {
                    null -> emptyList()
                    is MvTupleType -> {
                        retTypeType.typeList
                            .mapIndexed { i, type ->
                                psiFactory.specFunctionParameter(it, "result_${i + 1}", type.text)
                            }
                    }
                    else -> {
                        listOf(
                            psiFactory.specFunctionParameter(it, "result", retTypeType.text)
                        )
                    }
                }
            }
        }
    }

abstract class MvFunctionMixin : MvStubbedNamedElementImpl<MvFunctionStub>,
                                 MvFunction {
    constructor(node: ASTNode) : super(node)

    constructor(stub: MvFunctionStub, nodeType: IStubElementType<*, *>) : super(stub, nodeType)

    var builtIn = false

    override val qualName: ItemQualName?
        get() {
            val itemName = this.name ?: return null
            val moduleFQName = this.module?.qualName ?: return null
            return ItemQualName(this, moduleFQName.address, moduleFQName.itemName, itemName)
        }

//    override fun declaredType(msl: Boolean): TyFunction2 {
//        val subst = this.instantiateTypeParameters()
//        val paramTypes = parameters.map { it.type?.loweredType(msl) ?: TyUnknown }
//        val acquiresTypes = this.acquiresPathTypes.map { it.loweredType(msl) }
//        val retType = rawReturnType(msl)
//        return TyFunction2(subst, paramTypes, acquiresTypes, retType)
//    }

    override val modificationTracker = MvModificationTracker(this)

    override fun incModificationCount(element: PsiElement): Boolean {
        val shouldInc = codeBlock?.isAncestorOf(element) == true
//        item inside the function
//                && PsiTreeUtil.findChildOfAnyType(
//                    element,
//                    false,
//                    MvNamedElement::class.java,
//                ) == null
        if (shouldInc) modificationTracker.incModificationCount()
        return shouldInc
    }

    override fun canNavigate(): Boolean = !builtIn
    override fun canNavigateToSource(): Boolean = !builtIn

    override fun getIcon(flags: Int): Icon = MoveIcons.FUNCTION

    override fun getPresentation(): ItemPresentation? = this.functionItemPresentation
}
