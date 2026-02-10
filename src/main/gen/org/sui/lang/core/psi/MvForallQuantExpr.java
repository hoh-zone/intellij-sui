// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.sui.lang.core.psi.ext.MvQuantExpr;

public interface MvForallQuantExpr extends MvExpr, MvQuantExpr, MslOnlyElement {

  @Nullable
  MvExpr getExpr();

  @Nullable
  MvQuantBindings getQuantBindings();

  @Nullable
  MvQuantWhere getQuantWhere();

  @Nullable
  PsiElement getColon();

}
