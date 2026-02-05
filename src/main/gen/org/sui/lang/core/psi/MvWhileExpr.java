// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MvWhileExpr extends MvExpr, MvLoopLike {

  @Nullable
  MvCodeBlock getCodeBlock();

  @Nullable
  MvCondition getCondition();

  @Nullable
  MvInlineBlock getInlineBlock();

  @NotNull
  PsiElement getWhile();

}
