// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MvCastExpr extends MvExpr {

  @Nullable
  MvApplySchemaStmt getApplySchemaStmt();

  @Nullable
  MvBinaryOp getBinaryOp();

  @Nullable
  MvCondition getCondition();

  @NotNull
  List<MvExpr> getExprList();

  @Nullable
  MvExprStmt getExprStmt();

  @Nullable
  MvForIterCondition getForIterCondition();

  @Nullable
  MvGlobalVariableStmt getGlobalVariableStmt();

  @Nullable
  MvIncludeStmt getIncludeStmt();

  @Nullable
  MvLabelRef getLabelRef();

  @Nullable
  MvLetStmt getLetStmt();

  @Nullable
  MvPragmaSpecStmt getPragmaSpecStmt();

  @Nullable
  MvSchemaFieldStmt getSchemaFieldStmt();

  @Nullable
  MvSpecExprStmt getSpecExprStmt();

  @Nullable
  MvSpecInlineFunctionStmt getSpecInlineFunctionStmt();

  @Nullable
  MvSpecPropertyList getSpecPropertyList();

  @Nullable
  MvStmt getStmt();

  @Nullable
  MvType getType();

  @Nullable
  MvTypeParameterList getTypeParameterList();

  @Nullable
  MvUpdateSpecStmt getUpdateSpecStmt();

  @Nullable
  MvUseStmt getUseStmt();

  @NotNull
  PsiElement getAs();

}
