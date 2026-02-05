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

public class MvForExprImpl extends MvExprImpl implements MvForExpr {

  public MvForExprImpl(ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitForExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MvCodeBlock getCodeBlock() {
    return PsiTreeUtil.getChildOfType(this, MvCodeBlock.class);
  }

  @Override
  @Nullable
  public MvForIterCondition getForIterCondition() {
    return PsiTreeUtil.getChildOfType(this, MvForIterCondition.class);
  }

  @Override
  @Nullable
  public MvInlineBlock getInlineBlock() {
    return PsiTreeUtil.getChildOfType(this, MvInlineBlock.class);
  }

}
