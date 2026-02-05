// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MvElseBlock extends MvElement {

  @Nullable
  MvCodeBlock getCodeBlock();

  @Nullable
  MvInlineBlock getInlineBlock();

  @NotNull
  PsiElement getElse();

}
