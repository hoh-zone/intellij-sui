// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import org.sui.lang.core.psi.ext.MvCallable;
import org.sui.lang.core.resolve2.ref.InferenceCachedPathElement;
import org.sui.lang.core.resolve.ref.MvReferenceElement;
import org.sui.lang.core.psi.ext.MvItemsOwner;
import org.sui.lang.core.psi.ext.MvQuantExpr;
import org.sui.lang.core.psi.ext.MvDocAndAttributeOwner;
import org.sui.lang.core.types.infer.MvInferenceContextOwner;
import org.sui.lang.core.psi.ext.MvItemElement;
import org.sui.lang.core.resolve.ref.MvNameAccessChainReferenceElement;
import org.sui.lang.core.psi.ext.MvMethodOrPath;
import org.sui.lang.core.psi.ext.MvFieldsOwner;
import org.sui.lang.core.psi.ext.MvMethodOrField;
import org.sui.lang.core.psi.ext.MvStructOrEnumItemElement;
import org.sui.lang.core.resolve.ref.MvMandatoryReferenceElement;
import org.sui.lang.core.resolve.ref.NamedAddressReferenceElement;
import org.sui.lang.core.psi.ext.MvQuantBindingsOwner;
import org.sui.lang.core.resolve.ref.MvItemSpecParameterReferenceElement;
import org.sui.lang.core.resolve.ref.MvSchemaRefFieldReferenceElement;

public class MvVisitor extends PsiElementVisitor {

  public void visitAbilitiesList(@NotNull MvAbilitiesList o) {
    visitElement(o);
  }

  public void visitAbility(@NotNull MvAbility o) {
    visitElement(o);
  }

  public void visitAbortExpr(@NotNull MvAbortExpr o) {
    visitExpr(o);
  }

  public void visitAbortsIfSpecExpr(@NotNull MvAbortsIfSpecExpr o) {
    visitExpr(o);
    // visitBoolSpecExpr(o);
    // visitMslOnlyElement(o);
  }

  public void visitAbortsIfWith(@NotNull MvAbortsIfWith o) {
    visitMslOnlyElement(o);
  }

  public void visitAbortsWithSpecExpr(@NotNull MvAbortsWithSpecExpr o) {
    visitExpr(o);
    // visitMslOnlyElement(o);
  }

  public void visitAccessSpecifier(@NotNull MvAccessSpecifier o) {
    visitElement(o);
  }

  public void visitAccessSpecifierList(@NotNull MvAccessSpecifierList o) {
    visitElement(o);
  }

  public void visitAcquiresType(@NotNull MvAcquiresType o) {
    visitElement(o);
  }

  public void visitAddressBlock(@NotNull MvAddressBlock o) {
    visitElement(o);
  }

  public void visitAddressDef(@NotNull MvAddressDef o) {
    visitElement(o);
  }

  public void visitAddressLit(@NotNull MvAddressLit o) {
    visitElement(o);
  }

  public void visitAddressRef(@NotNull MvAddressRef o) {
    visitElement(o);
  }

  public void visitAddressSpecifier(@NotNull MvAddressSpecifier o) {
    visitElement(o);
  }

  public void visitAddressSpecifierArg(@NotNull MvAddressSpecifierArg o) {
    visitElement(o);
  }

  public void visitAddressSpecifierCallParam(@NotNull MvAddressSpecifierCallParam o) {
    visitElement(o);
  }

  public void visitAddressSpecifierLit(@NotNull MvAddressSpecifierLit o) {
    visitElement(o);
  }

  public void visitAndIncludeItem(@NotNull MvAndIncludeItem o) {
    visitIncludeItem(o);
  }

  public void visitAnnotatedExpr(@NotNull MvAnnotatedExpr o) {
    visitExpr(o);
  }

  public void visitApplyExcept(@NotNull MvApplyExcept o) {
    visitMslOnlyElement(o);
  }

  public void visitApplySchemaNameAttribute(@NotNull MvApplySchemaNameAttribute o) {
    visitMslOnlyElement(o);
  }

  public void visitApplySchemaStmt(@NotNull MvApplySchemaStmt o) {
    visitStmt(o);
    // visitMslOnlyElement(o);
  }

  public void visitApplyTo(@NotNull MvApplyTo o) {
    visitMslOnlyElement(o);
  }

  public void visitAssertBangExpr(@NotNull MvAssertBangExpr o) {
    visitExpr(o);
  }

