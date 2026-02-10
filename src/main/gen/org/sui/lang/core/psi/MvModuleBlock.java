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
  List<MvItemSpec> getItemSpecList();

  @NotNull
  List<MvModuleItemSpec> getModuleItemSpecList();

  @NotNull
  List<MvPublicUseFun> getPublicUseFunList();

  @NotNull
  List<MvSchema> getSchemaList();

  @NotNull
  List<MvSpecFunction> getSpecFunctionList();

  @NotNull
  List<MvStruct> getStructList();

  @NotNull
  List<MvUseStmt> getUseStmtList();

  @NotNull
  PsiElement getLBrace();

  @Nullable
  PsiElement getRBrace();

}
