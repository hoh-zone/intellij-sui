// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface MvAddressRef extends MvElement {

  @Nullable
  MvNamedAddress getNamedAddress();

  @Nullable
  PsiElement getBech32Address();

  @Nullable
  PsiElement getDiemAddress();

  @Nullable
  PsiElement getIntegerLiteral();

  @Nullable
  PsiElement getPlaceholderAddress();

  @Nullable
  PsiElement getPolkadotAddress();

}
