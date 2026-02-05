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

public class MvPatStructImpl extends MvPatImpl implements MvPatStruct {

  public MvPatStructImpl(ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitPatStruct(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<MvPatField> getPatFieldList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvPatField.class);
  }

  @Override
  @NotNull
  public MvPath getPath() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, MvPath.class));
  }

  @Override
  @NotNull
  public PsiElement getLBrace() {
    return notNullChild(findChildByType(L_BRACE));
  }

  @Override
  @NotNull
  public PsiElement getRBrace() {
    return notNullChild(findChildByType(R_BRACE));
  }

}
