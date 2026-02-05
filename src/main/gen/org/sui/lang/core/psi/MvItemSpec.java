// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.sui.lang.core.types.infer.MvInferenceContextOwner;

public interface MvItemSpec extends MvInferenceContextOwner, MvModificationTrackerOwner, ScopeMslOnlyElement {

  @Nullable
  MvItemSpecRef getItemSpecRef();

  @Nullable
  MvItemSpecSignature getItemSpecSignature();

  @NotNull
  PsiElement getSpec();

}
