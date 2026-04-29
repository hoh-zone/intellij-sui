package org.sui.lang.core.types.ty

import org.sui.ide.presentation.tyToString


abstract class TyPrimitive(val name: String) : Ty() {
    override fun abilities() = setOf(Ability.DROP, Ability.COPY, Ability.STORE)
}

object TyBool : TyPrimitive("bool") {
    override fun toString(): String = tyToString(this)
}

object TyAddress : TyPrimitive("address") {
    override fun toString(): String = tyToString(this)
}

object TySigner : TyPrimitive("signer") {
    override fun abilities() = setOf(Ability.DROP)
    override fun toString(): String = tyToString(this)
}

object TyUnit : TyPrimitive("()") {
    override fun abilities() = Ability.none()
    override fun toString(): String = tyToString(this)
}

data class TyInteger(val kind: Kind) : TyPrimitive(kind.name.lowercase()) {
    override fun abilities() = Ability.all()

    companion object {
        fun fromName(name: String): TyInteger =
            Kind.values().find { it.name == name }?.let(::TyInteger)!!

        val DEFAULT_KIND = Kind.NoPrecision
    }

    @Suppress("EnumEntryName")
    enum class Kind {
        NoPrecision, u8, u16, u32, u64, u128, u256
    }

    override fun toString(): String = tyToString(this)
}
