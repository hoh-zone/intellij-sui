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

public class MvCallExprImpl extends MvExprImpl implements MvCallExpr {

  public MvCallExprImpl(ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitCallExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public MvPath getPath() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, MvPath.class));
  }

  @Override
  @Nullable
  public MvTypeArgumentList getTypeArgumentList() {
    return PsiTreeUtil.getChildOfType(this, MvTypeArgumentList.class);
  }

  @Override
  @NotNull
  public MvValueArgumentList getValueArgumentList() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, MvValueArgumentList.class));
  }

  @Override
  @Nullable
  public PsiElement getExcl() {
    return findChildByType(EXCL);
  }

}
