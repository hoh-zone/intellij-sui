// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.sui.lang.core.psi.ext.MvItemElement;
import org.sui.lang.core.types.infer.MvInferenceContextOwner;
import com.intellij.psi.StubBasedPsiElement;
import org.sui.lang.core.stubs.MvSpecFunctionStub;

public interface MvSpecFunction extends MvQualNamedElement, MvFunctionLike, MvItemElement, MvInferenceContextOwner, ScopeMslOnlyElement, StubBasedPsiElement<MvSpecFunctionStub> {

  @NotNull
  List<MvAttr> getAttrList();

  @Nullable
  MvFunctionParameterList getFunctionParameterList();

  @Nullable
  MvReturnType getReturnType();

  @Nullable
  MvSpecCodeBlock getSpecCodeBlock();

  @Nullable
  MvTypeParameterList getTypeParameterList();

  @Nullable
  PsiElement getIdentifier();

  @Nullable
  PsiElement getSemicolon();

  @NotNull
  PsiElement getFun();

  @Nullable
  PsiElement getNative();

  @NotNull
  PsiElement getSpec();

}
