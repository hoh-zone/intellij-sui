// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.sui.lang.core.resolve2.ref.InferenceCachedPathElement;

public interface MvPatStruct extends MvPat, InferenceCachedPathElement {

  @NotNull
  List<MvPatField> getPatFieldList();

  @NotNull
  MvPath getPath();

  @NotNull
  PsiElement getLBrace();

  @NotNull
  PsiElement getRBrace();

}
