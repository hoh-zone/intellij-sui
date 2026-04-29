package org.sui.lang.core.psi.ext

import com.intellij.lang.ASTNode
import org.sui.lang.core.psi.MvCodeBlock
import org.sui.lang.core.psi.MvElementImpl
import org.sui.lang.core.psi.MvLetStmt

val MvCodeBlock.letStmts: List<MvLetStmt> get() = stmtList.filterIsInstance<MvLetStmt>()

abstract class MvCodeBlockMixin(node: ASTNode) : MvElementImpl(node), MvCodeBlock
