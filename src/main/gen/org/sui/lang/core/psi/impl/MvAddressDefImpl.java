// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.sui.lang.MvElementTypes.*;
import org.sui.lang.core.psi.ext.MvAddressDefMixin;
import org.sui.lang.core.psi.*;

public class MvAddressDefImpl extends MvAddressDefMixin implements MvAddressDef {

  public MvAddressDefImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitAddressDef(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MvAddressBlock getAddressBlock() {
    return PsiTreeUtil.getChildOfType(this, MvAddressBlock.class);
  }

  @Override
  @Nullable
  public MvAddressRef getAddressRef() {
    return PsiTreeUtil.getChildOfType(this, MvAddressRef.class);
  }

}
