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

public class MvAddressRefImpl extends MvElementImpl implements MvAddressRef {

  public MvAddressRefImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitAddressRef(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MvNamedAddress getNamedAddress() {
    return PsiTreeUtil.getChildOfType(this, MvNamedAddress.class);
  }

  @Override
  @Nullable
  public PsiElement getBech32Address() {
    return findChildByType(BECH32_ADDRESS);
  }

  @Override
  @Nullable
  public PsiElement getDiemAddress() {
    return findChildByType(DIEM_ADDRESS);
  }

  @Override
  @Nullable
  public PsiElement getIntegerLiteral() {
    return findChildByType(INTEGER_LITERAL);
  }

  @Override
  @Nullable
  public PsiElement getPlaceholderAddress() {
    return findChildByType(PLACEHOLDER_ADDRESS);
  }

  @Override
  @Nullable
  public PsiElement getPolkadotAddress() {
    return findChildByType(POLKADOT_ADDRESS);
  }

}
