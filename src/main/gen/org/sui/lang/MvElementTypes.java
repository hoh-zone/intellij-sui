// This is a generated file. Not intended for manual editing.
package org.sui.lang;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.sui.lang.core.MvElementType;
import org.sui.lang.core.stubs.StubsKt;
import org.sui.lang.core.MvTokenType;
import org.sui.lang.core.psi.impl.*;

public interface MvElementTypes {

  IElementType ABILITIES_LIST = new MvElementType("ABILITIES_LIST");
  IElementType ABILITY = new MvElementType("ABILITY");
  IElementType ABORTS_IF_SPEC_EXPR = new MvElementType("ABORTS_IF_SPEC_EXPR");
  IElementType ABORTS_IF_WITH = new MvElementType("ABORTS_IF_WITH");
  IElementType ABORTS_WITH_SPEC_EXPR = new MvElementType("ABORTS_WITH_SPEC_EXPR");
  IElementType ABORT_EXPR = new MvElementType("ABORT_EXPR");
  IElementType ACCESS_SPECIFIER = new MvElementType("ACCESS_SPECIFIER");
  IElementType ACCESS_SPECIFIER_LIST = new MvElementType("ACCESS_SPECIFIER_LIST");
  IElementType ACQUIRES_TYPE = new MvElementType("ACQUIRES_TYPE");
  IElementType ADDRESS_BLOCK = new MvElementType("ADDRESS_BLOCK");
  IElementType ADDRESS_DEF = new MvElementType("ADDRESS_DEF");
  IElementType ADDRESS_LIT = new MvElementType("ADDRESS_LIT");
  IElementType ADDRESS_REF = new MvElementType("ADDRESS_REF");
  IElementType ADDRESS_SPECIFIER = new MvElementType("ADDRESS_SPECIFIER");
  IElementType ADDRESS_SPECIFIER_ARG = new MvElementType("ADDRESS_SPECIFIER_ARG");
  IElementType ADDRESS_SPECIFIER_CALL_PARAM = new MvElementType("ADDRESS_SPECIFIER_CALL_PARAM");
  IElementType ADDRESS_SPECIFIER_LIT = new MvElementType("ADDRESS_SPECIFIER_LIT");
  IElementType AND_INCLUDE_ITEM = new MvElementType("AND_INCLUDE_ITEM");
  IElementType ANNOTATED_EXPR = new MvElementType("ANNOTATED_EXPR");
  IElementType APPLY_EXCEPT = new MvElementType("APPLY_EXCEPT");
  IElementType APPLY_SCHEMA_NAME_ATTRIBUTE = new MvElementType("APPLY_SCHEMA_NAME_ATTRIBUTE");
  IElementType APPLY_SCHEMA_STMT = new MvElementType("APPLY_SCHEMA_STMT");
  IElementType APPLY_TO = new MvElementType("APPLY_TO");
  IElementType ASSERT_BANG_EXPR = new MvElementType("ASSERT_BANG_EXPR");
  IElementType ASSERT_MACRO_EXPR = new MvElementType("ASSERT_MACRO_EXPR");
  IElementType ASSERT_SPEC_EXPR = new MvElementType("ASSERT_SPEC_EXPR");
  IElementType ASSIGNMENT_EXPR = new MvElementType("ASSIGNMENT_EXPR");
  IElementType ASSUME_SPEC_EXPR = new MvElementType("ASSUME_SPEC_EXPR");
  IElementType ATTR = new MvElementType("ATTR");
  IElementType ATTR_ITEM = new MvElementType("ATTR_ITEM");
  IElementType ATTR_ITEM_INITIALIZER = new MvElementType("ATTR_ITEM_INITIALIZER");
  IElementType ATTR_ITEM_LIST = new MvElementType("ATTR_ITEM_LIST");
  IElementType AXIOM_SPEC_EXPR = new MvElementType("AXIOM_SPEC_EXPR");
  IElementType BANG_EXPR = new MvElementType("BANG_EXPR");
  IElementType BINARY_EXPR = new MvElementType("BINARY_EXPR");
  IElementType BINARY_OP = new MvElementType("BINARY_OP");
  IElementType BLOCK_FIELDS = new MvElementType("BLOCK_FIELDS");
  IElementType BORROW_EXPR = new MvElementType("BORROW_EXPR");
  IElementType BREAK_EXPR = new MvElementType("BREAK_EXPR");
  IElementType CALL_EXPR = new MvElementType("CALL_EXPR");
  IElementType CAST_EXPR = new MvElementType("CAST_EXPR");
  IElementType CHOOSE_QUANT_EXPR = new MvElementType("CHOOSE_QUANT_EXPR");
  IElementType CODE_BLOCK = new MvElementType("CODE_BLOCK");
  IElementType CODE_BLOCK_EXPR = new MvElementType("CODE_BLOCK_EXPR");
  IElementType CONDITION = new MvElementType("CONDITION");
  IElementType CONST = StubsKt.factory("CONST");
  IElementType CONST_PAT = new MvElementType("CONST_PAT");
  IElementType CONTINUE_EXPR = new MvElementType("CONTINUE_EXPR");
  IElementType COPY_EXPR = new MvElementType("COPY_EXPR");
  IElementType DECREASES_SPEC_EXPR = new MvElementType("DECREASES_SPEC_EXPR");
  IElementType DEREF_EXPR = new MvElementType("DEREF_EXPR");
  IElementType DOT_EXPR = new MvElementType("DOT_EXPR");
  IElementType ELSE_BLOCK = new MvElementType("ELSE_BLOCK");
  IElementType EMITS_CONDITION = new MvElementType("EMITS_CONDITION");
  IElementType EMITS_SPEC_EXPR = new MvElementType("EMITS_SPEC_EXPR");
  IElementType ENSURES_SPEC_EXPR = new MvElementType("ENSURES_SPEC_EXPR");
  IElementType ENUM = StubsKt.factory("ENUM");
  IElementType ENUM_BODY = new MvElementType("ENUM_BODY");
  IElementType ENUM_VARIANT = StubsKt.factory("ENUM_VARIANT");
  IElementType ENUM_VARIANT_PAT = new MvElementType("ENUM_VARIANT_PAT");
  IElementType EXISTS_QUANT_EXPR = new MvElementType("EXISTS_QUANT_EXPR");
  IElementType EXPR = new MvElementType("EXPR");
  IElementType EXPR_STMT = new MvElementType("EXPR_STMT");
  IElementType FIELD_PAT = new MvElementType("FIELD_PAT");
  IElementType FIELD_PAT_BINDING = new MvElementType("FIELD_PAT_BINDING");
  IElementType FIELD_PAT_FULL = new MvElementType("FIELD_PAT_FULL");
  IElementType FORALL_QUANT_EXPR = new MvElementType("FORALL_QUANT_EXPR");
  IElementType FOR_EXPR = new MvElementType("FOR_EXPR");
  IElementType FOR_ITER_CONDITION = new MvElementType("FOR_ITER_CONDITION");
  IElementType FRIEND_DECL = new MvElementType("FRIEND_DECL");
  IElementType FUNCTION = StubsKt.factory("FUNCTION");
  IElementType FUNCTION_PARAMETER = new MvElementType("FUNCTION_PARAMETER");
  IElementType FUNCTION_PARAMETER_LIST = new MvElementType("FUNCTION_PARAMETER_LIST");
  IElementType FUNCTION_PATTERN = new MvElementType("FUNCTION_PATTERN");
  IElementType GLOBAL_VARIABLE_STMT = new MvElementType("GLOBAL_VARIABLE_STMT");
  IElementType IF_ELSE_INCLUDE_ITEM = new MvElementType("IF_ELSE_INCLUDE_ITEM");
  IElementType IF_EXPR = new MvElementType("IF_EXPR");
  IElementType IMPLY_INCLUDE_ITEM = new MvElementType("IMPLY_INCLUDE_ITEM");
  IElementType INCLUDE_ITEM = new MvElementType("INCLUDE_ITEM");
  IElementType INCLUDE_STMT = new MvElementType("INCLUDE_STMT");
  IElementType INDEX_EXPR = new MvElementType("INDEX_EXPR");
  IElementType INITIALIZER = new MvElementType("INITIALIZER");
  IElementType INLINE_BLOCK = new MvElementType("INLINE_BLOCK");
  IElementType INVARIANT_SPEC_EXPR = new MvElementType("INVARIANT_SPEC_EXPR");
  IElementType ITEM_SPEC = new MvElementType("ITEM_SPEC");
  IElementType ITEM_SPEC_BLOCK_EXPR = new MvElementType("ITEM_SPEC_BLOCK_EXPR");
  IElementType ITEM_SPEC_FUNCTION_PARAMETER = new MvElementType("ITEM_SPEC_FUNCTION_PARAMETER");
  IElementType ITEM_SPEC_FUNCTION_PARAMETER_LIST = new MvElementType("ITEM_SPEC_FUNCTION_PARAMETER_LIST");
  IElementType ITEM_SPEC_REF = new MvElementType("ITEM_SPEC_REF");
  IElementType ITEM_SPEC_SIGNATURE = new MvElementType("ITEM_SPEC_SIGNATURE");
  IElementType ITEM_SPEC_TYPE_PARAMETER = new MvElementType("ITEM_SPEC_TYPE_PARAMETER");
  IElementType ITEM_SPEC_TYPE_PARAMETER_LIST = new MvElementType("ITEM_SPEC_TYPE_PARAMETER_LIST");
  IElementType LABELED_BLOCK_EXPR = new MvElementType("LABELED_BLOCK_EXPR");
  IElementType LABEL_DECL = new MvElementType("LABEL_DECL");
  IElementType LABEL_REF = new MvElementType("LABEL_REF");
  IElementType LAMBDA_EXPR = new MvElementType("LAMBDA_EXPR");
  IElementType LAMBDA_EXPR_BLOCK = new MvElementType("LAMBDA_EXPR_BLOCK");
  IElementType LAMBDA_PARAM = new MvElementType("LAMBDA_PARAM");
  IElementType LAMBDA_TYPE = new MvElementType("LAMBDA_TYPE");
  IElementType LAMBDA_TYPE_PARAM = new MvElementType("LAMBDA_TYPE_PARAM");
  IElementType LET_STMT = new MvElementType("LET_STMT");
  IElementType LIT_EXPR = new MvElementType("LIT_EXPR");
  IElementType LOOP_EXPR = new MvElementType("LOOP_EXPR");
  IElementType MACRO_IDENT = new MvElementType("MACRO_IDENT");
  IElementType MATCH_ARGUMENT = new MvElementType("MATCH_ARGUMENT");
  IElementType MATCH_ARM = new MvElementType("MATCH_ARM");
  IElementType MATCH_ARM_GUARD = new MvElementType("MATCH_ARM_GUARD");
  IElementType MATCH_BODY = new MvElementType("MATCH_BODY");
  IElementType MATCH_EXPR = new MvElementType("MATCH_EXPR");
  IElementType MATCH_PAT = new MvElementType("MATCH_PAT");
  IElementType METHOD_CALL = new MvElementType("METHOD_CALL");
  IElementType MODIFIES_SPEC_EXPR = new MvElementType("MODIFIES_SPEC_EXPR");
  IElementType MODULE = StubsKt.factory("MODULE");
  IElementType MODULE_BLOCK = new MvElementType("MODULE_BLOCK");
  IElementType MODULE_ITEM_SPEC = new MvElementType("MODULE_ITEM_SPEC");
  IElementType MODULE_SPEC = StubsKt.factory("MODULE_SPEC");
  IElementType MODULE_SPEC_BLOCK = new MvElementType("MODULE_SPEC_BLOCK");
  IElementType MOVE_EXPR = new MvElementType("MOVE_EXPR");
  IElementType NAMED_ADDRESS = new MvElementType("NAMED_ADDRESS");
  IElementType NAMED_ADDRESS_DEF = new MvElementType("NAMED_ADDRESS_DEF");
  IElementType NAMED_FIELD_DECL = new MvElementType("NAMED_FIELD_DECL");
  IElementType PARENS_EXPR = new MvElementType("PARENS_EXPR");
  IElementType PARENS_TYPE = new MvElementType("PARENS_TYPE");
  IElementType PAT = new MvElementType("PAT");
  IElementType PATH = new MvElementType("PATH");
  IElementType PATH_ADDRESS = new MvElementType("PATH_ADDRESS");
  IElementType PATH_EXPR = new MvElementType("PATH_EXPR");
  IElementType PATH_PAT = new MvElementType("PATH_PAT");
  IElementType PATH_TYPE = new MvElementType("PATH_TYPE");
  IElementType PAT_BINDING = new MvElementType("PAT_BINDING");
  IElementType PAT_CONST = new MvElementType("PAT_CONST");
  IElementType PAT_FIELD = new MvElementType("PAT_FIELD");
  IElementType PAT_FIELD_FULL = new MvElementType("PAT_FIELD_FULL");
  IElementType PAT_IDENT = new MvElementType("PAT_IDENT");
  IElementType PAT_REST = new MvElementType("PAT_REST");
  IElementType PAT_STRUCT = new MvElementType("PAT_STRUCT");
  IElementType PAT_TUPLE = new MvElementType("PAT_TUPLE");
  IElementType PAT_WILD = new MvElementType("PAT_WILD");
  IElementType PRAGMA_ATTRIBUTE = new MvElementType("PRAGMA_ATTRIBUTE");
  IElementType PRAGMA_SPEC_STMT = new MvElementType("PRAGMA_SPEC_STMT");
  IElementType PUBLIC_USE_FUN = new MvElementType("PUBLIC_USE_FUN");
  IElementType QUAL_PATH_CODE_FRAGMENT_ELEMENT = new MvElementType("QUAL_PATH_CODE_FRAGMENT_ELEMENT");
  IElementType QUANT_BINDING = new MvElementType("QUANT_BINDING");
  IElementType QUANT_BINDINGS = new MvElementType("QUANT_BINDINGS");
  IElementType QUANT_WHERE = new MvElementType("QUANT_WHERE");
  IElementType RANGE_EXPR = new MvElementType("RANGE_EXPR");
  IElementType RANGE_QUANT_BINDING = new MvElementType("RANGE_QUANT_BINDING");
  IElementType REF_EXPR = new MvElementType("REF_EXPR");
  IElementType REF_TYPE = new MvElementType("REF_TYPE");
  IElementType REF_TYPE_START = new MvElementType("REF_TYPE_START");
  IElementType REQUIRES_SPEC_EXPR = new MvElementType("REQUIRES_SPEC_EXPR");
  IElementType RESOURCE_ACCESS_ITEM = new MvElementType("RESOURCE_ACCESS_ITEM");
  IElementType RESOURCE_ACCESS_ITEM_LIST = new MvElementType("RESOURCE_ACCESS_ITEM_LIST");
  IElementType RETURN_EXPR = new MvElementType("RETURN_EXPR");
  IElementType RETURN_TYPE = new MvElementType("RETURN_TYPE");
  IElementType SCHEMA = StubsKt.factory("SCHEMA");
  IElementType SCHEMA_FIELDS_BLOCK = new MvElementType("SCHEMA_FIELDS_BLOCK");
  IElementType SCHEMA_FIELD_STMT = new MvElementType("SCHEMA_FIELD_STMT");
  IElementType SCHEMA_INCLUDE_ITEM = new MvElementType("SCHEMA_INCLUDE_ITEM");
  IElementType SCHEMA_LIT = new MvElementType("SCHEMA_LIT");
  IElementType SCHEMA_LIT_FIELD = new MvElementType("SCHEMA_LIT_FIELD");
  IElementType SCHEMA_REF = new MvElementType("SCHEMA_REF");
  IElementType SCRIPT = new MvElementType("SCRIPT");
  IElementType SCRIPT_BLOCK = new MvElementType("SCRIPT_BLOCK");
  IElementType SPEC_CODE_BLOCK = new MvElementType("SPEC_CODE_BLOCK");
  IElementType SPEC_EXPR_STMT = new MvElementType("SPEC_EXPR_STMT");
  IElementType SPEC_FUNCTION = StubsKt.factory("SPEC_FUNCTION");
  IElementType SPEC_INLINE_FUNCTION = new MvElementType("SPEC_INLINE_FUNCTION");
  IElementType SPEC_INLINE_FUNCTION_STMT = new MvElementType("SPEC_INLINE_FUNCTION_STMT");
  IElementType SPEC_PROPERTY = new MvElementType("SPEC_PROPERTY");
  IElementType SPEC_PROPERTY_LIST = new MvElementType("SPEC_PROPERTY_LIST");
  IElementType STMT = new MvElementType("STMT");
  IElementType STRUCT = StubsKt.factory("STRUCT");
  IElementType STRUCT_DOT_FIELD = new MvElementType("STRUCT_DOT_FIELD");
  IElementType STRUCT_LIT_EXPR = new MvElementType("STRUCT_LIT_EXPR");
  IElementType STRUCT_LIT_FIELD = new MvElementType("STRUCT_LIT_FIELD");
  IElementType STRUCT_LIT_FIELDS_BLOCK = new MvElementType("STRUCT_LIT_FIELDS_BLOCK");
  IElementType STRUCT_PAT = new MvElementType("STRUCT_PAT");
  IElementType TUPLE_FIELDS = new MvElementType("TUPLE_FIELDS");
  IElementType TUPLE_FIELD_DECL = new MvElementType("TUPLE_FIELD_DECL");
  IElementType TUPLE_LIT_EXPR = new MvElementType("TUPLE_LIT_EXPR");
  IElementType TUPLE_PAT = new MvElementType("TUPLE_PAT");
  IElementType TUPLE_TYPE = new MvElementType("TUPLE_TYPE");
  IElementType TYPE = new MvElementType("TYPE");
  IElementType TYPE_ANNOTATION = new MvElementType("TYPE_ANNOTATION");
  IElementType TYPE_ARGUMENT = new MvElementType("TYPE_ARGUMENT");
  IElementType TYPE_ARGUMENT_LIST = new MvElementType("TYPE_ARGUMENT_LIST");
  IElementType TYPE_PARAMETER = new MvElementType("TYPE_PARAMETER");
  IElementType TYPE_PARAMETER_LIST = new MvElementType("TYPE_PARAMETER_LIST");
  IElementType TYPE_PARAM_BOUND = new MvElementType("TYPE_PARAM_BOUND");
  IElementType TYPE_QUANT_BINDING = new MvElementType("TYPE_QUANT_BINDING");
  IElementType UNIT_LIT_EXPR = new MvElementType("UNIT_LIT_EXPR");
  IElementType UNIT_TYPE = new MvElementType("UNIT_TYPE");
  IElementType UPDATE_SPEC_STMT = new MvElementType("UPDATE_SPEC_STMT");
  IElementType USE_ALIAS = new MvElementType("USE_ALIAS");
  IElementType USE_FUN_ALIAS = new MvElementType("USE_FUN_ALIAS");
  IElementType USE_FUN_METHOD_ALIAS = new MvElementType("USE_FUN_METHOD_ALIAS");
  IElementType USE_GROUP = new MvElementType("USE_GROUP");
  IElementType USE_SPECK = new MvElementType("USE_SPECK");
  IElementType USE_STMT = new MvElementType("USE_STMT");
  IElementType VALUE_ARGUMENT = new MvElementType("VALUE_ARGUMENT");
  IElementType VALUE_ARGUMENT_LIST = new MvElementType("VALUE_ARGUMENT_LIST");
  IElementType VECTOR_LIT_EXPR = new MvElementType("VECTOR_LIT_EXPR");
  IElementType VECTOR_LIT_ITEMS = new MvElementType("VECTOR_LIT_ITEMS");
  IElementType VISIBILITY_MODIFIER = new MvElementType("VISIBILITY_MODIFIER");
  IElementType WHILE_EXPR = new MvElementType("WHILE_EXPR");
  IElementType WILD_PAT = new MvElementType("WILD_PAT");
  IElementType WILD_TYPE = new MvElementType("WILD_TYPE");

