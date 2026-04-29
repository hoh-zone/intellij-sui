package org.sui.lang.core.resolve.ref

import java.util.*

sealed class Visibility2 {
    data object Public: Visibility2()
    data object Private: Visibility2()
    sealed class Restricted: Visibility2() {
        class Friend: Restricted()
        class Package: Restricted()
    }
}

enum class Namespace {
    NAME,
    FUNCTION,
    TYPE,
    ENUM,
    MODULE;

    companion object {
        fun all(): Set<Namespace> = EnumSet.of(NAME, FUNCTION, TYPE, ENUM, MODULE)

        fun none(): Set<Namespace> = setOf()
    }
}

val NONE = Namespace.none()
val NAMES = setOf(Namespace.NAME)

val MODULES = setOf(Namespace.MODULE)
val FUNCTIONS = setOf(Namespace.FUNCTION)
val FUNCTIONS_N_TYPES_N_ENUMS = setOf(Namespace.FUNCTION, Namespace.TYPE, Namespace.ENUM)
val TYPES = setOf(Namespace.TYPE)
val ENUMS = setOf(Namespace.ENUM)
val TYPES_N_ENUMS = setOf(Namespace.TYPE, Namespace.ENUM)

val TYPES_N_NAMES = setOf(Namespace.TYPE, Namespace.NAME)
val ENUMS_N_MODULES = setOf(Namespace.ENUM, Namespace.MODULE)
val TYPES_N_ENUMS_N_MODULES = setOf(Namespace.TYPE, Namespace.ENUM, Namespace.MODULE)

val ALL_NAMESPACES = Namespace.all()
val ITEM_NAMESPACES =
    setOf(Namespace.NAME, Namespace.FUNCTION, Namespace.TYPE, Namespace.ENUM)
