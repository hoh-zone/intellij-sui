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

public class MvScriptImpl extends MvElementImpl implements MvScript {

  public MvScriptImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitScript(this);
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
  public List<MvFunction> getFunctionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvFunction.class);
  }

  @Override
  @NotNull
  public List<MvPublicUseFun> getPublicUseFunList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvPublicUseFun.class);
  }

  @Override
  @NotNull
  public List<MvUseStmt> getUseStmtList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvUseStmt.class);
  }

  @Override
  @Nullable
  public PsiElement getLBrace() {
    return findChildByType(L_BRACE);
  }

  @Override
  @Nullable
  public PsiElement getRBrace() {
    return findChildByType(R_BRACE);
  }

  @Override
  @NotNull
  public PsiElement getScriptKw() {
    return notNullChild(findChildByType(SCRIPT_KW));
  }

}
