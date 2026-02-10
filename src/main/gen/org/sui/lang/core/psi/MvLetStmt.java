// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MvLetStmt extends MvStmt, MvTypeAnnotationOwner {

  @Nullable
  MvInitializer getInitializer();

  @Nullable
  MvPat getPat();

  @Nullable
  MvTypeAnnotation getTypeAnnotation();

  @Nullable
  PsiElement getSemicolon();

  @NotNull
  PsiElement getLet();

}