  IElementType ABORT = new MvTokenType("abort");
  IElementType ABORTS_IF = new MvTokenType("aborts_if_kw");
  IElementType ABORTS_WITH = new MvTokenType("aborts_with_kw");
  IElementType ACQUIRES = new MvTokenType("acquires");
  IElementType ADDRESS = new MvTokenType("address_kw");
  IElementType AND = new MvTokenType("&");
  IElementType AND_AND = new MvTokenType("&&");
  IElementType APPLY = new MvTokenType("apply_kw");
  IElementType AS = new MvTokenType("as");
  IElementType ASSERT = new MvTokenType("assert_kw");
  IElementType ASSUME = new MvTokenType("assume_kw");
  IElementType AT = new MvTokenType("@");
  IElementType AXIOM = new MvTokenType("axiom_kw");
  IElementType BACKTICK = new MvTokenType("`");
  IElementType BECH32_ADDRESS = new MvTokenType("BECH32_ADDRESS");
  IElementType BOOL_LITERAL = new MvTokenType("BOOL_LITERAL");
  IElementType BREAK = new MvTokenType("break");
  IElementType BYTE_STRING_LITERAL = new MvTokenType("BYTE_STRING_LITERAL");
  IElementType CHOOSE = new MvTokenType("choose_kw");
  IElementType COLON = new MvTokenType(":");
  IElementType COLON_COLON = new MvTokenType("::");
  IElementType COMMA = new MvTokenType(",");
  IElementType CONST_KW = new MvTokenType("const_kw");
  IElementType CONTINUE = new MvTokenType("continue");
  IElementType COPY = new MvTokenType("copy");
  IElementType DECREASES = new MvTokenType("decreases_kw");
  IElementType DIV = new MvTokenType("/");
  IElementType DOT = new MvTokenType(".");
  IElementType DOT_DOT = new MvTokenType("..");
  IElementType ELSE = new MvTokenType("else");
  IElementType EMITS = new MvTokenType("emits_kw");
  IElementType ENSURES = new MvTokenType("ensures_kw");
  IElementType ENTRY = new MvTokenType("entry_kw");
  IElementType ENUM_KW = new MvTokenType("enum_kw");
  IElementType EQ = new MvTokenType("=");
  IElementType EQ_EQ = new MvTokenType("==");
  IElementType EQ_EQ_GT = new MvTokenType("==>");
  IElementType EXCEPT = new MvTokenType("except_kw");
  IElementType EXCL = new MvTokenType("!");
  IElementType EXISTS = new MvTokenType("exists_kw");
  IElementType FAT_ARROW = new MvTokenType("=>");
  IElementType FOR = new MvTokenType("for_kw");
  IElementType FORALL = new MvTokenType("forall_kw");
  IElementType FRIEND = new MvTokenType("friend_kw");
  IElementType FUN = new MvTokenType("fun");
  IElementType FUNCTION_PATTERN_IDENT = new MvTokenType("FUNCTION_PATTERN_IDENT");
  IElementType GLOBAL = new MvTokenType("global_kw");
  IElementType GT = new MvTokenType(">");
  IElementType GT_EQ = new MvTokenType(">=");
  IElementType GT_GT = new MvTokenType(">>");
  IElementType HAS = new MvTokenType("has_kw");
  IElementType HASH = new MvTokenType("#");
  IElementType HEX_INTEGER_LITERAL = new MvTokenType("HEX_INTEGER_LITERAL");
  IElementType HEX_STRING_LITERAL = new MvTokenType("HEX_STRING_LITERAL");
  IElementType IDENTIFIER = new MvTokenType("IDENTIFIER");
  IElementType IF = new MvTokenType("if");
  IElementType IN = new MvTokenType("in_kw");
  IElementType INCLUDE = new MvTokenType("include_kw");
  IElementType INLINE = new MvTokenType("inline_kw");
  IElementType INTEGER_LITERAL = new MvTokenType("INTEGER_LITERAL");
  IElementType INTERNAL = new MvTokenType("internal_kw");
  IElementType INVARIANT = new MvTokenType("invariant_kw");
  IElementType LET = new MvTokenType("let");
  IElementType LOCAL = new MvTokenType("local_kw");
  IElementType LOOP = new MvTokenType("loop");
  IElementType LT = new MvTokenType("<");
  IElementType LT_EQ = new MvTokenType("<=");
  IElementType LT_EQ_EQ_GT = new MvTokenType("<==>");
  IElementType LT_LT = new MvTokenType("<<");
  IElementType L_BRACE = new MvTokenType("{");
  IElementType L_BRACK = new MvTokenType("[");
  IElementType L_PAREN = new MvTokenType("(");
  IElementType MACRO_KW = new MvTokenType("macro_kw");
  IElementType MARCO_IDENTIFIER = new MvTokenType("MARCO_IDENTIFIER");
  IElementType MATCH_KW = new MvTokenType("match_kw");
  IElementType MIN = new MvTokenType("min_kw");
  IElementType MINUS = new MvTokenType("-");
  IElementType MODIFIES = new MvTokenType("modifies_kw");
  IElementType MODULE_KW = new MvTokenType("module");
  IElementType MODULO = new MvTokenType("%");
  IElementType MOVE = new MvTokenType("move");
  IElementType MUL = new MvTokenType("*");
  IElementType MUT = new MvTokenType("mut");
  IElementType NATIVE = new MvTokenType("native");
  IElementType NOT_EQ = new MvTokenType("!=");
  IElementType OR = new MvTokenType("|");
  IElementType OR_OR = new MvTokenType("||");
  IElementType PACK = new MvTokenType("pack_kw");
  IElementType PACKAGE = new MvTokenType("package_kw");
  IElementType PHANTOM = new MvTokenType("phantom");
  IElementType PLACEHOLDER_ADDRESS = new MvTokenType("PLACEHOLDER_ADDRESS");
  IElementType PLUS = new MvTokenType("+");
  IElementType POLKADOT_ADDRESS = new MvTokenType("POLKADOT_ADDRESS");
  IElementType POST = new MvTokenType("post_kw");
  IElementType PRAGMA = new MvTokenType("pragma_kw");
  IElementType PUBLIC = new MvTokenType("public");
  IElementType PURE = new MvTokenType("pure_kw");
  IElementType QUOTE_IDENTIFIER = new MvTokenType("QUOTE_IDENTIFIER");
  IElementType READS = new MvTokenType("reads_kw");
  IElementType REQUIRES = new MvTokenType("requires_kw");
  IElementType RETURN = new MvTokenType("return");
  IElementType R_BRACE = new MvTokenType("}");
  IElementType R_BRACK = new MvTokenType("]");
  IElementType R_PAREN = new MvTokenType(")");
  IElementType SCHEMA_KW = new MvTokenType("schema_kw");
  IElementType SCRIPT_KW = new MvTokenType("script_kw");
  IElementType SEMICOLON = new MvTokenType(";");
  IElementType SPEC = new MvTokenType("spec");
  IElementType STRUCT_KW = new MvTokenType("struct_kw");
  IElementType SUI_ADDRESS = new MvTokenType("SUI_ADDRESS");
  IElementType TO = new MvTokenType("to_kw");
  IElementType TYPE_KW = new MvTokenType("type_kw");
  IElementType UNPACK = new MvTokenType("unpack_kw");
  IElementType UPDATE = new MvTokenType("update_kw");
  IElementType USE = new MvTokenType("use");
  IElementType WHERE = new MvTokenType("where_kw");
  IElementType WHILE = new MvTokenType("while");
  IElementType WITH = new MvTokenType("with_kw");
  IElementType WRITES = new MvTokenType("writes_kw");
  IElementType XOR = new MvTokenType("^");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ABILITIES_LIST) {
        return new MvAbilitiesListImpl(node);
      }
      else if (type == ABILITY) {
        return new MvAbilityImpl(node);
      }
      else if (type == ABORTS_IF_SPEC_EXPR) {
        return new MvAbortsIfSpecExprImpl(node);
      }
      else if (type == ABORTS_IF_WITH) {
        return new MvAbortsIfWithImpl(node);
      }
      else if (type == ABORTS_WITH_SPEC_EXPR) {
        return new MvAbortsWithSpecExprImpl(node);
      }
      else if (type == ABORT_EXPR) {
        return new MvAbortExprImpl(node);
      }
      else if (type == ACCESS_SPECIFIER) {
        return new MvAccessSpecifierImpl(node);
      }
      else if (type == ACCESS_SPECIFIER_LIST) {
        return new MvAccessSpecifierListImpl(node);
      }
      else if (type == ACQUIRES_TYPE) {
        return new MvAcquiresTypeImpl(node);
      }
      else if (type == ADDRESS_BLOCK) {
        return new MvAddressBlockImpl(node);
      }
      else if (type == ADDRESS_DEF) {
        return new MvAddressDefImpl(node);
      }
      else if (type == ADDRESS_LIT) {
        return new MvAddressLitImpl(node);
      }
      else if (type == ADDRESS_REF) {
        return new MvAddressRefImpl(node);
      }
      else if (type == ADDRESS_SPECIFIER) {
        return new MvAddressSpecifierImpl(node);
      }
      else if (type == ADDRESS_SPECIFIER_ARG) {
        return new MvAddressSpecifierArgImpl(node);
      }
      else if (type == ADDRESS_SPECIFIER_CALL_PARAM) {
        return new MvAddressSpecifierCallParamImpl(node);
      }
      else if (type == ADDRESS_SPECIFIER_LIT) {
        return new MvAddressSpecifierLitImpl(node);
      }
      else if (type == AND_INCLUDE_ITEM) {
        return new MvAndIncludeItemImpl(node);
      }
      else if (type == ANNOTATED_EXPR) {
        return new MvAnnotatedExprImpl(node);
      }
      else if (type == APPLY_EXCEPT) {
        return new MvApplyExceptImpl(node);
      }
      else if (type == APPLY_SCHEMA_NAME_ATTRIBUTE) {
        return new MvApplySchemaNameAttributeImpl(node);
      }
      else if (type == APPLY_SCHEMA_STMT) {
        return new MvApplySchemaStmtImpl(node);
      }
      else if (type == APPLY_TO) {
        return new MvApplyToImpl(node);
      }
      else if (type == ASSERT_BANG_EXPR) {
        return new MvAssertBangExprImpl(node);
      }
      else if (type == ASSERT_MACRO_EXPR) {
        return new MvAssertMacroExprImpl(node);
      }
      else if (type == ASSERT_SPEC_EXPR) {
        return new MvAssertSpecExprImpl(node);
      }
      else if (type == ASSIGNMENT_EXPR) {
        return new MvAssignmentExprImpl(node);
      }
      else if (type == ASSUME_SPEC_EXPR) {
        return new MvAssumeSpecExprImpl(node);
      }
      else if (type == ATTR) {
        return new MvAttrImpl(node);
      }
      else if (type == ATTR_ITEM) {
        return new MvAttrItemImpl(node);
      }
      else if (type == ATTR_ITEM_INITIALIZER) {
        return new MvAttrItemInitializerImpl(node);
      }
      else if (type == ATTR_ITEM_LIST) {
        return new MvAttrItemListImpl(node);
      }
      else if (type == AXIOM_SPEC_EXPR) {
        return new MvAxiomSpecExprImpl(node);
      }
      else if (type == BANG_EXPR) {
        return new MvBangExprImpl(node);
      }
      else if (type == BINARY_EXPR) {
        return new MvBinaryExprImpl(node);
      }
      else if (type == BINARY_OP) {
        return new MvBinaryOpImpl(node);
      }
      else if (type == BLOCK_FIELDS) {
        return new MvBlockFieldsImpl(node);
      }
      else if (type == BORROW_EXPR) {
        return new MvBorrowExprImpl(node);
      }
      else if (type == BREAK_EXPR) {
        return new MvBreakExprImpl(node);
      }
      else if (type == CALL_EXPR) {
        return new MvCallExprImpl(node);
      }
      else if (type == CAST_EXPR) {
        return new MvCastExprImpl(node);
      }
      else if (type == CHOOSE_QUANT_EXPR) {
        return new MvChooseQuantExprImpl(node);
      }
      else if (type == CODE_BLOCK) {
        return new MvCodeBlockImpl(node);
      }
      else if (type == CODE_BLOCK_EXPR) {
        return new MvCodeBlockExprImpl(node);
      }
      else if (type == CONDITION) {
        return new MvConditionImpl(node);
      }
      else if (type == CONST) {
        return new MvConstImpl(node);
      }
      else if (type == CONST_PAT) {
        return new MvConstPatImpl(node);
      }
      else if (type == CONTINUE_EXPR) {
        return new MvContinueExprImpl(node);
      }
      else if (type == COPY_EXPR) {
        return new MvCopyExprImpl(node);
      }
      else if (type == DECREASES_SPEC_EXPR) {
        return new MvDecreasesSpecExprImpl(node);
      }
      else if (type == DEREF_EXPR) {
        return new MvDerefExprImpl(node);
      }
      else if (type == DOT_EXPR) {
        return new MvDotExprImpl(node);
      }
      else if (type == ELSE_BLOCK) {
        return new MvElseBlockImpl(node);
      }
      else if (type == EMITS_CONDITION) {
        return new MvEmitsConditionImpl(node);
      }
      else if (type == EMITS_SPEC_EXPR) {
        return new MvEmitsSpecExprImpl(node);
      }
      else if (type == ENSURES_SPEC_EXPR) {
        return new MvEnsuresSpecExprImpl(node);
      }
      else if (type == ENUM) {
        return new MvEnumImpl(node);
      }
      else if (type == ENUM_BODY) {
        return new MvEnumBodyImpl(node);
      }
      else if (type == ENUM_VARIANT) {
        return new MvEnumVariantImpl(node);
      }
      else if (type == ENUM_VARIANT_PAT) {
        return new MvEnumVariantPatImpl(node);
      }
      else if (type == EXISTS_QUANT_EXPR) {
        return new MvExistsQuantExprImpl(node);
      }
      else if (type == EXPR_STMT) {
        return new MvExprStmtImpl(node);
      }
      else if (type == FIELD_PAT) {
        return new MvFieldPatImpl(node);
      }
      else if (type == FIELD_PAT_BINDING) {
        return new MvFieldPatBindingImpl(node);
      }
      else if (type == FIELD_PAT_FULL) {
        return new MvFieldPatFullImpl(node);
      }
      else if (type == FORALL_QUANT_EXPR) {
        return new MvForallQuantExprImpl(node);
      }
      else if (type == FOR_EXPR) {
        return new MvForExprImpl(node);
      }
      else if (type == FOR_ITER_CONDITION) {
        return new MvForIterConditionImpl(node);
      }
      else if (type == FRIEND_DECL) {
        return new MvFriendDeclImpl(node);
      }
      else if (type == FUNCTION) {
        return new MvFunctionImpl(node);
      }
      else if (type == FUNCTION_PARAMETER) {
        return new MvFunctionParameterImpl(node);
      }
      else if (type == FUNCTION_PARAMETER_LIST) {
        return new MvFunctionParameterListImpl(node);
      }
      else if (type == FUNCTION_PATTERN) {
        return new MvFunctionPatternImpl(node);
      }
      else if (type == GLOBAL_VARIABLE_STMT) {
        return new MvGlobalVariableStmtImpl(node);
      }
      else if (type == IF_ELSE_INCLUDE_ITEM) {
        return new MvIfElseIncludeItemImpl(node);
      }
      else if (type == IF_EXPR) {
        return new MvIfExprImpl(node);
      }
      else if (type == IMPLY_INCLUDE_ITEM) {
        return new MvImplyIncludeItemImpl(node);
      }
      else if (type == INCLUDE_STMT) {
        return new MvIncludeStmtImpl(node);
      }
      else if (type == INDEX_EXPR) {
        return new MvIndexExprImpl(node);
      }
      else if (type == INITIALIZER) {
        return new MvInitializerImpl(node);
      }
      else if (type == INLINE_BLOCK) {
        return new MvInlineBlockImpl(node);
      }
      else if (type == INVARIANT_SPEC_EXPR) {
        return new MvInvariantSpecExprImpl(node);
      }
      else if (type == ITEM_SPEC) {
        return new MvItemSpecImpl(node);
      }
      else if (type == ITEM_SPEC_BLOCK_EXPR) {
        return new MvItemSpecBlockExprImpl(node);
      }
      else if (type == ITEM_SPEC_FUNCTION_PARAMETER) {
        return new MvItemSpecFunctionParameterImpl(node);
      }
      else if (type == ITEM_SPEC_FUNCTION_PARAMETER_LIST) {
        return new MvItemSpecFunctionParameterListImpl(node);
      }
      else if (type == ITEM_SPEC_REF) {
        return new MvItemSpecRefImpl(node);
      }
      else if (type == ITEM_SPEC_SIGNATURE) {
        return new MvItemSpecSignatureImpl(node);
      }
      else if (type == ITEM_SPEC_TYPE_PARAMETER) {
        return new MvItemSpecTypeParameterImpl(node);
      }
      else if (type == ITEM_SPEC_TYPE_PARAMETER_LIST) {
        return new MvItemSpecTypeParameterListImpl(node);
      }
      else if (type == LABELED_BLOCK_EXPR) {
        return new MvLabeledBlockExprImpl(node);
      }
      else if (type == LABEL_DECL) {
        return new MvLabelDeclImpl(node);
      }
      else if (type == LABEL_REF) {
        return new MvLabelRefImpl(node);
      }
      else if (type == LAMBDA_EXPR) {
        return new MvLambdaExprImpl(node);
      }
      else if (type == LAMBDA_EXPR_BLOCK) {
        return new MvLambdaExprBlockImpl(node);
      }
      else if (type == LAMBDA_PARAM) {
        return new MvLambdaParamImpl(node);
      }
      else if (type == LAMBDA_TYPE) {
        return new MvLambdaTypeImpl(node);
      }
      else if (type == LAMBDA_TYPE_PARAM) {
        return new MvLambdaTypeParamImpl(node);
      }
      else if (type == LET_STMT) {
        return new MvLetStmtImpl(node);
      }
      else if (type == LIT_EXPR) {
        return new MvLitExprImpl(node);
      }
      else if (type == LOOP_EXPR) {
        return new MvLoopExprImpl(node);
      }
      else if (type == MACRO_IDENT) {
        return new MvMacroIdentImpl(node);
      }
      else if (type == MATCH_ARGUMENT) {
        return new MvMatchArgumentImpl(node);
      }
      else if (type == MATCH_ARM) {
        return new MvMatchArmImpl(node);
      }
      else if (type == MATCH_ARM_GUARD) {
        return new MvMatchArmGuardImpl(node);
      }
      else if (type == MATCH_BODY) {
        return new MvMatchBodyImpl(node);
      }
      else if (type == MATCH_EXPR) {
        return new MvMatchExprImpl(node);
      }
      else if (type == MATCH_PAT) {
        return new MvMatchPatImpl(node);
      }
      else if (type == METHOD_CALL) {
        return new MvMethodCallImpl(node);
      }
      else if (type == MODIFIES_SPEC_EXPR) {
        return new MvModifiesSpecExprImpl(node);
      }
      else if (type == MODULE) {
        return new MvModuleImpl(node);
      }
      else if (type == MODULE_BLOCK) {
        return new MvModuleBlockImpl(node);
      }
      else if (type == MODULE_ITEM_SPEC) {
        return new MvModuleItemSpecImpl(node);
      }
      else if (type == MODULE_SPEC) {
        return new MvModuleSpecImpl(node);
      }
      else if (type == MODULE_SPEC_BLOCK) {
        return new MvModuleSpecBlockImpl(node);
      }
      else if (type == MOVE_EXPR) {
        return new MvMoveExprImpl(node);
      }
      else if (type == NAMED_ADDRESS) {
        return new MvNamedAddressImpl(node);
      }
      else if (type == NAMED_ADDRESS_DEF) {
        return new MvNamedAddressDefImpl(node);
      }
      else if (type == NAMED_FIELD_DECL) {
        return new MvNamedFieldDeclImpl(node);
      }
      else if (type == PARENS_EXPR) {
        return new MvParensExprImpl(node);
      }
      else if (type == PARENS_TYPE) {
        return new MvParensTypeImpl(node);
      }
      else if (type == PATH) {
        return new MvPathImpl(node);
      }
      else if (type == PATH_ADDRESS) {
        return new MvPathAddressImpl(node);
      }
      else if (type == PATH_EXPR) {
        return new MvPathExprImpl(node);
      }
      else if (type == PATH_PAT) {
        return new MvPathPatImpl(node);
      }
      else if (type == PATH_TYPE) {
        return new MvPathTypeImpl(node);
      }
      else if (type == PAT_BINDING) {
        return new MvPatBindingImpl(node);
      }
      else if (type == PAT_CONST) {
        return new MvPatConstImpl(node);
      }
      else if (type == PAT_FIELD) {
        return new MvPatFieldImpl(node);
      }
      else if (type == PAT_FIELD_FULL) {
        return new MvPatFieldFullImpl(node);
      }
      else if (type == PAT_IDENT) {
        return new MvPatIdentImpl(node);
      }
      else if (type == PAT_REST) {
        return new MvPatRestImpl(node);
      }
      else if (type == PAT_STRUCT) {
        return new MvPatStructImpl(node);
      }
      else if (type == PAT_TUPLE) {
        return new MvPatTupleImpl(node);
      }
      else if (type == PAT_WILD) {
        return new MvPatWildImpl(node);
      }
      else if (type == PRAGMA_ATTRIBUTE) {
        return new MvPragmaAttributeImpl(node);
      }
      else if (type == PRAGMA_SPEC_STMT) {
        return new MvPragmaSpecStmtImpl(node);
      }
      else if (type == PUBLIC_USE_FUN) {
        return new MvPublicUseFunImpl(node);
      }
      else if (type == QUAL_PATH_CODE_FRAGMENT_ELEMENT) {
        return new MvQualPathCodeFragmentElementImpl(node);
      }
      else if (type == QUANT_BINDINGS) {
        return new MvQuantBindingsImpl(node);
      }
      else if (type == QUANT_WHERE) {
        return new MvQuantWhereImpl(node);
      }
      else if (type == RANGE_EXPR) {
        return new MvRangeExprImpl(node);
      }
      else if (type == RANGE_QUANT_BINDING) {
        return new MvRangeQuantBindingImpl(node);
      }
      else if (type == REF_EXPR) {
        return new MvRefExprImpl(node);
      }
      else if (type == REF_TYPE) {
        return new MvRefTypeImpl(node);
      }
      else if (type == REF_TYPE_START) {
        return new MvRefTypeStartImpl(node);
      }
      else if (type == REQUIRES_SPEC_EXPR) {
        return new MvRequiresSpecExprImpl(node);
      }
      else if (type == RESOURCE_ACCESS_ITEM) {
        return new MvResourceAccessItemImpl(node);
      }
      else if (type == RESOURCE_ACCESS_ITEM_LIST) {
        return new MvResourceAccessItemListImpl(node);
      }
      else if (type == RETURN_EXPR) {
        return new MvReturnExprImpl(node);
      }
      else if (type == RETURN_TYPE) {
        return new MvReturnTypeImpl(node);
      }
      else if (type == SCHEMA) {
        return new MvSchemaImpl(node);
      }
      else if (type == SCHEMA_FIELDS_BLOCK) {
        return new MvSchemaFieldsBlockImpl(node);
      }
      else if (type == SCHEMA_FIELD_STMT) {
        return new MvSchemaFieldStmtImpl(node);
      }
      else if (type == SCHEMA_INCLUDE_ITEM) {
        return new MvSchemaIncludeItemImpl(node);
      }
      else if (type == SCHEMA_LIT) {
        return new MvSchemaLitImpl(node);
      }
      else if (type == SCHEMA_LIT_FIELD) {
        return new MvSchemaLitFieldImpl(node);
      }
      else if (type == SCHEMA_REF) {
        return new MvSchemaRefImpl(node);
      }
      else if (type == SCRIPT) {
        return new MvScriptImpl(node);
      }
      else if (type == SCRIPT_BLOCK) {
        return new MvScriptBlockImpl(node);
      }
      else if (type == SPEC_CODE_BLOCK) {
        return new MvSpecCodeBlockImpl(node);
      }
      else if (type == SPEC_EXPR_STMT) {
        return new MvSpecExprStmtImpl(node);
      }
      else if (type == SPEC_FUNCTION) {
        return new MvSpecFunctionImpl(node);
      }
      else if (type == SPEC_INLINE_FUNCTION) {
        return new MvSpecInlineFunctionImpl(node);
      }
      else if (type == SPEC_INLINE_FUNCTION_STMT) {
        return new MvSpecInlineFunctionStmtImpl(node);
      }
      else if (type == SPEC_PROPERTY) {
        return new MvSpecPropertyImpl(node);
      }
      else if (type == SPEC_PROPERTY_LIST) {
        return new MvSpecPropertyListImpl(node);
      }
      else if (type == STRUCT) {
        return new MvStructImpl(node);
      }
      else if (type == STRUCT_DOT_FIELD) {
        return new MvStructDotFieldImpl(node);
      }
      else if (type == STRUCT_LIT_EXPR) {
        return new MvStructLitExprImpl(node);
      }
      else if (type == STRUCT_LIT_FIELD) {
        return new MvStructLitFieldImpl(node);
      }
      else if (type == STRUCT_LIT_FIELDS_BLOCK) {
        return new MvStructLitFieldsBlockImpl(node);
      }
      else if (type == STRUCT_PAT) {
        return new MvStructPatImpl(node);
      }
      else if (type == TUPLE_FIELDS) {
        return new MvTupleFieldsImpl(node);
      }
      else if (type == TUPLE_FIELD_DECL) {
        return new MvTupleFieldDeclImpl(node);
      }
      else if (type == TUPLE_LIT_EXPR) {
        return new MvTupleLitExprImpl(node);
      }
      else if (type == TUPLE_PAT) {
        return new MvTuplePatImpl(node);
      }
      else if (type == TUPLE_TYPE) {
        return new MvTupleTypeImpl(node);
      }
      else if (type == TYPE_ANNOTATION) {
        return new MvTypeAnnotationImpl(node);
      }
      else if (type == TYPE_ARGUMENT) {
        return new MvTypeArgumentImpl(node);
      }
      else if (type == TYPE_ARGUMENT_LIST) {
        return new MvTypeArgumentListImpl(node);
      }
      else if (type == TYPE_PARAMETER) {
        return new MvTypeParameterImpl(node);
      }
      else if (type == TYPE_PARAMETER_LIST) {
        return new MvTypeParameterListImpl(node);
      }
      else if (type == TYPE_PARAM_BOUND) {
        return new MvTypeParamBoundImpl(node);
      }
      else if (type == TYPE_QUANT_BINDING) {
        return new MvTypeQuantBindingImpl(node);
      }
      else if (type == UNIT_LIT_EXPR) {
        return new MvUnitLitExprImpl(node);
      }
      else if (type == UNIT_TYPE) {
        return new MvUnitTypeImpl(node);
      }
      else if (type == UPDATE_SPEC_STMT) {
        return new MvUpdateSpecStmtImpl(node);
      }
      else if (type == USE_ALIAS) {
        return new MvUseAliasImpl(node);
      }
      else if (type == USE_FUN_ALIAS) {
        return new MvUseFunAliasImpl(node);
      }
      else if (type == USE_FUN_METHOD_ALIAS) {
        return new MvUseFunMethodAliasImpl(node);
      }
      else if (type == USE_GROUP) {
        return new MvUseGroupImpl(node);
      }
      else if (type == USE_SPECK) {
        return new MvUseSpeckImpl(node);
      }
      else if (type == USE_STMT) {
        return new MvUseStmtImpl(node);
      }
      else if (type == VALUE_ARGUMENT) {
        return new MvValueArgumentImpl(node);
      }
      else if (type == VALUE_ARGUMENT_LIST) {
        return new MvValueArgumentListImpl(node);
      }
      else if (type == VECTOR_LIT_EXPR) {
        return new MvVectorLitExprImpl(node);
      }
      else if (type == VECTOR_LIT_ITEMS) {
        return new MvVectorLitItemsImpl(node);
      }
      else if (type == VISIBILITY_MODIFIER) {
        return new MvVisibilityModifierImpl(node);
      }
      else if (type == WHILE_EXPR) {
        return new MvWhileExprImpl(node);
      }
      else if (type == WILD_PAT) {
        return new MvWildPatImpl(node);
      }
      else if (type == WILD_TYPE) {
        return new MvWildTypeImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
