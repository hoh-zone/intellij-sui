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

public class MvAnnotatedExprImpl extends MvExprImpl implements MvAnnotatedExpr {

  public MvAnnotatedExprImpl(ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitAnnotatedExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public MvExpr getExpr() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, MvExpr.class));
  }

  @Override
  @Nullable
  public MvType getType() {
    return PsiTreeUtil.getChildOfType(this, MvType.class);
  }

  @Override
  @NotNull
  public PsiElement getColon() {
    return notNullChild(findChildByType(COLON));
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
