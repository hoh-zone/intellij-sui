// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.sui.lang.MvElementTypes.*;
import org.sui.lang.core.psi.ext.MvStructMixin;
import org.sui.lang.core.psi.*;
import com.intellij.psi.stubs.IStubElementType;
import org.sui.lang.core.stubs.MvStructStub;

public class MvStructImpl extends MvStructMixin implements MvStruct {

  public MvStructImpl(ASTNode node) {
    super(node);
  }

  public MvStructImpl(MvStructStub stub, IStubElementType stubType) {
    super(stub, stubType);
  }

  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitStruct(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MvAbilitiesList getAbilitiesList() {
    return PsiTreeUtil.getChildOfType(this, MvAbilitiesList.class);
  }

  @Override
  @NotNull
  public List<MvAttr> getAttrList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvAttr.class);
  }

  @Override
  @Nullable
  public MvBlockFields getBlockFields() {
    return PsiTreeUtil.getChildOfType(this, MvBlockFields.class);
  }

  @Override
  @Nullable
  public MvTupleFields getTupleFields() {
    return PsiTreeUtil.getChildOfType(this, MvTupleFields.class);
  }

  @Override
  @Nullable
  public MvTypeParameterList getTypeParameterList() {
    return PsiTreeUtil.getChildOfType(this, MvTypeParameterList.class);
  }

  @Override
  @NotNull
  public PsiElement getIdentifier() {
    return notNullChild(findChildByType(IDENTIFIER));
  }

  @Override
  @Nullable
  public PsiElement getSemicolon() {
    return findChildByType(SEMICOLON);
  }

  @Override
  @NotNull
  public PsiElement getStructKw() {
    return notNullChild(findChildByType(STRUCT_KW));
  }

  @Override
  @Nullable
  public PsiElement getNative() {
    return findChildByType(NATIVE);
  }

}
