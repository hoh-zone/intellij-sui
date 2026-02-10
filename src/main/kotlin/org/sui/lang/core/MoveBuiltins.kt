package org.sui.lang.core

val INTEGER_TYPE_IDENTIFIERS = setOf("u8", "u16", "u32", "u64", "u128", "u256")
val SPEC_INTEGER_TYPE_IDENTIFIERS = INTEGER_TYPE_IDENTIFIERS + setOf("num")
val SPEC_ONLY_PRIMITIVE_TYPES = setOf("num")
val PRIMITIVE_TYPE_IDENTIFIERS = INTEGER_TYPE_IDENTIFIERS + setOf("bool")
private val PRIMITIVE_BUILTIN_TYPE_IDENTIFIERS = setOf("address", "signer")
private val SUI_BUILTIN_TYPE_IDENTIFIERS = setOf(
    "transfer", "object", "tx_context", "vector", "option",
    "UID", "ID", "Option", "TxContext"
)
val BUILTIN_TYPE_IDENTIFIERS =
    PRIMITIVE_BUILTIN_TYPE_IDENTIFIERS + SUI_BUILTIN_TYPE_IDENTIFIERS + setOf("vector")

private val GLOBAL_STORAGE_ACCESS_FUNCTIONS =
    setOf("move_from", "borrow_global", "borrow_global_mut", "exists", "freeze")
val BUILTIN_FUNCTIONS = GLOBAL_STORAGE_ACCESS_FUNCTIONS + setOf("move_to")
