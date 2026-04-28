// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MvCastExpr extends MvExpr {

  @Nullable
  MvBinaryOp getBinaryOp();

  @Nullable
  MvCondition getCondition();

  @NotNull
  List<MvExpr> getExprList();

  @Nullable
  MvForIterCondition getForIterCondition();

  @Nullable
  MvLabelRef getLabelRef();

  @Nullable
  MvStmt getStmt();

  @Nullable
  MvType getType();

  @Nullable
  MvUseStmt getUseStmt();

  @NotNull
  PsiElement getAs();

}
