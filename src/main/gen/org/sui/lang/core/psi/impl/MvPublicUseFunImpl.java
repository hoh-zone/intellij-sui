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

public class MvPublicUseFunImpl extends MvElementImpl implements MvPublicUseFun {

  public MvPublicUseFunImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitPublicUseFun(this);
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
  public MvPath getPath() {
    return PsiTreeUtil.getChildOfType(this, MvPath.class);
  }

  @Override
  @Nullable
  public MvUseFunMethodAlias getUseFunMethodAlias() {
    return PsiTreeUtil.getChildOfType(this, MvUseFunMethodAlias.class);
  }

  @Override
  @Nullable
  public PsiElement getSemicolon() {
    return findChildByType(SEMICOLON);
  }

  @Override
  @Nullable
  public PsiElement getFun() {
    return findChildByType(FUN);
  }

  @Override
  @NotNull
  public PsiElement getUse() {
    return notNullChild(findChildByType(USE));
  }

}
