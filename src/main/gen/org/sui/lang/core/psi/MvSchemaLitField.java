// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.sui.lang.core.resolve.ref.MvSchemaRefFieldReferenceElement;

public interface MvSchemaLitField extends MvSchemaRefFieldReferenceElement, MslOnlyElement {

  @Nullable
  MvExpr getExpr();

  @Nullable
  PsiElement getColon();

  @NotNull
  PsiElement getIdentifier();

}
