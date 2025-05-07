package org.sui.lang.core.resolve2.util

import org.sui.lang.core.psi.MvPath
import org.sui.lang.core.psi.MvUseAlias
import org.sui.lang.core.psi.MvUseSpeck
import org.sui.lang.core.psi.MvUseStmt
import org.sui.lang.core.psi.ext.childOfType
import org.sui.lang.core.psi.ext.childrenOfType

fun interface LeafUseSpeckConsumer {
    fun consume(path: MvPath, useAlias: MvUseAlias?): Boolean
}

fun MvUseStmt.forEachLeafSpeck(consumer: LeafUseSpeckConsumer) {
    val rootUseSpeck = this.childOfType<MvUseSpeck>() ?: return
    val useGroup = rootUseSpeck.useGroup
    if (useGroup == null) {
        // basePath is null, path is full path of useSpeck
        val alias = rootUseSpeck.useAlias
        if (!consumer.consume(rootUseSpeck.path, alias)) return
    } else {
        for (childSpeck in useGroup.childrenOfType<MvUseSpeck>()) {
            val alias = childSpeck.useAlias
            if (!consumer.consume(childSpeck.path, alias)) continue
        }
    }
}
