// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.sui.lang.MvElementTypes.*;
import org.sui.lang.core.psi.ext.MvPathMixin;
import org.sui.lang.core.psi.*;

public class MvPathImpl extends MvPathMixin implements MvPath {

  public MvPathImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitPath(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MvPath getPath() {
    return PsiTreeUtil.getChildOfType(this, MvPath.class);
  }

  @Override
  @Nullable
  public MvPathAddress getPathAddress() {
    return PsiTreeUtil.getChildOfType(this, MvPathAddress.class);
  }

  @Override
  @Nullable
  public MvTypeArgumentList getTypeArgumentList() {
    return PsiTreeUtil.getChildOfType(this, MvTypeArgumentList.class);
  }

  @Override
  @Nullable
  public PsiElement getColonColon() {
    return findChildByType(COLON_COLON);
  }

  @Override
  @Nullable
  public PsiElement getExcl() {
    return findChildByType(EXCL);
  }

  @Override
  @Nullable
  public PsiElement getIdentifier() {
    return findChildByType(IDENTIFIER);
  }

  @Override
  @Nullable
  public PsiElement getMul() {
    return findChildByType(MUL);
  }

}
