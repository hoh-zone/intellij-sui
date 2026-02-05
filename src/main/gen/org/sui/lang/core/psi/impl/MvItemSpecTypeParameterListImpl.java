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

public class MvItemSpecTypeParameterListImpl extends MvElementImpl implements MvItemSpecTypeParameterList {

  public MvItemSpecTypeParameterListImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitItemSpecTypeParameterList(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<MvItemSpecTypeParameter> getItemSpecTypeParameterList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvItemSpecTypeParameter.class);
  }

  @Override
  @Nullable
  public PsiElement getGt() {
    return findChildByType(GT);
  }

  @Override
  @NotNull
  public PsiElement getLt() {
    return notNullChild(findChildByType(LT));
  }

}
