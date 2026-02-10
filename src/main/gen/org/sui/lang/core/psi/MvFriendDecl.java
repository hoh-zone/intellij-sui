// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.sui.lang.core.psi.ext.MvDocAndAttributeOwner;

public interface MvFriendDecl extends MvDocAndAttributeOwner {

  @NotNull
  List<MvAttr> getAttrList();

  @Nullable
  MvPath getPath();

  @Nullable
  PsiElement getSemicolon();

}
