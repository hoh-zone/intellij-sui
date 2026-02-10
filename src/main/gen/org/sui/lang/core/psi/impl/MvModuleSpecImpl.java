// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.sui.lang.MvElementTypes.*;
import org.sui.lang.core.psi.ext.MvModuleSpecMixin;
import org.sui.lang.core.psi.*;
import com.intellij.psi.stubs.IStubElementType;
import org.sui.lang.core.stubs.MvModuleSpecStub;

public class MvModuleSpecImpl extends MvModuleSpecMixin implements MvModuleSpec {

  public MvModuleSpecImpl(ASTNode node) {
    super(node);
  }

  public MvModuleSpecImpl(MvModuleSpecStub stub, IStubElementType stubType) {
    super(stub, stubType);
  }

  public void accept(@NotNull MvVisitor visitor) {
    visitor.visitModuleSpec(this);
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
  public MvModuleSpecBlock getModuleSpecBlock() {
    return PsiTreeUtil.getChildOfType(this, MvModuleSpecBlock.class);
  }

  @Override
  @Nullable
  public MvPath getPath() {
    return PsiTreeUtil.getChildOfType(this, MvPath.class);
  }

  @Override
  @NotNull
  public PsiElement getSpec() {
    return notNullChild(findChildByType(SPEC));
  }

}
