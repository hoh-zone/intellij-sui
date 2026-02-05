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

public class MvLabeledBlockExprImpl extends MvExprImpl implements MvLabeledBlockExpr {

  public MvLabeledBlockExprImpl(ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitLabeledBlockExpr(this);
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
  @NotNull
  public MvLabelDecl getLabelDecl() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, MvLabelDecl.class));
  }

}
