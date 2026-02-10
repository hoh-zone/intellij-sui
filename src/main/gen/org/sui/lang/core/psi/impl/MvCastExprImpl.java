// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.sui.lang.MvElementTypes.*;
import org.sui.lang.core.psi.*;

public class MvCastExprImpl extends MvExprImpl implements MvCastExpr {

  public MvCastExprImpl(ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitCastExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MvApplySchemaStmt getApplySchemaStmt() {
    return PsiTreeUtil.getChildOfType(this, MvApplySchemaStmt.class);
  }

  @Override
  @Nullable
  public MvBinaryOp getBinaryOp() {
    return PsiTreeUtil.getChildOfType(this, MvBinaryOp.class);
  }

  @Override
  @Nullable
  public MvCondition getCondition() {
    return PsiTreeUtil.getChildOfType(this, MvCondition.class);
  }

  @Override
  @NotNull
  public List<MvExpr> getExprList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvExpr.class);
  }

  @Override
  @Nullable
  public MvExprStmt getExprStmt() {
    return PsiTreeUtil.getChildOfType(this, MvExprStmt.class);
  }

  @Override
  @Nullable
  public MvForIterCondition getForIterCondition() {
    return PsiTreeUtil.getChildOfType(this, MvForIterCondition.class);
  }

  @Override
  @Nullable
  public MvGlobalVariableStmt getGlobalVariableStmt() {
    return PsiTreeUtil.getChildOfType(this, MvGlobalVariableStmt.class);
  }

  @Override
  @Nullable
  public MvIncludeStmt getIncludeStmt() {
    return PsiTreeUtil.getChildOfType(this, MvIncludeStmt.class);
  }

  @Override
  @Nullable
  public MvLabelRef getLabelRef() {
    return PsiTreeUtil.getChildOfType(this, MvLabelRef.class);
  }

  @Override
  @Nullable
  public MvLetStmt getLetStmt() {
    return PsiTreeUtil.getChildOfType(this, MvLetStmt.class);
  }

  @Override
  @Nullable
  public MvPragmaSpecStmt getPragmaSpecStmt() {
    return PsiTreeUtil.getChildOfType(this, MvPragmaSpecStmt.class);
  }

  @Override
  @Nullable
  public MvSchemaFieldStmt getSchemaFieldStmt() {
    return PsiTreeUtil.getChildOfType(this, MvSchemaFieldStmt.class);
  }

  @Override
  @Nullable
  public MvSpecExprStmt getSpecExprStmt() {
    return PsiTreeUtil.getChildOfType(this, MvSpecExprStmt.class);
  }

  @Override
  @Nullable
  public MvSpecInlineFunctionStmt getSpecInlineFunctionStmt() {
    return PsiTreeUtil.getChildOfType(this, MvSpecInlineFunctionStmt.class);
  }

  @Override
  @Nullable
  public MvSpecPropertyList getSpecPropertyList() {
    return PsiTreeUtil.getChildOfType(this, MvSpecPropertyList.class);
  }

  @Override
  @Nullable
  public MvStmt getStmt() {
    return PsiTreeUtil.getChildOfType(this, MvStmt.class);
  }

  @Override
  @Nullable
  public MvType getType() {
    return PsiTreeUtil.getChildOfType(this, MvType.class);
  }

  @Override
  @Nullable
  public MvTypeParameterList getTypeParameterList() {
    return PsiTreeUtil.getChildOfType(this, MvTypeParameterList.class);
  }

  @Override
  @Nullable
  public MvUpdateSpecStmt getUpdateSpecStmt() {
    return PsiTreeUtil.getChildOfType(this, MvUpdateSpecStmt.class);
  }

  @Override
  @Nullable
  public MvUseStmt getUseStmt() {
    return PsiTreeUtil.getChildOfType(this, MvUseStmt.class);
  }

  @Override
  @NotNull
  public PsiElement getAs() {
    return notNullChild(findChildByType(AS));
  }

}
