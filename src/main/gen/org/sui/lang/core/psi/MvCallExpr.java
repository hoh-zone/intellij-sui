// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.sui.lang.core.psi.ext.MvCallable;
import org.sui.lang.core.resolve2.ref.InferenceCachedPathElement;

public interface MvCallExpr extends MvExpr, MvCallable, MvAcquireTypesOwner, InferenceCachedPathElement {

  @NotNull
  MvPath getPath();

  @NotNull
  MvValueArgumentList getValueArgumentList();

  @Nullable
  PsiElement getExcl();

}
