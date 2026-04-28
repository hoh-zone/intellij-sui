// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.sui.lang.core.psi.ext.MvItemsOwner;

public interface MvModuleBlock extends MvItemsOwner {

  @NotNull
  List<MvConst> getConstList();

  @NotNull
  List<MvEnum> getEnumList();

  @NotNull
  List<MvFriendDecl> getFriendDeclList();

  @NotNull
  List<MvFunction> getFunctionList();

  @NotNull
  List<MvPublicUseFun> getPublicUseFunList();

  @NotNull
  List<MvStruct> getStructList();

  @NotNull
  List<MvUseStmt> getUseStmtList();

  @NotNull
  PsiElement getLBrace();

  @Nullable
  PsiElement getRBrace();

}