  public void visitAssertMacroExpr(@NotNull MvAssertMacroExpr o) {
    visitExpr(o);
    // visitCallable(o);
  }

  public void visitAssertSpecExpr(@NotNull MvAssertSpecExpr o) {
    visitExpr(o);
    // visitBoolSpecExpr(o);
    // visitMslOnlyElement(o);
  }

  public void visitAssignmentExpr(@NotNull MvAssignmentExpr o) {
    visitExpr(o);
  }

  public void visitAssumeSpecExpr(@NotNull MvAssumeSpecExpr o) {
    visitExpr(o);
    // visitBoolSpecExpr(o);
    // visitMslOnlyElement(o);
  }

  public void visitAttr(@NotNull MvAttr o) {
    visitElement(o);
  }

  public void visitAttrItem(@NotNull MvAttrItem o) {
    visitReferenceElement(o);
  }

  public void visitAttrItemInitializer(@NotNull MvAttrItemInitializer o) {
    visitElement(o);
  }

  public void visitAttrItemList(@NotNull MvAttrItemList o) {
    visitElement(o);
  }

  public void visitAxiomSpecExpr(@NotNull MvAxiomSpecExpr o) {
    visitExpr(o);
    // visitBoolSpecExpr(o);
    // visitTypeParametersOwner(o);
    // visitMslOnlyElement(o);
  }

  public void visitBangExpr(@NotNull MvBangExpr o) {
    visitExpr(o);
  }

  public void visitBinaryExpr(@NotNull MvBinaryExpr o) {
    visitExpr(o);
  }

  public void visitBinaryOp(@NotNull MvBinaryOp o) {
    visitElement(o);
  }

  public void visitBlockFields(@NotNull MvBlockFields o) {
    visitElement(o);
  }

  public void visitBorrowExpr(@NotNull MvBorrowExpr o) {
    visitExpr(o);
  }

  public void visitBreakExpr(@NotNull MvBreakExpr o) {
    visitExpr(o);
  }

  public void visitCallExpr(@NotNull MvCallExpr o) {
    visitExpr(o);
    // visitCallable(o);
    // visitAcquireTypesOwner(o);
    // visitInferenceCachedPathElement(o);
  }

  public void visitCastExpr(@NotNull MvCastExpr o) {
    visitExpr(o);
  }

  public void visitChooseQuantExpr(@NotNull MvChooseQuantExpr o) {
    visitExpr(o);
    // visitQuantBindingsOwner(o);
    // visitMslOnlyElement(o);
  }

  public void visitCodeBlock(@NotNull MvCodeBlock o) {
    visitItemsOwner(o);
    // visitAnyBlock(o);
  }

  public void visitCodeBlockExpr(@NotNull MvCodeBlockExpr o) {
    visitExpr(o);
  }

  public void visitCondition(@NotNull MvCondition o) {
    visitElement(o);
  }

  public void visitConst(@NotNull MvConst o) {
    visitQualNamedElement(o);
    // visitItemElement(o);
    // visitTypeAnnotationOwner(o);
  }

  public void visitConstPat(@NotNull MvConstPat o) {
    visitElement(o);
  }

  public void visitContinueExpr(@NotNull MvContinueExpr o) {
    visitExpr(o);
  }

  public void visitCopyExpr(@NotNull MvCopyExpr o) {
    visitExpr(o);
  }

  public void visitDecreasesSpecExpr(@NotNull MvDecreasesSpecExpr o) {
    visitExpr(o);
    // visitMslOnlyElement(o);
  }

  public void visitDerefExpr(@NotNull MvDerefExpr o) {
    visitExpr(o);
  }

  public void visitDotExpr(@NotNull MvDotExpr o) {
    visitExpr(o);
  }

  public void visitElseBlock(@NotNull MvElseBlock o) {
    visitElement(o);
  }

  public void visitEmitsCondition(@NotNull MvEmitsCondition o) {
    visitMslOnlyElement(o);
  }

  public void visitEmitsSpecExpr(@NotNull MvEmitsSpecExpr o) {
    visitExpr(o);
    // visitMslOnlyElement(o);
  }

  public void visitEnsuresSpecExpr(@NotNull MvEnsuresSpecExpr o) {
    visitExpr(o);
    // visitBoolSpecExpr(o);
    // visitMslOnlyElement(o);
  }

