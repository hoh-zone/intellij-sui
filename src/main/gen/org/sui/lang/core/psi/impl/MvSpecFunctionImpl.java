// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.sui.lang.MvElementTypes.*;
import org.sui.lang.core.psi.ext.MvSpecFunctionMixin;
import org.sui.lang.core.psi.*;
import com.intellij.psi.stubs.IStubElementType;
import org.sui.lang.core.stubs.MvSpecFunctionStub;

public class MvSpecFunctionImpl extends MvSpecFunctionMixin implements MvSpecFunction {

  public MvSpecFunctionImpl(ASTNode node) {
    super(node);
  }

  public MvSpecFunctionImpl(MvSpecFunctionStub stub, IStubElementType stubType) {
    super(stub, stubType);
  }

  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitSpecFunction(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<MvAttr> getAttrList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvAttr.class);
  }

  @Override
  @Nullable
  public MvFunctionParameterList getFunctionParameterList() {
    return PsiTreeUtil.getChildOfType(this, MvFunctionParameterList.class);
  }

  @Override
  @Nullable
  public MvReturnType getReturnType() {
    return PsiTreeUtil.getChildOfType(this, MvReturnType.class);
  }

  @Override
  @Nullable
  public MvSpecCodeBlock getSpecCodeBlock() {
    return PsiTreeUtil.getChildOfType(this, MvSpecCodeBlock.class);
  }

  @Override
  @Nullable
  public MvTypeParameterList getTypeParameterList() {
    return PsiTreeUtil.getChildOfType(this, MvTypeParameterList.class);
  }

  @Override
  @Nullable
  public PsiElement getIdentifier() {
    return findChildByType(IDENTIFIER);
  }

  @Override
  @Nullable
  public PsiElement getSemicolon() {
    return findChildByType(SEMICOLON);
  }

  @Override
  @NotNull
  public PsiElement getFun() {
    return notNullChild(findChildByType(FUN));
  }

  @Override
  @Nullable
  public PsiElement getNative() {
    return findChildByType(NATIVE);
  }

  @Override
  @NotNull
  public PsiElement getSpec() {
    return notNullChild(findChildByType(SPEC));
  }

}
