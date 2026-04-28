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

public class MvCastExprImpl extends MvExprImpl implements MvCastExpr {

  public MvCastExprImpl(ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitCastExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MvBinaryOp getBinaryOp() {
    return PsiTreeUtil.getChildOfType(this, MvBinaryOp.class);
  }

  @Override
  @Nullable
  public MvCondition getCondition() {
    return PsiTreeUtil.getChildOfType(this, MvCondition.class);
  }

  @Override
  @NotNull
  public List<MvExpr> getExprList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvExpr.class);
  }

  @Override
  @Nullable
  public MvForIterCondition getForIterCondition() {
    return PsiTreeUtil.getChildOfType(this, MvForIterCondition.class);
  }

  @Override
  @Nullable
  public MvLabelRef getLabelRef() {
    return PsiTreeUtil.getChildOfType(this, MvLabelRef.class);
  }

  @Override
  @Nullable
  public MvStmt getStmt() {
    return PsiTreeUtil.getChildOfType(this, MvStmt.class);
  }

  @Override
  @Nullable
  public MvType getType() {
    return PsiTreeUtil.getChildOfType(this, MvType.class);
  }

  @Override
  @Nullable
  public MvUseStmt getUseStmt() {
    return PsiTreeUtil.getChildOfType(this, MvUseStmt.class);
  }

  @Override
  @NotNull
  public PsiElement getAs() {
    return notNullChild(findChildByType(AS));
  }

}
