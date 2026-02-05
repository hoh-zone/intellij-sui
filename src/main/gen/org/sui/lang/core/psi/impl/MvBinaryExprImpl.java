// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.sui.lang.MvElementTypes.*;
import org.sui.lang.core.psi.ext.MvBinaryExprMixin;
import org.sui.lang.core.psi.*;

public class MvBinaryExprImpl extends MvBinaryExprMixin implements MvBinaryExpr {

  public MvBinaryExprImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitBinaryExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public MvBinaryOp getBinaryOp() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, MvBinaryOp.class));
  }

  @Override
  @NotNull
  public List<MvExpr> getExprList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvExpr.class);
  }

  @Override
  @NotNull
  public MvExpr getLeft() {
    List<MvExpr> p1 = getExprList();
    return p1.get(0);
  }

  @Override
  @Nullable
  public MvExpr getRight() {
    List<MvExpr> p1 = getExprList();
    return p1.size() < 2 ? null : p1.get(1);
  }

}
