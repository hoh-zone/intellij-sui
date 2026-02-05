// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MvDotExpr extends MvExpr {

  @NotNull
  MvExpr getExpr();

  @Nullable
  MvMethodCall getMethodCall();

  @Nullable
  MvStructDotField getStructDotField();

  @NotNull
  PsiElement getDot();

}
