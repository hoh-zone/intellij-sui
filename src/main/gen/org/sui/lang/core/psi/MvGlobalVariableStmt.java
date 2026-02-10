// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.sui.lang.core.psi.ext.MvItemElement;

public interface MvGlobalVariableStmt extends MvStmt, MvNameIdentifierOwner, MvTypeAnnotationOwner, MslOnlyElement, MvItemElement {

  @NotNull
  List<MvAttr> getAttrList();

  @Nullable
  MvExpr getExpr();

  @Nullable
  MvTypeAnnotation getTypeAnnotation();

  @Nullable
  MvTypeParameterList getTypeParameterList();

  @Nullable
  PsiElement getEq();

  @Nullable
  PsiElement getIdentifier();

  @Nullable
  PsiElement getSemicolon();

}
