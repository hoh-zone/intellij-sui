// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MvIfElseIncludeItem extends MvIncludeItem {

  @NotNull
  MvCondition getCondition();

  @NotNull
  List<MvSchemaLit> getSchemaLitList();

  @NotNull
  PsiElement getElse();

  @NotNull
  PsiElement getIf();

}