  public void visitEnum(@NotNull MvEnum o) {
    visitTypeParametersOwner(o);
    // visitStructOrEnumItemElement(o);
  }

  public void visitEnumBody(@NotNull MvEnumBody o) {
    visitElement(o);
  }

  public void visitEnumVariant(@NotNull MvEnumVariant o) {
    visitNameIdentifierOwner(o);
    // visitDocAndAttributeOwner(o);
    // visitFieldsOwner(o);
  }

  public void visitEnumVariantPat(@NotNull MvEnumVariantPat o) {
    visitElement(o);
  }

  public void visitExistsQuantExpr(@NotNull MvExistsQuantExpr o) {
    visitExpr(o);
    // visitQuantExpr(o);
    // visitMslOnlyElement(o);
  }

  public void visitExpr(@NotNull MvExpr o) {
    visitElement(o);
  }

  public void visitExprStmt(@NotNull MvExprStmt o) {
    visitStmt(o);
  }

  public void visitFieldPat(@NotNull MvFieldPat o) {
    visitElement(o);
  }

  public void visitFieldPatBinding(@NotNull MvFieldPatBinding o) {
    visitElement(o);
  }

  public void visitFieldPatFull(@NotNull MvFieldPatFull o) {
    visitElement(o);
  }

  public void visitForExpr(@NotNull MvForExpr o) {
    visitExpr(o);
    // visitLoopLike(o);
  }

  public void visitForIterCondition(@NotNull MvForIterCondition o) {
    visitElement(o);
  }

  public void visitForallQuantExpr(@NotNull MvForallQuantExpr o) {
    visitExpr(o);
    // visitQuantExpr(o);
    // visitMslOnlyElement(o);
  }

  public void visitFriendDecl(@NotNull MvFriendDecl o) {
    visitDocAndAttributeOwner(o);
  }

  public void visitFunction(@NotNull MvFunction o) {
    visitQualNamedElement(o);
    // visitFunctionLike(o);
    // visitItemElement(o);
    // visitInferenceContextOwner(o);
    // visitModificationTrackerOwner(o);
  }

  public void visitFunctionParameter(@NotNull MvFunctionParameter o) {
    visitTypeAnnotationOwner(o);
  }

  public void visitFunctionParameterList(@NotNull MvFunctionParameterList o) {
    visitElement(o);
  }

  public void visitFunctionPattern(@NotNull MvFunctionPattern o) {
    visitMslOnlyElement(o);
  }

  public void visitGlobalVariableStmt(@NotNull MvGlobalVariableStmt o) {
    visitStmt(o);
    // visitNameIdentifierOwner(o);
    // visitTypeAnnotationOwner(o);
    // visitMslOnlyElement(o);
    // visitItemElement(o);
  }

  public void visitIfElseIncludeItem(@NotNull MvIfElseIncludeItem o) {
    visitIncludeItem(o);
  }

  public void visitIfExpr(@NotNull MvIfExpr o) {
    visitExpr(o);
  }

  public void visitImplyIncludeItem(@NotNull MvImplyIncludeItem o) {
    visitIncludeItem(o);
  }

  public void visitIncludeItem(@NotNull MvIncludeItem o) {
    visitMslOnlyElement(o);
  }

  public void visitIncludeStmt(@NotNull MvIncludeStmt o) {
    visitStmt(o);
    // visitMslOnlyElement(o);
  }

  public void visitIndexExpr(@NotNull MvIndexExpr o) {
    visitExpr(o);
    // visitAcquireTypesOwner(o);
  }

  public void visitInitializer(@NotNull MvInitializer o) {
    visitElement(o);
  }

  public void visitInlineBlock(@NotNull MvInlineBlock o) {
    visitElement(o);
  }

  public void visitInvariantSpecExpr(@NotNull MvInvariantSpecExpr o) {
    visitExpr(o);
    // visitBoolSpecExpr(o);
    // visitMslOnlyElement(o);
  }

  public void visitItemSpec(@NotNull MvItemSpec o) {
    visitInferenceContextOwner(o);
    // visitModificationTrackerOwner(o);
    // visitScopeMslOnlyElement(o);
  }

  public void visitItemSpecBlockExpr(@NotNull MvItemSpecBlockExpr o) {
    visitExpr(o);
    // visitScopeMslOnlyElement(o);
  }

  public void visitItemSpecFunctionParameter(@NotNull MvItemSpecFunctionParameter o) {
    visitItemSpecParameterReferenceElement(o);
    // visitTypeAnnotationOwner(o);
  }

