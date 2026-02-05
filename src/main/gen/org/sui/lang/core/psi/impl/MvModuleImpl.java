// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.sui.lang.MvElementTypes.*;
import org.sui.lang.core.psi.ext.MvModuleMixin;
import org.sui.lang.core.psi.*;
import com.intellij.psi.stubs.IStubElementType;
import org.sui.lang.core.stubs.MvModuleStub;

public class MvModuleImpl extends MvModuleMixin implements MvModule {

  public MvModuleImpl(ASTNode node) {
    super(node);
  }

  public MvModuleImpl(MvModuleStub stub, IStubElementType stubType) {
    super(stub, stubType);
  }

  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitModule(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MvAddressRef getAddressRef() {
    return PsiTreeUtil.getChildOfType(this, MvAddressRef.class);
  }

  @Override
  @NotNull
  public List<MvAttr> getAttrList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvAttr.class);
  }

  @Override
  @NotNull
  public List<MvConst> getConstList() {
    return PsiTreeUtil.getStubChildrenOfTypeAsList(this, MvConst.class);
  }

  @Override
  @NotNull
  public List<MvEnum> getEnumList() {
    return PsiTreeUtil.getStubChildrenOfTypeAsList(this, MvEnum.class);
  }

  @Override
  @NotNull
  public List<MvFriendDecl> getFriendDeclList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvFriendDecl.class);
  }

  @Override
  @NotNull
  public List<MvFunction> getFunctionList() {
    return PsiTreeUtil.getStubChildrenOfTypeAsList(this, MvFunction.class);
  }

  @Override
  @NotNull
  public List<MvItemSpec> getItemSpecList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvItemSpec.class);
  }

  @Override
  @NotNull
  public List<MvModuleItemSpec> getModuleItemSpecList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvModuleItemSpec.class);
  }

  @Override
  @NotNull
  public List<MvPublicUseFun> getPublicUseFunList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvPublicUseFun.class);
  }

  @Override
  @NotNull
  public List<MvSchema> getSchemaList() {
    return PsiTreeUtil.getStubChildrenOfTypeAsList(this, MvSchema.class);
  }

  @Override
  @NotNull
  public List<MvSpecFunction> getSpecFunctionList() {
    return PsiTreeUtil.getStubChildrenOfTypeAsList(this, MvSpecFunction.class);
  }

  @Override
  @NotNull
  public List<MvStruct> getStructList() {
    return PsiTreeUtil.getStubChildrenOfTypeAsList(this, MvStruct.class);
  }

  @Override
  @NotNull
  public List<MvUseStmt> getUseStmtList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvUseStmt.class);
  }

  @Override
  @Nullable
  public PsiElement getColonColon() {
    return findChildByType(COLON_COLON);
  }

  @Override
  @Nullable
  public PsiElement getIdentifier() {
    return findChildByType(IDENTIFIER);
  }

  @Override
  @Nullable
  public PsiElement getLBrace() {
    return findChildByType(L_BRACE);
  }

  @Override
  @NotNull
  public PsiElement getModuleKw() {
    return notNullChild(findChildByType(MODULE_KW));
  }

  @Override
  @Nullable
  public PsiElement getRBrace() {
    return findChildByType(R_BRACE);
  }

  @Override
  @Nullable
  public PsiElement getSemicolon() {
    return findChildByType(SEMICOLON);
  }

}
