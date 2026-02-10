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

public class MvMatchArmImpl extends MvElementImpl implements MvMatchArm {

  public MvMatchArmImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitMatchArm(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<MvAttr> getAttrList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvAttr.class);
  }

  @Override
  @Nullable
  public MvExpr getExpr() {
    return PsiTreeUtil.getChildOfType(this, MvExpr.class);
  }

  @Override
  @Nullable
  public MvMatchArmGuard getMatchArmGuard() {
    return PsiTreeUtil.getChildOfType(this, MvMatchArmGuard.class);
  }

  @Override
  @NotNull
  public List<MvMatchPat> getMatchPatList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvMatchPat.class);
  }

  @Override
  @Nullable
  public PsiElement getComma() {
    return findChildByType(COMMA);
  }

  @Override
  @Nullable
  public PsiElement getFatArrow() {
    return findChildByType(FAT_ARROW);
  }

}
