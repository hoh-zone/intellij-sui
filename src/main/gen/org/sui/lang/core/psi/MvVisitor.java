// This is a generated file. Not intended for manual editing.
package org.sui.lang.core.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import org.sui.lang.core.psi.ext.MvStructOrEnumItemElement;
import org.sui.lang.core.psi.ext.MvCallable;
import org.sui.lang.core.resolve2.ref.InferenceCachedPathElement;
import org.sui.lang.core.resolve.ref.MvReferenceElement;
import org.sui.lang.core.resolve.ref.MvMandatoryReferenceElement;
import org.sui.lang.core.psi.ext.MvItemsOwner;
import org.sui.lang.core.psi.ext.MvDocAndAttributeOwner;
import org.sui.lang.core.resolve.ref.NamedAddressReferenceElement;
import org.sui.lang.core.psi.ext.MvMethodOrField;
import org.sui.lang.core.psi.ext.MvMethodOrPath;
import org.sui.lang.core.resolve.ref.MvNameAccessChainReferenceElement;
import org.sui.lang.core.psi.ext.MvFieldsOwner;
import org.sui.lang.core.psi.ext.MvItemElement;

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

  public void visitAnnotatedExpr(@NotNull MvAnnotatedExpr o) {
    visitExpr(o);
  }

  public void visitAssertBangExpr(@NotNull MvAssertBangExpr o) {
    visitExpr(o);
  }

  public void visitAssertMacroExpr(@NotNull MvAssertMacroExpr o) {
    visitExpr(o);
    // visitCallable(o);
  }

  public void visitAssignmentExpr(@NotNull MvAssignmentExpr o) {
    visitExpr(o);
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
    // visitInferenceCachedPathElement(o);
  }

  public void visitCastExpr(@NotNull MvCastExpr o) {
    visitExpr(o);
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

  public void visitDerefExpr(@NotNull MvDerefExpr o) {
    visitExpr(o);
  }

  public void visitDotExpr(@NotNull MvDotExpr o) {
    visitExpr(o);
  }

  public void visitElseBlock(@NotNull MvElseBlock o) {
    visitElement(o);
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

  public void visitFriendDecl(@NotNull MvFriendDecl o) {
    visitDocAndAttributeOwner(o);
  }

  public void visitFunction(@NotNull MvFunction o) {
    visitQualNamedElement(o);
    // visitFunctionLike(o);
    // visitItemElement(o);
    // visitModificationTrackerOwner(o);
  }

  public void visitFunctionParameter(@NotNull MvFunctionParameter o) {
    visitTypeAnnotationOwner(o);
  }

  public void visitFunctionParameterList(@NotNull MvFunctionParameterList o) {
    visitElement(o);
  }

  public void visitIfExpr(@NotNull MvIfExpr o) {
    visitExpr(o);
  }

  public void visitIndexExpr(@NotNull MvIndexExpr o) {
    visitExpr(o);
  }

  public void visitInitializer(@NotNull MvInitializer o) {
    visitElement(o);
  }

  public void visitInlineBlock(@NotNull MvInlineBlock o) {
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

  public void visitPublicUseFun(@NotNull MvPublicUseFun o) {
    visitDocAndAttributeOwner(o);
  }

  public void visitQualPathCodeFragmentElement(@NotNull MvQualPathCodeFragmentElement o) {
    visitElement(o);
  }

  public void visitRangeExpr(@NotNull MvRangeExpr o) {
    visitExpr(o);
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

  public void visitReturnExpr(@NotNull MvReturnExpr o) {
    visitExpr(o);
  }

  public void visitReturnType(@NotNull MvReturnType o) {
    visitElement(o);
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

  public void visitUnitLitExpr(@NotNull MvUnitLitExpr o) {
    visitExpr(o);
  }

  public void visitUnitType(@NotNull MvUnitType o) {
    visitType(o);
  }

  public void visitUseAlias(@NotNull MvUseAlias o) {
    visitNameIdentifierOwner(o);
  }

  public void visitUseFunAlias(@NotNull MvUseFunAlias o) {
    visitElement(o);
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

  public void visitMandatoryReferenceElement(@NotNull MvMandatoryReferenceElement o) {
    visitElement(o);
  }

  public void visitNameAccessChainReferenceElement(@NotNull MvNameAccessChainReferenceElement o) {
    visitElement(o);
  }

  public void visitReferenceElement(@NotNull MvReferenceElement o) {
    visitElement(o);
  }

  public void visitNamedAddressReferenceElement(@NotNull NamedAddressReferenceElement o) {
    visitElement(o);
  }

  public void visitElement(@NotNull MvElement o) {
    super.visitElement(o);
  }

}
