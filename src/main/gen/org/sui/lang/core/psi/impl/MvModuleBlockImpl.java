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

public class MvModuleBlockImpl extends MvElementImpl implements MvModuleBlock {

  public MvModuleBlockImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitModuleBlock(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<MvConst> getConstList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvConst.class);
  }

  @Override
  @NotNull
  public List<MvEnum> getEnumList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvEnum.class);
  }

  @Override
  @NotNull
  public List<MvFriendDecl> getFriendDeclList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvFriendDecl.class);
  }

  @Override
  @NotNull
  public List<MvFunction> getFunctionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvFunction.class);
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
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvSchema.class);
  }

  @Override
  @NotNull
  public List<MvSpecFunction> getSpecFunctionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvSpecFunction.class);
  }

  @Override
  @NotNull
  public List<MvStruct> getStructList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvStruct.class);
  }

  @Override
  @NotNull
  public List<MvUseStmt> getUseStmtList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvUseStmt.class);
  }

  @Override
  @NotNull
  public PsiElement getLBrace() {
    return notNullChild(findChildByType(L_BRACE));
  }

  @Override
  @Nullable
  public PsiElement getRBrace() {
    return findChildByType(R_BRACE);
  }

}
