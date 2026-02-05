// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.sui.lang.MvElementTypes.*;
import org.sui.lang.core.psi.ext.MvFunctionMixin;
import org.sui.lang.core.psi.*;
import com.intellij.psi.stubs.IStubElementType;
import org.sui.lang.core.stubs.MvFunctionStub;

public class MvFunctionImpl extends MvFunctionMixin implements MvFunction {

  public MvFunctionImpl(ASTNode node) {
    super(node);
  }

  public MvFunctionImpl(MvFunctionStub stub, IStubElementType stubType) {
    super(stub, stubType);
  }

  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitFunction(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MvAcquiresType getAcquiresType() {
    return PsiTreeUtil.getChildOfType(this, MvAcquiresType.class);
  }

  @Override
  @NotNull
  public List<MvAttr> getAttrList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvAttr.class);
  }

  @Override
  @Nullable
  public MvCodeBlock getCodeBlock() {
    return PsiTreeUtil.getChildOfType(this, MvCodeBlock.class);
  }

  @Override
  @Nullable
  public MvFunctionParameterList getFunctionParameterList() {
    return PsiTreeUtil.getChildOfType(this, MvFunctionParameterList.class);
  }

  @Override
  @Nullable
  public MvResourceAccessItemList getResourceAccessItemList() {
    return PsiTreeUtil.getChildOfType(this, MvResourceAccessItemList.class);
  }

  @Override
  @Nullable
  public MvReturnType getReturnType() {
    return PsiTreeUtil.getChildOfType(this, MvReturnType.class);
  }

  @Override
  @Nullable
  public MvTypeParameterList getTypeParameterList() {
    return PsiTreeUtil.getChildOfType(this, MvTypeParameterList.class);
  }

  @Override
  @Nullable
  public MvVisibilityModifier getVisibilityModifier() {
    return PsiTreeUtil.getChildOfType(this, MvVisibilityModifier.class);
  }

  @Override
  @Nullable
  public PsiElement getIdentifier() {
    return findChildByType(IDENTIFIER);
  }

  @Override
  @Nullable
  public PsiElement getMacroKw() {
    return findChildByType(MACRO_KW);
  }

  @Override
  @Nullable
  public PsiElement getSemicolon() {
    return findChildByType(SEMICOLON);
  }

  @Override
  @Nullable
  public PsiElement getFun() {
    return findChildByType(FUN);
  }

  @Override
  @Nullable
  public PsiElement getNative() {
    return findChildByType(NATIVE);
  }

}
