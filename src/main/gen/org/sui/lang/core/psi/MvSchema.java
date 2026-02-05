// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.sui.lang.core.types.infer.MvInferenceContextOwner;
import org.sui.lang.core.psi.ext.MvItemElement;
import com.intellij.psi.StubBasedPsiElement;
import org.sui.lang.core.stubs.MvSchemaStub;

public interface MvSchema extends MvTypeParametersOwner, MvQualNamedElement, MvInferenceContextOwner, ScopeMslOnlyElement, MvItemElement, StubBasedPsiElement<MvSchemaStub> {

  @NotNull
  List<MvAttr> getAttrList();

  @Nullable
  MvTypeParameterList getTypeParameterList();

  @Nullable
  PsiElement getIdentifier();

  @NotNull
  PsiElement getSpec();

}
