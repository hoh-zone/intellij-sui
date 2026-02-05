// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.sui.lang.core.types.infer.MvInferenceContextOwner;
import org.sui.lang.core.psi.ext.MvItemElement;

public interface MvSpecInlineFunction extends MvFunctionLike, MvInferenceContextOwner, MvItemElement, ScopeMslOnlyElement {

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

}
