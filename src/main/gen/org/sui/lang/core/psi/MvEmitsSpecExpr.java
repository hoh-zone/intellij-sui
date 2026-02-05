// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MvEmitsSpecExpr extends MvExpr, MslOnlyElement {

  @Nullable
  MvEmitsCondition getEmitsCondition();

  @NotNull
  List<MvExpr> getExprList();

  @Nullable
  MvSpecPropertyList getSpecPropertyList();

}
