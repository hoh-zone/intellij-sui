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

public class MvAndIncludeItemImpl extends MvIncludeItemImpl implements MvAndIncludeItem {

  public MvAndIncludeItemImpl(ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitAndIncludeItem(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public MvBinaryOp getBinaryOp() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, MvBinaryOp.class));
  }

  @Override
  @NotNull
  public List<MvSchemaLit> getSchemaLitList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvSchemaLit.class);
  }

}
