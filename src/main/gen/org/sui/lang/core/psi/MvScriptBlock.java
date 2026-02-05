// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MvScriptBlock extends MvElement {

  @NotNull
  List<MvConst> getConstList();

  @NotNull
  List<MvFunction> getFunctionList();

  @NotNull
  List<MvUseStmt> getUseStmtList();

  @NotNull
  PsiElement getLBrace();

  @NotNull
  PsiElement getRBrace();

}
