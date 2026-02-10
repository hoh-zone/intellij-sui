// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.sui.lang.core.psi.ext.MvStructOrEnumItemElement;
import com.intellij.psi.StubBasedPsiElement;
import org.sui.lang.core.stubs.MvEnumStub;

public interface MvEnum extends MvTypeParametersOwner, MvStructOrEnumItemElement, StubBasedPsiElement<MvEnumStub> {

  @Nullable
  MvAbilitiesList getAbilitiesList();

  @NotNull
  List<MvAttr> getAttrList();

  @Nullable
  MvEnumBody getEnumBody();

  @Nullable
  MvTypeParameterList getTypeParameterList();

  @Nullable
  MvVisibilityModifier getVisibilityModifier();

  @Nullable
  PsiElement getIdentifier();

}
