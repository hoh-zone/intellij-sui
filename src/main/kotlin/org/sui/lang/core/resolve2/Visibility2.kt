package org.sui.lang.core.resolve2

import org.sui.cli.containingMovePackage
import org.sui.cli.settings.moveSettings
import org.sui.ide.inspections.imports.usageScope
import org.sui.lang.core.psi.*
import org.sui.lang.core.psi.NamedItemScope.MAIN
import org.sui.lang.core.psi.ext.*
import org.sui.lang.core.resolve.ModInfo
import org.sui.lang.core.resolve.VisibilityFilter
import org.sui.lang.core.resolve.VisibilityStatus.Invisible
import org.sui.lang.core.resolve.VisibilityStatus.Visible
import org.sui.lang.core.resolve.ref.Namespace.*
import org.sui.lang.core.resolve.ref.Visibility2
import org.sui.lang.core.resolve.ref.Visibility2.*

data class ItemVisibilityInfo(
    val item: MvNamedElement,
    val itemScopeAdjustment: NamedItemScope,
    val vis: Visibility2,
)

fun MvNamedElement.visInfo(adjustScope: NamedItemScope = MAIN): ItemVisibilityInfo {
    val visibility = (this as? MvVisibilityOwner)?.visibility2 ?: Public
    return ItemVisibilityInfo(this, adjustScope, visibility)
}

/** Creates filter which determines whether item with [this] visibility is visible from specific [ModInfo] */
fun ItemVisibilityInfo.createFilter(): VisibilityFilter {
    val (item, itemScopeAdjustment, visibility) = this
    return VisibilityFilter { methodOrPath, namespaces ->

        // inside msl everything is visible
        if (methodOrPath.isMsl()) return@VisibilityFilter Visible

        // if inside MvAttrItem like abort_code=
        val attrItem = methodOrPath.ancestorStrict<MvAttrItem>()
        if (attrItem != null) return@VisibilityFilter Visible

        val pathUsageScope = methodOrPath.usageScope

        val path = methodOrPath as? MvPath
        if (path != null) {
            val useSpeck = path.useSpeck
            if (useSpeck != null) {
                // inside import, all visibilities except for private work
                if (visibility !is Private) return@VisibilityFilter Visible

                // msl-only items are available from imports
                if (item.isMslOnlyItem) return@VisibilityFilter Visible

                // consts are importable in tests
                if (pathUsageScope.isTest && namespaces.contains(NAME)) return@VisibilityFilter Visible
            }
        }

        // #[test] functions cannot be used from non-imports
        if (item is MvFunction && item.hasTestAttr) return@VisibilityFilter Invisible

        val itemModule = item.containingModule
        // 0x0::builtins module items are always visible
        if (itemModule != null && itemModule.isBuiltins) return@VisibilityFilter Visible

        val itemUsageScope = item.usageScope.shrinkScope(itemScopeAdjustment)
        // #[test_only] items in non-test-only scope
        if (itemUsageScope != MAIN) {
            // cannot be used everywhere, need to check for scope compatibility
            if (itemUsageScope != pathUsageScope) return@VisibilityFilter Invisible
        }

        // we're in non-msl scope at this point, msl only items aren't available
        if (item is MslOnlyElement) return@VisibilityFilter Invisible

        val pathModule = methodOrPath.containingModule
        // local methods, Self::method - everything is visible
        if (itemModule == pathModule) return@VisibilityFilter Visible

        // types visibility is ignored, their correct usage is checked in a separate inspection
        if (namespaces.contains(TYPE) || namespaces.contains(ENUM)) return@VisibilityFilter Visible

        when (visibility) {
            is Restricted -> {
                when (visibility) {
                    is Restricted.Friend -> {
                        if (pathModule != null && itemModule != null) {
                            val friendModules = itemModule.friendModules
                            if (friendModules.any { isModulesEqual(it, pathModule) }) {
                                return@VisibilityFilter Visible
                            }
                        }
                        Invisible
                    }
                    is Restricted.Package -> {
                        if (!item.project.moveSettings.enablePublicPackage) {
                            return@VisibilityFilter Invisible
                        }
                        val pathPackage =
                            methodOrPath.containingMovePackage ?: return@VisibilityFilter Invisible
                        val itemPackage = item.containingMovePackage ?: return@VisibilityFilter Invisible
//                        val originPackage = visibility.originPackage.value ?: return@VisibilityFilter Invisible
                        if (pathPackage == itemPackage) Visible else Invisible
                    }
                }
            }
            is Public -> Visible
            is Private -> Invisible
        }
    }
}