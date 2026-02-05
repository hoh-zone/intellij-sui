// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MvTypeParameterList extends MvElement {

  @NotNull
  List<MvTypeParameter> getTypeParameterList();

  @Nullable
  PsiElement getGt();

  @NotNull
  PsiElement getLt();

}
