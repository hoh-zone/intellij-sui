// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.sui.lang.core.psi.ext.MvMethodOrField;
import org.sui.lang.core.psi.ext.MvMethodOrPath;
import org.sui.lang.core.psi.ext.MvCallable;

public interface MvMethodCall extends MvMethodOrField, MvMethodOrPath, MvCallable, MvAcquireTypesOwner {

  @Nullable
  MvTypeArgumentList getTypeArgumentList();

  @NotNull
  MvValueArgumentList getValueArgumentList();

  @Nullable
  PsiElement getExcl();

  @NotNull
  PsiElement getIdentifier();

}
