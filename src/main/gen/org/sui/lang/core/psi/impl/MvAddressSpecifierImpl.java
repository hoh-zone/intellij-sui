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

public class MvAddressSpecifierImpl extends MvElementImpl implements MvAddressSpecifier {

  public MvAddressSpecifierImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitAddressSpecifier(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MvAddressSpecifierArg getAddressSpecifierArg() {
    return PsiTreeUtil.getChildOfType(this, MvAddressSpecifierArg.class);
  }

  @Override
  @NotNull
  public PsiElement getLParen() {
    return notNullChild(findChildByType(L_PAREN));
  }

  @Override
  @Nullable
  public PsiElement getRParen() {
    return findChildByType(R_PAREN);
  }

}
