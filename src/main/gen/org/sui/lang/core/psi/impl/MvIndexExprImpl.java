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

public class MvIndexExprImpl extends MvExprImpl implements MvIndexExpr {

  public MvIndexExprImpl(ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitIndexExpr(this);
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
  public PsiElement getLBrack() {
    return notNullChild(findChildByType(L_BRACK));
  }

  @Override
  @NotNull
  public PsiElement getRBrack() {
    return notNullChild(findChildByType(R_BRACK));
  }

}
