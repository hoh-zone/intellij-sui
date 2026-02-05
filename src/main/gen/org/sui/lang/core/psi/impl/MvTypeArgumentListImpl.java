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

public class MvTypeArgumentListImpl extends MvElementImpl implements MvTypeArgumentList {

  public MvTypeArgumentListImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitTypeArgumentList(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<MvTypeArgument> getTypeArgumentList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvTypeArgument.class);
  }

  @Override
  @Nullable
  public PsiElement getColonColon() {
    return findChildByType(COLON_COLON);
  }

  @Override
  @NotNull
  public PsiElement getGt() {
    return notNullChild(findChildByType(GT));
  }

  @Override
  @NotNull
  public PsiElement getLt() {
    return notNullChild(findChildByType(LT));
  }

}
