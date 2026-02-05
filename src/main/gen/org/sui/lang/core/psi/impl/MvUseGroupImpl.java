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

public class MvUseGroupImpl extends MvElementImpl implements MvUseGroup {

  public MvUseGroupImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitUseGroup(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<MvUseSpeck> getUseSpeckList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvUseSpeck.class);
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
