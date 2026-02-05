// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MvVectorLitExpr extends MvExpr {

  @Nullable
  MvTypeArgument getTypeArgument();

  @NotNull
  MvVectorLitItems getVectorLitItems();

  @Nullable
  PsiElement getGt();

  @Nullable
  PsiElement getLt();

}
