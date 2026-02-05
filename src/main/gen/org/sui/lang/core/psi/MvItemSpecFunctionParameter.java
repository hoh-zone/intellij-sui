// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.sui.lang.core.resolve.ref.MvItemSpecParameterReferenceElement;

public interface MvItemSpecFunctionParameter extends MvItemSpecParameterReferenceElement, MvTypeAnnotationOwner {

  @Nullable
  MvTypeAnnotation getTypeAnnotation();

  @Nullable
  PsiElement getIdentifier();

}
