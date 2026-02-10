// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.sui.lang.core.psi.ext.MvDocAndAttributeOwner;
import org.sui.lang.core.psi.ext.MvFieldsOwner;
import com.intellij.psi.StubBasedPsiElement;
import org.sui.lang.core.stubs.MvEnumVariantStub;

public interface MvEnumVariant extends MvNameIdentifierOwner, MvDocAndAttributeOwner, MvFieldsOwner, StubBasedPsiElement<MvEnumVariantStub> {

  @NotNull
  List<MvAttr> getAttrList();

  @Nullable
  MvBlockFields getBlockFields();

  @Nullable
  MvTupleFields getTupleFields();

  @NotNull
  PsiElement getIdentifier();

}