  public void visitItemSpecFunctionParameterList(@NotNull MvItemSpecFunctionParameterList o) {
    visitElement(o);
  }

  public void visitItemSpecRef(@NotNull MvItemSpecRef o) {
    visitMandatoryReferenceElement(o);
  }

  public void visitItemSpecSignature(@NotNull MvItemSpecSignature o) {
    visitElement(o);
  }

  public void visitItemSpecTypeParameter(@NotNull MvItemSpecTypeParameter o) {
    visitItemSpecParameterReferenceElement(o);
  }

  public void visitItemSpecTypeParameterList(@NotNull MvItemSpecTypeParameterList o) {
    visitElement(o);
  }

  public void visitLabelDecl(@NotNull MvLabelDecl o) {
    visitElement(o);
  }

  public void visitLabelRef(@NotNull MvLabelRef o) {
    visitElement(o);
  }

  public void visitLabeledBlockExpr(@NotNull MvLabeledBlockExpr o) {
    visitExpr(o);
  }

  public void visitLambdaExpr(@NotNull MvLambdaExpr o) {
    visitExpr(o);
  }

  public void visitLambdaExprBlock(@NotNull MvLambdaExprBlock o) {
    visitElement(o);
  }

  public void visitLambdaParam(@NotNull MvLambdaParam o) {
    visitElement(o);
  }

  public void visitLambdaType(@NotNull MvLambdaType o) {
    visitType(o);
  }

  public void visitLambdaTypeParam(@NotNull MvLambdaTypeParam o) {
    visitElement(o);
  }

  public void visitLetStmt(@NotNull MvLetStmt o) {
    visitStmt(o);
    // visitTypeAnnotationOwner(o);
  }

  public void visitLitExpr(@NotNull MvLitExpr o) {
    visitExpr(o);
  }

  public void visitLoopExpr(@NotNull MvLoopExpr o) {
    visitExpr(o);
    // visitLoopLike(o);
  }

  public void visitMacroIdent(@NotNull MvMacroIdent o) {
    visitElement(o);
  }

  public void visitMatchArgument(@NotNull MvMatchArgument o) {
    visitElement(o);
  }

  public void visitMatchArm(@NotNull MvMatchArm o) {
    visitDocAndAttributeOwner(o);
  }

  public void visitMatchArmGuard(@NotNull MvMatchArmGuard o) {
    visitElement(o);
  }

  public void visitMatchBody(@NotNull MvMatchBody o) {
    visitElement(o);
  }

  public void visitMatchExpr(@NotNull MvMatchExpr o) {
    visitExpr(o);
    // visitDocAndAttributeOwner(o);
  }

  public void visitMatchPat(@NotNull MvMatchPat o) {
    visitElement(o);
  }

  public void visitMethodCall(@NotNull MvMethodCall o) {
    visitMethodOrField(o);
    // visitMethodOrPath(o);
    // visitCallable(o);
    // visitAcquireTypesOwner(o);
  }

  public void visitModifiesSpecExpr(@NotNull MvModifiesSpecExpr o) {
    visitExpr(o);
    // visitMslOnlyElement(o);
  }

  public void visitModule(@NotNull MvModule o) {
    visitQualNamedElement(o);
    // visitNameIdentifierOwner(o);
    // visitDocAndAttributeOwner(o);
    // visitItemsOwner(o);
  }

  public void visitModuleBlock(@NotNull MvModuleBlock o) {
    visitItemsOwner(o);
  }

  public void visitModuleItemSpec(@NotNull MvModuleItemSpec o) {
    visitInferenceContextOwner(o);
    // visitScopeMslOnlyElement(o);
  }

  public void visitModuleSpec(@NotNull MvModuleSpec o) {
    visitElement(o);
  }

  public void visitModuleSpecBlock(@NotNull MvModuleSpecBlock o) {
    visitItemsOwner(o);
    // visitScopeMslOnlyElement(o);
    // visitAnyBlock(o);
  }

  public void visitMoveExpr(@NotNull MvMoveExpr o) {
    visitExpr(o);
  }

  public void visitNamedAddress(@NotNull MvNamedAddress o) {
    visitNamedAddressReferenceElement(o);
  }

  public void visitNamedAddressDef(@NotNull MvNamedAddressDef o) {
    visitElement(o);
  }

