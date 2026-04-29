package org.sui.lang.core.psi.ext

import com.intellij.lang.ASTNode
import org.sui.cli.settings.debugErrorOrFallback
import org.sui.lang.MvElementTypes.COLON_COLON
import org.sui.lang.core.psi.*
import org.sui.lang.core.resolve.ref.*
import org.sui.lang.core.resolve2.ref.MvPath2ReferenceImpl

/** For `Foo::bar` in `Foo::bar::baz::quux` returns `Foo::bar::baz::quux` */
tailrec fun MvPath.rootPath(): MvPath {
    // Use `parent` instead of `context` because of better performance.
    // Assume nobody set a context for a part of a path
    val parent = parent
    return if (parent is MvPath) parent.rootPath() else this
}

fun MvPath.allowedNamespaces(isCompletion: Boolean = false): Set<Namespace> {
    val qualifier = this.path
    val parent = this.parent
    val rootParent = this.rootPath().parent
    return when {
        // mod::foo::bar
        //      ^
        parent is MvPath && qualifier != null -> when (rootParent) {
            is MvCallExpr -> FUNCTIONS_N_TYPES_N_ENUMS
            is MvPublicUseFun, is MvUseFunAlias -> FUNCTIONS
            else -> ENUMS
        }
        // foo::bar
        //  ^
        parent is MvPath -> ENUMS_N_MODULES
        // use 0x1::foo::bar; | use 0x1::foo::{bar, baz}
        //               ^                     ^
        parent is MvUseSpeck -> ITEM_NAMESPACES
        // a: bar
        //     ^
        parent is MvPathType && qualifier == null ->
            if (isCompletion) TYPES_N_ENUMS_N_MODULES else TYPES_N_ENUMS
        // a: foo::bar
        //         ^
        parent is MvPathType && qualifier != null -> TYPES_N_ENUMS
        parent is MvCallExpr -> FUNCTIONS_N_TYPES_N_ENUMS
        parent is MvPathExpr
                && this.hasAncestor<MvAttrItemInitializer>() -> ALL_NAMESPACES

        // can be anything in completion
        parent is MvPathExpr -> if (isCompletion) ALL_NAMESPACES else NAMES
        parent is MvStructLitExpr
                || parent is MvPatStruct
                || parent is MvPatConst -> TYPES_N_ENUMS
        parent is MvFriendDecl -> MODULES

        else -> debugErrorOrFallback(
            "Cannot build path namespaces: unhandled parent type ${parent?.elementType}",
            NAMES
        )
    }
}

val MvPath.qualifier: MvPath?
    get() {
        path?.let { return it }
        var ctx = context
        while (ctx is MvPath) {
            ctx = ctx.context
        }
        // returns the base qualifier, if it's inside the MvUseGroup
        return (ctx as? MvUseSpeck)?.qualifier
    }

abstract class MvPathMixin(node: ASTNode): MvElementImpl(node), MvPath {

    override fun getReference(): MvPath2Reference? = MvPath2ReferenceImpl(this)
}

val MvPath.isColonColonNext: Boolean get() = nextNonWsSibling?.elementType == COLON_COLON

val MvPath.useSpeck: MvUseSpeck? get() = this.rootPath().parent as? MvUseSpeck

val MvPath.isUseSpeck get() = useSpeck != null
