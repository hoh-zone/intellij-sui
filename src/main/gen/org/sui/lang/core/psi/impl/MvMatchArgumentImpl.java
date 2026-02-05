// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.sui.lang.MvElementTypes.*;
import org.sui.lang.core.psi.MvElementImpl;
import org.sui.lang.core.psi.*;

public class MvMatchArgumentImpl extends MvElementImpl implements MvMatchArgument {

  public MvMatchArgumentImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitMatchArgument(this);
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
  @NotNull
  public PsiElement getLParen() {
    return notNullChild(findChildByType(L_PAREN));
  }

  @Override
  @NotNull
  public PsiElement getRParen() {
    return notNullChild(findChildByType(R_PAREN));
  }

}
