// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.sui.lang.core.psi.ext.MvQuantBindingsOwner;

public interface MvChooseQuantExpr extends MvExpr, MvQuantBindingsOwner, MslOnlyElement {

  @Nullable
  MvQuantBindings getQuantBindings();

  @Nullable
  MvQuantWhere getQuantWhere();

}
