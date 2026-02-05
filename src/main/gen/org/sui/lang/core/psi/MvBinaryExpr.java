// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MvBinaryExpr extends MvExpr {

  @NotNull
  MvBinaryOp getBinaryOp();

  @NotNull
  List<MvExpr> getExprList();

  @NotNull
  MvExpr getLeft();

  @Nullable
  MvExpr getRight();

}
