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

public class MvLetStmtImpl extends MvStmtImpl implements MvLetStmt {

  public MvLetStmtImpl(ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitLetStmt(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public MvInitializer getInitializer() {
    return PsiTreeUtil.getChildOfType(this, MvInitializer.class);
  }

  @Override
  @Nullable
  public MvPat getPat() {
    return PsiTreeUtil.getChildOfType(this, MvPat.class);
  }

  @Override
  @Nullable
  public MvTypeAnnotation getTypeAnnotation() {
    return PsiTreeUtil.getChildOfType(this, MvTypeAnnotation.class);
  }

  @Override
  @Nullable
  public PsiElement getSemicolon() {
    return findChildByType(SEMICOLON);
  }

  @Override
  @NotNull
  public PsiElement getLet() {
    return notNullChild(findChildByType(LET));
  }

}
