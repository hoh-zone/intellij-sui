// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.sui.lang.core.psi.ext.MvDocAndAttributeOwner;

public interface MvUseStmt extends MvStmt, MvDocAndAttributeOwner {

  @NotNull
  List<MvAttr> getAttrList();

  @Nullable
  MvUseSpeck getUseSpeck();

  @Nullable
  PsiElement getSemicolon();

  @NotNull
  PsiElement getUse();

}