  public void visitNamedFieldDecl(@NotNull MvNamedFieldDecl o) {
    visitMandatoryNameIdentifierOwner(o);
    // visitDocAndAttributeOwner(o);
    // visitTypeAnnotationOwner(o);
  }

  public void visitParensExpr(@NotNull MvParensExpr o) {
    visitExpr(o);
  }

  public void visitParensType(@NotNull MvParensType o) {
    visitType(o);
  }

  public void visitPat(@NotNull MvPat o) {
    visitElement(o);
  }

  public void visitPatBinding(@NotNull MvPatBinding o) {
    visitPat(o);
    // visitMandatoryNameIdentifierOwner(o);
    // visitMandatoryReferenceElement(o);
  }

  public void visitPatConst(@NotNull MvPatConst o) {
    visitPat(o);
  }

  public void visitPatField(@NotNull MvPatField o) {
    visitElement(o);
  }

  public void visitPatFieldFull(@NotNull MvPatFieldFull o) {
    visitMandatoryReferenceElement(o);
  }

  public void visitPatIdent(@NotNull MvPatIdent o) {
    visitElement(o);
  }

  public void visitPatRest(@NotNull MvPatRest o) {
    visitElement(o);
  }

  public void visitPatStruct(@NotNull MvPatStruct o) {
    visitPat(o);
    // visitInferenceCachedPathElement(o);
  }

  public void visitPatTuple(@NotNull MvPatTuple o) {
    visitPat(o);
  }

  public void visitPatWild(@NotNull MvPatWild o) {
    visitPat(o);
  }

  public void visitPath(@NotNull MvPath o) {
    visitNameAccessChainReferenceElement(o);
    // visitMethodOrPath(o);
  }

  public void visitPathAddress(@NotNull MvPathAddress o) {
    visitElement(o);
  }

  public void visitPathExpr(@NotNull MvPathExpr o) {
    visitExpr(o);
    // visitInferenceCachedPathElement(o);
  }

  public void visitPathPat(@NotNull MvPathPat o) {
    visitElement(o);
  }

  public void visitPathType(@NotNull MvPathType o) {
    visitType(o);
  }

  public void visitPragmaAttribute(@NotNull MvPragmaAttribute o) {
    visitMslOnlyElement(o);
  }

  public void visitPragmaSpecStmt(@NotNull MvPragmaSpecStmt o) {
    visitStmt(o);
    // visitMslOnlyElement(o);
  }

  public void visitPublicUseFun(@NotNull MvPublicUseFun o) {
    visitDocAndAttributeOwner(o);
  }

  public void visitQualPathCodeFragmentElement(@NotNull MvQualPathCodeFragmentElement o) {
    visitElement(o);
  }

  public void visitQuantBinding(@NotNull MvQuantBinding o) {
    visitMslOnlyElement(o);
  }

  public void visitQuantBindings(@NotNull MvQuantBindings o) {
    visitElement(o);
  }

  public void visitQuantWhere(@NotNull MvQuantWhere o) {
    visitMslOnlyElement(o);
  }

  public void visitRangeExpr(@NotNull MvRangeExpr o) {
    visitExpr(o);
  }

  public void visitRangeQuantBinding(@NotNull MvRangeQuantBinding o) {
    visitQuantBinding(o);
  }

  public void visitRefExpr(@NotNull MvRefExpr o) {
    visitExpr(o);
  }

  public void visitRefType(@NotNull MvRefType o) {
    visitType(o);
  }

  public void visitRefTypeStart(@NotNull MvRefTypeStart o) {
    visitElement(o);
  }

  public void visitRequiresSpecExpr(@NotNull MvRequiresSpecExpr o) {
    visitExpr(o);
    // visitBoolSpecExpr(o);
    // visitMslOnlyElement(o);
  }

  public void visitResourceAccessItem(@NotNull MvResourceAccessItem o) {
    visitElement(o);
  }

  public void visitResourceAccessItemList(@NotNull MvResourceAccessItemList o) {
    visitElement(o);
  }

  public void visitReturnExpr(@NotNull MvReturnExpr o) {
    visitExpr(o);
  }

  public void visitReturnType(@NotNull MvReturnType o) {
    visitElement(o);
  }

  public void visitSchema(@NotNull MvSchema o) {
    visitTypeParametersOwner(o);
    // visitQualNamedElement(o);
    // visitInferenceContextOwner(o);
    // visitScopeMslOnlyElement(o);
    // visitItemElement(o);
  }

