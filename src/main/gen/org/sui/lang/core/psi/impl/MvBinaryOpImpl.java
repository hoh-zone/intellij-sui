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

public class MvBinaryOpImpl extends MvElementImpl implements MvBinaryOp {

  public MvBinaryOpImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitBinaryOp(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getAnd() {
    return findChildByType(AND);
  }

  @Override
  @Nullable
  public PsiElement getAndAnd() {
    return findChildByType(AND_AND);
  }

  @Override
  @Nullable
  public PsiElement getDiv() {
    return findChildByType(DIV);
  }

  @Override
  @Nullable
  public PsiElement getEq() {
    return findChildByType(EQ);
  }

  @Override
  @Nullable
  public PsiElement getEqEq() {
    return findChildByType(EQ_EQ);
  }

  @Override
  @Nullable
  public PsiElement getEqEqGt() {
    return findChildByType(EQ_EQ_GT);
  }

  @Override
  @Nullable
  public PsiElement getGt() {
    return findChildByType(GT);
  }

  @Override
  @Nullable
  public PsiElement getGtEq() {
    return findChildByType(GT_EQ);
  }

  @Override
  @Nullable
  public PsiElement getGtGt() {
    return findChildByType(GT_GT);
  }

  @Override
  @Nullable
  public PsiElement getLt() {
    return findChildByType(LT);
  }

  @Override
  @Nullable
  public PsiElement getLtEq() {
    return findChildByType(LT_EQ);
  }

  @Override
  @Nullable
  public PsiElement getLtEqEqGt() {
    return findChildByType(LT_EQ_EQ_GT);
  }

  @Override
  @Nullable
  public PsiElement getLtLt() {
    return findChildByType(LT_LT);
  }

  @Override
  @Nullable
  public PsiElement getMinus() {
    return findChildByType(MINUS);
  }

  @Override
  @Nullable
  public PsiElement getModulo() {
    return findChildByType(MODULO);
  }

  @Override
  @Nullable
  public PsiElement getMul() {
    return findChildByType(MUL);
  }

  @Override
  @Nullable
  public PsiElement getNotEq() {
    return findChildByType(NOT_EQ);
  }

  @Override
  @Nullable
  public PsiElement getOr() {
    return findChildByType(OR);
  }

  @Override
  @Nullable
  public PsiElement getOrOr() {
    return findChildByType(OR_OR);
  }

  @Override
  @Nullable
  public PsiElement getPlus() {
    return findChildByType(PLUS);
  }

  @Override
  @Nullable
  public PsiElement getXor() {
    return findChildByType(XOR);
  }

}
