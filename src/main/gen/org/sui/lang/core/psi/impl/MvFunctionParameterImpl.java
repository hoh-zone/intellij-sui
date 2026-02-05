// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.sui.lang.MvElementTypes.*;
import org.sui.lang.core.psi.ext.MvFunctionParameterMixin;
import org.sui.lang.core.psi.*;

public class MvFunctionParameterImpl extends MvFunctionParameterMixin implements MvFunctionParameter {

  public MvFunctionParameterImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitFunctionParameter(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public MvPatBinding getPatBinding() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, MvPatBinding.class));
  }

  @Override
  @Nullable
  public MvTypeAnnotation getTypeAnnotation() {
    return PsiTreeUtil.getChildOfType(this, MvTypeAnnotation.class);
  }

}