  public void visitSchemaFieldStmt(@NotNull MvSchemaFieldStmt o) {
    visitStmt(o);
    // visitTypeAnnotationOwner(o);
    // visitMslOnlyElement(o);
  }

  public void visitSchemaFieldsBlock(@NotNull MvSchemaFieldsBlock o) {
    visitElement(o);
  }

  public void visitSchemaIncludeItem(@NotNull MvSchemaIncludeItem o) {
    visitIncludeItem(o);
  }

  public void visitSchemaLit(@NotNull MvSchemaLit o) {
    visitMslOnlyElement(o);
  }

  public void visitSchemaLitField(@NotNull MvSchemaLitField o) {
    visitSchemaRefFieldReferenceElement(o);
    // visitMslOnlyElement(o);
  }

  public void visitSchemaRef(@NotNull MvSchemaRef o) {
    visitMslOnlyElement(o);
  }

  public void visitScript(@NotNull MvScript o) {
    visitItemsOwner(o);
  }

  public void visitScriptBlock(@NotNull MvScriptBlock o) {
    visitElement(o);
  }

  public void visitSpecCodeBlock(@NotNull MvSpecCodeBlock o) {
    visitItemsOwner(o);
    // visitAnyBlock(o);
    // visitMslOnlyElement(o);
  }

  public void visitSpecExprStmt(@NotNull MvSpecExprStmt o) {
    visitStmt(o);
  }

  public void visitSpecFunction(@NotNull MvSpecFunction o) {
    visitQualNamedElement(o);
    // visitFunctionLike(o);
    // visitItemElement(o);
    // visitInferenceContextOwner(o);
    // visitScopeMslOnlyElement(o);
  }

  public void visitSpecInlineFunction(@NotNull MvSpecInlineFunction o) {
    visitFunctionLike(o);
    // visitInferenceContextOwner(o);
    // visitItemElement(o);
    // visitScopeMslOnlyElement(o);
  }

  public void visitSpecInlineFunctionStmt(@NotNull MvSpecInlineFunctionStmt o) {
    visitStmt(o);
    // visitMslOnlyElement(o);
  }

  public void visitSpecProperty(@NotNull MvSpecProperty o) {
    visitMslOnlyElement(o);
  }

  public void visitSpecPropertyList(@NotNull MvSpecPropertyList o) {
    visitMslOnlyElement(o);
  }

  public void visitStmt(@NotNull MvStmt o) {
    visitElement(o);
  }

  public void visitStruct(@NotNull MvStruct o) {
    visitQualNamedElement(o);
    // visitTypeParametersOwner(o);
    // visitStructOrEnumItemElement(o);
    // visitFieldsOwner(o);
  }

  public void visitStructDotField(@NotNull MvStructDotField o) {
    visitMethodOrField(o);
  }

  public void visitStructLitExpr(@NotNull MvStructLitExpr o) {
    visitExpr(o);
    // visitInferenceCachedPathElement(o);
  }

  public void visitStructLitField(@NotNull MvStructLitField o) {
    visitMandatoryReferenceElement(o);
  }

  public void visitStructLitFieldsBlock(@NotNull MvStructLitFieldsBlock o) {
    visitElement(o);
  }

  public void visitStructPat(@NotNull MvStructPat o) {
    visitElement(o);
  }

  public void visitTupleFieldDecl(@NotNull MvTupleFieldDecl o) {
    visitElement(o);
  }

  public void visitTupleFields(@NotNull MvTupleFields o) {
    visitElement(o);
  }

  public void visitTupleLitExpr(@NotNull MvTupleLitExpr o) {
    visitExpr(o);
  }

  public void visitTuplePat(@NotNull MvTuplePat o) {
    visitElement(o);
  }

  public void visitTupleType(@NotNull MvTupleType o) {
    visitType(o);
  }

  public void visitType(@NotNull MvType o) {
    visitElement(o);
  }

  public void visitTypeAnnotation(@NotNull MvTypeAnnotation o) {
    visitElement(o);
  }

  public void visitTypeArgument(@NotNull MvTypeArgument o) {
    visitElement(o);
  }

  public void visitTypeArgumentList(@NotNull MvTypeArgumentList o) {
    visitElement(o);
  }

  public void visitTypeParamBound(@NotNull MvTypeParamBound o) {
    visitElement(o);
  }

