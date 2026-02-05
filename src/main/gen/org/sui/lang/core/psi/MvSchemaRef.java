// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MvSchemaRef extends MslOnlyElement {

  @NotNull
  List<MvApplySchemaNameAttribute> getApplySchemaNameAttributeList();

  @NotNull
  MvPath getPath();

  @Nullable
  PsiElement getLBrace();

  @Nullable
  PsiElement getRBrace();

}
