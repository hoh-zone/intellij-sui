// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.sui.lang.core.psi.ext.MvItemElement;
import org.sui.lang.core.types.infer.MvInferenceContextOwner;
import com.intellij.psi.StubBasedPsiElement;
import org.sui.lang.core.stubs.MvFunctionStub;

public interface MvFunction extends MvQualNamedElement, MvFunctionLike, MvItemElement, MvInferenceContextOwner, MvModificationTrackerOwner, StubBasedPsiElement<MvFunctionStub> {

  @Nullable
  MvAcquiresType getAcquiresType();

  @NotNull
  List<MvAttr> getAttrList();

  @Nullable
  MvCodeBlock getCodeBlock();

  @Nullable
  MvFunctionParameterList getFunctionParameterList();

  @Nullable
  MvResourceAccessItemList getResourceAccessItemList();

  @Nullable
  MvReturnType getReturnType();

  @Nullable
  MvTypeParameterList getTypeParameterList();

  @Nullable
  MvVisibilityModifier getVisibilityModifier();

  @Nullable
  PsiElement getIdentifier();

  @Nullable
  PsiElement getMacroKw();

  @Nullable
  PsiElement getSemicolon();

  @Nullable
  PsiElement getFun();

  @Nullable
  PsiElement getNative();

}
