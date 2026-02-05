// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.sui.lang.core.psi.ext.MvDocAndAttributeOwner;
import org.sui.lang.core.psi.ext.MvItemsOwner;
import com.intellij.psi.StubBasedPsiElement;
import org.sui.lang.core.stubs.MvModuleStub;

public interface MvModule extends MvQualNamedElement, MvNameIdentifierOwner, MvDocAndAttributeOwner, MvItemsOwner, StubBasedPsiElement<MvModuleStub> {

  @Nullable
  MvAddressRef getAddressRef();

  @NotNull
  List<MvAttr> getAttrList();

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

  @Nullable
  PsiElement getColonColon();

  @Nullable
  PsiElement getIdentifier();

  @Nullable
  PsiElement getLBrace();

  @NotNull
  PsiElement getModuleKw();

  @Nullable
  PsiElement getRBrace();

  @Nullable
  PsiElement getSemicolon();

}
