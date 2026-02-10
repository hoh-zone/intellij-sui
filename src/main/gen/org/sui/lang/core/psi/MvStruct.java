// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.sui.lang.core.psi.ext.MvStructOrEnumItemElement;
import org.sui.lang.core.psi.ext.MvFieldsOwner;
import com.intellij.psi.StubBasedPsiElement;
import org.sui.lang.core.stubs.MvStructStub;

public interface MvStruct extends MvQualNamedElement, MvTypeParametersOwner, MvStructOrEnumItemElement, MvFieldsOwner, StubBasedPsiElement<MvStructStub> {

  @Nullable
  MvAbilitiesList getAbilitiesList();

  @NotNull
  List<MvAttr> getAttrList();

  @Nullable
  MvBlockFields getBlockFields();

  @Nullable
  MvTupleFields getTupleFields();

  @Nullable
  MvTypeParameterList getTypeParameterList();

  @NotNull
  PsiElement getIdentifier();

  @Nullable
  PsiElement getSemicolon();

  @NotNull
  PsiElement getStructKw();

  @Nullable
  PsiElement getNative();

}
