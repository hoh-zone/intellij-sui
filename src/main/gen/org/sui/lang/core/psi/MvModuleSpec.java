// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import org.sui.lang.core.stubs.MvModuleSpecStub;

public interface MvModuleSpec extends MvElement, StubBasedPsiElement<MvModuleSpecStub> {

  @NotNull
  List<MvAttr> getAttrList();

  @Nullable
  MvModuleSpecBlock getModuleSpecBlock();

  @Nullable
  MvPath getPath();

  @NotNull
  PsiElement getSpec();

}