  public void visitTypeParameter(@NotNull MvTypeParameter o) {
    visitNameIdentifierOwner(o);
  }

  public void visitTypeParameterList(@NotNull MvTypeParameterList o) {
    visitElement(o);
  }

  public void visitTypeQuantBinding(@NotNull MvTypeQuantBinding o) {
    visitQuantBinding(o);
  }

  public void visitUnitLitExpr(@NotNull MvUnitLitExpr o) {
    visitExpr(o);
  }

  public void visitUnitType(@NotNull MvUnitType o) {
    visitType(o);
  }

  public void visitUpdateSpecStmt(@NotNull MvUpdateSpecStmt o) {
    visitStmt(o);
    // visitMslOnlyElement(o);
  }

  public void visitUseAlias(@NotNull MvUseAlias o) {
    visitNameIdentifierOwner(o);
  }

  public void visitUseFunMethodAlias(@NotNull MvUseFunMethodAlias o) {
    visitElement(o);
  }

  public void visitUseGroup(@NotNull MvUseGroup o) {
    visitElement(o);
  }

  public void visitUseSpeck(@NotNull MvUseSpeck o) {
    visitElement(o);
  }

  public void visitUseStmt(@NotNull MvUseStmt o) {
    visitStmt(o);
    // visitDocAndAttributeOwner(o);
  }

  public void visitValueArgument(@NotNull MvValueArgument o) {
    visitElement(o);
  }

  public void visitValueArgumentList(@NotNull MvValueArgumentList o) {
    visitElement(o);
  }

  public void visitVectorLitExpr(@NotNull MvVectorLitExpr o) {
    visitExpr(o);
  }

  public void visitVectorLitItems(@NotNull MvVectorLitItems o) {
    visitElement(o);
  }

  public void visitVisibilityModifier(@NotNull MvVisibilityModifier o) {
    visitElement(o);
  }

  public void visitWhileExpr(@NotNull MvWhileExpr o) {
    visitExpr(o);
    // visitLoopLike(o);
  }

  public void visitWildPat(@NotNull MvWildPat o) {
    visitElement(o);
  }

  public void visitWildType(@NotNull MvWildType o) {
    visitType(o);
  }

  public void visitMslOnlyElement(@NotNull MslOnlyElement o) {
    visitElement(o);
  }

  public void visitFunctionLike(@NotNull MvFunctionLike o) {
    visitElement(o);
  }

  public void visitMandatoryNameIdentifierOwner(@NotNull MvMandatoryNameIdentifierOwner o) {
    visitElement(o);
  }

  public void visitNameIdentifierOwner(@NotNull MvNameIdentifierOwner o) {
    visitElement(o);
  }

  public void visitQualNamedElement(@NotNull MvQualNamedElement o) {
    visitElement(o);
  }

  public void visitTypeAnnotationOwner(@NotNull MvTypeAnnotationOwner o) {
    visitElement(o);
  }

  public void visitTypeParametersOwner(@NotNull MvTypeParametersOwner o) {
    visitElement(o);
  }

  public void visitDocAndAttributeOwner(@NotNull MvDocAndAttributeOwner o) {
    visitElement(o);
  }

  public void visitItemsOwner(@NotNull MvItemsOwner o) {
    visitElement(o);
  }

  public void visitMethodOrField(@NotNull MvMethodOrField o) {
    visitElement(o);
  }

  public void visitItemSpecParameterReferenceElement(@NotNull MvItemSpecParameterReferenceElement o) {
    visitElement(o);
  }

  public void visitMandatoryReferenceElement(@NotNull MvMandatoryReferenceElement o) {
    visitElement(o);
  }

  public void visitNameAccessChainReferenceElement(@NotNull MvNameAccessChainReferenceElement o) {
    visitElement(o);
  }

  public void visitReferenceElement(@NotNull MvReferenceElement o) {
    visitElement(o);
  }

  public void visitSchemaRefFieldReferenceElement(@NotNull MvSchemaRefFieldReferenceElement o) {
    visitElement(o);
  }

  public void visitNamedAddressReferenceElement(@NotNull NamedAddressReferenceElement o) {
    visitElement(o);
  }

  public void visitInferenceContextOwner(@NotNull MvInferenceContextOwner o) {
    visitElement(o);
  }

  public void visitElement(@NotNull MvElement o) {
    super.visitElement(o);
  }

}
