// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MvIndexExpr extends MvExpr, MvAcquireTypesOwner {

  @NotNull
  List<MvExpr> getExprList();

  @NotNull
  PsiElement getLBrack();

  @NotNull
  PsiElement getRBrack();

}
