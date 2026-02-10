// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.sui.lang.core.psi.ext.MvItemsOwner;

public interface MvScript extends MvItemsOwner {

  @NotNull
  List<MvConst> getConstList();

  @NotNull
  List<MvFunction> getFunctionList();

  @NotNull
  List<MvPublicUseFun> getPublicUseFunList();

  @NotNull
  List<MvUseStmt> getUseStmtList();

  @Nullable
  PsiElement getLBrace();

  @Nullable
  PsiElement getRBrace();

  @NotNull
  PsiElement getScriptKw();

}
