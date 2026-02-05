// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MvAttr extends MvElement {

  @NotNull
  List<MvAttrItem> getAttrItemList();

  @NotNull
  PsiElement getHash();

  @Nullable
  PsiElement getLBrack();

  @Nullable
  PsiElement getRBrack();

}
