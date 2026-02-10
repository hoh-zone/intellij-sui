// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.sui.lang.core.resolve.ref.MvNameAccessChainReferenceElement;
import org.sui.lang.core.psi.ext.MvMethodOrPath;

public interface MvPath extends MvNameAccessChainReferenceElement, MvMethodOrPath {

  @Nullable
  MvPath getPath();

  @Nullable
  MvPathAddress getPathAddress();

  @Nullable
  MvTypeArgumentList getTypeArgumentList();

  @Nullable
  PsiElement getColonColon();

  @Nullable
  PsiElement getExcl();

  @Nullable
  PsiElement getIdentifier();

  @Nullable
  PsiElement getMul();

  @Nullable
  PsiElement getQuoteIdentifier();

}
