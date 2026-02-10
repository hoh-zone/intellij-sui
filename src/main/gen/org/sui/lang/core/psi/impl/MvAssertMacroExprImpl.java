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

public class MvAssertMacroExprImpl extends MvExprImpl implements MvAssertMacroExpr {

  public MvAssertMacroExprImpl(ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitAssertMacroExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public MvValueArgumentList getValueArgumentList() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, MvValueArgumentList.class));
  }

  @Override
  @NotNull
  public PsiElement getExcl() {
    return notNullChild(findChildByType(EXCL));
  }

}
