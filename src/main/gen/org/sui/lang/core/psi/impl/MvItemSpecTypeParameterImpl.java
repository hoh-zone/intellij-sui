// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.sui.lang.MvElementTypes.*;
import org.sui.lang.core.psi.ext.MvItemSpecTypeParameterMixin;
import org.sui.lang.core.psi.*;

public class MvItemSpecTypeParameterImpl extends MvItemSpecTypeParameterMixin implements MvItemSpecTypeParameter {

  public MvItemSpecTypeParameterImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitItemSpecTypeParameter(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MvTypeParamBound getTypeParamBound() {
    return PsiTreeUtil.getChildOfType(this, MvTypeParamBound.class);
  }

  @Override
  @NotNull
  public PsiElement getIdentifier() {
    return notNullChild(findChildByType(IDENTIFIER));
  }

}
