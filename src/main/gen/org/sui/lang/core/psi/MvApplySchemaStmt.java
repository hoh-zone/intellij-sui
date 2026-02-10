// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MvApplySchemaStmt extends MvStmt, MslOnlyElement {

  @Nullable
  MvApplyExcept getApplyExcept();

  @Nullable
  MvApplyTo getApplyTo();

  @Nullable
  MvSchemaRef getSchemaRef();

  @Nullable
  PsiElement getSemicolon();

}
