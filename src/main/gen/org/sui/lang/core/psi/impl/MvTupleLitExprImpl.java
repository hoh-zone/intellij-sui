// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.sui.lang.MvElementTypes.*;
import org.sui.lang.core.psi.*;

public class MvTupleLitExprImpl extends MvExprImpl implements MvTupleLitExpr {

  public MvTupleLitExprImpl(ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitTupleLitExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<MvExpr> getExprList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvExpr.class);
  }

  @Override
  @NotNull
  public PsiElement getLParen() {
    return notNullChild(findChildByType(L_PAREN));
  }

  @Override
  @Nullable
  public PsiElement getRParen() {
    return findChildByType(R_PAREN);
  }

}
