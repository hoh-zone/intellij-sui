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

public class MvItemSpecSignatureImpl extends MvElementImpl implements MvItemSpecSignature {

  public MvItemSpecSignatureImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitItemSpecSignature(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public MvItemSpecFunctionParameterList getItemSpecFunctionParameterList() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, MvItemSpecFunctionParameterList.class));
  }

  @Override
  @Nullable
  public MvItemSpecTypeParameterList getItemSpecTypeParameterList() {
    return PsiTreeUtil.getChildOfType(this, MvItemSpecTypeParameterList.class);
  }

  @Override
  @Nullable
  public MvReturnType getReturnType() {
    return PsiTreeUtil.getChildOfType(this, MvReturnType.class);
  }

}
