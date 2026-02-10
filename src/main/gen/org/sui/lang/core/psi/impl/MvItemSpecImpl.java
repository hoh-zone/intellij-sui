// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.sui.lang.MvElementTypes.*;
import org.sui.lang.core.psi.ext.MvItemSpecMixin;
import org.sui.lang.core.psi.*;

public class MvItemSpecImpl extends MvItemSpecMixin implements MvItemSpec {

  public MvItemSpecImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitItemSpec(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MvItemSpecRef getItemSpecRef() {
    return PsiTreeUtil.getChildOfType(this, MvItemSpecRef.class);
  }

  @Override
  @Nullable
  public MvItemSpecSignature getItemSpecSignature() {
    return PsiTreeUtil.getChildOfType(this, MvItemSpecSignature.class);
  }

  @Override
  @NotNull
  public PsiElement getSpec() {
    return notNullChild(findChildByType(SPEC));
  }

}
