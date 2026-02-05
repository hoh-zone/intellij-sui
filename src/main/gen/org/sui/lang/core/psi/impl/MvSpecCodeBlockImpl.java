// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.sui.lang.MvElementTypes.*;
import org.sui.lang.core.psi.ext.MvSpecCodeBlockMixin;
import org.sui.lang.core.psi.*;

public class MvSpecCodeBlockImpl extends MvSpecCodeBlockMixin implements MvSpecCodeBlock {

  public MvSpecCodeBlockImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitSpecCodeBlock(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MvExpr getExpr() {
    return PsiTreeUtil.getChildOfType(this, MvExpr.class);
  }

  @Override
  @NotNull
  public List<MvStmt> getStmtList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvStmt.class);
  }

  @Override
  @NotNull
  public PsiElement getLBrace() {
    return notNullChild(findChildByType(L_BRACE));
  }

  @Override
  @Nullable
  public PsiElement getRBrace() {
    return findChildByType(R_BRACE);
  }

}
