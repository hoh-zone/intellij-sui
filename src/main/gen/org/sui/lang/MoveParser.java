// This is a generated file. Not intended for manual editing.
package org.sui.lang;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static org.sui.lang.MvElementTypes.*;
import static org.sui.lang.core.MoveParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;
import static org.sui.lang.core.MoveParserUtil.PathParsingMode.*;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class MoveParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    parseLight(root_, builder_);
    return builder_.getTreeBuilt();
  }

  public void parseLight(IElementType root_, PsiBuilder builder_) {
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this, EXTENDS_SETS_);
    Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
    result_ = parse_root_(root_, builder_);
    exit_section_(builder_, 0, marker_, root_, result_, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType root_, PsiBuilder builder_) {
    return parse_root_(root_, builder_, 0);
  }

  static boolean parse_root_(IElementType root_, PsiBuilder builder_, int level_) {
    boolean result_;
    if (root_ == QUAL_PATH_CODE_FRAGMENT_ELEMENT) {
      result_ = QualPathCodeFragmentElement(builder_, level_ + 1);
    }
    else {
      result_ = File(builder_, level_ + 1);
    }
    return result_;
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(QUANT_BINDING, RANGE_QUANT_BINDING, TYPE_QUANT_BINDING),
    create_token_set_(AND_INCLUDE_ITEM, IF_ELSE_INCLUDE_ITEM, IMPLY_INCLUDE_ITEM, INCLUDE_ITEM,
      SCHEMA_INCLUDE_ITEM),
    create_token_set_(PAT, PAT_BINDING, PAT_CONST, PAT_STRUCT,
      PAT_TUPLE, PAT_WILD),
    create_token_set_(LAMBDA_TYPE, PARENS_TYPE, PATH_TYPE, REF_TYPE,
      TUPLE_TYPE, TYPE, UNIT_TYPE, WILD_TYPE),
    create_token_set_(APPLY_SCHEMA_STMT, EXPR_STMT, GLOBAL_VARIABLE_STMT, INCLUDE_STMT,
      LET_STMT, PRAGMA_SPEC_STMT, SCHEMA_FIELD_STMT, SPEC_EXPR_STMT,
      SPEC_INLINE_FUNCTION_STMT, STMT, UPDATE_SPEC_STMT, USE_STMT),
    create_token_set_(ABORTS_IF_SPEC_EXPR, ABORTS_WITH_SPEC_EXPR, ABORT_EXPR, ANNOTATED_EXPR,
      ASSERT_BANG_EXPR, ASSERT_MACRO_EXPR, ASSERT_SPEC_EXPR, ASSIGNMENT_EXPR,
      ASSUME_SPEC_EXPR, AXIOM_SPEC_EXPR, BANG_EXPR, BINARY_EXPR,
      BORROW_EXPR, BREAK_EXPR, CALL_EXPR, CAST_EXPR,
      CHOOSE_QUANT_EXPR, CODE_BLOCK_EXPR, CONTINUE_EXPR, COPY_EXPR,
      DECREASES_SPEC_EXPR, DEREF_EXPR, DOT_EXPR, EMITS_SPEC_EXPR,
      ENSURES_SPEC_EXPR, EXISTS_QUANT_EXPR, EXPR, FORALL_QUANT_EXPR,
      FOR_EXPR, IF_EXPR, INDEX_EXPR, INVARIANT_SPEC_EXPR,
      ITEM_SPEC_BLOCK_EXPR, LABELED_BLOCK_EXPR, LAMBDA_EXPR, LIT_EXPR,
      LOOP_EXPR, MATCH_EXPR, MODIFIES_SPEC_EXPR, MOVE_EXPR,
      PARENS_EXPR, PATH_EXPR, RANGE_EXPR, REF_EXPR,
      REQUIRES_SPEC_EXPR, RETURN_EXPR, STRUCT_LIT_EXPR, TUPLE_LIT_EXPR,
      UNIT_LIT_EXPR, VECTOR_LIT_EXPR, WHILE_EXPR),
  };

  /* ********************************************************** */
  // has <<non_empty_comma_sep_items Ability>>
  public static boolean AbilitiesList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AbilitiesList")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ABILITIES_LIST, "<abilities list>");
    result_ = has(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && non_empty_comma_sep_items(builder_, level_ + 1, MoveParser::Ability);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // copy | IDENTIFIER
  public static boolean Ability(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Ability")) return false;
    if (!nextTokenIs(builder_, "<ability>", COPY, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ABILITY, "<ability>");
    result_ = consumeToken(builder_, COPY);
    if (!result_) result_ = consumeToken(builder_, IDENTIFIER);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // aborts_if SpecPropertyList? Expr AbortsIfWith?
  public static boolean AbortsIfSpecExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AbortsIfSpecExpr")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, ABORTS_IF_SPEC_EXPR, "<expression>");
    result_ = aborts_if(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, AbortsIfSpecExpr_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, Expr(builder_, level_ + 1, -1)) && result_;
    result_ = pinned_ && AbortsIfSpecExpr_3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // SpecPropertyList?
  private static boolean AbortsIfSpecExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AbortsIfSpecExpr_1")) return false;
    SpecPropertyList(builder_, level_ + 1);
    return true;
  }

  // AbortsIfWith?
  private static boolean AbortsIfSpecExpr_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AbortsIfSpecExpr_3")) return false;
    AbortsIfWith(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // with Expr
  public static boolean AbortsIfWith(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AbortsIfWith")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ABORTS_IF_WITH, "<aborts if with>");
    result_ = with(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && Expr(builder_, level_ + 1, -1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // aborts_with SpecPropertyList? <<non_empty_comma_sep_items Expr>>
  public static boolean AbortsWithSpecExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AbortsWithSpecExpr")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, ABORTS_WITH_SPEC_EXPR, "<expression>");
    result_ = aborts_with(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, AbortsWithSpecExpr_1(builder_, level_ + 1));
    result_ = pinned_ && non_empty_comma_sep_items(builder_, level_ + 1, Expr_parser_) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // SpecPropertyList?
  private static boolean AbortsWithSpecExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AbortsWithSpecExpr_1")) return false;
    SpecPropertyList(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // <<pathMode 'WILDCARD' PathImpl>> AddressSpecifier?
  public static boolean AccessSpecifier(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AccessSpecifier")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ACCESS_SPECIFIER, "<access specifier>");
    result_ = pathMode(builder_, level_ + 1, WILDCARD, MoveParser::PathImpl);
    result_ = result_ && AccessSpecifier_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // AddressSpecifier?
  private static boolean AccessSpecifier_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AccessSpecifier_1")) return false;
    AddressSpecifier(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // <<non_empty_comma_sep_items AccessSpecifier>>
  public static boolean AccessSpecifierList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AccessSpecifierList")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ACCESS_SPECIFIER_LIST, "<access specifier list>");
    result_ = non_empty_comma_sep_items(builder_, level_ + 1, MoveParser::AccessSpecifier);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // acquires AcquiresType_items
  public static boolean AcquiresType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AcquiresType")) return false;
    if (!nextTokenIs(builder_, ACQUIRES)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ACQUIRES_TYPE, null);
    result_ = consumeToken(builder_, ACQUIRES);
    pinned_ = result_; // pin = 1
    result_ = result_ && AcquiresType_items(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // <<non_empty_comma_sep_items PathType>>
  static boolean AcquiresType_items(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AcquiresType_items")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = non_empty_comma_sep_items(builder_, level_ + 1, MoveParser::PathType);
    exit_section_(builder_, level_, marker_, result_, false, MoveParser::AcquiresType_items_recover);
    return result_;
  }

  /* ********************************************************** */
  // !(';' | '{')
  static boolean AcquiresType_items_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AcquiresType_items_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !AcquiresType_items_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ';' | '{'
  private static boolean AcquiresType_items_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AcquiresType_items_recover_0")) return false;
    boolean result_;
    result_ = consumeTokenFast(builder_, SEMICOLON);
    if (!result_) result_ = consumeTokenFast(builder_, L_BRACE);
    return result_;
  }

  /* ********************************************************** */
  // '{' AddressBlockItems '}'
  public static boolean AddressBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AddressBlock")) return false;
    if (!nextTokenIs(builder_, L_BRACE)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ADDRESS_BLOCK, null);
    result_ = consumeToken(builder_, L_BRACE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, AddressBlockItems(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_BRACE) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // Module*
  static boolean AddressBlockItems(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AddressBlockItems")) return false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Module(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "AddressBlockItems", pos_)) break;
    }
    exit_section_(builder_, level_, marker_, true, false, MoveParser::AddressBlockItems_recover);
    return true;
  }

  /* ********************************************************** */
  // !'}'
  static boolean AddressBlockItems_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AddressBlockItems_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !consumeTokenFast(builder_, R_BRACE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // address AddressRef AddressBlock
  public static boolean AddressDef(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AddressDef")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ADDRESS_DEF, "<address def>");
    result_ = address(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, AddressRef(builder_, level_ + 1));
    result_ = pinned_ && AddressBlock(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // '@' AddressRef
  public static boolean AddressLit(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AddressLit")) return false;
    if (!nextTokenIs(builder_, AT)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ADDRESS_LIT, null);
    result_ = consumeToken(builder_, AT);
    pinned_ = result_; // pin = 1
    result_ = result_ && AddressRef(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // NamedAddress
  //                 | DIEM_ADDRESS
  //                 | INTEGER_LITERAL
  //                 | PLACEHOLDER_ADDRESS
  //                 | BECH32_ADDRESS
  //                 | POLKADOT_ADDRESS
  public static boolean AddressRef(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AddressRef")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ADDRESS_REF, "<address ref>");
    result_ = NamedAddress(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, DIEM_ADDRESS);
    if (!result_) result_ = consumeToken(builder_, INTEGER_LITERAL);
    if (!result_) result_ = consumeToken(builder_, PLACEHOLDER_ADDRESS);
    if (!result_) result_ = consumeToken(builder_, BECH32_ADDRESS);
    if (!result_) result_ = consumeToken(builder_, POLKADOT_ADDRESS);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // '('AddressSpecifierArg')'
  public static boolean AddressSpecifier(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AddressSpecifier")) return false;
    if (!nextTokenIs(builder_, L_PAREN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ADDRESS_SPECIFIER, null);
    result_ = consumeToken(builder_, L_PAREN);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, AddressSpecifierArg(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_PAREN) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // '*'
  // |AddressSpecifierLit
  //                         | <<pathMode 'VALUE' (PathImpl '(' AddressSpecifierCallParam ')')>>
  // |IDENTIFIER
  public static boolean AddressSpecifierArg(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AddressSpecifierArg")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ADDRESS_SPECIFIER_ARG, "<address specifier arg>");
    result_ = consumeToken(builder_, MUL);
    if (!result_) result_ = AddressSpecifierLit(builder_, level_ + 1);
    if (!result_) result_ = pathMode(builder_, level_ + 1, VALUE, MoveParser::AddressSpecifierArg_2_1);
    if (!result_) result_ = consumeToken(builder_, IDENTIFIER);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // PathImpl '(' AddressSpecifierCallParam ')'
  private static boolean AddressSpecifierArg_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AddressSpecifierArg_2_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = PathImpl(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, L_PAREN);
    result_ = result_ && AddressSpecifierCallParam(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // AddressSpecifierCallParamImpl
  public static boolean AddressSpecifierCallParam(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AddressSpecifierCallParam")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ADDRESS_SPECIFIER_CALL_PARAM, "<address specifier call param>");
    result_ = AddressSpecifierCallParamImpl(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // PathIdent
  public static boolean AddressSpecifierCallParamImpl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AddressSpecifierCallParamImpl")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, PATH, "<address specifier call param impl>");
    result_ = PathIdent(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // DIEM_ADDRESS|INTEGER_LITERAL
  public static boolean AddressSpecifierLit(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AddressSpecifierLit")) return false;
    if (!nextTokenIs(builder_, "<address specifier lit>", DIEM_ADDRESS, INTEGER_LITERAL)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ADDRESS_SPECIFIER_LIT, "<address specifier lit>");
    result_ = consumeToken(builder_, DIEM_ADDRESS);
    if (!result_) result_ = consumeToken(builder_, INTEGER_LITERAL);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // andand
  public static boolean AndBinOp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AndBinOp")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, BINARY_OP, "<operator>");
    result_ = andand(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // SchemaLit AndBinOp SchemaLit
  public static boolean AndIncludeItem(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AndIncludeItem")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, AND_INCLUDE_ITEM, "<and include item>");
    result_ = SchemaLit(builder_, level_ + 1);
    result_ = result_ && AndBinOp(builder_, level_ + 1);
    result_ = result_ && SchemaLit(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // '(' Expr ':'
  static boolean AnnotatedExpPrefix(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AnnotatedExpPrefix")) return false;
    if (!nextTokenIs(builder_, L_PAREN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, L_PAREN);
    result_ = result_ && Expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, COLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // CodeBlock | InlineBlock
  static boolean AnyBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AnyBlock")) return false;
    boolean result_;
    result_ = CodeBlock(builder_, level_ + 1);
    if (!result_) result_ = InlineBlock(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // HEX_INTEGER_LITERAL
  //                                   | BOOL_LITERAL
  //                                   | INTEGER_LITERAL
  //                                   | HEX_STRING_LITERAL
  //                                   | BYTE_STRING_LITERAL
  //                                   | '@'
  static boolean AnyLitToken_first(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AnyLitToken_first")) return false;
    boolean result_;
    result_ = HEX_INTEGER_LITERAL(builder_, level_ + 1);
    if (!result_) result_ = consumeTokenFast(builder_, BOOL_LITERAL);
    if (!result_) result_ = consumeTokenFast(builder_, INTEGER_LITERAL);
    if (!result_) result_ = consumeTokenFast(builder_, HEX_STRING_LITERAL);
    if (!result_) result_ = consumeTokenFast(builder_, BYTE_STRING_LITERAL);
    if (!result_) result_ = consumeTokenFast(builder_, AT);
    return result_;
  }

  /* ********************************************************** */
  // except <<non_empty_comma_sep_items FunctionPattern>>
  public static boolean ApplyExcept(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ApplyExcept")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, APPLY_EXCEPT, "<apply except>");
    result_ = except(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && non_empty_comma_sep_items(builder_, level_ + 1, MoveParser::FunctionPattern);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // IDENTIFIER ':' Expr
  public static boolean ApplySchemaNameAttribute(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ApplySchemaNameAttribute")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IDENTIFIER, COLON);
    result_ = result_ && Expr(builder_, level_ + 1, -1);
    exit_section_(builder_, marker_, APPLY_SCHEMA_NAME_ATTRIBUTE, result_);
    return result_;
  }

  /* ********************************************************** */
  // apply SchemaRef ApplyTo ApplyExcept? ';'
  public static boolean ApplySchemaStmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ApplySchemaStmt")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, APPLY_SCHEMA_STMT, "<apply schema stmt>");
    result_ = apply(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, SchemaRef(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, ApplyTo(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, ApplySchemaStmt_3(builder_, level_ + 1)) && result_;
    result_ = pinned_ && consumeToken(builder_, SEMICOLON) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // ApplyExcept?
  private static boolean ApplySchemaStmt_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ApplySchemaStmt_3")) return false;
    ApplyExcept(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // to <<non_empty_comma_sep_items FunctionPattern>>
  public static boolean ApplyTo(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ApplyTo")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, APPLY_TO, "<apply to>");
    result_ = to(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && non_empty_comma_sep_items(builder_, level_ + 1, MoveParser::FunctionPattern);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // <<assertIdent>> '!' ValueArgumentList
  public static boolean AssertBangExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AssertBangExpr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ASSERT_BANG_EXPR, "<expression>");
    result_ = assertIdent(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, EXCL);
    result_ = result_ && ValueArgumentList(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // assert SpecPropertyList? Expr
  public static boolean AssertSpecExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AssertSpecExpr")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, ASSERT_SPEC_EXPR, "<expression>");
    result_ = assert_$(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, AssertSpecExpr_1(builder_, level_ + 1));
    result_ = pinned_ && Expr(builder_, level_ + 1, -1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // SpecPropertyList?
  private static boolean AssertSpecExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AssertSpecExpr_1")) return false;
    SpecPropertyList(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // assume SpecPropertyList? Expr
  public static boolean AssumeSpecExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AssumeSpecExpr")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, ASSUME_SPEC_EXPR, "<expression>");
    result_ = assume(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, AssumeSpecExpr_1(builder_, level_ + 1));
    result_ = pinned_ && Expr(builder_, level_ + 1, -1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // SpecPropertyList?
  private static boolean AssumeSpecExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AssumeSpecExpr_1")) return false;
    SpecPropertyList(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // '#' '[' <<non_empty_comma_sep_items AttrItem>> ']'
  public static boolean Attr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Attr")) return false;
    if (!nextTokenIs(builder_, HASH)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ATTR, null);
    result_ = consumeTokens(builder_, 1, HASH, L_BRACK);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, non_empty_comma_sep_items(builder_, level_ + 1, MoveParser::AttrItem));
    result_ = pinned_ && consumeToken(builder_, R_BRACK) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // IDENTIFIER (AttrItemList | AttrItemInitializer)?
  public static boolean AttrItem(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AttrItem")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER);
    result_ = result_ && AttrItem_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, ATTR_ITEM, result_);
    return result_;
  }

  // (AttrItemList | AttrItemInitializer)?
  private static boolean AttrItem_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AttrItem_1")) return false;
    AttrItem_1_0(builder_, level_ + 1);
    return true;
  }

  // AttrItemList | AttrItemInitializer
  private static boolean AttrItem_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AttrItem_1_0")) return false;
    boolean result_;
    result_ = AttrItemList(builder_, level_ + 1);
    if (!result_) result_ = AttrItemInitializer(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // '=' Expr
  public static boolean AttrItemInitializer(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AttrItemInitializer")) return false;
    if (!nextTokenIs(builder_, EQ)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ATTR_ITEM_INITIALIZER, null);
    result_ = consumeToken(builder_, EQ);
    pinned_ = result_; // pin = 1
    result_ = result_ && Expr(builder_, level_ + 1, -1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // '(' <<non_empty_comma_sep_items AttrItem>>? ')'
  public static boolean AttrItemList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AttrItemList")) return false;
    if (!nextTokenIs(builder_, L_PAREN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ATTR_ITEM_LIST, null);
    result_ = consumeToken(builder_, L_PAREN);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, AttrItemList_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_PAREN) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // <<non_empty_comma_sep_items AttrItem>>?
  private static boolean AttrItemList_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AttrItemList_1")) return false;
    non_empty_comma_sep_items(builder_, level_ + 1, MoveParser::AttrItem);
    return true;
  }

  /* ********************************************************** */
  // '#'
  static boolean Attr_first(PsiBuilder builder_, int level_) {
    return consumeTokenFast(builder_, HASH);
  }

  /* ********************************************************** */
  // '#'
  static boolean AttrsAndVis_first(PsiBuilder builder_, int level_) {
    return consumeTokenFast(builder_, HASH);
  }

  /* ********************************************************** */
  // axiom TypeParameterList? SpecPropertyList? Expr
  public static boolean AxiomSpecExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AxiomSpecExpr")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, AXIOM_SPEC_EXPR, "<expression>");
    result_ = axiom(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, AxiomSpecExpr_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, AxiomSpecExpr_2(builder_, level_ + 1)) && result_;
    result_ = pinned_ && Expr(builder_, level_ + 1, -1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // TypeParameterList?
  private static boolean AxiomSpecExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AxiomSpecExpr_1")) return false;
    TypeParameterList(builder_, level_ + 1);
    return true;
  }

  // SpecPropertyList?
  private static boolean AxiomSpecExpr_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AxiomSpecExpr_2")) return false;
    SpecPropertyList(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // '&'
  public static boolean BitAndBinOp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BitAndBinOp")) return false;
    if (!nextTokenIs(builder_, "<operator>", AND)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BINARY_OP, "<operator>");
    result_ = consumeToken(builder_, AND);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // '|'
  public static boolean BitOrBinOp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BitOrBinOp")) return false;
    if (!nextTokenIs(builder_, "<operator>", OR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BINARY_OP, "<operator>");
    result_ = consumeToken(builder_, OR);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // '^'
  public static boolean BitXorBinOp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BitXorBinOp")) return false;
    if (!nextTokenIs(builder_, "<operator>", XOR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BINARY_OP, "<operator>");
    result_ = consumeToken(builder_, XOR);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // '{' NamedFieldDecl_with_recover* '}'
  public static boolean BlockFields(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BlockFields")) return false;
    if (!nextTokenIs(builder_, L_BRACE)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BLOCK_FIELDS, null);
    result_ = consumeToken(builder_, L_BRACE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, BlockFields_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_BRACE) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // NamedFieldDecl_with_recover*
  private static boolean BlockFields_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BlockFields_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!NamedFieldDecl_with_recover(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "BlockFields_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // '{' CodeBlock_items '}'
  public static boolean CodeBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CodeBlock")) return false;
    if (!nextTokenIs(builder_, L_BRACE)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, CODE_BLOCK, null);
    result_ = consumeToken(builder_, L_BRACE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, CodeBlock_items(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_BRACE) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // UseStmt*
  //                             Stmt*
  //                             ExprStmt_expr?
  static boolean CodeBlock_items(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CodeBlock_items")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = CodeBlock_items_0(builder_, level_ + 1);
    result_ = result_ && CodeBlock_items_1(builder_, level_ + 1);
    result_ = result_ && CodeBlock_items_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, MoveParser::CodeBlock_items_recover);
    return result_;
  }

  // UseStmt*
  private static boolean CodeBlock_items_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CodeBlock_items_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!UseStmt(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "CodeBlock_items_0", pos_)) break;
    }
    return true;
  }

  // Stmt*
  private static boolean CodeBlock_items_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CodeBlock_items_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Stmt(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "CodeBlock_items_1", pos_)) break;
    }
    return true;
  }

  // ExprStmt_expr?
  private static boolean CodeBlock_items_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CodeBlock_items_2")) return false;
    ExprStmt_expr(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // !('}' | <<eof>>)
  static boolean CodeBlock_items_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CodeBlock_items_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !CodeBlock_items_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // '}' | <<eof>>
  private static boolean CodeBlock_items_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CodeBlock_items_recover_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenFast(builder_, R_BRACE);
    if (!result_) result_ = eof(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // (&'::')? TypeArgumentListImpl
  public static boolean ColonTypeArgumentList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ColonTypeArgumentList")) return false;
    if (!nextTokenIs(builder_, "<colon type argument list>", COLON_COLON, LT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, TYPE_ARGUMENT_LIST, "<colon type argument list>");
    result_ = ColonTypeArgumentList_0(builder_, level_ + 1);
    result_ = result_ && TypeArgumentListImpl(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (&'::')?
  private static boolean ColonTypeArgumentList_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ColonTypeArgumentList_0")) return false;
    ColonTypeArgumentList_0_0(builder_, level_ + 1);
    return true;
  }

  // &'::'
  private static boolean ColonTypeArgumentList_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ColonTypeArgumentList_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _AND_);
    result_ = consumeToken(builder_, COLON_COLON);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // '(' ConditionBody ')'
  public static boolean Condition(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Condition")) return false;
    if (!nextTokenIs(builder_, L_PAREN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, CONDITION, null);
    result_ = consumeToken(builder_, L_PAREN);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, ConditionBody(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_PAREN) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // Expr
  static boolean ConditionBody(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ConditionBody")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = Expr(builder_, level_ + 1, -1);
    exit_section_(builder_, level_, marker_, result_, false, MoveParser::ConditionBody_recover);
    return result_;
  }

  /* ********************************************************** */
  // !')'
  static boolean ConditionBody_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ConditionBody_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !consumeTokenFast(builder_, R_PAREN);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // Attr* CONST_KW IDENTIFIER TypeAnnotation Initializer ';'
  public static boolean Const(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Const")) return false;
    if (!nextTokenIs(builder_, "<const>", CONST_KW, HASH)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, CONST, "<const>");
    result_ = Const_0(builder_, level_ + 1);
    result_ = result_ && consumeTokens(builder_, 1, CONST_KW, IDENTIFIER);
    pinned_ = result_; // pin = CONST_KW
    result_ = result_ && report_error_(builder_, TypeAnnotation(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, Initializer(builder_, level_ + 1)) && result_;
    result_ = pinned_ && consumeToken(builder_, SEMICOLON) && result_;
    register_hook_(builder_, LEFT_BINDER, ADJACENT_LINE_COMMENTS);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // Attr*
  private static boolean Const_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Const_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Attr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Const_0", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // PathImpl
  public static boolean ConstPat(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ConstPat")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, CONST_PAT, "<const pat>");
    result_ = PathImpl(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // decreases SpecPropertyList? Expr
  public static boolean DecreasesSpecExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DecreasesSpecExpr")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, DECREASES_SPEC_EXPR, "<expression>");
    result_ = decreases(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, DecreasesSpecExpr_1(builder_, level_ + 1));
    result_ = pinned_ && Expr(builder_, level_ + 1, -1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // SpecPropertyList?
  private static boolean DecreasesSpecExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DecreasesSpecExpr_1")) return false;
    SpecPropertyList(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // '/'
  public static boolean DivBinOp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DivBinOp")) return false;
    if (!nextTokenIs(builder_, "<operator>", DIV)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BINARY_OP, "<operator>");
    result_ = consumeToken(builder_, DIV);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // '.' !('.' | VectorStart) (MethodCall | StructDotField)
  static boolean DotExpr_inner(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DotExpr_inner")) return false;
    if (!nextTokenIsFast(builder_, DOT)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeTokenFast(builder_, DOT);
    result_ = result_ && DotExpr_inner_1(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && DotExpr_inner_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // !('.' | VectorStart)
  private static boolean DotExpr_inner_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DotExpr_inner_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !DotExpr_inner_1_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // '.' | VectorStart
  private static boolean DotExpr_inner_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DotExpr_inner_1_0")) return false;
    boolean result_;
    result_ = consumeTokenFast(builder_, DOT);
    if (!result_) result_ = VectorStart(builder_, level_ + 1);
    return result_;
  }

  // MethodCall | StructDotField
  private static boolean DotExpr_inner_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DotExpr_inner_2")) return false;
    boolean result_;
    result_ = MethodCall(builder_, level_ + 1);
    if (!result_) result_ = StructDotField(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // else AnyBlock
  public static boolean ElseBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ElseBlock")) return false;
    if (!nextTokenIs(builder_, ELSE)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ELSE_BLOCK, null);
    result_ = consumeToken(builder_, ELSE);
    pinned_ = result_; // pin = 1
    result_ = result_ && AnyBlock(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // if Expr
  public static boolean EmitsCondition(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EmitsCondition")) return false;
    if (!nextTokenIs(builder_, IF)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, EMITS_CONDITION, null);
    result_ = consumeToken(builder_, IF);
    pinned_ = result_; // pin = 1
    result_ = result_ && Expr(builder_, level_ + 1, -1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // emits SpecPropertyList? Expr to Expr EmitsCondition?
  public static boolean EmitsSpecExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EmitsSpecExpr")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, EMITS_SPEC_EXPR, "<expression>");
    result_ = emits(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, EmitsSpecExpr_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, Expr(builder_, level_ + 1, -1)) && result_;
    result_ = pinned_ && report_error_(builder_, to(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, Expr(builder_, level_ + 1, -1)) && result_;
    result_ = pinned_ && EmitsSpecExpr_5(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // SpecPropertyList?
  private static boolean EmitsSpecExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EmitsSpecExpr_1")) return false;
    SpecPropertyList(builder_, level_ + 1);
    return true;
  }

  // EmitsCondition?
  private static boolean EmitsSpecExpr_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EmitsSpecExpr_5")) return false;
    EmitsCondition(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // ensures SpecPropertyList? SpecExpr
  public static boolean EnsuresSpecExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EnsuresSpecExpr")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, ENSURES_SPEC_EXPR, "<expression>");
    result_ = ensures(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, EnsuresSpecExpr_1(builder_, level_ + 1));
    result_ = pinned_ && SpecExpr(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // SpecPropertyList?
  private static boolean EnsuresSpecExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EnsuresSpecExpr_1")) return false;
    SpecPropertyList(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // Attr* VisibilityModifier? enum IDENTIFIER TypeParameterList? AbilitiesList? EnumBody
  public static boolean Enum(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Enum")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ENUM, "<enum>");
    result_ = Enum_0(builder_, level_ + 1);
    result_ = result_ && Enum_1(builder_, level_ + 1);
    result_ = result_ && enum_$(builder_, level_ + 1);
    pinned_ = result_; // pin = enum
    result_ = result_ && report_error_(builder_, consumeToken(builder_, IDENTIFIER));
    result_ = pinned_ && report_error_(builder_, Enum_4(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, Enum_5(builder_, level_ + 1)) && result_;
    result_ = pinned_ && EnumBody(builder_, level_ + 1) && result_;
    register_hook_(builder_, LEFT_BINDER, ADJACENT_LINE_COMMENTS);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // Attr*
  private static boolean Enum_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Enum_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Attr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Enum_0", pos_)) break;
    }
    return true;
  }

  // VisibilityModifier?
  private static boolean Enum_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Enum_1")) return false;
    VisibilityModifier(builder_, level_ + 1);
    return true;
  }

  // TypeParameterList?
  private static boolean Enum_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Enum_4")) return false;
    TypeParameterList(builder_, level_ + 1);
    return true;
  }

  // AbilitiesList?
  private static boolean Enum_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Enum_5")) return false;
    AbilitiesList(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // '{' (EnumVariant ','?)* '}'
  public static boolean EnumBody(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EnumBody")) return false;
    if (!nextTokenIs(builder_, L_BRACE)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ENUM_BODY, null);
    result_ = consumeToken(builder_, L_BRACE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, EnumBody_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_BRACE) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (EnumVariant ','?)*
  private static boolean EnumBody_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EnumBody_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!EnumBody_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "EnumBody_1", pos_)) break;
    }
    return true;
  }

  // EnumVariant ','?
  private static boolean EnumBody_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EnumBody_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = EnumVariant(builder_, level_ + 1);
    result_ = result_ && EnumBody_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ','?
  private static boolean EnumBody_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EnumBody_1_0_1")) return false;
    consumeToken(builder_, COMMA);
    return true;
  }

  /* ********************************************************** */
  // Attr* IDENTIFIER BlockFields?
  public static boolean EnumVariant(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EnumVariant")) return false;
    if (!nextTokenIs(builder_, "<enum variant>", HASH, IDENTIFIER)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ENUM_VARIANT, "<enum variant>");
    result_ = EnumVariant_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    pinned_ = result_; // pin = 2
    result_ = result_ && EnumVariant_2(builder_, level_ + 1);
    register_hook_(builder_, LEFT_BINDER, ADJACENT_LINE_COMMENTS);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // Attr*
  private static boolean EnumVariant_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EnumVariant_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Attr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "EnumVariant_0", pos_)) break;
    }
    return true;
  }

  // BlockFields?
  private static boolean EnumVariant_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EnumVariant_2")) return false;
    BlockFields(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // PathImpl
  public static boolean EnumVariantPat(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EnumVariantPat")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ENUM_VARIANT_PAT, "<enum variant pat>");
    result_ = PathImpl(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // '=='
  public static boolean EqualsBinOp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EqualsBinOp")) return false;
    if (!nextTokenIs(builder_, "<operator>", EQ_EQ)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BINARY_OP, "<operator>");
    result_ = consumeToken(builder_, EQ_EQ);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // (ExprStmt_expr !('}')) ';'
  public static boolean ExprStmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ExprStmt")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, EXPR_STMT, "<expr stmt>");
    result_ = ExprStmt_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && consumeToken(builder_, SEMICOLON);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // ExprStmt_expr !('}')
  private static boolean ExprStmt_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ExprStmt_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = ExprStmt_expr(builder_, level_ + 1);
    result_ = result_ && ExprStmt_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // !('}')
  private static boolean ExprStmt_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ExprStmt_0_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !consumeToken(builder_, R_BRACE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // ItemSpecBlockExpr | Expr
  static boolean ExprStmt_expr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ExprStmt_expr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = ItemSpecBlockExpr(builder_, level_ + 1);
    if (!result_) result_ = Expr(builder_, level_ + 1, -1);
    exit_section_(builder_, level_, marker_, result_, false, MoveParser::ExprStmt_recover);
    return result_;
  }

  /* ********************************************************** */
  // !(';' | '}' | <<mslOnly SpecExpr_first>> | Expr_first)
  static boolean ExprStmt_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ExprStmt_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !ExprStmt_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ';' | '}' | <<mslOnly SpecExpr_first>> | Expr_first
  private static boolean ExprStmt_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ExprStmt_recover_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenFast(builder_, SEMICOLON);
    if (!result_) result_ = consumeTokenFast(builder_, R_BRACE);
    if (!result_) result_ = mslOnly(builder_, level_ + 1, MoveParser::SpecExpr_first);
    if (!result_) result_ = Expr_first(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // let | if | while | loop | continue | break | return | spec
  //                         | copy | move | abort
  // //                        | (<<VECTOR_IDENTIFIER>> ('<' | '['))
  //                         | IDENTIFIER
  //                         | QUOTE_IDENTIFIER
  //                         | '*' | '&' | '!' | '|' | '{' | '('
  //                         | AnyLitToken_first
  //                         | (AddressRef '::')
  static boolean Expr_first(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Expr_first")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenFast(builder_, LET);
    if (!result_) result_ = consumeTokenFast(builder_, IF);
    if (!result_) result_ = consumeTokenFast(builder_, WHILE);
    if (!result_) result_ = consumeTokenFast(builder_, LOOP);
    if (!result_) result_ = consumeTokenFast(builder_, CONTINUE);
    if (!result_) result_ = consumeTokenFast(builder_, BREAK);
    if (!result_) result_ = consumeTokenFast(builder_, RETURN);
    if (!result_) result_ = consumeTokenFast(builder_, SPEC);
    if (!result_) result_ = consumeTokenFast(builder_, COPY);
    if (!result_) result_ = consumeTokenFast(builder_, MOVE);
    if (!result_) result_ = consumeTokenFast(builder_, ABORT);
    if (!result_) result_ = consumeTokenFast(builder_, IDENTIFIER);
    if (!result_) result_ = consumeTokenFast(builder_, QUOTE_IDENTIFIER);
    if (!result_) result_ = consumeTokenFast(builder_, MUL);
    if (!result_) result_ = consumeTokenFast(builder_, AND);
    if (!result_) result_ = consumeTokenFast(builder_, EXCL);
    if (!result_) result_ = consumeTokenFast(builder_, OR);
    if (!result_) result_ = consumeTokenFast(builder_, L_BRACE);
    if (!result_) result_ = consumeTokenFast(builder_, L_PAREN);
    if (!result_) result_ = AnyLitToken_first(builder_, level_ + 1);
    if (!result_) result_ = Expr_first_20(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // AddressRef '::'
  private static boolean Expr_first_20(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Expr_first_20")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = AddressRef(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON_COLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // PatFieldFull | PatBinding
  public static boolean FieldPat(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FieldPat")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FIELD_PAT, "<field pat>");
    result_ = PatFieldFull(builder_, level_ + 1);
    if (!result_) result_ = PatBinding(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // ':' Pat
  public static boolean FieldPatBinding(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FieldPatBinding")) return false;
    if (!nextTokenIs(builder_, COLON)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON);
    result_ = result_ && Pat(builder_, level_ + 1);
    exit_section_(builder_, marker_, FIELD_PAT_BINDING, result_);
    return result_;
  }

  /* ********************************************************** */
  // mut? IDENTIFIER ':' Pat
  public static boolean FieldPatFull(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FieldPatFull")) return false;
    if (!nextTokenIs(builder_, "<field pat full>", IDENTIFIER, MUT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FIELD_PAT_FULL, "<field pat full>");
    result_ = FieldPatFull_0(builder_, level_ + 1);
    result_ = result_ && consumeTokens(builder_, 0, IDENTIFIER, COLON);
    result_ = result_ && Pat(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // mut?
  private static boolean FieldPatFull_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FieldPatFull_0")) return false;
    consumeToken(builder_, MUT);
    return true;
  }

  /* ********************************************************** */
  // Module*
  static boolean File(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "File")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Module(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "File", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // '(' PatBinding in Expr ')'
  public static boolean ForIterCondition(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForIterCondition")) return false;
    if (!nextTokenIs(builder_, L_PAREN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FOR_ITER_CONDITION, null);
    result_ = consumeToken(builder_, L_PAREN);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, PatBinding(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, in(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, Expr(builder_, level_ + 1, -1)) && result_;
    result_ = pinned_ && consumeToken(builder_, R_PAREN) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // '::' | '<' | '(' | '{' | '!'
  static boolean ForbiddenPatBindingLast(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForbiddenPatBindingLast")) return false;
    boolean result_;
    result_ = consumeTokenFast(builder_, COLON_COLON);
    if (!result_) result_ = consumeTokenFast(builder_, LT);
    if (!result_) result_ = consumeTokenFast(builder_, L_PAREN);
    if (!result_) result_ = consumeTokenFast(builder_, L_BRACE);
    if (!result_) result_ = consumeTokenFast(builder_, EXCL);
    return result_;
  }

  /* ********************************************************** */
  // Attr* friend PathImpl ';'
  public static boolean FriendDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FriendDecl")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FRIEND_DECL, "<friend decl>");
    result_ = FriendDecl_0(builder_, level_ + 1);
    result_ = result_ && friend(builder_, level_ + 1);
    pinned_ = result_; // pin = friend
    result_ = result_ && report_error_(builder_, PathImpl(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, SEMICOLON) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // Attr*
  private static boolean FriendDecl_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FriendDecl_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Attr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "FriendDecl_0", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // Attr* FunctionInnerFirst FunctionSignatureInner CodeBlock
  public static boolean FunctionInner(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionInner")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, FUNCTION, "<function inner>");
    result_ = FunctionInner_0(builder_, level_ + 1);
    result_ = result_ && FunctionInnerFirst(builder_, level_ + 1);
    pinned_ = result_; // pin = FunctionInnerFirst
    result_ = result_ && report_error_(builder_, FunctionSignatureInner(builder_, level_ + 1));
    result_ = pinned_ && CodeBlock(builder_, level_ + 1) && result_;
    register_hook_(builder_, LEFT_BINDER, ADJACENT_LINE_COMMENTS);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // Attr*
  private static boolean FunctionInner_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionInner_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Attr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "FunctionInner_0", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // FunctionInnerVisFirst | FunctionInnerNoVisFirst
  static boolean FunctionInnerFirst(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionInnerFirst")) return false;
    boolean result_;
    result_ = FunctionInnerVisFirst(builder_, level_ + 1);
    if (!result_) result_ = FunctionInnerNoVisFirst(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // MACRO_KW? fun
  static boolean FunctionInnerNoVisFirst(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionInnerNoVisFirst")) return false;
    if (!nextTokenIs(builder_, "", FUN, MACRO_KW)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = FunctionInnerNoVisFirst_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, FUN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // MACRO_KW?
  private static boolean FunctionInnerNoVisFirst_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionInnerNoVisFirst_0")) return false;
    consumeToken(builder_, MACRO_KW);
    return true;
  }

  /* ********************************************************** */
  // FunctionModifierSetInner MACRO_KW? fun
  static boolean FunctionInnerVisFirst(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionInnerVisFirst")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = FunctionModifierSetInner(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, FunctionInnerVisFirst_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, FUN) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // MACRO_KW?
  private static boolean FunctionInnerVisFirst_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionInnerVisFirst_1")) return false;
    consumeToken(builder_, MACRO_KW);
    return true;
  }

  /* ********************************************************** */
  // NativeFunctionInner | FunctionInner
  static boolean FunctionItem(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionItem")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = NativeFunctionInner(builder_, level_ + 1);
    if (!result_) result_ = FunctionInner(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // <<functionModifierSet VisibilityModifier>>
  static boolean FunctionModifierSetInner(PsiBuilder builder_, int level_) {
    return functionModifierSet(builder_, level_ + 1, MoveParser::VisibilityModifier);
  }

  /* ********************************************************** */
  // PatBinding TypeAnnotation
  public static boolean FunctionParameter(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionParameter")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FUNCTION_PARAMETER, "<function parameter>");
    result_ = PatBinding(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && TypeAnnotation(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // '(' FunctionParameter_with_recover* ')'
  public static boolean FunctionParameterList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionParameterList")) return false;
    if (!nextTokenIs(builder_, L_PAREN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FUNCTION_PARAMETER_LIST, null);
    result_ = consumeToken(builder_, L_PAREN);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, FunctionParameterList_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_PAREN) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // FunctionParameter_with_recover*
  private static boolean FunctionParameterList_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionParameterList_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!FunctionParameter_with_recover(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "FunctionParameterList_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // !(')' | '{' | ';' | IDENTIFIER | mut)
  static boolean FunctionParameter_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionParameter_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !FunctionParameter_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ')' | '{' | ';' | IDENTIFIER | mut
  private static boolean FunctionParameter_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionParameter_recover_0")) return false;
    boolean result_;
    result_ = consumeTokenFast(builder_, R_PAREN);
    if (!result_) result_ = consumeTokenFast(builder_, L_BRACE);
    if (!result_) result_ = consumeTokenFast(builder_, SEMICOLON);
    if (!result_) result_ = consumeTokenFast(builder_, IDENTIFIER);
    if (!result_) result_ = consumeTokenFast(builder_, MUT);
    return result_;
  }

  /* ********************************************************** */
  // !(')' | '{' | ';') FunctionParameter (',' | &')')
  static boolean FunctionParameter_with_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionParameter_with_recover")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = FunctionParameter_with_recover_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, FunctionParameter(builder_, level_ + 1));
    result_ = pinned_ && FunctionParameter_with_recover_2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, MoveParser::FunctionParameter_recover);
    return result_ || pinned_;
  }

  // !(')' | '{' | ';')
  private static boolean FunctionParameter_with_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionParameter_with_recover_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !FunctionParameter_with_recover_0_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ')' | '{' | ';'
  private static boolean FunctionParameter_with_recover_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionParameter_with_recover_0_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, R_PAREN);
    if (!result_) result_ = consumeToken(builder_, L_BRACE);
    if (!result_) result_ = consumeToken(builder_, SEMICOLON);
    return result_;
  }

  // ',' | &')'
  private static boolean FunctionParameter_with_recover_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionParameter_with_recover_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    if (!result_) result_ = FunctionParameter_with_recover_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // &')'
  private static boolean FunctionParameter_with_recover_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionParameter_with_recover_2_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _AND_);
    result_ = consumeToken(builder_, R_PAREN);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // PatternVis? PatternIdent TypeParameterList?
  public static boolean FunctionPattern(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionPattern")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FUNCTION_PATTERN, "<function pattern>");
    result_ = FunctionPattern_0(builder_, level_ + 1);
    result_ = result_ && PatternIdent(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && FunctionPattern_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // PatternVis?
  private static boolean FunctionPattern_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionPattern_0")) return false;
    PatternVis(builder_, level_ + 1);
    return true;
  }

  // TypeParameterList?
  private static boolean FunctionPattern_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionPattern_2")) return false;
    TypeParameterList(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER TypeParameterList?
  //                                     FunctionParameterList ReturnType?
  static boolean FunctionSignatureInner(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionSignatureInner")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, IDENTIFIER);
    result_ = result_ && FunctionSignatureInner_1(builder_, level_ + 1);
    result_ = result_ && FunctionParameterList(builder_, level_ + 1);
    result_ = result_ && FunctionSignatureInner_3(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, MoveParser::FunctionSignatureInner_recover);
    return result_;
  }

  // TypeParameterList?
  private static boolean FunctionSignatureInner_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionSignatureInner_1")) return false;
    TypeParameterList(builder_, level_ + 1);
    return true;
  }

  // ReturnType?
  private static boolean FunctionSignatureInner_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionSignatureInner_3")) return false;
    ReturnType(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // !('{' | '}' | ';' | <<eof>> | ModuleItem_first)
  static boolean FunctionSignatureInner_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionSignatureInner_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !FunctionSignatureInner_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // '{' | '}' | ';' | <<eof>> | ModuleItem_first
  private static boolean FunctionSignatureInner_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FunctionSignatureInner_recover_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenFast(builder_, L_BRACE);
    if (!result_) result_ = consumeTokenFast(builder_, R_BRACE);
    if (!result_) result_ = consumeTokenFast(builder_, SEMICOLON);
    if (!result_) result_ = eof(builder_, level_ + 1);
    if (!result_) result_ = ModuleItem_first(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // Attr* global IDENTIFIER TypeParameterList? TypeAnnotation ('=' Expr)? ';'
  public static boolean GlobalVariableStmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GlobalVariableStmt")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, GLOBAL_VARIABLE_STMT, "<global variable stmt>");
    result_ = GlobalVariableStmt_0(builder_, level_ + 1);
    result_ = result_ && global(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, consumeToken(builder_, IDENTIFIER));
    result_ = pinned_ && report_error_(builder_, GlobalVariableStmt_3(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, TypeAnnotation(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, GlobalVariableStmt_5(builder_, level_ + 1)) && result_;
    result_ = pinned_ && consumeToken(builder_, SEMICOLON) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // Attr*
  private static boolean GlobalVariableStmt_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GlobalVariableStmt_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Attr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "GlobalVariableStmt_0", pos_)) break;
    }
    return true;
  }

  // TypeParameterList?
  private static boolean GlobalVariableStmt_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GlobalVariableStmt_3")) return false;
    TypeParameterList(builder_, level_ + 1);
    return true;
  }

  // ('=' Expr)?
  private static boolean GlobalVariableStmt_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GlobalVariableStmt_5")) return false;
    GlobalVariableStmt_5_0(builder_, level_ + 1);
    return true;
  }

  // '=' Expr
  private static boolean GlobalVariableStmt_5_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GlobalVariableStmt_5_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, EQ);
    result_ = result_ && Expr(builder_, level_ + 1, -1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // '>'
  public static boolean GreaterBinOp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GreaterBinOp")) return false;
    if (!nextTokenIs(builder_, "<operator>", GT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BINARY_OP, "<operator>");
    result_ = consumeToken(builder_, GT);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // gteq
  public static boolean GreaterEqualsBinOp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GreaterEqualsBinOp")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, BINARY_OP, "<operator>");
    result_ = gteq(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // <<hexIntegerLiteral>>
  static boolean HEX_INTEGER_LITERAL(PsiBuilder builder_, int level_) {
    return hexIntegerLiteral(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // if Condition SchemaLit else SchemaLit
  public static boolean IfElseIncludeItem(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IfElseIncludeItem")) return false;
    if (!nextTokenIs(builder_, IF)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IF);
    result_ = result_ && Condition(builder_, level_ + 1);
    result_ = result_ && SchemaLit(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, ELSE);
    result_ = result_ && SchemaLit(builder_, level_ + 1);
    exit_section_(builder_, marker_, IF_ELSE_INCLUDE_ITEM, result_);
    return result_;
  }

  /* ********************************************************** */
  // <<includeStmtModeFalse>> <<mslOnly eqeq_gt>>
  public static boolean ImplyBinOp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ImplyBinOp")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BINARY_OP, "<operator>");
    result_ = includeStmtModeFalse(builder_, level_ + 1);
    result_ = result_ && mslOnly(builder_, level_ + 1, MoveParser::eqeq_gt);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // <<includeStmtMode Expr>> ImplyBinOp SchemaLit
  public static boolean ImplyIncludeItem(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ImplyIncludeItem")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, IMPLY_INCLUDE_ITEM, "<imply include item>");
    result_ = includeStmtMode(builder_, level_ + 1, Expr_parser_);
    result_ = result_ && ImplyBinOp(builder_, level_ + 1);
    result_ = result_ && SchemaLit(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // SchemaIncludeItem | AndIncludeItem | IfElseIncludeItem | ImplyIncludeItem
  public static boolean IncludeItem(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IncludeItem")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, INCLUDE_ITEM, "<include item>");
    result_ = SchemaIncludeItem(builder_, level_ + 1);
    if (!result_) result_ = AndIncludeItem(builder_, level_ + 1);
    if (!result_) result_ = IfElseIncludeItem(builder_, level_ + 1);
    if (!result_) result_ = ImplyIncludeItem(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // include SpecPropertyList? IncludeItem ';'
  public static boolean IncludeStmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IncludeStmt")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, INCLUDE_STMT, "<include stmt>");
    result_ = include(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, IncludeStmt_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, IncludeItem(builder_, level_ + 1)) && result_;
    result_ = pinned_ && consumeToken(builder_, SEMICOLON) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // SpecPropertyList?
  private static boolean IncludeStmt_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IncludeStmt_1")) return false;
    SpecPropertyList(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // '[' IndexArg_items? ']'
  static boolean IndexArg(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IndexArg")) return false;
    if (!nextTokenIs(builder_, L_BRACK)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, L_BRACK);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, IndexArg_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_BRACK) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // IndexArg_items?
  private static boolean IndexArg_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IndexArg_1")) return false;
    IndexArg_items(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // Expr (',' Expr)*
  static boolean IndexArg_items(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IndexArg_items")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Expr(builder_, level_ + 1, -1);
    result_ = result_ && IndexArg_items_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (',' Expr)*
  private static boolean IndexArg_items_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IndexArg_items_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!IndexArg_items_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "IndexArg_items_1", pos_)) break;
    }
    return true;
  }

  // ',' Expr
  private static boolean IndexArg_items_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IndexArg_items_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && Expr(builder_, level_ + 1, -1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // '=' Expr
  public static boolean Initializer(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Initializer")) return false;
    if (!nextTokenIs(builder_, EQ)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, INITIALIZER, null);
    result_ = consumeToken(builder_, EQ);
    pinned_ = result_; // pin = 1
    result_ = result_ && Expr(builder_, level_ + 1, -1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // Expr
  public static boolean InlineBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InlineBlock")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, INLINE_BLOCK, "<inline block>");
    result_ = Expr(builder_, level_ + 1, -1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // invariant TypeParameterList? update? SpecPropertyList? Expr
  public static boolean InvariantSpecExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InvariantSpecExpr")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, INVARIANT_SPEC_EXPR, "<expression>");
    result_ = invariant(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, InvariantSpecExpr_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, InvariantSpecExpr_2(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, InvariantSpecExpr_3(builder_, level_ + 1)) && result_;
    result_ = pinned_ && Expr(builder_, level_ + 1, -1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // TypeParameterList?
  private static boolean InvariantSpecExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InvariantSpecExpr_1")) return false;
    TypeParameterList(builder_, level_ + 1);
    return true;
  }

  // update?
  private static boolean InvariantSpecExpr_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InvariantSpecExpr_2")) return false;
    update(builder_, level_ + 1);
    return true;
  }

  // SpecPropertyList?
  private static boolean InvariantSpecExpr_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "InvariantSpecExpr_3")) return false;
    SpecPropertyList(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // (spec !MODULE_KW) ItemSpecRef ItemSpecSignature? <<msl SpecCodeBlock>>
  public static boolean ItemSpec(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpec")) return false;
    if (!nextTokenIs(builder_, SPEC)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ITEM_SPEC, null);
    result_ = ItemSpec_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, ItemSpecRef(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, ItemSpec_2(builder_, level_ + 1)) && result_;
    result_ = pinned_ && msl(builder_, level_ + 1, MoveParser::SpecCodeBlock) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // spec !MODULE_KW
  private static boolean ItemSpec_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpec_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SPEC);
    result_ = result_ && ItemSpec_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // !MODULE_KW
  private static boolean ItemSpec_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpec_0_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !consumeToken(builder_, MODULE_KW);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ItemSpecSignature?
  private static boolean ItemSpec_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpec_2")) return false;
    ItemSpecSignature(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // spec <<msl SpecCodeBlock>>
  public static boolean ItemSpecBlockExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecBlockExpr")) return false;
    if (!nextTokenIsFast(builder_, SPEC)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ITEM_SPEC_BLOCK_EXPR, "<expression>");
    result_ = consumeTokenFast(builder_, SPEC);
    result_ = result_ && msl(builder_, level_ + 1, MoveParser::SpecCodeBlock);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // (SpecStmt | ExprStmt)* (SpecExprStmt_items | ExprStmt_expr)?
  static boolean ItemSpecBlock_items(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecBlock_items")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = ItemSpecBlock_items_0(builder_, level_ + 1);
    result_ = result_ && ItemSpecBlock_items_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, MoveParser::ItemSpecBlock_items_recover);
    return result_;
  }

  // (SpecStmt | ExprStmt)*
  private static boolean ItemSpecBlock_items_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecBlock_items_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!ItemSpecBlock_items_0_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "ItemSpecBlock_items_0", pos_)) break;
    }
    return true;
  }

  // SpecStmt | ExprStmt
  private static boolean ItemSpecBlock_items_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecBlock_items_0_0")) return false;
    boolean result_;
    result_ = SpecStmt(builder_, level_ + 1);
    if (!result_) result_ = ExprStmt(builder_, level_ + 1);
    return result_;
  }

  // (SpecExprStmt_items | ExprStmt_expr)?
  private static boolean ItemSpecBlock_items_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecBlock_items_1")) return false;
    ItemSpecBlock_items_1_0(builder_, level_ + 1);
    return true;
  }

  // SpecExprStmt_items | ExprStmt_expr
  private static boolean ItemSpecBlock_items_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecBlock_items_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = SpecExprStmt_items(builder_, level_ + 1);
    if (!result_) result_ = ExprStmt_expr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // !('}' | <<eof>>)
  static boolean ItemSpecBlock_items_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecBlock_items_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !ItemSpecBlock_items_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // '}' | <<eof>>
  private static boolean ItemSpecBlock_items_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecBlock_items_recover_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenFast(builder_, R_BRACE);
    if (!result_) result_ = eof(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // 'mut'? IDENTIFIER TypeAnnotation
  public static boolean ItemSpecFunctionParameter(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecFunctionParameter")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ITEM_SPEC_FUNCTION_PARAMETER, "<item spec function parameter>");
    result_ = ItemSpecFunctionParameter_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, consumeToken(builder_, IDENTIFIER));
    result_ = pinned_ && TypeAnnotation(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // 'mut'?
  private static boolean ItemSpecFunctionParameter_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecFunctionParameter_0")) return false;
    consumeToken(builder_, "mut");
    return true;
  }

  /* ********************************************************** */
  // '(' ItemSpecFunctionParameter_with_recover* ')'
  public static boolean ItemSpecFunctionParameterList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecFunctionParameterList")) return false;
    if (!nextTokenIs(builder_, L_PAREN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ITEM_SPEC_FUNCTION_PARAMETER_LIST, null);
    result_ = consumeToken(builder_, L_PAREN);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, ItemSpecFunctionParameterList_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_PAREN) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // ItemSpecFunctionParameter_with_recover*
  private static boolean ItemSpecFunctionParameterList_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecFunctionParameterList_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!ItemSpecFunctionParameter_with_recover(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "ItemSpecFunctionParameterList_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // !(')' | '{' | ';' | IDENTIFIER)
  static boolean ItemSpecFunctionParameter_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecFunctionParameter_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !ItemSpecFunctionParameter_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ')' | '{' | ';' | IDENTIFIER
  private static boolean ItemSpecFunctionParameter_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecFunctionParameter_recover_0")) return false;
    boolean result_;
    result_ = consumeTokenFast(builder_, R_PAREN);
    if (!result_) result_ = consumeTokenFast(builder_, L_BRACE);
    if (!result_) result_ = consumeTokenFast(builder_, SEMICOLON);
    if (!result_) result_ = consumeTokenFast(builder_, IDENTIFIER);
    return result_;
  }

  /* ********************************************************** */
  // !(')' | '{' | ';') ItemSpecFunctionParameter (',' | &')')
  static boolean ItemSpecFunctionParameter_with_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecFunctionParameter_with_recover")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = ItemSpecFunctionParameter_with_recover_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, ItemSpecFunctionParameter(builder_, level_ + 1));
    result_ = pinned_ && ItemSpecFunctionParameter_with_recover_2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, MoveParser::ItemSpecFunctionParameter_recover);
    return result_ || pinned_;
  }

  // !(')' | '{' | ';')
  private static boolean ItemSpecFunctionParameter_with_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecFunctionParameter_with_recover_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !ItemSpecFunctionParameter_with_recover_0_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ')' | '{' | ';'
  private static boolean ItemSpecFunctionParameter_with_recover_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecFunctionParameter_with_recover_0_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, R_PAREN);
    if (!result_) result_ = consumeToken(builder_, L_BRACE);
    if (!result_) result_ = consumeToken(builder_, SEMICOLON);
    return result_;
  }

  // ',' | &')'
  private static boolean ItemSpecFunctionParameter_with_recover_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecFunctionParameter_with_recover_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    if (!result_) result_ = ItemSpecFunctionParameter_with_recover_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // &')'
  private static boolean ItemSpecFunctionParameter_with_recover_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecFunctionParameter_with_recover_2_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _AND_);
    result_ = consumeToken(builder_, R_PAREN);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean ItemSpecRef(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecRef")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER);
    exit_section_(builder_, marker_, ITEM_SPEC_REF, result_);
    return result_;
  }

  /* ********************************************************** */
  // ItemSpecTypeParameterList? ItemSpecFunctionParameterList ReturnType?
  public static boolean ItemSpecSignature(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecSignature")) return false;
    if (!nextTokenIs(builder_, "<function signature>", LT, L_PAREN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ITEM_SPEC_SIGNATURE, "<function signature>");
    result_ = ItemSpecSignature_0(builder_, level_ + 1);
    result_ = result_ && ItemSpecFunctionParameterList(builder_, level_ + 1);
    pinned_ = result_; // pin = ItemSpecFunctionParameterList
    result_ = result_ && ItemSpecSignature_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // ItemSpecTypeParameterList?
  private static boolean ItemSpecSignature_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecSignature_0")) return false;
    ItemSpecTypeParameterList(builder_, level_ + 1);
    return true;
  }

  // ReturnType?
  private static boolean ItemSpecSignature_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecSignature_2")) return false;
    ReturnType(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER TypeParamBound?
  public static boolean ItemSpecTypeParameter(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecTypeParameter")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ITEM_SPEC_TYPE_PARAMETER, null);
    result_ = consumeToken(builder_, IDENTIFIER);
    pinned_ = result_; // pin = 1
    result_ = result_ && ItemSpecTypeParameter_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // TypeParamBound?
  private static boolean ItemSpecTypeParameter_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecTypeParameter_1")) return false;
    TypeParamBound(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // '<' ItemSpecTypeParameter_with_recover* '>'
  public static boolean ItemSpecTypeParameterList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecTypeParameterList")) return false;
    if (!nextTokenIs(builder_, LT)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ITEM_SPEC_TYPE_PARAMETER_LIST, null);
    result_ = consumeToken(builder_, LT);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, ItemSpecTypeParameterList_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, GT) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // ItemSpecTypeParameter_with_recover*
  private static boolean ItemSpecTypeParameterList_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecTypeParameterList_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!ItemSpecTypeParameter_with_recover(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "ItemSpecTypeParameterList_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // !('>' | '(' | '{' | phantom | IDENTIFIER)
  static boolean ItemSpecTypeParameter_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecTypeParameter_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !ItemSpecTypeParameter_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // '>' | '(' | '{' | phantom | IDENTIFIER
  private static boolean ItemSpecTypeParameter_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecTypeParameter_recover_0")) return false;
    boolean result_;
    result_ = consumeTokenFast(builder_, GT);
    if (!result_) result_ = consumeTokenFast(builder_, L_PAREN);
    if (!result_) result_ = consumeTokenFast(builder_, L_BRACE);
    if (!result_) result_ = consumeTokenFast(builder_, PHANTOM);
    if (!result_) result_ = consumeTokenFast(builder_, IDENTIFIER);
    return result_;
  }

  /* ********************************************************** */
  // !'>' ItemSpecTypeParameter (',' | &'>')
  static boolean ItemSpecTypeParameter_with_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecTypeParameter_with_recover")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = ItemSpecTypeParameter_with_recover_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, ItemSpecTypeParameter(builder_, level_ + 1));
    result_ = pinned_ && ItemSpecTypeParameter_with_recover_2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, MoveParser::ItemSpecTypeParameter_recover);
    return result_ || pinned_;
  }

  // !'>'
  private static boolean ItemSpecTypeParameter_with_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecTypeParameter_with_recover_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !consumeToken(builder_, GT);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ',' | &'>'
  private static boolean ItemSpecTypeParameter_with_recover_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecTypeParameter_with_recover_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    if (!result_) result_ = ItemSpecTypeParameter_with_recover_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // &'>'
  private static boolean ItemSpecTypeParameter_with_recover_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ItemSpecTypeParameter_with_recover_2_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _AND_);
    result_ = consumeToken(builder_, GT);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // QUOTE_IDENTIFIER ':'
  public static boolean LabelDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LabelDecl")) return false;
    if (!nextTokenIs(builder_, QUOTE_IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, QUOTE_IDENTIFIER, COLON);
    exit_section_(builder_, marker_, LABEL_DECL, result_);
    return result_;
  }

  /* ********************************************************** */
  // QUOTE_IDENTIFIER
  public static boolean LabelRef(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LabelRef")) return false;
    if (!nextTokenIs(builder_, QUOTE_IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, QUOTE_IDENTIFIER);
    exit_section_(builder_, marker_, LABEL_REF, result_);
    return result_;
  }

  /* ********************************************************** */
  // '->' Type CodeBlock
  public static boolean LambdaExprBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LambdaExprBlock")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, LAMBDA_EXPR_BLOCK, "<lambda expr block>");
    result_ = consumeToken(builder_, "->");
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, Type(builder_, level_ + 1));
    result_ = pinned_ && CodeBlock(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // Expr
  static boolean LambdaExprBody(PsiBuilder builder_, int level_) {
    return Expr(builder_, level_ + 1, -1);
  }

  /* ********************************************************** */
  // PatBinding TypeAnnotation?
  public static boolean LambdaParam(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LambdaParam")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, LAMBDA_PARAM, "<lambda param>");
    result_ = PatBinding(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && LambdaParam_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // TypeAnnotation?
  private static boolean LambdaParam_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LambdaParam_1")) return false;
    TypeAnnotation(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // LambdaParam (',' LambdaParam)*
  static boolean LambdaParams_list(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LambdaParams_list")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = LambdaParam(builder_, level_ + 1);
    result_ = result_ && LambdaParams_list_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (',' LambdaParam)*
  private static boolean LambdaParams_list_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LambdaParams_list_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!LambdaParams_list_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "LambdaParams_list_1", pos_)) break;
    }
    return true;
  }

  // ',' LambdaParam
  private static boolean LambdaParams_list_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LambdaParams_list_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && LambdaParam(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // '|' <<non_empty_comma_sep_items LambdaTypeParam>>? '|' (('->')? Type)?
  public static boolean LambdaType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LambdaType")) return false;
    if (!nextTokenIs(builder_, OR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, LAMBDA_TYPE, null);
    result_ = consumeToken(builder_, OR);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, LambdaType_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, OR)) && result_;
    result_ = pinned_ && LambdaType_3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // <<non_empty_comma_sep_items LambdaTypeParam>>?
  private static boolean LambdaType_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LambdaType_1")) return false;
    non_empty_comma_sep_items(builder_, level_ + 1, MoveParser::LambdaTypeParam);
    return true;
  }

  // (('->')? Type)?
  private static boolean LambdaType_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LambdaType_3")) return false;
    LambdaType_3_0(builder_, level_ + 1);
    return true;
  }

  // ('->')? Type
  private static boolean LambdaType_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LambdaType_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = LambdaType_3_0_0(builder_, level_ + 1);
    result_ = result_ && Type(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ('->')?
  private static boolean LambdaType_3_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LambdaType_3_0_0")) return false;
    LambdaType_3_0_0_0(builder_, level_ + 1);
    return true;
  }

  // ('->')
  private static boolean LambdaType_3_0_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LambdaType_3_0_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, "->");
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // Type
  public static boolean LambdaTypeParam(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LambdaTypeParam")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, LAMBDA_TYPE_PARAM, "<lambda type param>");
    result_ = Type(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // ltlt
  public static boolean LeftShiftBinOp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LeftShiftBinOp")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, BINARY_OP, "<operator>");
    result_ = ltlt(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // '<'
  public static boolean LessBinOp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LessBinOp")) return false;
    if (!nextTokenIs(builder_, "<operator>", LT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BINARY_OP, "<operator>");
    result_ = consumeToken(builder_, LT);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // lteq
  public static boolean LessEqualsBinOp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LessEqualsBinOp")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, BINARY_OP, "<operator>");
    result_ = lteq(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // let mut? Pat TypeAnnotation? Initializer? ';'
  public static boolean LetMoveStmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LetMoveStmt")) return false;
    if (!nextTokenIs(builder_, LET)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, LET_STMT, null);
    result_ = consumeToken(builder_, LET);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, LetMoveStmt_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, Pat(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, LetMoveStmt_3(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, LetMoveStmt_4(builder_, level_ + 1)) && result_;
    result_ = pinned_ && consumeToken(builder_, SEMICOLON) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // mut?
  private static boolean LetMoveStmt_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LetMoveStmt_1")) return false;
    consumeToken(builder_, MUT);
    return true;
  }

  // TypeAnnotation?
  private static boolean LetMoveStmt_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LetMoveStmt_3")) return false;
    TypeAnnotation(builder_, level_ + 1);
    return true;
  }

  // Initializer?
  private static boolean LetMoveStmt_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LetMoveStmt_4")) return false;
    Initializer(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // (let post? ) mut? Pat TypeAnnotation? Initializer? ';'
  public static boolean LetMslStmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LetMslStmt")) return false;
    if (!nextTokenIs(builder_, LET)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, LET_STMT, null);
    result_ = LetMslStmt_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, LetMslStmt_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, Pat(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, LetMslStmt_3(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, LetMslStmt_4(builder_, level_ + 1)) && result_;
    result_ = pinned_ && consumeToken(builder_, SEMICOLON) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // let post?
  private static boolean LetMslStmt_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LetMslStmt_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LET);
    result_ = result_ && LetMslStmt_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // post?
  private static boolean LetMslStmt_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LetMslStmt_0_1")) return false;
    post(builder_, level_ + 1);
    return true;
  }

  // mut?
  private static boolean LetMslStmt_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LetMslStmt_1")) return false;
    consumeToken(builder_, MUT);
    return true;
  }

  // TypeAnnotation?
  private static boolean LetMslStmt_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LetMslStmt_3")) return false;
    TypeAnnotation(builder_, level_ + 1);
    return true;
  }

  // Initializer?
  private static boolean LetMslStmt_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LetMslStmt_4")) return false;
    Initializer(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER '!'
  public static boolean MacroIdent(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MacroIdent")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IDENTIFIER, EXCL);
    exit_section_(builder_, marker_, MACRO_IDENT, result_);
    return result_;
  }

  /* ********************************************************** */
  // '(' Expr ')'
  public static boolean MatchArgument(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MatchArgument")) return false;
    if (!nextTokenIs(builder_, L_PAREN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, L_PAREN);
    result_ = result_ && Expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, R_PAREN);
    exit_section_(builder_, marker_, MATCH_ARGUMENT, result_);
    return result_;
  }

  /* ********************************************************** */
  // Attr* (Pat) MatchArmGuard? '=>' Expr ','?
  public static boolean MatchArm(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MatchArm")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, MATCH_ARM, "<match arm>");
    result_ = MatchArm_0(builder_, level_ + 1);
    result_ = result_ && MatchArm_1(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, MatchArm_2(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, FAT_ARROW)) && result_;
    result_ = pinned_ && report_error_(builder_, Expr(builder_, level_ + 1, -1)) && result_;
    result_ = pinned_ && MatchArm_5(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // Attr*
  private static boolean MatchArm_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MatchArm_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Attr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "MatchArm_0", pos_)) break;
    }
    return true;
  }

  // (Pat)
  private static boolean MatchArm_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MatchArm_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Pat(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // MatchArmGuard?
  private static boolean MatchArm_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MatchArm_2")) return false;
    MatchArmGuard(builder_, level_ + 1);
    return true;
  }

  // ','?
  private static boolean MatchArm_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MatchArm_5")) return false;
    consumeToken(builder_, COMMA);
    return true;
  }

  /* ********************************************************** */
  // if Expr
  public static boolean MatchArmGuard(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MatchArmGuard")) return false;
    if (!nextTokenIs(builder_, IF)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IF);
    result_ = result_ && Expr(builder_, level_ + 1, -1);
    exit_section_(builder_, marker_, MATCH_ARM_GUARD, result_);
    return result_;
  }

  /* ********************************************************** */
  // !(Pat_first | Attr_first | '}')
  static boolean MatchArm_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MatchArm_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !MatchArm_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // Pat_first | Attr_first | '}'
  private static boolean MatchArm_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MatchArm_recover_0")) return false;
    boolean result_;
    result_ = Pat_first(builder_, level_ + 1);
    if (!result_) result_ = Attr_first(builder_, level_ + 1);
    if (!result_) result_ = consumeTokenFast(builder_, R_BRACE);
    return result_;
  }

  /* ********************************************************** */
  // !'}' MatchArm
  static boolean MatchArm_with_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MatchArm_with_recover")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = MatchArm_with_recover_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && MatchArm(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, MoveParser::MatchArm_recover);
    return result_ || pinned_;
  }

  // !'}'
  private static boolean MatchArm_with_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MatchArm_with_recover_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !consumeToken(builder_, R_BRACE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // '{' MatchArm_with_recover* '}'
  public static boolean MatchBody(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MatchBody")) return false;
    if (!nextTokenIs(builder_, L_BRACE)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, MATCH_BODY, null);
    result_ = consumeToken(builder_, L_BRACE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, MatchBody_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_BRACE) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // MatchArm_with_recover*
  private static boolean MatchBody_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MatchBody_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!MatchArm_with_recover(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "MatchBody_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // Pat | PathPat
  public static boolean MatchPat(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MatchPat")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, MATCH_PAT, "<match pat>");
    result_ = Pat(builder_, level_ + 1);
    if (!result_) result_ = PathPat(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER'!'? ColonTypeArgumentList? ValueArgumentList
  public static boolean MethodCall(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MethodCall")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER);
    result_ = result_ && MethodCall_1(builder_, level_ + 1);
    result_ = result_ && MethodCall_2(builder_, level_ + 1);
    result_ = result_ && ValueArgumentList(builder_, level_ + 1);
    exit_section_(builder_, marker_, METHOD_CALL, result_);
    return result_;
  }

  // '!'?
  private static boolean MethodCall_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MethodCall_1")) return false;
    consumeToken(builder_, EXCL);
    return true;
  }

  // ColonTypeArgumentList?
  private static boolean MethodCall_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MethodCall_2")) return false;
    ColonTypeArgumentList(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // '-'
  public static boolean MinusBinOp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MinusBinOp")) return false;
    if (!nextTokenIs(builder_, "<operator>", MINUS)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BINARY_OP, "<operator>");
    result_ = consumeToken(builder_, MINUS);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // '%'
  public static boolean ModBinOp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModBinOp")) return false;
    if (!nextTokenIs(builder_, "<operator>", MODULO)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BINARY_OP, "<operator>");
    result_ = consumeToken(builder_, MODULO);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // modifies SpecPropertyList? Expr
  public static boolean ModifiesSpecExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModifiesSpecExpr")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, MODIFIES_SPEC_EXPR, "<expression>");
    result_ = modifies(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, ModifiesSpecExpr_1(builder_, level_ + 1));
    result_ = pinned_ && Expr(builder_, level_ + 1, -1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // SpecPropertyList?
  private static boolean ModifiesSpecExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModifiesSpecExpr_1")) return false;
    SpecPropertyList(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // Attr* MODULE_KW (AddressRef '::')? IDENTIFIER ';'? '{'? ModuleItem_with_recover* '}'?
  public static boolean Module(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Module")) return false;
    if (!nextTokenIs(builder_, "<module declaration>", HASH, MODULE_KW)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, MODULE, "<module declaration>");
    result_ = Module_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, MODULE_KW);
    pinned_ = result_; // pin = MODULE_KW
    result_ = result_ && report_error_(builder_, Module_2(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, IDENTIFIER)) && result_;
    result_ = pinned_ && report_error_(builder_, Module_4(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, Module_5(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, Module_6(builder_, level_ + 1)) && result_;
    result_ = pinned_ && Module_7(builder_, level_ + 1) && result_;
    register_hook_(builder_, LEFT_BINDER, ADJACENT_LINE_COMMENTS);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // Attr*
  private static boolean Module_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Module_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Attr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Module_0", pos_)) break;
    }
    return true;
  }

  // (AddressRef '::')?
  private static boolean Module_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Module_2")) return false;
    Module_2_0(builder_, level_ + 1);
    return true;
  }

  // AddressRef '::'
  private static boolean Module_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Module_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = AddressRef(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON_COLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ';'?
  private static boolean Module_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Module_4")) return false;
    consumeToken(builder_, SEMICOLON);
    return true;
  }

  // '{'?
  private static boolean Module_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Module_5")) return false;
    consumeToken(builder_, L_BRACE);
    return true;
  }

  // ModuleItem_with_recover*
  private static boolean Module_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Module_6")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!ModuleItem_with_recover(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Module_6", pos_)) break;
    }
    return true;
  }

  // '}'?
  private static boolean Module_7(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Module_7")) return false;
    consumeToken(builder_, R_BRACE);
    return true;
  }

  /* ********************************************************** */
  // '{' ModuleItem_with_recover* '}'
  public static boolean ModuleBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleBlock")) return false;
    if (!nextTokenIs(builder_, L_BRACE)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, MODULE_BLOCK, null);
    result_ = consumeToken(builder_, L_BRACE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, ModuleBlock_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_BRACE) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // ModuleItem_with_recover*
  private static boolean ModuleBlock_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleBlock_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!ModuleItem_with_recover(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "ModuleBlock_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // UseStmt
  //                             | PublicUseFun
  //                             | FriendDecl
  //                             | StructItem
  //                             | Enum
  //                             | FunctionItem
  //                             | SpecFunctionItem
  //                             | Const
  //                             | Schema
  //                             | ModuleItemSpec
  //                             | ItemSpec
  static boolean ModuleItem(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleItem")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = UseStmt(builder_, level_ + 1);
    if (!result_) result_ = PublicUseFun(builder_, level_ + 1);
    if (!result_) result_ = FriendDecl(builder_, level_ + 1);
    if (!result_) result_ = StructItem(builder_, level_ + 1);
    if (!result_) result_ = Enum(builder_, level_ + 1);
    if (!result_) result_ = FunctionItem(builder_, level_ + 1);
    if (!result_) result_ = SpecFunctionItem(builder_, level_ + 1);
    if (!result_) result_ = Const(builder_, level_ + 1);
    if (!result_) result_ = Schema(builder_, level_ + 1);
    if (!result_) result_ = ModuleItemSpec(builder_, level_ + 1);
    if (!result_) result_ = ItemSpec(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // spec MODULE_KW <<msl SpecCodeBlock>>
  public static boolean ModuleItemSpec(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleItemSpec")) return false;
    if (!nextTokenIs(builder_, SPEC)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, MODULE_ITEM_SPEC, null);
    result_ = consumeTokens(builder_, 2, SPEC, MODULE_KW);
    pinned_ = result_; // pin = 2
    result_ = result_ && msl(builder_, level_ + 1, MoveParser::SpecCodeBlock);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // use | public | native | fun | CONST_KW | STRUCT_KW | spec
  //                             | Attr_first | "friend" | "entry" | "inline" | "enum" | MACRO_KW
  static boolean ModuleItem_first(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleItem_first")) return false;
    boolean result_;
    result_ = consumeTokenFast(builder_, USE);
    if (!result_) result_ = consumeTokenFast(builder_, PUBLIC);
    if (!result_) result_ = consumeTokenFast(builder_, NATIVE);
    if (!result_) result_ = consumeTokenFast(builder_, FUN);
    if (!result_) result_ = consumeTokenFast(builder_, CONST_KW);
    if (!result_) result_ = consumeTokenFast(builder_, STRUCT_KW);
    if (!result_) result_ = consumeTokenFast(builder_, SPEC);
    if (!result_) result_ = Attr_first(builder_, level_ + 1);
    if (!result_) result_ = consumeTokenFast(builder_, "friend");
    if (!result_) result_ = consumeTokenFast(builder_, "entry");
    if (!result_) result_ = consumeTokenFast(builder_, "inline");
    if (!result_) result_ = consumeTokenFast(builder_, "enum");
    if (!result_) result_ = consumeTokenFast(builder_, MACRO_KW);
    return result_;
  }

  /* ********************************************************** */
  // !('}' | ModuleItem_first | TopLevel_first)
  static boolean ModuleItem_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleItem_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !ModuleItem_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // '}' | ModuleItem_first | TopLevel_first
  private static boolean ModuleItem_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleItem_recover_0")) return false;
    boolean result_;
    result_ = consumeTokenFast(builder_, R_BRACE);
    if (!result_) result_ = ModuleItem_first(builder_, level_ + 1);
    if (!result_) result_ = TopLevel_first(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // !('}' | <<eof>>) ModuleItem
  static boolean ModuleItem_with_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleItem_with_recover")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = ModuleItem_with_recover_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && ModuleItem(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, MoveParser::ModuleItem_recover);
    return result_ || pinned_;
  }

  // !('}' | <<eof>>)
  private static boolean ModuleItem_with_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleItem_with_recover_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !ModuleItem_with_recover_0_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // '}' | <<eof>>
  private static boolean ModuleItem_with_recover_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleItem_with_recover_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, R_BRACE);
    if (!result_) result_ = eof(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // Attr* spec PathImpl ModuleSpecBlock
  public static boolean ModuleSpec(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleSpec")) return false;
    if (!nextTokenIs(builder_, "<module spec declaration>", HASH, SPEC)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, MODULE_SPEC, "<module spec declaration>");
    result_ = ModuleSpec_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SPEC);
    pinned_ = result_; // pin = spec
    result_ = result_ && report_error_(builder_, PathImpl(builder_, level_ + 1));
    result_ = pinned_ && ModuleSpecBlock(builder_, level_ + 1) && result_;
    register_hook_(builder_, LEFT_BINDER, ADJACENT_LINE_COMMENTS);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // Attr*
  private static boolean ModuleSpec_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleSpec_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Attr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "ModuleSpec_0", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // '{' ModuleSpecBlock_item_with_recover* '}'
  public static boolean ModuleSpecBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleSpecBlock")) return false;
    if (!nextTokenIs(builder_, L_BRACE)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, MODULE_SPEC_BLOCK, null);
    result_ = consumeToken(builder_, L_BRACE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, ModuleSpecBlock_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_BRACE) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // ModuleSpecBlock_item_with_recover*
  private static boolean ModuleSpecBlock_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleSpecBlock_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!ModuleSpecBlock_item_with_recover(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "ModuleSpecBlock_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // UseStmt | SpecFunctionItem | Schema | ModuleItemSpec | ItemSpec
  static boolean ModuleSpecBlock_item(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleSpecBlock_item")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = UseStmt(builder_, level_ + 1);
    if (!result_) result_ = SpecFunctionItem(builder_, level_ + 1);
    if (!result_) result_ = Schema(builder_, level_ + 1);
    if (!result_) result_ = ModuleItemSpec(builder_, level_ + 1);
    if (!result_) result_ = ItemSpec(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // !('}' | <<eof>> | spec | use)
  static boolean ModuleSpecBlock_item_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleSpecBlock_item_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !ModuleSpecBlock_item_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // '}' | <<eof>> | spec | use
  private static boolean ModuleSpecBlock_item_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleSpecBlock_item_recover_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenFast(builder_, R_BRACE);
    if (!result_) result_ = eof(builder_, level_ + 1);
    if (!result_) result_ = consumeTokenFast(builder_, SPEC);
    if (!result_) result_ = consumeTokenFast(builder_, USE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // !('}' | <<eof>>) ModuleSpecBlock_item
  static boolean ModuleSpecBlock_item_with_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleSpecBlock_item_with_recover")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = ModuleSpecBlock_item_with_recover_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && ModuleSpecBlock_item(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, MoveParser::ModuleSpecBlock_item_recover);
    return result_ || pinned_;
  }

  // !('}' | <<eof>>)
  private static boolean ModuleSpecBlock_item_with_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleSpecBlock_item_with_recover_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !ModuleSpecBlock_item_with_recover_0_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // '}' | <<eof>>
  private static boolean ModuleSpecBlock_item_with_recover_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ModuleSpecBlock_item_with_recover_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, R_BRACE);
    if (!result_) result_ = eof(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // '*'
  public static boolean MulBinOp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MulBinOp")) return false;
    if (!nextTokenIs(builder_, "<operator>", MUL)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BINARY_OP, "<operator>");
    result_ = consumeToken(builder_, MUL);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean NamedAddress(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NamedAddress")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER);
    exit_section_(builder_, marker_, NAMED_ADDRESS, result_);
    return result_;
  }

  /* ********************************************************** */
  // address IDENTIFIER '=' AddressRef ';'
  public static boolean NamedAddressDef(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NamedAddressDef")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, NAMED_ADDRESS_DEF, "<named address def>");
    result_ = address(builder_, level_ + 1);
    result_ = result_ && consumeTokens(builder_, 2, IDENTIFIER, EQ);
    pinned_ = result_; // pin = 3
    result_ = result_ && report_error_(builder_, AddressRef(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, SEMICOLON) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // Attr* IDENTIFIER TypeAnnotation &(',' | '}')
  public static boolean NamedFieldDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NamedFieldDecl")) return false;
    if (!nextTokenIs(builder_, "<named field decl>", HASH, IDENTIFIER)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, NAMED_FIELD_DECL, "<named field decl>");
    result_ = NamedFieldDecl_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, TypeAnnotation(builder_, level_ + 1));
    result_ = pinned_ && NamedFieldDecl_3(builder_, level_ + 1) && result_;
    register_hook_(builder_, LEFT_BINDER, ADJACENT_LINE_COMMENTS);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // Attr*
  private static boolean NamedFieldDecl_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NamedFieldDecl_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Attr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "NamedFieldDecl_0", pos_)) break;
    }
    return true;
  }

  // &(',' | '}')
  private static boolean NamedFieldDecl_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NamedFieldDecl_3")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _AND_);
    result_ = NamedFieldDecl_3_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ',' | '}'
  private static boolean NamedFieldDecl_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NamedFieldDecl_3_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, COMMA);
    if (!result_) result_ = consumeToken(builder_, R_BRACE);
    return result_;
  }

  /* ********************************************************** */
  // AttrsAndVis_first | IDENTIFIER
  static boolean NamedFieldDecl_first(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NamedFieldDecl_first")) return false;
    if (!nextTokenIsFast(builder_, HASH, IDENTIFIER)) return false;
    boolean result_;
    result_ = AttrsAndVis_first(builder_, level_ + 1);
    if (!result_) result_ = consumeTokenFast(builder_, IDENTIFIER);
    return result_;
  }

  /* ********************************************************** */
  // !('}' | NamedFieldDecl_first)
  static boolean NamedFieldDecl_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NamedFieldDecl_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !NamedFieldDecl_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // '}' | NamedFieldDecl_first
  private static boolean NamedFieldDecl_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NamedFieldDecl_recover_0")) return false;
    boolean result_;
    result_ = consumeTokenFast(builder_, R_BRACE);
    if (!result_) result_ = NamedFieldDecl_first(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // !'}' NamedFieldDecl (',' | &'}')
  static boolean NamedFieldDecl_with_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NamedFieldDecl_with_recover")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = NamedFieldDecl_with_recover_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, NamedFieldDecl(builder_, level_ + 1));
    result_ = pinned_ && NamedFieldDecl_with_recover_2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, MoveParser::NamedFieldDecl_recover);
    return result_ || pinned_;
  }

  // !'}'
  private static boolean NamedFieldDecl_with_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NamedFieldDecl_with_recover_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !consumeToken(builder_, R_BRACE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ',' | &'}'
  private static boolean NamedFieldDecl_with_recover_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NamedFieldDecl_with_recover_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    if (!result_) result_ = NamedFieldDecl_with_recover_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // &'}'
  private static boolean NamedFieldDecl_with_recover_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NamedFieldDecl_with_recover_2_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _AND_);
    result_ = consumeToken(builder_, R_BRACE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // Attr* NativeFunctionModifierSetInner fun FunctionSignatureInner ';'
  public static boolean NativeFunctionInner(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NativeFunctionInner")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, FUNCTION, "<native function inner>");
    result_ = NativeFunctionInner_0(builder_, level_ + 1);
    result_ = result_ && NativeFunctionModifierSetInner(builder_, level_ + 1);
    pinned_ = result_; // pin = NativeFunctionModifierSetInner
    result_ = result_ && report_error_(builder_, consumeToken(builder_, FUN));
    result_ = pinned_ && report_error_(builder_, FunctionSignatureInner(builder_, level_ + 1)) && result_;
    result_ = pinned_ && consumeToken(builder_, SEMICOLON) && result_;
    register_hook_(builder_, LEFT_BINDER, ADJACENT_LINE_COMMENTS);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // Attr*
  private static boolean NativeFunctionInner_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NativeFunctionInner_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Attr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "NativeFunctionInner_0", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // <<nativeFunctionModifierSet VisibilityModifier>>
  static boolean NativeFunctionModifierSetInner(PsiBuilder builder_, int level_) {
    return nativeFunctionModifierSet(builder_, level_ + 1, MoveParser::VisibilityModifier);
  }

  /* ********************************************************** */
  // Attr* spec (native fun) IDENTIFIER TypeParameterList?
  //                              FunctionParameterList ReturnType? ';'
  public static boolean NativeSpecFunctionInner(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NativeSpecFunctionInner")) return false;
    if (!nextTokenIs(builder_, "<native spec function inner>", HASH, SPEC)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, SPEC_FUNCTION, "<native spec function inner>");
    result_ = NativeSpecFunctionInner_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SPEC);
    result_ = result_ && NativeSpecFunctionInner_2(builder_, level_ + 1);
    pinned_ = result_; // pin = 3
    result_ = result_ && report_error_(builder_, consumeToken(builder_, IDENTIFIER));
    result_ = pinned_ && report_error_(builder_, NativeSpecFunctionInner_4(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, FunctionParameterList(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, NativeSpecFunctionInner_6(builder_, level_ + 1)) && result_;
    result_ = pinned_ && consumeToken(builder_, SEMICOLON) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // Attr*
  private static boolean NativeSpecFunctionInner_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NativeSpecFunctionInner_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Attr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "NativeSpecFunctionInner_0", pos_)) break;
    }
    return true;
  }

  // native fun
  private static boolean NativeSpecFunctionInner_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NativeSpecFunctionInner_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, NATIVE, FUN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // TypeParameterList?
  private static boolean NativeSpecFunctionInner_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NativeSpecFunctionInner_4")) return false;
    TypeParameterList(builder_, level_ + 1);
    return true;
  }

  // ReturnType?
  private static boolean NativeSpecFunctionInner_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NativeSpecFunctionInner_6")) return false;
    ReturnType(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // (native fun) IDENTIFIER TypeParameterList?
  //                                   FunctionParameterList ReturnType? ';'
  public static boolean NativeSpecInlineFunctionInner(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NativeSpecInlineFunctionInner")) return false;
    if (!nextTokenIs(builder_, NATIVE)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, SPEC_INLINE_FUNCTION, null);
    result_ = NativeSpecInlineFunctionInner_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, consumeToken(builder_, IDENTIFIER));
    result_ = pinned_ && report_error_(builder_, NativeSpecInlineFunctionInner_2(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, FunctionParameterList(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, NativeSpecInlineFunctionInner_4(builder_, level_ + 1)) && result_;
    result_ = pinned_ && consumeToken(builder_, SEMICOLON) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // native fun
  private static boolean NativeSpecInlineFunctionInner_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NativeSpecInlineFunctionInner_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, NATIVE, FUN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // TypeParameterList?
  private static boolean NativeSpecInlineFunctionInner_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NativeSpecInlineFunctionInner_2")) return false;
    TypeParameterList(builder_, level_ + 1);
    return true;
  }

  // ReturnType?
  private static boolean NativeSpecInlineFunctionInner_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NativeSpecInlineFunctionInner_4")) return false;
    ReturnType(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // Attr* PublicKeyword? NativeStructInnerFirst IDENTIFIER TypeParameterList? AbilitiesList? ';'
  public static boolean NativeStructInner(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NativeStructInner")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, STRUCT, "<native struct inner>");
    result_ = NativeStructInner_0(builder_, level_ + 1);
    result_ = result_ && NativeStructInner_1(builder_, level_ + 1);
    result_ = result_ && NativeStructInnerFirst(builder_, level_ + 1);
    pinned_ = result_; // pin = NativeStructInnerFirst
    result_ = result_ && report_error_(builder_, consumeToken(builder_, IDENTIFIER));
    result_ = pinned_ && report_error_(builder_, NativeStructInner_4(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, NativeStructInner_5(builder_, level_ + 1)) && result_;
    result_ = pinned_ && consumeToken(builder_, SEMICOLON) && result_;
    register_hook_(builder_, LEFT_BINDER, ADJACENT_LINE_COMMENTS);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // Attr*
  private static boolean NativeStructInner_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NativeStructInner_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Attr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "NativeStructInner_0", pos_)) break;
    }
    return true;
  }

  // PublicKeyword?
  private static boolean NativeStructInner_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NativeStructInner_1")) return false;
    PublicKeyword(builder_, level_ + 1);
    return true;
  }

  // TypeParameterList?
  private static boolean NativeStructInner_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NativeStructInner_4")) return false;
    TypeParameterList(builder_, level_ + 1);
    return true;
  }

  // AbilitiesList?
  private static boolean NativeStructInner_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NativeStructInner_5")) return false;
    AbilitiesList(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // native STRUCT_KW
  static boolean NativeStructInnerFirst(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NativeStructInnerFirst")) return false;
    if (!nextTokenIs(builder_, NATIVE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, NATIVE, STRUCT_KW);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // '!='
  public static boolean NotEqualsBinOp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "NotEqualsBinOp")) return false;
    if (!nextTokenIs(builder_, "<operator>", NOT_EQ)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BINARY_OP, "<operator>");
    result_ = consumeToken(builder_, NOT_EQ);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // oror
  public static boolean OrBinOp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OrBinOp")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, BINARY_OP, "<operator>");
    result_ = oror(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // (<<isPathMode 'VALUE' >> IDENTIFIER '!'?)
  //                                 | (<<isPathMode 'WILDCARD' >> ('*' | IDENTIFIER))
  static boolean PATH_MODE_IDENTIFIER(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PATH_MODE_IDENTIFIER")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = PATH_MODE_IDENTIFIER_0(builder_, level_ + 1);
    if (!result_) result_ = PATH_MODE_IDENTIFIER_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // <<isPathMode 'VALUE' >> IDENTIFIER '!'?
  private static boolean PATH_MODE_IDENTIFIER_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PATH_MODE_IDENTIFIER_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = isPathMode(builder_, level_ + 1, VALUE);
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    result_ = result_ && PATH_MODE_IDENTIFIER_0_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '!'?
  private static boolean PATH_MODE_IDENTIFIER_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PATH_MODE_IDENTIFIER_0_2")) return false;
    consumeToken(builder_, EXCL);
    return true;
  }

  // <<isPathMode 'WILDCARD' >> ('*' | IDENTIFIER)
  private static boolean PATH_MODE_IDENTIFIER_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PATH_MODE_IDENTIFIER_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = isPathMode(builder_, level_ + 1, WILDCARD);
    result_ = result_ && PATH_MODE_IDENTIFIER_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '*' | IDENTIFIER
  private static boolean PATH_MODE_IDENTIFIER_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PATH_MODE_IDENTIFIER_1_1")) return false;
    boolean result_;
    result_ = consumeToken(builder_, MUL);
    if (!result_) result_ = consumeToken(builder_, IDENTIFIER);
    return result_;
  }

  /* ********************************************************** */
  // !(')' | Pat_first)
  static boolean ParenListElemPat_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ParenListElemPat_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !ParenListElemPat_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ')' | Pat_first
  private static boolean ParenListElemPat_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ParenListElemPat_recover_0")) return false;
    boolean result_;
    result_ = consumeTokenFast(builder_, R_PAREN);
    if (!result_) result_ = Pat_first(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // !')' Pat (',' | &')')
  static boolean ParenListElemPat_with_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ParenListElemPat_with_recover")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = ParenListElemPat_with_recover_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, Pat(builder_, level_ + 1));
    result_ = pinned_ && ParenListElemPat_with_recover_2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, MoveParser::ParenListElemPat_recover);
    return result_ || pinned_;
  }

  // !')'
  private static boolean ParenListElemPat_with_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ParenListElemPat_with_recover_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !consumeToken(builder_, R_PAREN);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ',' | &')'
  private static boolean ParenListElemPat_with_recover_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ParenListElemPat_with_recover_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    if (!result_) result_ = ParenListElemPat_with_recover_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // &')'
  private static boolean ParenListElemPat_with_recover_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ParenListElemPat_with_recover_2_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _AND_);
    result_ = consumeToken(builder_, R_PAREN);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // '(' (Type !',') ')'
  public static boolean ParensType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ParensType")) return false;
    if (!nextTokenIs(builder_, L_PAREN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PARENS_TYPE, null);
    result_ = consumeToken(builder_, L_PAREN);
    result_ = result_ && ParensType_1(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, R_PAREN);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // Type !','
  private static boolean ParensType_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ParensType_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Type(builder_, level_ + 1);
    result_ = result_ && ParensType_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // !','
  private static boolean ParensType_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ParensType_1_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !consumeToken(builder_, COMMA);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // <<mslOnly lt_eqeq_gt>>
  public static boolean PartialImplyBinOp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PartialImplyBinOp")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, BINARY_OP, "<operator>");
    result_ = mslOnly(builder_, level_ + 1, MoveParser::lt_eqeq_gt);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // PatWild
  //         | PatTuple
  //         | PatBinding
  //         | PatStruct
  //         | PatConst
  public static boolean Pat(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Pat")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, PAT, "<pattern>");
    result_ = PatWild(builder_, level_ + 1);
    if (!result_) result_ = PatTuple(builder_, level_ + 1);
    if (!result_) result_ = PatBinding(builder_, level_ + 1);
    if (!result_) result_ = PatStruct(builder_, level_ + 1);
    if (!result_) result_ = PatConst(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // mut? (IDENTIFIER|MARCO_IDENTIFIER) !ForbiddenPatBindingLast
  public static boolean PatBinding(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PatBinding")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PAT_BINDING, "<pat binding>");
    result_ = PatBinding_0(builder_, level_ + 1);
    result_ = result_ && PatBinding_1(builder_, level_ + 1);
    result_ = result_ && PatBinding_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // mut?
  private static boolean PatBinding_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PatBinding_0")) return false;
    consumeToken(builder_, MUT);
    return true;
  }

  // IDENTIFIER|MARCO_IDENTIFIER
  private static boolean PatBinding_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PatBinding_1")) return false;
    boolean result_;
    result_ = consumeToken(builder_, IDENTIFIER);
    if (!result_) result_ = consumeToken(builder_, MARCO_IDENTIFIER);
    return result_;
  }

  // !ForbiddenPatBindingLast
  private static boolean PatBinding_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PatBinding_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !ForbiddenPatBindingLast(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // PathExpr
  public static boolean PatConst(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PatConst")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PAT_CONST, "<pat const>");
    result_ = PathExpr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // PatFieldFull | PatBinding | PatRest
  public static boolean PatField(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PatField")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PAT_FIELD, "<pat field>");
    result_ = PatFieldFull(builder_, level_ + 1);
    if (!result_) result_ = PatBinding(builder_, level_ + 1);
    if (!result_) result_ = PatRest(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // mut? IDENTIFIER ':' Pat
  public static boolean PatFieldFull(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PatFieldFull")) return false;
    if (!nextTokenIs(builder_, "<pat field full>", IDENTIFIER, MUT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PAT_FIELD_FULL, "<pat field full>");
    result_ = PatFieldFull_0(builder_, level_ + 1);
    result_ = result_ && consumeTokens(builder_, 0, IDENTIFIER, COLON);
    result_ = result_ && Pat(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // mut?
  private static boolean PatFieldFull_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PatFieldFull_0")) return false;
    consumeToken(builder_, MUT);
    return true;
  }

  /* ********************************************************** */
  // !('}' | DOT_DOT | mut? IDENTIFIER)
  static boolean PatField_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PatField_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !PatField_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // '}' | DOT_DOT | mut? IDENTIFIER
  private static boolean PatField_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PatField_recover_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenFast(builder_, R_BRACE);
    if (!result_) result_ = consumeTokenFast(builder_, DOT_DOT);
    if (!result_) result_ = PatField_recover_0_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // mut? IDENTIFIER
  private static boolean PatField_recover_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PatField_recover_0_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = PatField_recover_0_2_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, IDENTIFIER);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // mut?
  private static boolean PatField_recover_0_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PatField_recover_0_2_0")) return false;
    consumeTokenFast(builder_, MUT);
    return true;
  }

  /* ********************************************************** */
  // !'}' PatField (',' | &'}')
  static boolean PatField_with_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PatField_with_recover")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = PatField_with_recover_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, PatField(builder_, level_ + 1));
    result_ = pinned_ && PatField_with_recover_2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, MoveParser::PatField_recover);
    return result_ || pinned_;
  }

  // !'}'
  private static boolean PatField_with_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PatField_with_recover_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !consumeToken(builder_, R_BRACE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ',' | &'}'
  private static boolean PatField_with_recover_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PatField_with_recover_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    if (!result_) result_ = PatField_with_recover_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // &'}'
  private static boolean PatField_with_recover_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PatField_with_recover_2_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _AND_);
    result_ = consumeToken(builder_, R_BRACE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // PatBinding
  public static boolean PatIdent(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PatIdent")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PAT_IDENT, "<pat ident>");
    result_ = PatBinding(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // DOT_DOT
  public static boolean PatRest(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PatRest")) return false;
    if (!nextTokenIs(builder_, DOT_DOT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOT_DOT);
    exit_section_(builder_, marker_, PAT_REST, result_);
    return result_;
  }

  /* ********************************************************** */
  // PathImpl '{' PatField_with_recover* '}'
  public static boolean PatStruct(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PatStruct")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PAT_STRUCT, "<pat struct>");
    result_ = PathImpl(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, L_BRACE);
    result_ = result_ && PatStruct_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_BRACE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // PatField_with_recover*
  private static boolean PatStruct_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PatStruct_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!PatField_with_recover(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "PatStruct_2", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // '(' ParenListElemPat_with_recover* ')'
  public static boolean PatTuple(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PatTuple")) return false;
    if (!nextTokenIs(builder_, L_PAREN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, L_PAREN);
    result_ = result_ && PatTuple_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_PAREN);
    exit_section_(builder_, marker_, PAT_TUPLE, result_);
    return result_;
  }

  // ParenListElemPat_with_recover*
  private static boolean PatTuple_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PatTuple_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!ParenListElemPat_with_recover(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "PatTuple_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // '_'
  public static boolean PatWild(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PatWild")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PAT_WILD, "<pat wild>");
    result_ = consumeToken(builder_, "_");
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // '_' | '(' | Path_first | AnyLitToken_first | mut
  static boolean Pat_first(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Pat_first")) return false;
    boolean result_;
    result_ = consumeTokenFast(builder_, "_");
    if (!result_) result_ = consumeTokenFast(builder_, L_PAREN);
    if (!result_) result_ = Path_first(builder_, level_ + 1);
    if (!result_) result_ = AnyLitToken_first(builder_, level_ + 1);
    if (!result_) result_ = consumeTokenFast(builder_, MUT);
    return result_;
  }

  /* ********************************************************** */
  // DIEM_ADDRESS
  //                 | INTEGER_LITERAL
  //                 | PLACEHOLDER_ADDRESS
  //                 | BECH32_ADDRESS
  //                 | POLKADOT_ADDRESS
  public static boolean PathAddress(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PathAddress")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PATH_ADDRESS, "<path address>");
    result_ = consumeToken(builder_, DIEM_ADDRESS);
    if (!result_) result_ = consumeToken(builder_, INTEGER_LITERAL);
    if (!result_) result_ = consumeToken(builder_, PLACEHOLDER_ADDRESS);
    if (!result_) result_ = consumeToken(builder_, BECH32_ADDRESS);
    if (!result_) result_ = consumeToken(builder_, POLKADOT_ADDRESS);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // PATH_MODE_IDENTIFIER | IDENTIFIER | QUOTE_IDENTIFIER
  static boolean PathIdent(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PathIdent")) return false;
    boolean result_;
    result_ = PATH_MODE_IDENTIFIER(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, IDENTIFIER);
    if (!result_) result_ = consumeToken(builder_, QUOTE_IDENTIFIER);
    return result_;
  }

  /* ********************************************************** */
  // PathStart PathSegment*
  static boolean PathImpl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PathImpl")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = PathStart(builder_, level_ + 1);
    result_ = result_ && PathImpl_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // PathSegment*
  private static boolean PathImpl_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PathImpl_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!PathSegment(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "PathImpl_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // PathImpl
  public static boolean PathPat(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PathPat")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PATH_PAT, "<path pat>");
    result_ = PathImpl(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // '::' !'{' PathIdent TypeArgumentList?
  public static boolean PathSegment(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PathSegment")) return false;
    if (!nextTokenIs(builder_, COLON_COLON)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _LEFT_, PATH, null);
    result_ = consumeToken(builder_, COLON_COLON);
    result_ = result_ && PathSegment_1(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, PathIdent(builder_, level_ + 1));
    result_ = pinned_ && PathSegment_3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // !'{'
  private static boolean PathSegment_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PathSegment_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !consumeToken(builder_, L_BRACE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // TypeArgumentList?
  private static boolean PathSegment_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PathSegment_3")) return false;
    TypeArgumentList(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // ((PathAddress &'::') | PathIdent) TypeArgumentList?
  public static boolean PathStart(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PathStart")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, PATH, "<path>");
    result_ = PathStart_0(builder_, level_ + 1);
    result_ = result_ && PathStart_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (PathAddress &'::') | PathIdent
  private static boolean PathStart_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PathStart_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = PathStart_0_0(builder_, level_ + 1);
    if (!result_) result_ = PathIdent(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // PathAddress &'::'
  private static boolean PathStart_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PathStart_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = PathAddress(builder_, level_ + 1);
    result_ = result_ && PathStart_0_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // &'::'
  private static boolean PathStart_0_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PathStart_0_0_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _AND_);
    result_ = consumeToken(builder_, COLON_COLON);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // TypeArgumentList?
  private static boolean PathStart_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PathStart_1")) return false;
    TypeArgumentList(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // PathImpl
  public static boolean PathType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PathType")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PATH_TYPE, "<type>");
    result_ = PathImpl(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // PathImpl
  static boolean PathWithoutTypeArgs(PsiBuilder builder_, int level_) {
    return PathImpl(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // PATH_MODE_IDENTIFIER | DIEM_ADDRESS | INTEGER_LITERAL
  static boolean Path_first(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Path_first")) return false;
    boolean result_;
    result_ = PATH_MODE_IDENTIFIER(builder_, level_ + 1);
    if (!result_) result_ = consumeTokenFast(builder_, DIEM_ADDRESS);
    if (!result_) result_ = consumeTokenFast(builder_, INTEGER_LITERAL);
    return result_;
  }

  /* ********************************************************** */
  // <<patternIdent>>
  static boolean PatternIdent(PsiBuilder builder_, int level_) {
    return patternIdent(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<patternVisibility>>
  static boolean PatternVis(PsiBuilder builder_, int level_) {
    return patternVisibility(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // phantom IDENTIFIER TypeParamBound?
  public static boolean PhantomTypeParameter(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PhantomTypeParameter")) return false;
    if (!nextTokenIs(builder_, PHANTOM)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, TYPE_PARAMETER, null);
    result_ = consumeTokens(builder_, 1, PHANTOM, IDENTIFIER);
    pinned_ = result_; // pin = 1
    result_ = result_ && PhantomTypeParameter_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // TypeParamBound?
  private static boolean PhantomTypeParameter_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PhantomTypeParameter_2")) return false;
    TypeParamBound(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER TypeParamBound?
  public static boolean PlainTypeParameter(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PlainTypeParameter")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, TYPE_PARAMETER, null);
    result_ = consumeToken(builder_, IDENTIFIER);
    pinned_ = result_; // pin = 1
    result_ = result_ && PlainTypeParameter_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // TypeParamBound?
  private static boolean PlainTypeParameter_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PlainTypeParameter_1")) return false;
    TypeParamBound(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // '+'
  public static boolean PlusBinOp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PlusBinOp")) return false;
    if (!nextTokenIs(builder_, "<operator>", PLUS)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BINARY_OP, "<operator>");
    result_ = consumeToken(builder_, PLUS);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER ('=' Expr)?
  public static boolean PragmaAttribute(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PragmaAttribute")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER);
    result_ = result_ && PragmaAttribute_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, PRAGMA_ATTRIBUTE, result_);
    return result_;
  }

  // ('=' Expr)?
  private static boolean PragmaAttribute_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PragmaAttribute_1")) return false;
    PragmaAttribute_1_0(builder_, level_ + 1);
    return true;
  }

  // '=' Expr
  private static boolean PragmaAttribute_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PragmaAttribute_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, EQ);
    result_ = result_ && Expr(builder_, level_ + 1, -1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // pragma <<non_empty_comma_sep_items PragmaAttribute>> ';'
  public static boolean PragmaSpecStmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PragmaSpecStmt")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, PRAGMA_SPEC_STMT, "<pragma spec stmt>");
    result_ = pragma(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, non_empty_comma_sep_items(builder_, level_ + 1, MoveParser::PragmaAttribute));
    result_ = pinned_ && consumeToken(builder_, SEMICOLON) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // 'public'
  static boolean PublicKeyword(PsiBuilder builder_, int level_) {
    return consumeToken(builder_, "public");
  }

  /* ********************************************************** */
  // Attr* 'public'? use fun PathImpl 'as' UseFunMethodAlias ';'
  public static boolean PublicUseFun(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PublicUseFun")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PUBLIC_USE_FUN, "<public use fun>");
    result_ = PublicUseFun_0(builder_, level_ + 1);
    result_ = result_ && PublicUseFun_1(builder_, level_ + 1);
    result_ = result_ && consumeTokens(builder_, 1, USE, FUN);
    pinned_ = result_; // pin = 3
    result_ = result_ && report_error_(builder_, PathImpl(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, "as")) && result_;
    result_ = pinned_ && report_error_(builder_, UseFunMethodAlias(builder_, level_ + 1)) && result_;
    result_ = pinned_ && consumeToken(builder_, SEMICOLON) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // Attr*
  private static boolean PublicUseFun_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PublicUseFun_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Attr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "PublicUseFun_0", pos_)) break;
    }
    return true;
  }

  // 'public'?
  private static boolean PublicUseFun_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PublicUseFun_1")) return false;
    consumeToken(builder_, "public");
    return true;
  }

  /* ********************************************************** */
  // PathImpl
  public static boolean QualPathCodeFragmentElement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "QualPathCodeFragmentElement")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, QUAL_PATH_CODE_FRAGMENT_ELEMENT, "<qual path code fragment element>");
    result_ = PathImpl(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // RangeQuantBinding | TypeQuantBinding
  public static boolean QuantBinding(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "QuantBinding")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, QUANT_BINDING, "<quant binding>");
    result_ = RangeQuantBinding(builder_, level_ + 1);
    if (!result_) result_ = TypeQuantBinding(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // <<non_empty_comma_sep_items QuantBinding>>
  public static boolean QuantBindings(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "QuantBindings")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, QUANT_BINDINGS, "<quant bindings>");
    result_ = non_empty_comma_sep_items(builder_, level_ + 1, MoveParser::QuantBinding);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // where Expr
  public static boolean QuantWhere(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "QuantWhere")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, QUANT_WHERE, "<quant where>");
    result_ = where(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && Expr(builder_, level_ + 1, -1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // PatBinding in Expr
  public static boolean RangeQuantBinding(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RangeQuantBinding")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, RANGE_QUANT_BINDING, "<range quant binding>");
    result_ = PatBinding(builder_, level_ + 1);
    result_ = result_ && in(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && Expr(builder_, level_ + 1, -1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // PathImpl
  public static boolean RefExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RefExpr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, REF_EXPR, "<expression>");
    result_ = PathImpl(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // RefTypeStart Type
  public static boolean RefType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RefType")) return false;
    if (!nextTokenIs(builder_, AND)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, REF_TYPE, null);
    result_ = RefTypeStart(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && Type(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // '&' mut?
  public static boolean RefTypeStart(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RefTypeStart")) return false;
    if (!nextTokenIs(builder_, AND)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, AND);
    result_ = result_ && RefTypeStart_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, REF_TYPE_START, result_);
    return result_;
  }

  // mut?
  private static boolean RefTypeStart_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RefTypeStart_1")) return false;
    consumeToken(builder_, MUT);
    return true;
  }

  /* ********************************************************** */
  // requires SpecPropertyList? Expr
  public static boolean RequiresSpecExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RequiresSpecExpr")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, REQUIRES_SPEC_EXPR, "<expression>");
    result_ = requires(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, RequiresSpecExpr_1(builder_, level_ + 1));
    result_ = pinned_ && Expr(builder_, level_ + 1, -1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // SpecPropertyList?
  private static boolean RequiresSpecExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RequiresSpecExpr_1")) return false;
    SpecPropertyList(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // pure|(('!')?(acquires|reads|writes)AccessSpecifierList)
  public static boolean ResourceAccessItem(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ResourceAccessItem")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, RESOURCE_ACCESS_ITEM, "<resource access item>");
    result_ = pure(builder_, level_ + 1);
    if (!result_) result_ = ResourceAccessItem_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ('!')?(acquires|reads|writes)AccessSpecifierList
  private static boolean ResourceAccessItem_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ResourceAccessItem_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = ResourceAccessItem_1_0(builder_, level_ + 1);
    result_ = result_ && ResourceAccessItem_1_1(builder_, level_ + 1);
    result_ = result_ && AccessSpecifierList(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ('!')?
  private static boolean ResourceAccessItem_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ResourceAccessItem_1_0")) return false;
    consumeToken(builder_, EXCL);
    return true;
  }

  // acquires|reads|writes
  private static boolean ResourceAccessItem_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ResourceAccessItem_1_1")) return false;
    boolean result_;
    result_ = consumeToken(builder_, ACQUIRES);
    if (!result_) result_ = reads(builder_, level_ + 1);
    if (!result_) result_ = writes(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // <<isResourceAccessEnabled>>ResourceAccessItem*
  public static boolean ResourceAccessItemList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ResourceAccessItemList")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, RESOURCE_ACCESS_ITEM_LIST, "<resource access item list>");
    result_ = isResourceAccessEnabled(builder_, level_ + 1);
    result_ = result_ && ResourceAccessItemList_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ResourceAccessItem*
  private static boolean ResourceAccessItemList_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ResourceAccessItemList_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!ResourceAccessItem(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "ResourceAccessItemList_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ':' ReturnTypeItem_with_recover
  public static boolean ReturnType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ReturnType")) return false;
    if (!nextTokenIs(builder_, COLON)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, RETURN_TYPE, null);
    result_ = consumeToken(builder_, COLON);
    pinned_ = result_; // pin = 1
    result_ = result_ && ReturnTypeItem_with_recover(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // !( '{' | ';' | '!' )
  static boolean ReturnTypeItem_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ReturnTypeItem_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !ReturnTypeItem_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // '{' | ';' | '!'
  private static boolean ReturnTypeItem_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ReturnTypeItem_recover_0")) return false;
    boolean result_;
    result_ = consumeTokenFast(builder_, L_BRACE);
    if (!result_) result_ = consumeTokenFast(builder_, SEMICOLON);
    if (!result_) result_ = consumeTokenFast(builder_, EXCL);
    return result_;
  }

  /* ********************************************************** */
  // Type
  static boolean ReturnTypeItem_with_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ReturnTypeItem_with_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = Type(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, MoveParser::ReturnTypeItem_recover);
    return result_;
  }

  /* ********************************************************** */
  // gtgt
  public static boolean RightShiftBinOp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RightShiftBinOp")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, BINARY_OP, "<operator>");
    result_ = gtgt(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // Attr* (spec schema) IDENTIFIER TypeParameterList? <<msl SpecCodeBlock>>
  public static boolean Schema(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Schema")) return false;
    if (!nextTokenIs(builder_, "<schema>", HASH, SPEC)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, SCHEMA, "<schema>");
    result_ = Schema_0(builder_, level_ + 1);
    result_ = result_ && Schema_1(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, consumeToken(builder_, IDENTIFIER));
    result_ = pinned_ && report_error_(builder_, Schema_3(builder_, level_ + 1)) && result_;
    result_ = pinned_ && msl(builder_, level_ + 1, MoveParser::SpecCodeBlock) && result_;
    register_hook_(builder_, LEFT_BINDER, ADJACENT_LINE_COMMENTS);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // Attr*
  private static boolean Schema_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Schema_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Attr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Schema_0", pos_)) break;
    }
    return true;
  }

  // spec schema
  private static boolean Schema_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Schema_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SPEC);
    result_ = result_ && schema(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // TypeParameterList?
  private static boolean Schema_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Schema_3")) return false;
    TypeParameterList(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // SchemaFieldStmt_local | SchemaFieldStmt_simple
  static boolean SchemaFieldStmtImpl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaFieldStmtImpl")) return false;
    boolean result_;
    result_ = SchemaFieldStmt_local(builder_, level_ + 1);
    if (!result_) result_ = SchemaFieldStmt_simple(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // local PatBinding TypeAnnotation ';'
  public static boolean SchemaFieldStmt_local(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaFieldStmt_local")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, SCHEMA_FIELD_STMT, "<schema field stmt local>");
    result_ = local(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, PatBinding(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, TypeAnnotation(builder_, level_ + 1)) && result_;
    result_ = pinned_ && consumeToken(builder_, SEMICOLON) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // PatBinding TypeAnnotation ';'
  public static boolean SchemaFieldStmt_simple(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaFieldStmt_simple")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, SCHEMA_FIELD_STMT, "<schema field stmt simple>");
    result_ = PatBinding(builder_, level_ + 1);
    result_ = result_ && TypeAnnotation(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && consumeToken(builder_, SEMICOLON);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // !('}' | IDENTIFIER)
  static boolean SchemaField_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaField_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !SchemaField_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // '}' | IDENTIFIER
  private static boolean SchemaField_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaField_recover_0")) return false;
    boolean result_;
    result_ = consumeTokenFast(builder_, R_BRACE);
    if (!result_) result_ = consumeTokenFast(builder_, IDENTIFIER);
    return result_;
  }

  /* ********************************************************** */
  // !'}' SchemaLitField (',' | &'}')
  static boolean SchemaField_with_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaField_with_recover")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = SchemaField_with_recover_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, SchemaLitField(builder_, level_ + 1));
    result_ = pinned_ && SchemaField_with_recover_2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, MoveParser::SchemaField_recover);
    return result_ || pinned_;
  }

  // !'}'
  private static boolean SchemaField_with_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaField_with_recover_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !consumeToken(builder_, R_BRACE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ',' | &'}'
  private static boolean SchemaField_with_recover_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaField_with_recover_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    if (!result_) result_ = SchemaField_with_recover_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // &'}'
  private static boolean SchemaField_with_recover_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaField_with_recover_2_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _AND_);
    result_ = consumeToken(builder_, R_BRACE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // '{' SchemaField_with_recover* '}'
  public static boolean SchemaFieldsBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaFieldsBlock")) return false;
    if (!nextTokenIs(builder_, L_BRACE)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, SCHEMA_FIELDS_BLOCK, null);
    result_ = consumeToken(builder_, L_BRACE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, SchemaFieldsBlock_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_BRACE) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // SchemaField_with_recover*
  private static boolean SchemaFieldsBlock_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaFieldsBlock_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!SchemaField_with_recover(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "SchemaFieldsBlock_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // SchemaLit &(';' | '}' | SpecExprStmt_first)
  public static boolean SchemaIncludeItem(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaIncludeItem")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, SCHEMA_INCLUDE_ITEM, "<schema include item>");
    result_ = SchemaLit(builder_, level_ + 1);
    result_ = result_ && SchemaIncludeItem_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // &(';' | '}' | SpecExprStmt_first)
  private static boolean SchemaIncludeItem_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaIncludeItem_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _AND_);
    result_ = SchemaIncludeItem_1_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ';' | '}' | SpecExprStmt_first
  private static boolean SchemaIncludeItem_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaIncludeItem_1_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, SEMICOLON);
    if (!result_) result_ = consumeToken(builder_, R_BRACE);
    if (!result_) result_ = SpecExprStmt_first(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // PathImpl SchemaFieldsBlock?
  public static boolean SchemaLit(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaLit")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, SCHEMA_LIT, "<schema lit>");
    result_ = PathImpl(builder_, level_ + 1);
    result_ = result_ && SchemaLit_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // SchemaFieldsBlock?
  private static boolean SchemaLit_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaLit_1")) return false;
    SchemaFieldsBlock(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER SchemaLitFieldInit?
  public static boolean SchemaLitField(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaLitField")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER);
    result_ = result_ && SchemaLitField_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, SCHEMA_LIT_FIELD, result_);
    return result_;
  }

  // SchemaLitFieldInit?
  private static boolean SchemaLitField_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaLitField_1")) return false;
    SchemaLitFieldInit(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // ':' Expr
  static boolean SchemaLitFieldInit(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaLitFieldInit")) return false;
    if (!nextTokenIs(builder_, COLON)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, COLON);
    pinned_ = result_; // pin = 1
    result_ = result_ && Expr(builder_, level_ + 1, -1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // PathImpl ('{' <<non_empty_comma_sep_items ApplySchemaNameAttribute>> '}')?
  public static boolean SchemaRef(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaRef")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, SCHEMA_REF, "<schema ref>");
    result_ = PathImpl(builder_, level_ + 1);
    result_ = result_ && SchemaRef_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ('{' <<non_empty_comma_sep_items ApplySchemaNameAttribute>> '}')?
  private static boolean SchemaRef_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaRef_1")) return false;
    SchemaRef_1_0(builder_, level_ + 1);
    return true;
  }

  // '{' <<non_empty_comma_sep_items ApplySchemaNameAttribute>> '}'
  private static boolean SchemaRef_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SchemaRef_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, L_BRACE);
    result_ = result_ && non_empty_comma_sep_items(builder_, level_ + 1, MoveParser::ApplySchemaNameAttribute);
    result_ = result_ && consumeToken(builder_, R_BRACE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // SCRIPT_KW '{' ScriptItem_with_recover* '}'
  public static boolean Script(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Script")) return false;
    if (!nextTokenIs(builder_, SCRIPT_KW)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, SCRIPT, null);
    result_ = consumeTokens(builder_, 1, SCRIPT_KW, L_BRACE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, Script_2(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_BRACE) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // ScriptItem_with_recover*
  private static boolean Script_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Script_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!ScriptItem_with_recover(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Script_2", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // '{' ScriptItem_with_recover* '}' {
  // //    pin = 1
  // //    implements = [
  // //        "org.move.lang.core.psi.ext.MvItemsOwner"
  // //    ]
  // }
  public static boolean ScriptBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ScriptBlock")) return false;
    if (!nextTokenIs(builder_, L_BRACE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, L_BRACE);
    result_ = result_ && ScriptBlock_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_BRACE);
    result_ = result_ && ScriptBlock_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, SCRIPT_BLOCK, result_);
    return result_;
  }

  // ScriptItem_with_recover*
  private static boolean ScriptBlock_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ScriptBlock_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!ScriptItem_with_recover(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "ScriptBlock_1", pos_)) break;
    }
    return true;
  }

  // {
  // //    pin = 1
  // //    implements = [
  // //        "org.move.lang.core.psi.ext.MvItemsOwner"
  // //    ]
  // }
  private static boolean ScriptBlock_3(PsiBuilder builder_, int level_) {
    return true;
  }

  /* ********************************************************** */
  // PublicUseFun | UseStmt | Const | FunctionInner
  static boolean ScriptItem(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ScriptItem")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = PublicUseFun(builder_, level_ + 1);
    if (!result_) result_ = UseStmt(builder_, level_ + 1);
    if (!result_) result_ = Const(builder_, level_ + 1);
    if (!result_) result_ = FunctionInner(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // use | CONST_KW | fun
  static boolean ScriptItem_first(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ScriptItem_first")) return false;
    boolean result_;
    result_ = consumeTokenFast(builder_, USE);
    if (!result_) result_ = consumeTokenFast(builder_, CONST_KW);
    if (!result_) result_ = consumeTokenFast(builder_, FUN);
    return result_;
  }

  /* ********************************************************** */
  // !('}' | ScriptItem_first | TopLevel_first)
  static boolean ScriptItem_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ScriptItem_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !ScriptItem_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // '}' | ScriptItem_first | TopLevel_first
  private static boolean ScriptItem_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ScriptItem_recover_0")) return false;
    boolean result_;
    result_ = consumeTokenFast(builder_, R_BRACE);
    if (!result_) result_ = ScriptItem_first(builder_, level_ + 1);
    if (!result_) result_ = TopLevel_first(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // !('}' | <<eof>>) ScriptItem
  static boolean ScriptItem_with_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ScriptItem_with_recover")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = ScriptItem_with_recover_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && ScriptItem(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, MoveParser::ScriptItem_recover);
    return result_ || pinned_;
  }

  // !('}' | <<eof>>)
  private static boolean ScriptItem_with_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ScriptItem_with_recover_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !ScriptItem_with_recover_0_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // '}' | <<eof>>
  private static boolean ScriptItem_with_recover_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ScriptItem_with_recover_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, R_BRACE);
    if (!result_) result_ = eof(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // '{' ItemSpecBlock_items '}'
  public static boolean SpecCodeBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecCodeBlock")) return false;
    if (!nextTokenIs(builder_, L_BRACE)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, SPEC_CODE_BLOCK, null);
    result_ = consumeToken(builder_, L_BRACE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, ItemSpecBlock_items(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_BRACE) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // SpecExprStmt_items | Expr
  static boolean SpecExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecExpr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null, "<expression>");
    result_ = SpecExprStmt_items(builder_, level_ + 1);
    if (!result_) result_ = Expr(builder_, level_ + 1, -1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // (SpecExpr_item !'}') ';'
  public static boolean SpecExprStmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecExprStmt")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, SPEC_EXPR_STMT, "<spec expr stmt>");
    result_ = SpecExprStmt_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && consumeToken(builder_, SEMICOLON);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // SpecExpr_item !'}'
  private static boolean SpecExprStmt_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecExprStmt_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = SpecExpr_item(builder_, level_ + 1);
    result_ = result_ && SpecExprStmt_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // !'}'
  private static boolean SpecExprStmt_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecExprStmt_0_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !consumeToken(builder_, R_BRACE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // let | if | while
  //                                     | include | apply | pragma | emits | assume | assert | ensures
  //                                     | axiom | modifies | aborts_if | aborts_with | requires | invariant
  static boolean SpecExprStmt_first(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecExprStmt_first")) return false;
    boolean result_;
    result_ = consumeTokenFast(builder_, LET);
    if (!result_) result_ = consumeTokenFast(builder_, IF);
    if (!result_) result_ = consumeTokenFast(builder_, WHILE);
    if (!result_) result_ = include(builder_, level_ + 1);
    if (!result_) result_ = apply(builder_, level_ + 1);
    if (!result_) result_ = pragma(builder_, level_ + 1);
    if (!result_) result_ = emits(builder_, level_ + 1);
    if (!result_) result_ = assume(builder_, level_ + 1);
    if (!result_) result_ = assert_$(builder_, level_ + 1);
    if (!result_) result_ = ensures(builder_, level_ + 1);
    if (!result_) result_ = axiom(builder_, level_ + 1);
    if (!result_) result_ = modifies(builder_, level_ + 1);
    if (!result_) result_ = aborts_if(builder_, level_ + 1);
    if (!result_) result_ = aborts_with(builder_, level_ + 1);
    if (!result_) result_ = requires(builder_, level_ + 1);
    if (!result_) result_ = invariant(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // AssumeSpecExpr | AssertSpecExpr | AbortsIfSpecExpr | AbortsWithSpecExpr
  //                 | RequiresSpecExpr | DecreasesSpecExpr
  //                 | EnsuresSpecExpr | ModifiesSpecExpr | InvariantSpecExpr | AxiomSpecExpr
  //                 | EmitsSpecExpr
  static boolean SpecExprStmt_items(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecExprStmt_items")) return false;
    boolean result_;
    result_ = AssumeSpecExpr(builder_, level_ + 1);
    if (!result_) result_ = AssertSpecExpr(builder_, level_ + 1);
    if (!result_) result_ = AbortsIfSpecExpr(builder_, level_ + 1);
    if (!result_) result_ = AbortsWithSpecExpr(builder_, level_ + 1);
    if (!result_) result_ = RequiresSpecExpr(builder_, level_ + 1);
    if (!result_) result_ = DecreasesSpecExpr(builder_, level_ + 1);
    if (!result_) result_ = EnsuresSpecExpr(builder_, level_ + 1);
    if (!result_) result_ = ModifiesSpecExpr(builder_, level_ + 1);
    if (!result_) result_ = InvariantSpecExpr(builder_, level_ + 1);
    if (!result_) result_ = AxiomSpecExpr(builder_, level_ + 1);
    if (!result_) result_ = EmitsSpecExpr(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // !(';' | '}' | SpecExpr_first | Expr_first)
  static boolean SpecExprStmt_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecExprStmt_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !SpecExprStmt_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ';' | '}' | SpecExpr_first | Expr_first
  private static boolean SpecExprStmt_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecExprStmt_recover_0")) return false;
    boolean result_;
    result_ = consumeTokenFast(builder_, SEMICOLON);
    if (!result_) result_ = consumeTokenFast(builder_, R_BRACE);
    if (!result_) result_ = SpecExpr_first(builder_, level_ + 1);
    if (!result_) result_ = Expr_first(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // include | apply | pragma | emits | assume | assert | ensures
  //                             | axiom | modifies | aborts_if | aborts_with | requires
  //                             | invariant | choose | decreases | update
  static boolean SpecExpr_first(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecExpr_first")) return false;
    boolean result_;
    result_ = include(builder_, level_ + 1);
    if (!result_) result_ = apply(builder_, level_ + 1);
    if (!result_) result_ = pragma(builder_, level_ + 1);
    if (!result_) result_ = emits(builder_, level_ + 1);
    if (!result_) result_ = assume(builder_, level_ + 1);
    if (!result_) result_ = assert_$(builder_, level_ + 1);
    if (!result_) result_ = ensures(builder_, level_ + 1);
    if (!result_) result_ = axiom(builder_, level_ + 1);
    if (!result_) result_ = modifies(builder_, level_ + 1);
    if (!result_) result_ = aborts_if(builder_, level_ + 1);
    if (!result_) result_ = aborts_with(builder_, level_ + 1);
    if (!result_) result_ = requires(builder_, level_ + 1);
    if (!result_) result_ = invariant(builder_, level_ + 1);
    if (!result_) result_ = choose(builder_, level_ + 1);
    if (!result_) result_ = decreases(builder_, level_ + 1);
    if (!result_) result_ = update(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // SpecExprStmt_items | ExprStmt_expr
  static boolean SpecExpr_item(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecExpr_item")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = SpecExprStmt_items(builder_, level_ + 1);
    if (!result_) result_ = ExprStmt_expr(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, MoveParser::SpecExprStmt_recover);
    return result_;
  }

  /* ********************************************************** */
  // Attr* spec fun IDENTIFIER TypeParameterList?
  //                        FunctionParameterList ReturnType? (<<msl SpecCodeBlock>> | ';')
  public static boolean SpecFunctionInner(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecFunctionInner")) return false;
    if (!nextTokenIs(builder_, "<spec function inner>", HASH, SPEC)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, SPEC_FUNCTION, "<spec function inner>");
    result_ = SpecFunctionInner_0(builder_, level_ + 1);
    result_ = result_ && consumeTokens(builder_, 2, SPEC, FUN, IDENTIFIER);
    pinned_ = result_; // pin = 3
    result_ = result_ && report_error_(builder_, SpecFunctionInner_4(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, FunctionParameterList(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, SpecFunctionInner_6(builder_, level_ + 1)) && result_;
    result_ = pinned_ && SpecFunctionInner_7(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // Attr*
  private static boolean SpecFunctionInner_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecFunctionInner_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Attr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "SpecFunctionInner_0", pos_)) break;
    }
    return true;
  }

  // TypeParameterList?
  private static boolean SpecFunctionInner_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecFunctionInner_4")) return false;
    TypeParameterList(builder_, level_ + 1);
    return true;
  }

  // ReturnType?
  private static boolean SpecFunctionInner_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecFunctionInner_6")) return false;
    ReturnType(builder_, level_ + 1);
    return true;
  }

  // <<msl SpecCodeBlock>> | ';'
  private static boolean SpecFunctionInner_7(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecFunctionInner_7")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = msl(builder_, level_ + 1, MoveParser::SpecCodeBlock);
    if (!result_) result_ = consumeToken(builder_, SEMICOLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // SpecFunctionInner
  //                             | NativeSpecFunctionInner
  static boolean SpecFunctionItem(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecFunctionItem")) return false;
    if (!nextTokenIs(builder_, "", HASH, SPEC)) return false;
    boolean result_;
    result_ = SpecFunctionInner(builder_, level_ + 1);
    if (!result_) result_ = NativeSpecFunctionInner(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // fun IDENTIFIER TypeParameterList?
  //                              FunctionParameterList ReturnType? (<<msl SpecCodeBlock>> | ';')
  public static boolean SpecInlineFunctionInner(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecInlineFunctionInner")) return false;
    if (!nextTokenIs(builder_, FUN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, SPEC_INLINE_FUNCTION, null);
    result_ = consumeTokens(builder_, 1, FUN, IDENTIFIER);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, SpecInlineFunctionInner_2(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, FunctionParameterList(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, SpecInlineFunctionInner_4(builder_, level_ + 1)) && result_;
    result_ = pinned_ && SpecInlineFunctionInner_5(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // TypeParameterList?
  private static boolean SpecInlineFunctionInner_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecInlineFunctionInner_2")) return false;
    TypeParameterList(builder_, level_ + 1);
    return true;
  }

  // ReturnType?
  private static boolean SpecInlineFunctionInner_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecInlineFunctionInner_4")) return false;
    ReturnType(builder_, level_ + 1);
    return true;
  }

  // <<msl SpecCodeBlock>> | ';'
  private static boolean SpecInlineFunctionInner_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecInlineFunctionInner_5")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = msl(builder_, level_ + 1, MoveParser::SpecCodeBlock);
    if (!result_) result_ = consumeToken(builder_, SEMICOLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // SpecInlineFunctionInner
  //                             | NativeSpecInlineFunctionInner
  public static boolean SpecInlineFunctionStmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecInlineFunctionStmt")) return false;
    if (!nextTokenIs(builder_, "<spec inline function stmt>", FUN, NATIVE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, SPEC_INLINE_FUNCTION_STMT, "<spec inline function stmt>");
    result_ = SpecInlineFunctionInner(builder_, level_ + 1);
    if (!result_) result_ = NativeSpecInlineFunctionInner(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // !']' IDENTIFIER ('=' LitExpr)? &(',' | ']')
  public static boolean SpecProperty(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecProperty")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, SPEC_PROPERTY, "<spec property>");
    result_ = SpecProperty_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, consumeToken(builder_, IDENTIFIER));
    result_ = pinned_ && report_error_(builder_, SpecProperty_2(builder_, level_ + 1)) && result_;
    result_ = pinned_ && SpecProperty_3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // !']'
  private static boolean SpecProperty_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecProperty_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !consumeToken(builder_, R_BRACK);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ('=' LitExpr)?
  private static boolean SpecProperty_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecProperty_2")) return false;
    SpecProperty_2_0(builder_, level_ + 1);
    return true;
  }

  // '=' LitExpr
  private static boolean SpecProperty_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecProperty_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, EQ);
    result_ = result_ && LitExpr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // &(',' | ']')
  private static boolean SpecProperty_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecProperty_3")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _AND_);
    result_ = SpecProperty_3_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ',' | ']'
  private static boolean SpecProperty_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecProperty_3_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, COMMA);
    if (!result_) result_ = consumeToken(builder_, R_BRACK);
    return result_;
  }

  /* ********************************************************** */
  // '[' SpecPropertyList_items? ']'
  public static boolean SpecPropertyList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecPropertyList")) return false;
    if (!nextTokenIs(builder_, L_BRACK)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, L_BRACK);
    result_ = result_ && SpecPropertyList_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_BRACK);
    exit_section_(builder_, marker_, SPEC_PROPERTY_LIST, result_);
    return result_;
  }

  // SpecPropertyList_items?
  private static boolean SpecPropertyList_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecPropertyList_1")) return false;
    SpecPropertyList_items(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // SpecProperty (',' SpecProperty)* ','?
  static boolean SpecPropertyList_items(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecPropertyList_items")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = SpecProperty(builder_, level_ + 1);
    result_ = result_ && SpecPropertyList_items_1(builder_, level_ + 1);
    result_ = result_ && SpecPropertyList_items_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (',' SpecProperty)*
  private static boolean SpecPropertyList_items_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecPropertyList_items_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!SpecPropertyList_items_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "SpecPropertyList_items_1", pos_)) break;
    }
    return true;
  }

  // ',' SpecProperty
  private static boolean SpecPropertyList_items_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecPropertyList_items_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && SpecProperty(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ','?
  private static boolean SpecPropertyList_items_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecPropertyList_items_2")) return false;
    consumeToken(builder_, COMMA);
    return true;
  }

  /* ********************************************************** */
  // UseStmt
  //                        | SchemaFieldStmtImpl
  //                        | GlobalVariableStmt
  //                        | SpecInlineFunctionStmt
  //                        | PragmaSpecStmt
  //                        | IncludeStmt
  //                        | UpdateSpecStmt
  //                        | ApplySchemaStmt
  //                        | LetMslStmt
  //                        | SpecExprStmt
  static boolean SpecStmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecStmt")) return false;
    boolean result_;
    result_ = UseStmt(builder_, level_ + 1);
    if (!result_) result_ = SchemaFieldStmtImpl(builder_, level_ + 1);
    if (!result_) result_ = GlobalVariableStmt(builder_, level_ + 1);
    if (!result_) result_ = SpecInlineFunctionStmt(builder_, level_ + 1);
    if (!result_) result_ = PragmaSpecStmt(builder_, level_ + 1);
    if (!result_) result_ = IncludeStmt(builder_, level_ + 1);
    if (!result_) result_ = UpdateSpecStmt(builder_, level_ + 1);
    if (!result_) result_ = ApplySchemaStmt(builder_, level_ + 1);
    if (!result_) result_ = LetMslStmt(builder_, level_ + 1);
    if (!result_) result_ = SpecExprStmt(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // LetMoveStmt | ExprStmt
  public static boolean Stmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Stmt")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, STMT, "<stmt>");
    result_ = LetMoveStmt(builder_, level_ + 1);
    if (!result_) result_ = ExprStmt(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // AbilitiesList  (';' | BlockFields | TupleFields)?
  static boolean StructBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructBlock")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = AbilitiesList(builder_, level_ + 1);
    result_ = result_ && StructBlock_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (';' | BlockFields | TupleFields)?
  private static boolean StructBlock_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructBlock_1")) return false;
    StructBlock_1_0(builder_, level_ + 1);
    return true;
  }

  // ';' | BlockFields | TupleFields
  private static boolean StructBlock_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructBlock_1_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, SEMICOLON);
    if (!result_) result_ = BlockFields(builder_, level_ + 1);
    if (!result_) result_ = TupleFields(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // BlockFields (AbilitiesList';')?
  static boolean StructBlockFields(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructBlockFields")) return false;
    if (!nextTokenIs(builder_, L_BRACE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = BlockFields(builder_, level_ + 1);
    result_ = result_ && StructBlockFields_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (AbilitiesList';')?
  private static boolean StructBlockFields_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructBlockFields_1")) return false;
    StructBlockFields_1_0(builder_, level_ + 1);
    return true;
  }

  // AbilitiesList';'
  private static boolean StructBlockFields_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructBlockFields_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = AbilitiesList(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEMICOLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // (IDENTIFIER | INTEGER_LITERAL) !('(' | '::' | '!' | '{')
  public static boolean StructDotField(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructDotField")) return false;
    if (!nextTokenIs(builder_, "<struct dot field>", IDENTIFIER, INTEGER_LITERAL)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, STRUCT_DOT_FIELD, "<struct dot field>");
    result_ = StructDotField_0(builder_, level_ + 1);
    result_ = result_ && StructDotField_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // IDENTIFIER | INTEGER_LITERAL
  private static boolean StructDotField_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructDotField_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, IDENTIFIER);
    if (!result_) result_ = consumeToken(builder_, INTEGER_LITERAL);
    return result_;
  }

  // !('(' | '::' | '!' | '{')
  private static boolean StructDotField_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructDotField_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !StructDotField_1_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // '(' | '::' | '!' | '{'
  private static boolean StructDotField_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructDotField_1_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, L_PAREN);
    if (!result_) result_ = consumeToken(builder_, COLON_COLON);
    if (!result_) result_ = consumeToken(builder_, EXCL);
    if (!result_) result_ = consumeToken(builder_, L_BRACE);
    return result_;
  }

  /* ********************************************************** */
  // Attr* VisibilityModifier? StructInnerFirst IDENTIFIER TypeParameterList?  (StructBlock | StructTupleBlock | StructBlockFields)?
  public static boolean StructInner(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructInner")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, STRUCT, "<struct inner>");
    result_ = StructInner_0(builder_, level_ + 1);
    result_ = result_ && StructInner_1(builder_, level_ + 1);
    result_ = result_ && StructInnerFirst(builder_, level_ + 1);
    pinned_ = result_; // pin = StructInnerFirst
    result_ = result_ && report_error_(builder_, consumeToken(builder_, IDENTIFIER));
    result_ = pinned_ && report_error_(builder_, StructInner_4(builder_, level_ + 1)) && result_;
    result_ = pinned_ && StructInner_5(builder_, level_ + 1) && result_;
    register_hook_(builder_, LEFT_BINDER, ADJACENT_LINE_COMMENTS);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // Attr*
  private static boolean StructInner_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructInner_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Attr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "StructInner_0", pos_)) break;
    }
    return true;
  }

  // VisibilityModifier?
  private static boolean StructInner_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructInner_1")) return false;
    VisibilityModifier(builder_, level_ + 1);
    return true;
  }

  // TypeParameterList?
  private static boolean StructInner_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructInner_4")) return false;
    TypeParameterList(builder_, level_ + 1);
    return true;
  }

  // (StructBlock | StructTupleBlock | StructBlockFields)?
  private static boolean StructInner_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructInner_5")) return false;
    StructInner_5_0(builder_, level_ + 1);
    return true;
  }

  // StructBlock | StructTupleBlock | StructBlockFields
  private static boolean StructInner_5_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructInner_5_0")) return false;
    boolean result_;
    result_ = StructBlock(builder_, level_ + 1);
    if (!result_) result_ = StructTupleBlock(builder_, level_ + 1);
    if (!result_) result_ = StructBlockFields(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // STRUCT_KW
  static boolean StructInnerFirst(PsiBuilder builder_, int level_) {
    return consumeToken(builder_, STRUCT_KW);
  }

  /* ********************************************************** */
  // StructInner | NativeStructInner
  static boolean StructItem(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructItem")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = StructInner(builder_, level_ + 1);
    if (!result_) result_ = NativeStructInner(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // IDENTIFIER StructLitFieldInit?
  public static boolean StructLitField(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructLitField")) return false;
    if (!nextTokenIs(builder_, IDENTIFIER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, IDENTIFIER);
    result_ = result_ && StructLitField_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, STRUCT_LIT_FIELD, result_);
    return result_;
  }

  // StructLitFieldInit?
  private static boolean StructLitField_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructLitField_1")) return false;
    StructLitFieldInit(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // ':' Expr
  static boolean StructLitFieldInit(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructLitFieldInit")) return false;
    if (!nextTokenIs(builder_, COLON)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = consumeToken(builder_, COLON);
    pinned_ = result_; // pin = 1
    result_ = result_ && Expr(builder_, level_ + 1, -1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // !('}' | IDENTIFIER)
  static boolean StructLitField_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructLitField_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !StructLitField_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // '}' | IDENTIFIER
  private static boolean StructLitField_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructLitField_recover_0")) return false;
    boolean result_;
    result_ = consumeTokenFast(builder_, R_BRACE);
    if (!result_) result_ = consumeTokenFast(builder_, IDENTIFIER);
    return result_;
  }

  /* ********************************************************** */
  // !'}' StructLitField (',' | &'}')
  static boolean StructLitField_with_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructLitField_with_recover")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = StructLitField_with_recover_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, StructLitField(builder_, level_ + 1));
    result_ = pinned_ && StructLitField_with_recover_2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, MoveParser::StructLitField_recover);
    return result_ || pinned_;
  }

  // !'}'
  private static boolean StructLitField_with_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructLitField_with_recover_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !consumeToken(builder_, R_BRACE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ',' | &'}'
  private static boolean StructLitField_with_recover_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructLitField_with_recover_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    if (!result_) result_ = StructLitField_with_recover_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // &'}'
  private static boolean StructLitField_with_recover_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructLitField_with_recover_2_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _AND_);
    result_ = consumeToken(builder_, R_BRACE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // '{' StructLitField_with_recover* '}'
  public static boolean StructLitFieldsBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructLitFieldsBlock")) return false;
    if (!nextTokenIs(builder_, L_BRACE)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, STRUCT_LIT_FIELDS_BLOCK, null);
    result_ = consumeToken(builder_, L_BRACE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, StructLitFieldsBlock_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_BRACE) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // StructLitField_with_recover*
  private static boolean StructLitFieldsBlock_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructLitFieldsBlock_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!StructLitField_with_recover(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "StructLitFieldsBlock_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // PathImpl '{' PatField_with_recover* '}'
  public static boolean StructPat(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructPat")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, STRUCT_PAT, "<struct pat>");
    result_ = PathImpl(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, L_BRACE);
    result_ = result_ && StructPat_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_BRACE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // PatField_with_recover*
  private static boolean StructPat_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructPat_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!PatField_with_recover(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "StructPat_2", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // TupleFields (AbilitiesList';')?
  static boolean StructTupleBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructTupleBlock")) return false;
    if (!nextTokenIs(builder_, L_PAREN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = TupleFields(builder_, level_ + 1);
    result_ = result_ && StructTupleBlock_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (AbilitiesList';')?
  private static boolean StructTupleBlock_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructTupleBlock_1")) return false;
    StructTupleBlock_1_0(builder_, level_ + 1);
    return true;
  }

  // AbilitiesList';'
  private static boolean StructTupleBlock_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructTupleBlock_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = AbilitiesList(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEMICOLON);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // MODULE_KW
  static boolean TopLevel_first(PsiBuilder builder_, int level_) {
    return consumeTokenFast(builder_, MODULE_KW);
  }

  /* ********************************************************** */
  // Type
  public static boolean TupleFieldDecl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TupleFieldDecl")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, TUPLE_FIELD_DECL, "<tuple field decl>");
    result_ = Type(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // !(')' | Type )
  static boolean TupleFieldDecl_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TupleFieldDecl_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !TupleFieldDecl_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ')' | Type
  private static boolean TupleFieldDecl_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TupleFieldDecl_recover_0")) return false;
    boolean result_;
    result_ = consumeTokenFast(builder_, R_PAREN);
    if (!result_) result_ = Type(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // !')' TupleFieldDecl (',' | &')')
  static boolean TupleFieldDecl_with_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TupleFieldDecl_with_recover")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = TupleFieldDecl_with_recover_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, TupleFieldDecl(builder_, level_ + 1));
    result_ = pinned_ && TupleFieldDecl_with_recover_2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, MoveParser::TupleFieldDecl_recover);
    return result_ || pinned_;
  }

  // !')'
  private static boolean TupleFieldDecl_with_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TupleFieldDecl_with_recover_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !consumeToken(builder_, R_PAREN);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ',' | &')'
  private static boolean TupleFieldDecl_with_recover_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TupleFieldDecl_with_recover_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    if (!result_) result_ = TupleFieldDecl_with_recover_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // &')'
  private static boolean TupleFieldDecl_with_recover_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TupleFieldDecl_with_recover_2_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _AND_);
    result_ = consumeToken(builder_, R_PAREN);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // '(' TupleFieldDecl_with_recover* ')'
  public static boolean TupleFields(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TupleFields")) return false;
    if (!nextTokenIs(builder_, L_PAREN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, L_PAREN);
    result_ = result_ && TupleFields_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_PAREN);
    exit_section_(builder_, marker_, TUPLE_FIELDS, result_);
    return result_;
  }

  // TupleFieldDecl_with_recover*
  private static boolean TupleFields_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TupleFields_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!TupleFieldDecl_with_recover(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "TupleFields_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ',' [ Expr (',' Expr)* ','? ] ')'
  public static boolean TupleLitExprUpper(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TupleLitExprUpper")) return false;
    if (!nextTokenIs(builder_, COMMA)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _UPPER_, TUPLE_LIT_EXPR, null);
    result_ = consumeToken(builder_, COMMA);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, TupleLitExprUpper_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_PAREN) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // [ Expr (',' Expr)* ','? ]
  private static boolean TupleLitExprUpper_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TupleLitExprUpper_1")) return false;
    TupleLitExprUpper_1_0(builder_, level_ + 1);
    return true;
  }

  // Expr (',' Expr)* ','?
  private static boolean TupleLitExprUpper_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TupleLitExprUpper_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Expr(builder_, level_ + 1, -1);
    result_ = result_ && TupleLitExprUpper_1_0_1(builder_, level_ + 1);
    result_ = result_ && TupleLitExprUpper_1_0_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (',' Expr)*
  private static boolean TupleLitExprUpper_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TupleLitExprUpper_1_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!TupleLitExprUpper_1_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "TupleLitExprUpper_1_0_1", pos_)) break;
    }
    return true;
  }

  // ',' Expr
  private static boolean TupleLitExprUpper_1_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TupleLitExprUpper_1_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && Expr(builder_, level_ + 1, -1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ','?
  private static boolean TupleLitExprUpper_1_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TupleLitExprUpper_1_0_2")) return false;
    consumeToken(builder_, COMMA);
    return true;
  }

  /* ********************************************************** */
  // '(' ParenListElemPat_with_recover* ')'
  public static boolean TuplePat(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TuplePat")) return false;
    if (!nextTokenIs(builder_, L_PAREN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, L_PAREN);
    result_ = result_ && TuplePat_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_PAREN);
    exit_section_(builder_, marker_, TUPLE_PAT, result_);
    return result_;
  }

  // ParenListElemPat_with_recover*
  private static boolean TuplePat_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TuplePat_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!ParenListElemPat_with_recover(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "TuplePat_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // '(' Type ',' (Type ','?)* ')'
  public static boolean TupleType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TupleType")) return false;
    if (!nextTokenIs(builder_, L_PAREN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, TUPLE_TYPE, null);
    result_ = consumeToken(builder_, L_PAREN);
    result_ = result_ && Type(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COMMA);
    pinned_ = result_; // pin = 3
    result_ = result_ && report_error_(builder_, TupleType_3(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_PAREN) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (Type ','?)*
  private static boolean TupleType_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TupleType_3")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!TupleType_3_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "TupleType_3", pos_)) break;
    }
    return true;
  }

  // Type ','?
  private static boolean TupleType_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TupleType_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Type(builder_, level_ + 1);
    result_ = result_ && TupleType_3_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ','?
  private static boolean TupleType_3_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TupleType_3_0_1")) return false;
    consumeToken(builder_, COMMA);
    return true;
  }

  /* ********************************************************** */
  // RefType | PathType | TupleType | LambdaType | UnitType | ParensType | WildType
  public static boolean Type(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Type")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, TYPE, "<type>");
    result_ = RefType(builder_, level_ + 1);
    if (!result_) result_ = PathType(builder_, level_ + 1);
    if (!result_) result_ = TupleType(builder_, level_ + 1);
    if (!result_) result_ = LambdaType(builder_, level_ + 1);
    if (!result_) result_ = UnitType(builder_, level_ + 1);
    if (!result_) result_ = ParensType(builder_, level_ + 1);
    if (!result_) result_ = WildType(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // ':' Type
  public static boolean TypeAnnotation(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeAnnotation")) return false;
    if (!nextTokenIs(builder_, "<type annotation>", COLON)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, TYPE_ANNOTATION, "<type annotation>");
    result_ = consumeToken(builder_, COLON);
    pinned_ = result_; // pin = 1
    result_ = result_ && Type(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // Type
  public static boolean TypeArgument(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeArgument")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, TYPE_ARGUMENT, "<type argument>");
    result_ = Type(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // TypeArgumentListImpl
  public static boolean TypeArgumentList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeArgumentList")) return false;
    if (!nextTokenIs(builder_, "<type arguments>", COLON_COLON, LT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, TYPE_ARGUMENT_LIST, "<type arguments>");
    result_ = TypeArgumentListImpl(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // '::'? '<' !'=' <<non_empty_comma_sep_items ( TypeArgument &(',' | '>'))>>? '>'
  static boolean TypeArgumentListImpl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeArgumentListImpl")) return false;
    if (!nextTokenIs(builder_, "", COLON_COLON, LT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = TypeArgumentListImpl_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, LT);
    result_ = result_ && TypeArgumentListImpl_2(builder_, level_ + 1);
    result_ = result_ && TypeArgumentListImpl_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, GT);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '::'?
  private static boolean TypeArgumentListImpl_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeArgumentListImpl_0")) return false;
    consumeToken(builder_, COLON_COLON);
    return true;
  }

  // !'='
  private static boolean TypeArgumentListImpl_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeArgumentListImpl_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !consumeToken(builder_, EQ);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // <<non_empty_comma_sep_items ( TypeArgument &(',' | '>'))>>?
  private static boolean TypeArgumentListImpl_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeArgumentListImpl_3")) return false;
    non_empty_comma_sep_items(builder_, level_ + 1, MoveParser::TypeArgumentListImpl_3_0_0);
    return true;
  }

  // TypeArgument &(',' | '>')
  private static boolean TypeArgumentListImpl_3_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeArgumentListImpl_3_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = TypeArgument(builder_, level_ + 1);
    result_ = result_ && TypeArgumentListImpl_3_0_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // &(',' | '>')
  private static boolean TypeArgumentListImpl_3_0_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeArgumentListImpl_3_0_0_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _AND_);
    result_ = TypeArgumentListImpl_3_0_0_1_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ',' | '>'
  private static boolean TypeArgumentListImpl_3_0_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeArgumentListImpl_3_0_0_1_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, COMMA);
    if (!result_) result_ = consumeToken(builder_, GT);
    return result_;
  }

  /* ********************************************************** */
  // ':' TypeParamBound_items
  public static boolean TypeParamBound(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeParamBound")) return false;
    if (!nextTokenIs(builder_, COLON)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, TYPE_PARAM_BOUND, null);
    result_ = consumeToken(builder_, COLON);
    pinned_ = result_; // pin = 1
    result_ = result_ && TypeParamBound_items(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // Ability ( '+' Ability )*
  static boolean TypeParamBound_items(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeParamBound_items")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = Ability(builder_, level_ + 1);
    result_ = result_ && TypeParamBound_items_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, MoveParser::TypeParamBound_items_recover);
    return result_;
  }

  // ( '+' Ability )*
  private static boolean TypeParamBound_items_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeParamBound_items_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!TypeParamBound_items_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "TypeParamBound_items_1", pos_)) break;
    }
    return true;
  }

  // '+' Ability
  private static boolean TypeParamBound_items_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeParamBound_items_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, PLUS);
    result_ = result_ && Ability(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // !('>' | ',')
  static boolean TypeParamBound_items_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeParamBound_items_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !TypeParamBound_items_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // '>' | ','
  private static boolean TypeParamBound_items_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeParamBound_items_recover_0")) return false;
    boolean result_;
    result_ = consumeTokenFast(builder_, GT);
    if (!result_) result_ = consumeTokenFast(builder_, COMMA);
    return result_;
  }

  /* ********************************************************** */
  // PlainTypeParameter | PhantomTypeParameter
  static boolean TypeParameterImpl(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeParameterImpl")) return false;
    if (!nextTokenIs(builder_, "", IDENTIFIER, PHANTOM)) return false;
    boolean result_;
    result_ = PlainTypeParameter(builder_, level_ + 1);
    if (!result_) result_ = PhantomTypeParameter(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // '<' TypeParameter_with_recover* '>'
  public static boolean TypeParameterList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeParameterList")) return false;
    if (!nextTokenIs(builder_, "<type parameters>", LT)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, TYPE_PARAMETER_LIST, "<type parameters>");
    result_ = consumeToken(builder_, LT);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, TypeParameterList_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, GT) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // TypeParameter_with_recover*
  private static boolean TypeParameterList_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeParameterList_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!TypeParameter_with_recover(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "TypeParameterList_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // !('>' | '(' | '{' | phantom | IDENTIFIER )
  static boolean TypeParameter_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeParameter_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !TypeParameter_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // '>' | '(' | '{' | phantom | IDENTIFIER
  private static boolean TypeParameter_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeParameter_recover_0")) return false;
    boolean result_;
    result_ = consumeTokenFast(builder_, GT);
    if (!result_) result_ = consumeTokenFast(builder_, L_PAREN);
    if (!result_) result_ = consumeTokenFast(builder_, L_BRACE);
    if (!result_) result_ = consumeTokenFast(builder_, PHANTOM);
    if (!result_) result_ = consumeTokenFast(builder_, IDENTIFIER);
    return result_;
  }

  /* ********************************************************** */
  // !'>' TypeParameterImpl (',' | &'>')
  static boolean TypeParameter_with_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeParameter_with_recover")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = TypeParameter_with_recover_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, TypeParameterImpl(builder_, level_ + 1));
    result_ = pinned_ && TypeParameter_with_recover_2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, MoveParser::TypeParameter_recover);
    return result_ || pinned_;
  }

  // !'>'
  private static boolean TypeParameter_with_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeParameter_with_recover_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !consumeToken(builder_, GT);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ',' | &'>'
  private static boolean TypeParameter_with_recover_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeParameter_with_recover_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    if (!result_) result_ = TypeParameter_with_recover_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // &'>'
  private static boolean TypeParameter_with_recover_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeParameter_with_recover_2_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _AND_);
    result_ = consumeToken(builder_, GT);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // PatBinding ':' Type
  public static boolean TypeQuantBinding(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TypeQuantBinding")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, TYPE_QUANT_BINDING, "<type quant binding>");
    result_ = PatBinding(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON);
    pinned_ = result_; // pin = 2
    result_ = result_ && Type(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // '(' ')'
  public static boolean UnitType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UnitType")) return false;
    if (!nextTokenIs(builder_, L_PAREN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, L_PAREN, R_PAREN);
    exit_section_(builder_, marker_, UNIT_TYPE, result_);
    return result_;
  }

  /* ********************************************************** */
  // UnaryExpr | BorrowExpr | AtomExpr
  static boolean UpdateExpr_items(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UpdateExpr_items")) return false;
    boolean result_;
    result_ = Expr(builder_, level_ + 1, 17);
    if (!result_) result_ = BorrowExpr(builder_, level_ + 1);
    if (!result_) result_ = Expr(builder_, level_ + 1, 19);
    return result_;
  }

  /* ********************************************************** */
  // update UpdateExpr_items '=' Expr ';'
  public static boolean UpdateSpecStmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UpdateSpecStmt")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, UPDATE_SPEC_STMT, "<update spec stmt>");
    result_ = update(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, UpdateExpr_items(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, EQ)) && result_;
    result_ = pinned_ && report_error_(builder_, Expr(builder_, level_ + 1, -1)) && result_;
    result_ = pinned_ && consumeToken(builder_, SEMICOLON) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // as IDENTIFIER
  public static boolean UseAlias(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UseAlias")) return false;
    if (!nextTokenIs(builder_, AS)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, USE_ALIAS, null);
    result_ = consumeTokens(builder_, 1, AS, IDENTIFIER);
    pinned_ = result_; // pin = 1
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // PathImpl '.' IDENTIFIER
  public static boolean UseFunMethodAlias(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UseFunMethodAlias")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, USE_FUN_METHOD_ALIAS, "<use fun method alias>");
    result_ = PathImpl(builder_, level_ + 1);
    result_ = result_ && consumeTokens(builder_, 1, DOT, IDENTIFIER);
    pinned_ = result_; // pin = 2
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // '{' UseSpeck_with_recover* '}'
  public static boolean UseGroup(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UseGroup")) return false;
    if (!nextTokenIs(builder_, L_BRACE)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, USE_GROUP, null);
    result_ = consumeToken(builder_, L_BRACE);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, UseGroup_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_BRACE) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // UseSpeck_with_recover*
  private static boolean UseGroup_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UseGroup_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!UseSpeck_with_recover(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "UseGroup_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // PathImpl ( UseAlias | ('::' UseGroup) )?
  public static boolean UseSpeck(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UseSpeck")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, USE_SPECK, "<use speck>");
    result_ = PathImpl(builder_, level_ + 1);
    result_ = result_ && UseSpeck_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ( UseAlias | ('::' UseGroup) )?
  private static boolean UseSpeck_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UseSpeck_1")) return false;
    UseSpeck_1_0(builder_, level_ + 1);
    return true;
  }

  // UseAlias | ('::' UseGroup)
  private static boolean UseSpeck_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UseSpeck_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = UseAlias(builder_, level_ + 1);
    if (!result_) result_ = UseSpeck_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '::' UseGroup
  private static boolean UseSpeck_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UseSpeck_1_0_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON_COLON);
    result_ = result_ && UseGroup(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // !('}' | '{' | IDENTIFIER | '::' )
  static boolean UseSpeck_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UseSpeck_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !UseSpeck_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // '}' | '{' | IDENTIFIER | '::'
  private static boolean UseSpeck_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UseSpeck_recover_0")) return false;
    boolean result_;
    result_ = consumeTokenFast(builder_, R_BRACE);
    if (!result_) result_ = consumeTokenFast(builder_, L_BRACE);
    if (!result_) result_ = consumeTokenFast(builder_, IDENTIFIER);
    if (!result_) result_ = consumeTokenFast(builder_, COLON_COLON);
    return result_;
  }

  /* ********************************************************** */
  // !'}' UseSpeck (','|&'}')
  static boolean UseSpeck_with_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UseSpeck_with_recover")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = UseSpeck_with_recover_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, UseSpeck(builder_, level_ + 1));
    result_ = pinned_ && UseSpeck_with_recover_2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, MoveParser::UseSpeck_recover);
    return result_ || pinned_;
  }

  // !'}'
  private static boolean UseSpeck_with_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UseSpeck_with_recover_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !consumeToken(builder_, R_BRACE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ','|&'}'
  private static boolean UseSpeck_with_recover_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UseSpeck_with_recover_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    if (!result_) result_ = UseSpeck_with_recover_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // &'}'
  private static boolean UseSpeck_with_recover_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UseSpeck_with_recover_2_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _AND_);
    result_ = consumeToken(builder_, R_BRACE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // Attr* use UseSpeck ';'
  public static boolean UseStmt(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UseStmt")) return false;
    if (!nextTokenIs(builder_, "<use stmt>", HASH, USE)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, USE_STMT, "<use stmt>");
    result_ = UseStmt_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, USE);
    pinned_ = result_; // pin = use
    result_ = result_ && report_error_(builder_, UseSpeck(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, SEMICOLON) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // Attr*
  private static boolean UseStmt_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UseStmt_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Attr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "UseStmt_0", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // !')' Expr &(',' | ')')
  public static boolean ValueArgument(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ValueArgument")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, VALUE_ARGUMENT, "<value argument>");
    result_ = ValueArgument_0(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, Expr(builder_, level_ + 1, -1));
    result_ = pinned_ && ValueArgument_2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, MoveParser::ValueArgument_recover);
    return result_ || pinned_;
  }

  // !')'
  private static boolean ValueArgument_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ValueArgument_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !consumeToken(builder_, R_PAREN);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // &(',' | ')')
  private static boolean ValueArgument_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ValueArgument_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _AND_);
    result_ = ValueArgument_2_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ',' | ')'
  private static boolean ValueArgument_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ValueArgument_2_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, COMMA);
    if (!result_) result_ = consumeToken(builder_, R_PAREN);
    return result_;
  }

  /* ********************************************************** */
  // '(' ValueArgumentList_items? ')'
  public static boolean ValueArgumentList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ValueArgumentList")) return false;
    if (!nextTokenIs(builder_, L_PAREN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, VALUE_ARGUMENT_LIST, null);
    result_ = consumeToken(builder_, L_PAREN);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, ValueArgumentList_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_PAREN) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // ValueArgumentList_items?
  private static boolean ValueArgumentList_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ValueArgumentList_1")) return false;
    ValueArgumentList_items(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // ValueArgument (',' ValueArgument)* ','?
  static boolean ValueArgumentList_items(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ValueArgumentList_items")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = ValueArgument(builder_, level_ + 1);
    result_ = result_ && ValueArgumentList_items_1(builder_, level_ + 1);
    result_ = result_ && ValueArgumentList_items_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (',' ValueArgument)*
  private static boolean ValueArgumentList_items_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ValueArgumentList_items_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!ValueArgumentList_items_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "ValueArgumentList_items_1", pos_)) break;
    }
    return true;
  }

  // ',' ValueArgument
  private static boolean ValueArgumentList_items_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ValueArgumentList_items_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && ValueArgument(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ','?
  private static boolean ValueArgumentList_items_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ValueArgumentList_items_2")) return false;
    consumeToken(builder_, COMMA);
    return true;
  }

  /* ********************************************************** */
  // !(<<mslOnly SpecExpr_first>> | Expr_first | ')' | '}' | ';' | ',')
  static boolean ValueArgument_recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ValueArgument_recover")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !ValueArgument_recover_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // <<mslOnly SpecExpr_first>> | Expr_first | ')' | '}' | ';' | ','
  private static boolean ValueArgument_recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ValueArgument_recover_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = mslOnly(builder_, level_ + 1, MoveParser::SpecExpr_first);
    if (!result_) result_ = Expr_first(builder_, level_ + 1);
    if (!result_) result_ = consumeTokenFast(builder_, R_PAREN);
    if (!result_) result_ = consumeTokenFast(builder_, R_BRACE);
    if (!result_) result_ = consumeTokenFast(builder_, SEMICOLON);
    if (!result_) result_ = consumeTokenFast(builder_, COMMA);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // [BlockFields | TupleFields]
  static boolean VariantArgs(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VariantArgs")) return false;
    VariantArgs_0(builder_, level_ + 1);
    return true;
  }

  // BlockFields | TupleFields
  private static boolean VariantArgs_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VariantArgs_0")) return false;
    boolean result_;
    result_ = BlockFields(builder_, level_ + 1);
    if (!result_) result_ = TupleFields(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // '[' <<non_empty_comma_sep_items Expr>>? ']'
  public static boolean VectorLitItems(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VectorLitItems")) return false;
    if (!nextTokenIs(builder_, L_BRACK)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, VECTOR_LIT_ITEMS, null);
    result_ = consumeToken(builder_, L_BRACK);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, VectorLitItems_1(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_BRACK) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // <<non_empty_comma_sep_items Expr>>?
  private static boolean VectorLitItems_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VectorLitItems_1")) return false;
    non_empty_comma_sep_items(builder_, level_ + 1, Expr_parser_);
    return true;
  }

  /* ********************************************************** */
  // <<vectorIdent>> ('[' | '<')
  static boolean VectorStart(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VectorStart")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = vectorIdent(builder_, level_ + 1);
    result_ = result_ && VectorStart_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // '[' | '<'
  private static boolean VectorStart_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VectorStart_1")) return false;
    boolean result_;
    result_ = consumeToken(builder_, L_BRACK);
    if (!result_) result_ = consumeToken(builder_, LT);
    return result_;
  }

  /* ********************************************************** */
  // public ('(' ( package | friend) ')')?
  public static boolean VisibilityModifier(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VisibilityModifier")) return false;
    if (!nextTokenIs(builder_, PUBLIC)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, PUBLIC);
    result_ = result_ && VisibilityModifier_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, VISIBILITY_MODIFIER, result_);
    return result_;
  }

  // ('(' ( package | friend) ')')?
  private static boolean VisibilityModifier_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VisibilityModifier_1")) return false;
    VisibilityModifier_1_0(builder_, level_ + 1);
    return true;
  }

  // '(' ( package | friend) ')'
  private static boolean VisibilityModifier_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VisibilityModifier_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, L_PAREN);
    result_ = result_ && VisibilityModifier_1_0_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, R_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // package | friend
  private static boolean VisibilityModifier_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VisibilityModifier_1_0_1")) return false;
    boolean result_;
    result_ = package_$(builder_, level_ + 1);
    if (!result_) result_ = friend(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // '_'
  public static boolean WildPat(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WildPat")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, WILD_PAT, "<wild pat>");
    result_ = consumeToken(builder_, "_");
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // '_'
  public static boolean WildType(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WildType")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, WILD_TYPE, "<wild type>");
    result_ = consumeToken(builder_, "_");
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // <<abortsIfKeyword>>
  static boolean aborts_if(PsiBuilder builder_, int level_) {
    return abortsIfKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<abortsWithKeyword>>
  static boolean aborts_with(PsiBuilder builder_, int level_) {
    return abortsWithKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<addressKeyword>>
  static boolean address(PsiBuilder builder_, int level_) {
    return addressKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<andandImpl>>
  static boolean andand(PsiBuilder builder_, int level_) {
    return andandImpl(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<applyKeyword>>
  static boolean apply(PsiBuilder builder_, int level_) {
    return applyKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<assertKeyword>>
  static boolean assert_$(PsiBuilder builder_, int level_) {
    return assertKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<assumeKeyword>>
  static boolean assume(PsiBuilder builder_, int level_) {
    return assumeKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<axiomKeyword>>
  static boolean axiom(PsiBuilder builder_, int level_) {
    return axiomKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<chooseKeyword>>
  static boolean choose(PsiBuilder builder_, int level_) {
    return chooseKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<param>> ( ',' <<param>> )* ','?
  static boolean comma_separated_list(PsiBuilder builder_, int level_, Parser param) {
    if (!recursion_guard_(builder_, level_, "comma_separated_list")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = param.parse(builder_, level_);
    result_ = result_ && comma_separated_list_1(builder_, level_ + 1, param);
    result_ = result_ && comma_separated_list_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ( ',' <<param>> )*
  private static boolean comma_separated_list_1(PsiBuilder builder_, int level_, Parser param) {
    if (!recursion_guard_(builder_, level_, "comma_separated_list_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!comma_separated_list_1_0(builder_, level_ + 1, param)) break;
      if (!empty_element_parsed_guard_(builder_, "comma_separated_list_1", pos_)) break;
    }
    return true;
  }

  // ',' <<param>>
  private static boolean comma_separated_list_1_0(PsiBuilder builder_, int level_, Parser param) {
    if (!recursion_guard_(builder_, level_, "comma_separated_list_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && param.parse(builder_, level_);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ','?
  private static boolean comma_separated_list_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "comma_separated_list_2")) return false;
    consumeToken(builder_, COMMA);
    return true;
  }

  /* ********************************************************** */
  // <<decreasesKeyword>>
  static boolean decreases(PsiBuilder builder_, int level_) {
    return decreasesKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<dotdotImpl>>
  static boolean dotdot(PsiBuilder builder_, int level_) {
    return dotdotImpl(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<emitsKeyword>>
  static boolean emits(PsiBuilder builder_, int level_) {
    return emitsKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<ensuresKeyword>>
  static boolean ensures(PsiBuilder builder_, int level_) {
    return ensuresKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<entryKeyword>>
  static boolean entry(PsiBuilder builder_, int level_) {
    return entryKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<enumKeyword>>
  static boolean enum_$(PsiBuilder builder_, int level_) {
    return enumKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<eqeqgtImpl>>
  static boolean eqeq_gt(PsiBuilder builder_, int level_) {
    return eqeqgtImpl(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<exceptKeyword>>
  static boolean except(PsiBuilder builder_, int level_) {
    return exceptKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<existsKeyword>>
  static boolean exists(PsiBuilder builder_, int level_) {
    return existsKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<forKeyword>>
  static boolean for_$(PsiBuilder builder_, int level_) {
    return forKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<forallKeyword>>
  static boolean forall(PsiBuilder builder_, int level_) {
    return forallKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<friendKeyword>>
  static boolean friend(PsiBuilder builder_, int level_) {
    return friendKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<globalKeyword>>
  static boolean global(PsiBuilder builder_, int level_) {
    return globalKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<gteqImpl>>
  static boolean gteq(PsiBuilder builder_, int level_) {
    return gteqImpl(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<gtgtImpl>>
  static boolean gtgt(PsiBuilder builder_, int level_) {
    return gtgtImpl(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<hasKeyword>>
  static boolean has(PsiBuilder builder_, int level_) {
    return hasKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<inKeyword>>
  static boolean in(PsiBuilder builder_, int level_) {
    return inKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<includeKeyword>>
  static boolean include(PsiBuilder builder_, int level_) {
    return includeKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<inlineKeyword>>
  static boolean inline(PsiBuilder builder_, int level_) {
    return inlineKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<invariantKeyword>>
  static boolean invariant(PsiBuilder builder_, int level_) {
    return invariantKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // !'>' <<param>> (',' | &'>')
  static boolean list_element(PsiBuilder builder_, int level_, Parser param) {
    if (!recursion_guard_(builder_, level_, "list_element")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_);
    result_ = list_element_0(builder_, level_ + 1);
    result_ = result_ && param.parse(builder_, level_);
    pinned_ = result_; // pin = 2
    result_ = result_ && list_element_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // !'>'
  private static boolean list_element_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "list_element_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !consumeToken(builder_, GT);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ',' | &'>'
  private static boolean list_element_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "list_element_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    if (!result_) result_ = list_element_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // &'>'
  private static boolean list_element_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "list_element_2_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _AND_);
    result_ = consumeToken(builder_, GT);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // <<localKeyword>>
  static boolean local(PsiBuilder builder_, int level_) {
    return localKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<lteqeqgtImpl>>
  static boolean lt_eqeq_gt(PsiBuilder builder_, int level_) {
    return lteqeqgtImpl(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<lteqImpl>>
  static boolean lteq(PsiBuilder builder_, int level_) {
    return lteqImpl(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<ltltImpl>>
  static boolean ltlt(PsiBuilder builder_, int level_) {
    return ltltImpl(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<matchKeyword>>
  static boolean match(PsiBuilder builder_, int level_) {
    return matchKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<minKeyword>>
  static boolean min(PsiBuilder builder_, int level_) {
    return minKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<modifiesKeyword>>
  static boolean modifies(PsiBuilder builder_, int level_) {
    return modifiesKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<param>> ( ',' <<param>> )* ','?
  static boolean non_empty_comma_sep_items(PsiBuilder builder_, int level_, Parser param) {
    if (!recursion_guard_(builder_, level_, "non_empty_comma_sep_items")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = param.parse(builder_, level_);
    result_ = result_ && non_empty_comma_sep_items_1(builder_, level_ + 1, param);
    result_ = result_ && non_empty_comma_sep_items_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ( ',' <<param>> )*
  private static boolean non_empty_comma_sep_items_1(PsiBuilder builder_, int level_, Parser param) {
    if (!recursion_guard_(builder_, level_, "non_empty_comma_sep_items_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!non_empty_comma_sep_items_1_0(builder_, level_ + 1, param)) break;
      if (!empty_element_parsed_guard_(builder_, "non_empty_comma_sep_items_1", pos_)) break;
    }
    return true;
  }

  // ',' <<param>>
  private static boolean non_empty_comma_sep_items_1_0(PsiBuilder builder_, int level_, Parser param) {
    if (!recursion_guard_(builder_, level_, "non_empty_comma_sep_items_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA);
    result_ = result_ && param.parse(builder_, level_);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ','?
  private static boolean non_empty_comma_sep_items_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "non_empty_comma_sep_items_2")) return false;
    consumeToken(builder_, COMMA);
    return true;
  }

  /* ********************************************************** */
  // <<ororImpl>>
  static boolean oror(PsiBuilder builder_, int level_) {
    return ororImpl(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<packageKeyword>>
  static boolean package_$(PsiBuilder builder_, int level_) {
    return packageKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<postKeyword>>
  static boolean post(PsiBuilder builder_, int level_) {
    return postKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<pragmaKeyword>>
  static boolean pragma(PsiBuilder builder_, int level_) {
    return pragmaKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<pureKeyword>>
  static boolean pure(PsiBuilder builder_, int level_) {
    return pureKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<readsKeyword>>
  static boolean reads(PsiBuilder builder_, int level_) {
    return readsKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<requiresKeyword>>
  static boolean requires(PsiBuilder builder_, int level_) {
    return requiresKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<schemaKeyword>>
  static boolean schema(PsiBuilder builder_, int level_) {
    return schemaKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<toKeyword>>
  static boolean to(PsiBuilder builder_, int level_) {
    return toKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<updateKeyword>>
  static boolean update(PsiBuilder builder_, int level_) {
    return updateKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<whereKeyword>>
  static boolean where(PsiBuilder builder_, int level_) {
    return whereKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<withKeyword>>
  static boolean with(PsiBuilder builder_, int level_) {
    return withKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<writesKeyword>>
  static boolean writes(PsiBuilder builder_, int level_) {
    return writesKeyword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // Expression root: Expr
  // Operator priority table:
  // 0: POSTFIX(AssignmentExpr)
  // 1: BINARY(RangeExpr)
  // 2: ATOM(ForallQuantExpr)
  // 3: ATOM(ExistsQuantExpr)
  // 4: ATOM(ChooseQuantExpr)
  // 5: BINARY(ImplyBinExpr) BINARY(PartialImplyBinExpr)
  // 6: BINARY(OrBinExpr)
  // 7: BINARY(AndBinExpr)
  // 8: BINARY(EqualsBinExpr) BINARY(NotEqualsBinExpr) BINARY(LessEqualsBinExpr) BINARY(LessBinExpr)
  //    BINARY(GreaterEqualsBinExpr) BINARY(GreaterBinExpr)
  // 9: BINARY(BitOrBinExpr)
  // 10: BINARY(BitXorBinExpr)
  // 11: BINARY(BitAndBinExpr)
  // 12: BINARY(LeftShiftBinExpr)
  // 13: BINARY(RightShiftBinExpr)
  // 14: BINARY(PlusBinExpr) BINARY(MinusBinExpr)
  // 15: BINARY(DivBinExpr) BINARY(MulBinExpr) BINARY(ModBinExpr)
  // 16: POSTFIX(CastExpr)
  // 17: ATOM(IfExpr) ATOM(LoopExpr) ATOM(MatchExpr) ATOM(WhileExpr)
  //    ATOM(ForExpr) ATOM(LabeledBlockExpr)
  // 18: PREFIX(CopyExpr) PREFIX(MoveExpr) PREFIX(DerefExpr) PREFIX(BangExpr)
  //    ATOM(ReturnExpr) ATOM(ContinueExpr) ATOM(BreakExpr) PREFIX(AbortExpr)
  // 19: PREFIX(BorrowExpr)
  // 20: ATOM(AnnotatedExpr) ATOM(UnitLitExpr) ATOM(TupleLitOrParenExpr) ATOM(StructLitExpr)
  //    ATOM(VectorLitExpr) POSTFIX(DotExpr) POSTFIX(IndexExpr) ATOM(PathExpr)
  //    ATOM(CallExpr) ATOM(AssertMacroExpr) ATOM(LambdaExpr) ATOM(LitExpr)
  //    ATOM(CodeBlockExpr)
  public static boolean Expr(PsiBuilder builder_, int level_, int priority_) {
    if (!recursion_guard_(builder_, level_, "Expr")) return false;
    addVariant(builder_, "<expression>");
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<expression>");
    result_ = ForallQuantExpr(builder_, level_ + 1);
    if (!result_) result_ = ExistsQuantExpr(builder_, level_ + 1);
    if (!result_) result_ = ChooseQuantExpr(builder_, level_ + 1);
    if (!result_) result_ = IfExpr(builder_, level_ + 1);
    if (!result_) result_ = LoopExpr(builder_, level_ + 1);
    if (!result_) result_ = MatchExpr(builder_, level_ + 1);
    if (!result_) result_ = WhileExpr(builder_, level_ + 1);
    if (!result_) result_ = ForExpr(builder_, level_ + 1);
    if (!result_) result_ = LabeledBlockExpr(builder_, level_ + 1);
    if (!result_) result_ = CopyExpr(builder_, level_ + 1);
    if (!result_) result_ = MoveExpr(builder_, level_ + 1);
    if (!result_) result_ = DerefExpr(builder_, level_ + 1);
    if (!result_) result_ = BangExpr(builder_, level_ + 1);
    if (!result_) result_ = ReturnExpr(builder_, level_ + 1);
    if (!result_) result_ = ContinueExpr(builder_, level_ + 1);
    if (!result_) result_ = BreakExpr(builder_, level_ + 1);
    if (!result_) result_ = AbortExpr(builder_, level_ + 1);
    if (!result_) result_ = BorrowExpr(builder_, level_ + 1);
    if (!result_) result_ = AnnotatedExpr(builder_, level_ + 1);
    if (!result_) result_ = UnitLitExpr(builder_, level_ + 1);
    if (!result_) result_ = TupleLitOrParenExpr(builder_, level_ + 1);
    if (!result_) result_ = StructLitExpr(builder_, level_ + 1);
    if (!result_) result_ = VectorLitExpr(builder_, level_ + 1);
    if (!result_) result_ = PathExpr(builder_, level_ + 1);
    if (!result_) result_ = CallExpr(builder_, level_ + 1);
    if (!result_) result_ = AssertMacroExpr(builder_, level_ + 1);
    if (!result_) result_ = LambdaExpr(builder_, level_ + 1);
    if (!result_) result_ = LitExpr(builder_, level_ + 1);
    if (!result_) result_ = CodeBlockExpr(builder_, level_ + 1);
    pinned_ = result_;
    result_ = result_ && Expr_0(builder_, level_ + 1, priority_);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  public static boolean Expr_0(PsiBuilder builder_, int level_, int priority_) {
    if (!recursion_guard_(builder_, level_, "Expr_0")) return false;
    boolean result_ = true;
    while (true) {
      Marker marker_ = enter_section_(builder_, level_, _LEFT_, null);
      if (priority_ < 0 && Initializer(builder_, level_ + 1)) {
        result_ = true;
        exit_section_(builder_, level_, marker_, ASSIGNMENT_EXPR, result_, true, null);
      }
      else if (priority_ < 1 && dotdot(builder_, level_ + 1)) {
        result_ = Expr(builder_, level_, 1);
        exit_section_(builder_, level_, marker_, RANGE_EXPR, result_, true, null);
      }
      else if (priority_ < 5 && ImplyBinOp(builder_, level_ + 1)) {
        result_ = Expr(builder_, level_, 5);
        exit_section_(builder_, level_, marker_, BINARY_EXPR, result_, true, null);
      }
      else if (priority_ < 5 && PartialImplyBinOp(builder_, level_ + 1)) {
        result_ = Expr(builder_, level_, 5);
        exit_section_(builder_, level_, marker_, BINARY_EXPR, result_, true, null);
      }
      else if (priority_ < 6 && OrBinOp(builder_, level_ + 1)) {
        result_ = Expr(builder_, level_, 6);
        exit_section_(builder_, level_, marker_, BINARY_EXPR, result_, true, null);
      }
      else if (priority_ < 7 && AndBinOp(builder_, level_ + 1)) {
        result_ = Expr(builder_, level_, 7);
        exit_section_(builder_, level_, marker_, BINARY_EXPR, result_, true, null);
      }
      else if (priority_ < 8 && EqualsBinExpr_0(builder_, level_ + 1)) {
        result_ = Expr(builder_, level_, 8);
        exit_section_(builder_, level_, marker_, BINARY_EXPR, result_, true, null);
      }
      else if (priority_ < 8 && NotEqualsBinOp(builder_, level_ + 1)) {
        result_ = Expr(builder_, level_, 8);
        exit_section_(builder_, level_, marker_, BINARY_EXPR, result_, true, null);
      }
      else if (priority_ < 8 && LessEqualsBinOp(builder_, level_ + 1)) {
        result_ = Expr(builder_, level_, 8);
        exit_section_(builder_, level_, marker_, BINARY_EXPR, result_, true, null);
      }
      else if (priority_ < 8 && LessBinExpr_0(builder_, level_ + 1)) {
        result_ = Expr(builder_, level_, 8);
        exit_section_(builder_, level_, marker_, BINARY_EXPR, result_, true, null);
      }
      else if (priority_ < 8 && GreaterEqualsBinOp(builder_, level_ + 1)) {
        result_ = Expr(builder_, level_, 8);
        exit_section_(builder_, level_, marker_, BINARY_EXPR, result_, true, null);
      }
      else if (priority_ < 8 && GreaterBinExpr_0(builder_, level_ + 1)) {
        result_ = Expr(builder_, level_, 8);
        exit_section_(builder_, level_, marker_, BINARY_EXPR, result_, true, null);
      }
      else if (priority_ < 9 && BitOrBinExpr_0(builder_, level_ + 1)) {
        result_ = Expr(builder_, level_, 9);
        exit_section_(builder_, level_, marker_, BINARY_EXPR, result_, true, null);
      }
      else if (priority_ < 10 && BitXorBinOp(builder_, level_ + 1)) {
        result_ = Expr(builder_, level_, 10);
        exit_section_(builder_, level_, marker_, BINARY_EXPR, result_, true, null);
      }
      else if (priority_ < 11 && BitAndBinExpr_0(builder_, level_ + 1)) {
        result_ = Expr(builder_, level_, 11);
        exit_section_(builder_, level_, marker_, BINARY_EXPR, result_, true, null);
      }
      else if (priority_ < 12 && LeftShiftBinOp(builder_, level_ + 1)) {
        result_ = Expr(builder_, level_, 12);
        exit_section_(builder_, level_, marker_, BINARY_EXPR, result_, true, null);
      }
      else if (priority_ < 13 && RightShiftBinOp(builder_, level_ + 1)) {
        result_ = Expr(builder_, level_, 13);
        exit_section_(builder_, level_, marker_, BINARY_EXPR, result_, true, null);
      }
      else if (priority_ < 14 && PlusBinOp(builder_, level_ + 1)) {
        result_ = Expr(builder_, level_, 14);
        exit_section_(builder_, level_, marker_, BINARY_EXPR, result_, true, null);
      }
      else if (priority_ < 14 && MinusBinOp(builder_, level_ + 1)) {
        result_ = Expr(builder_, level_, 14);
        exit_section_(builder_, level_, marker_, BINARY_EXPR, result_, true, null);
      }
      else if (priority_ < 15 && DivBinOp(builder_, level_ + 1)) {
        result_ = Expr(builder_, level_, 15);
        exit_section_(builder_, level_, marker_, BINARY_EXPR, result_, true, null);
      }
      else if (priority_ < 15 && MulBinOp(builder_, level_ + 1)) {
        result_ = Expr(builder_, level_, 15);
        exit_section_(builder_, level_, marker_, BINARY_EXPR, result_, true, null);
      }
      else if (priority_ < 15 && ModBinOp(builder_, level_ + 1)) {
        result_ = Expr(builder_, level_, 15);
        exit_section_(builder_, level_, marker_, BINARY_EXPR, result_, true, null);
      }
      else if (priority_ < 16 && CastExpr_0(builder_, level_ + 1)) {
        result_ = true;
        exit_section_(builder_, level_, marker_, CAST_EXPR, result_, true, null);
      }
      else if (priority_ < 20 && DotExpr_inner(builder_, level_ + 1)) {
        result_ = true;
        exit_section_(builder_, level_, marker_, DOT_EXPR, result_, true, null);
      }
      else if (priority_ < 20 && IndexArg(builder_, level_ + 1)) {
        result_ = true;
        exit_section_(builder_, level_, marker_, INDEX_EXPR, result_, true, null);
      }
      else {
        exit_section_(builder_, level_, marker_, null, false, false, null);
        break;
      }
    }
    return result_;
  }

  // <<mslOnly forall>> QuantBindings QuantWhere? ':' Expr
  public static boolean ForallQuantExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForallQuantExpr")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, FORALL_QUANT_EXPR, "<expression>");
    result_ = mslOnly(builder_, level_ + 1, MoveParser::forall);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, QuantBindings(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, ForallQuantExpr_2(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, COLON)) && result_;
    result_ = pinned_ && Expr(builder_, level_ + 1, -1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // QuantWhere?
  private static boolean ForallQuantExpr_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForallQuantExpr_2")) return false;
    QuantWhere(builder_, level_ + 1);
    return true;
  }

  // <<mslOnly exists>> QuantBindings QuantWhere? ':' Expr
  public static boolean ExistsQuantExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ExistsQuantExpr")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, EXISTS_QUANT_EXPR, "<expression>");
    result_ = mslOnly(builder_, level_ + 1, MoveParser::exists);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, QuantBindings(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, ExistsQuantExpr_2(builder_, level_ + 1)) && result_;
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, COLON)) && result_;
    result_ = pinned_ && Expr(builder_, level_ + 1, -1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // QuantWhere?
  private static boolean ExistsQuantExpr_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ExistsQuantExpr_2")) return false;
    QuantWhere(builder_, level_ + 1);
    return true;
  }

  // choose min? QuantBindings QuantWhere?
  public static boolean ChooseQuantExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ChooseQuantExpr")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, CHOOSE_QUANT_EXPR, "<expression>");
    result_ = choose(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, ChooseQuantExpr_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, QuantBindings(builder_, level_ + 1)) && result_;
    result_ = pinned_ && ChooseQuantExpr_3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // min?
  private static boolean ChooseQuantExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ChooseQuantExpr_1")) return false;
    min(builder_, level_ + 1);
    return true;
  }

  // QuantWhere?
  private static boolean ChooseQuantExpr_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ChooseQuantExpr_3")) return false;
    QuantWhere(builder_, level_ + 1);
    return true;
  }

  // !eqeq_gt EqualsBinOp
  private static boolean EqualsBinExpr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EqualsBinExpr_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = EqualsBinExpr_0_0(builder_, level_ + 1);
    result_ = result_ && EqualsBinOp(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // !eqeq_gt
  private static boolean EqualsBinExpr_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EqualsBinExpr_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !eqeq_gt(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // !(ltlt | lt_eqeq_gt) LessBinOp
  private static boolean LessBinExpr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LessBinExpr_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = LessBinExpr_0_0(builder_, level_ + 1);
    result_ = result_ && LessBinOp(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // !(ltlt | lt_eqeq_gt)
  private static boolean LessBinExpr_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LessBinExpr_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !LessBinExpr_0_0_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ltlt | lt_eqeq_gt
  private static boolean LessBinExpr_0_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LessBinExpr_0_0_0")) return false;
    boolean result_;
    result_ = ltlt(builder_, level_ + 1);
    if (!result_) result_ = lt_eqeq_gt(builder_, level_ + 1);
    return result_;
  }

  // !gtgt GreaterBinOp
  private static boolean GreaterBinExpr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GreaterBinExpr_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = GreaterBinExpr_0_0(builder_, level_ + 1);
    result_ = result_ && GreaterBinOp(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // !gtgt
  private static boolean GreaterBinExpr_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "GreaterBinExpr_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !gtgt(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // !oror BitOrBinOp
  private static boolean BitOrBinExpr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BitOrBinExpr_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = BitOrBinExpr_0_0(builder_, level_ + 1);
    result_ = result_ && BitOrBinOp(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // !oror
  private static boolean BitOrBinExpr_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BitOrBinExpr_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !oror(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // !andand BitAndBinOp
  private static boolean BitAndBinExpr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BitAndBinExpr_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = BitAndBinExpr_0_0(builder_, level_ + 1);
    result_ = result_ && BitAndBinOp(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // !andand
  private static boolean BitAndBinExpr_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BitAndBinExpr_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !andand(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // as Type
  private static boolean CastExpr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CastExpr_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, AS);
    result_ = result_ && Type(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // if Condition AnyBlock ElseBlock?
  public static boolean IfExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IfExpr")) return false;
    if (!nextTokenIsSmart(builder_, IF)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, IF_EXPR, "<expression>");
    result_ = consumeTokenSmart(builder_, IF);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, Condition(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, AnyBlock(builder_, level_ + 1)) && result_;
    result_ = pinned_ && IfExpr_3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // ElseBlock?
  private static boolean IfExpr_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IfExpr_3")) return false;
    ElseBlock(builder_, level_ + 1);
    return true;
  }

  // LabelDecl? loop AnyBlock
  public static boolean LoopExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LoopExpr")) return false;
    if (!nextTokenIsSmart(builder_, LOOP, QUOTE_IDENTIFIER)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, LOOP_EXPR, "<expression>");
    result_ = LoopExpr_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, LOOP);
    pinned_ = result_; // pin = 2
    result_ = result_ && AnyBlock(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // LabelDecl?
  private static boolean LoopExpr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LoopExpr_0")) return false;
    LabelDecl(builder_, level_ + 1);
    return true;
  }

  // Attr* <<remapContextualKwOnRollback (match MatchArgument MatchBody)>>
  public static boolean MatchExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MatchExpr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, MATCH_EXPR, "<expression>");
    result_ = MatchExpr_0(builder_, level_ + 1);
    result_ = result_ && remapContextualKwOnRollback(builder_, level_ + 1, MoveParser::MatchExpr_1_0);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // Attr*
  private static boolean MatchExpr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MatchExpr_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Attr(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "MatchExpr_0", pos_)) break;
    }
    return true;
  }

  // match MatchArgument MatchBody
  private static boolean MatchExpr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MatchExpr_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = match(builder_, level_ + 1);
    result_ = result_ && MatchArgument(builder_, level_ + 1);
    result_ = result_ && MatchBody(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // LabelDecl? while Condition AnyBlock
  public static boolean WhileExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WhileExpr")) return false;
    if (!nextTokenIsSmart(builder_, QUOTE_IDENTIFIER, WHILE)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, WHILE_EXPR, "<expression>");
    result_ = WhileExpr_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, WHILE);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, Condition(builder_, level_ + 1));
    result_ = pinned_ && AnyBlock(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // LabelDecl?
  private static boolean WhileExpr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WhileExpr_0")) return false;
    LabelDecl(builder_, level_ + 1);
    return true;
  }

  // LabelDecl? for ForIterCondition AnyBlock
  public static boolean ForExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForExpr")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, FOR_EXPR, "<expression>");
    result_ = ForExpr_0(builder_, level_ + 1);
    result_ = result_ && for_$(builder_, level_ + 1);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, ForIterCondition(builder_, level_ + 1));
    result_ = pinned_ && AnyBlock(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // LabelDecl?
  private static boolean ForExpr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForExpr_0")) return false;
    LabelDecl(builder_, level_ + 1);
    return true;
  }

  // LabelDecl CodeBlock
  public static boolean LabeledBlockExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LabeledBlockExpr")) return false;
    if (!nextTokenIsSmart(builder_, QUOTE_IDENTIFIER)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, LABELED_BLOCK_EXPR, "<expression>");
    result_ = LabelDecl(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && CodeBlock(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  public static boolean CopyExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CopyExpr")) return false;
    if (!nextTokenIsSmart(builder_, COPY)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeTokenSmart(builder_, COPY);
    pinned_ = result_;
    result_ = pinned_ && Expr(builder_, level_, 18);
    exit_section_(builder_, level_, marker_, COPY_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  public static boolean MoveExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MoveExpr")) return false;
    if (!nextTokenIsSmart(builder_, MOVE)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeTokenSmart(builder_, MOVE);
    pinned_ = result_;
    result_ = pinned_ && Expr(builder_, level_, 18);
    exit_section_(builder_, level_, marker_, MOVE_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  public static boolean DerefExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DerefExpr")) return false;
    if (!nextTokenIsSmart(builder_, MUL)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeTokenSmart(builder_, MUL);
    pinned_ = result_;
    result_ = pinned_ && Expr(builder_, level_, 18);
    exit_section_(builder_, level_, marker_, DEREF_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  public static boolean BangExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BangExpr")) return false;
    if (!nextTokenIsSmart(builder_, EXCL)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeTokenSmart(builder_, EXCL);
    pinned_ = result_;
    result_ = pinned_ && Expr(builder_, level_, 18);
    exit_section_(builder_, level_, marker_, BANG_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  // return LabelRef? Expr?
  public static boolean ReturnExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ReturnExpr")) return false;
    if (!nextTokenIsSmart(builder_, RETURN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, RETURN_EXPR, "<expression>");
    result_ = consumeTokenSmart(builder_, RETURN);
    result_ = result_ && ReturnExpr_1(builder_, level_ + 1);
    result_ = result_ && ReturnExpr_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // LabelRef?
  private static boolean ReturnExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ReturnExpr_1")) return false;
    LabelRef(builder_, level_ + 1);
    return true;
  }

  // Expr?
  private static boolean ReturnExpr_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ReturnExpr_2")) return false;
    Expr(builder_, level_ + 1, -1);
    return true;
  }

  // continue LabelRef?
  public static boolean ContinueExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ContinueExpr")) return false;
    if (!nextTokenIsSmart(builder_, CONTINUE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, CONTINUE_EXPR, "<expression>");
    result_ = consumeTokenSmart(builder_, CONTINUE);
    result_ = result_ && ContinueExpr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // LabelRef?
  private static boolean ContinueExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ContinueExpr_1")) return false;
    LabelRef(builder_, level_ + 1);
    return true;
  }

  // break LabelRef? Expr?
  public static boolean BreakExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BreakExpr")) return false;
    if (!nextTokenIsSmart(builder_, BREAK)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BREAK_EXPR, "<expression>");
    result_ = consumeTokenSmart(builder_, BREAK);
    result_ = result_ && BreakExpr_1(builder_, level_ + 1);
    result_ = result_ && BreakExpr_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // LabelRef?
  private static boolean BreakExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BreakExpr_1")) return false;
    LabelRef(builder_, level_ + 1);
    return true;
  }

  // Expr?
  private static boolean BreakExpr_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BreakExpr_2")) return false;
    Expr(builder_, level_ + 1, -1);
    return true;
  }

  public static boolean AbortExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AbortExpr")) return false;
    if (!nextTokenIsSmart(builder_, ABORT)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeTokenSmart(builder_, ABORT);
    pinned_ = result_;
    result_ = pinned_ && Expr(builder_, level_, 18);
    exit_section_(builder_, level_, marker_, ABORT_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  public static boolean BorrowExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BorrowExpr")) return false;
    if (!nextTokenIsSmart(builder_, AND)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = BorrowExpr_0(builder_, level_ + 1);
    pinned_ = result_;
    result_ = pinned_ && Expr(builder_, level_, 19);
    exit_section_(builder_, level_, marker_, BORROW_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  // '&' mut?
  private static boolean BorrowExpr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BorrowExpr_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, AND);
    result_ = result_ && BorrowExpr_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // mut?
  private static boolean BorrowExpr_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BorrowExpr_0_1")) return false;
    consumeTokenSmart(builder_, MUT);
    return true;
  }

  // AnnotatedExpPrefix Type ')'
  public static boolean AnnotatedExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AnnotatedExpr")) return false;
    if (!nextTokenIsSmart(builder_, L_PAREN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ANNOTATED_EXPR, "<expression>");
    result_ = AnnotatedExpPrefix(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, Type(builder_, level_ + 1));
    result_ = pinned_ && consumeToken(builder_, R_PAREN) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // '(' ')'
  public static boolean UnitLitExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UnitLitExpr")) return false;
    if (!nextTokenIsSmart(builder_, L_PAREN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, UNIT_LIT_EXPR, "<expression>");
    result_ = consumeTokensSmart(builder_, 1, L_PAREN, R_PAREN);
    pinned_ = result_; // pin = 1
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // '(' Expr (TupleLitExprUpper | ')')
  public static boolean TupleLitOrParenExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TupleLitOrParenExpr")) return false;
    if (!nextTokenIsSmart(builder_, L_PAREN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PARENS_EXPR, "<expression>");
    result_ = consumeTokenSmart(builder_, L_PAREN);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, Expr(builder_, level_ + 1, -1));
    result_ = pinned_ && TupleLitOrParenExpr_2(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // TupleLitExprUpper | ')'
  private static boolean TupleLitOrParenExpr_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TupleLitOrParenExpr_2")) return false;
    boolean result_;
    result_ = TupleLitExprUpper(builder_, level_ + 1);
    if (!result_) result_ = consumeTokenSmart(builder_, R_PAREN);
    return result_;
  }

  // <<includeStmtModeFalse>> PathImpl StructLitFieldsBlock
  public static boolean StructLitExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "StructLitExpr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, STRUCT_LIT_EXPR, "<expression>");
    result_ = includeStmtModeFalse(builder_, level_ + 1);
    result_ = result_ && PathImpl(builder_, level_ + 1);
    result_ = result_ && StructLitFieldsBlock(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // <<vectorIdent>> ('<' TypeArgument '>')? VectorLitItems
  public static boolean VectorLitExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VectorLitExpr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, VECTOR_LIT_EXPR, "<expression>");
    result_ = vectorIdent(builder_, level_ + 1);
    result_ = result_ && VectorLitExpr_1(builder_, level_ + 1);
    result_ = result_ && VectorLitItems(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ('<' TypeArgument '>')?
  private static boolean VectorLitExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VectorLitExpr_1")) return false;
    VectorLitExpr_1_0(builder_, level_ + 1);
    return true;
  }

  // '<' TypeArgument '>'
  private static boolean VectorLitExpr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VectorLitExpr_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, LT);
    result_ = result_ && TypeArgument(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, GT);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // PathImpl !('(' | '!')
  public static boolean PathExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PathExpr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PATH_EXPR, "<expression>");
    result_ = PathImpl(builder_, level_ + 1);
    result_ = result_ && PathExpr_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // !('(' | '!')
  private static boolean PathExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PathExpr_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NOT_);
    result_ = !PathExpr_1_0(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // '(' | '!'
  private static boolean PathExpr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PathExpr_1_0")) return false;
    boolean result_;
    result_ = consumeTokenSmart(builder_, L_PAREN);
    if (!result_) result_ = consumeTokenSmart(builder_, EXCL);
    return result_;
  }

  // PathImpl '!'? TypeArgumentList? ValueArgumentList
  public static boolean CallExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CallExpr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, CALL_EXPR, "<expression>");
    result_ = PathImpl(builder_, level_ + 1);
    result_ = result_ && CallExpr_1(builder_, level_ + 1);
    result_ = result_ && CallExpr_2(builder_, level_ + 1);
    result_ = result_ && ValueArgumentList(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // '!'?
  private static boolean CallExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CallExpr_1")) return false;
    consumeTokenSmart(builder_, EXCL);
    return true;
  }

  // TypeArgumentList?
  private static boolean CallExpr_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CallExpr_2")) return false;
    TypeArgumentList(builder_, level_ + 1);
    return true;
  }

  // <<assertIdent>> '!' ValueArgumentList
  public static boolean AssertMacroExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AssertMacroExpr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ASSERT_MACRO_EXPR, "<expression>");
    result_ = assertIdent(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, EXCL);
    result_ = result_ && ValueArgumentList(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // '|' LambdaParams_list? '|' ( LambdaExprBody | LambdaExprBlock )
  public static boolean LambdaExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LambdaExpr")) return false;
    if (!nextTokenIsSmart(builder_, OR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, LAMBDA_EXPR, "<expression>");
    result_ = consumeTokenSmart(builder_, OR);
    pinned_ = result_; // pin = 1
    result_ = result_ && report_error_(builder_, LambdaExpr_1(builder_, level_ + 1));
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, OR)) && result_;
    result_ = pinned_ && LambdaExpr_3(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // LambdaParams_list?
  private static boolean LambdaExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LambdaExpr_1")) return false;
    LambdaParams_list(builder_, level_ + 1);
    return true;
  }

  // LambdaExprBody | LambdaExprBlock
  private static boolean LambdaExpr_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LambdaExpr_3")) return false;
    boolean result_;
    result_ = LambdaExprBody(builder_, level_ + 1);
    if (!result_) result_ = LambdaExprBlock(builder_, level_ + 1);
    return result_;
  }

  // HEX_INTEGER_LITERAL
  //                 | BOOL_LITERAL
  //                 | INTEGER_LITERAL
  //                 | HEX_STRING_LITERAL
  //                 | BYTE_STRING_LITERAL
  //                 | AddressLit
  public static boolean LitExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LitExpr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _COLLAPSE_, LIT_EXPR, "<expression>");
    result_ = HEX_INTEGER_LITERAL(builder_, level_ + 1);
    if (!result_) result_ = consumeTokenSmart(builder_, BOOL_LITERAL);
    if (!result_) result_ = consumeTokenSmart(builder_, INTEGER_LITERAL);
    if (!result_) result_ = consumeTokenSmart(builder_, HEX_STRING_LITERAL);
    if (!result_) result_ = consumeTokenSmart(builder_, BYTE_STRING_LITERAL);
    if (!result_) result_ = AddressLit(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // CodeBlock
  public static boolean CodeBlockExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CodeBlockExpr")) return false;
    if (!nextTokenIsSmart(builder_, L_BRACE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, CODE_BLOCK_EXPR, "<expression>");
    result_ = CodeBlock(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  static final Parser Expr_parser_ = (builder_, level_) -> Expr(builder_, level_ + 1, -1);
}
