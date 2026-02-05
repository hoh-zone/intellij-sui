// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MvMatchBody extends MvElement {

  @NotNull
  List<MvMatchArm> getMatchArmList();

  @NotNull
  PsiElement getLBrace();

  @Nullable
  PsiElement getRBrace();

}
