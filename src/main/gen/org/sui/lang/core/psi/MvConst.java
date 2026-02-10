// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.sui.lang.core.psi.ext.MvItemElement;
import com.intellij.psi.StubBasedPsiElement;
import org.sui.lang.core.stubs.MvConstStub;

public interface MvConst extends MvQualNamedElement, MvItemElement, MvTypeAnnotationOwner, StubBasedPsiElement<MvConstStub> {

  @NotNull
  List<MvAttr> getAttrList();

  @Nullable
  MvInitializer getInitializer();

  @Nullable
  MvTypeAnnotation getTypeAnnotation();

  @NotNull
  PsiElement getConstKw();

  @Nullable
  PsiElement getIdentifier();

  @Nullable
  PsiElement getSemicolon();

}
