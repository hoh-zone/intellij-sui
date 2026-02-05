// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.sui.lang.MvElementTypes.*;
import org.sui.lang.core.psi.ext.MvConstMixin;
import org.sui.lang.core.psi.*;
import com.intellij.psi.stubs.IStubElementType;
import org.sui.lang.core.stubs.MvConstStub;

public class MvConstImpl extends MvConstMixin implements MvConst {

  public MvConstImpl(ASTNode node) {
    super(node);
  }

  public MvConstImpl(MvConstStub stub, IStubElementType stubType) {
    super(stub, stubType);
  }

  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitConst(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof MvVisitor) accept((MvVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<MvAttr> getAttrList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, MvAttr.class);
  }

  @Override
  @Nullable
  public MvInitializer getInitializer() {
    return PsiTreeUtil.getChildOfType(this, MvInitializer.class);
  }

  @Override
  @Nullable
  public MvTypeAnnotation getTypeAnnotation() {
    return PsiTreeUtil.getChildOfType(this, MvTypeAnnotation.class);
  }

  @Override
  @NotNull
  public PsiElement getConstKw() {
    return notNullChild(findChildByType(CONST_KW));
  }

  @Override
  @Nullable
  public PsiElement getIdentifier() {
    return findChildByType(IDENTIFIER);
  }

  @Override
  @Nullable
  public PsiElement getSemicolon() {
    return findChildByType(SEMICOLON);
  }

}
