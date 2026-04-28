package org.sui.lang.core.types.infer

import com.google.common.collect.Maps
import org.sui.lang.core.psi.MvTypeParameter
import org.sui.lang.core.types.ty.*

open class Substitution(val typeSubst: Map<TyTypeParameter, Ty> = emptyMap()) : TypeFoldable<Substitution> {

    val types: Collection<Ty> get() = typeSubst.values

    operator fun plus(other: Substitution): Substitution =
        Substitution(mergeMaps(typeSubst, other.typeSubst))

    operator fun get(key: TyTypeParameter): Ty? = typeSubst[key]
    operator fun get(psi: MvTypeParameter): Ty? = typeSubst[TyTypeParameter(psi)]

    fun foldValues(folder: TypeFolder): Substitution =
        Substitution(
            typeSubst.mapValues { (_, value) -> value.foldWith(folder) },
        )

    override fun innerFoldWith(folder: TypeFolder): Substitution = foldValues(folder)

    override fun innerVisitWith(visitor: TypeVisitor): Boolean {
        return typeSubst.values.any { it.visitWith(visitor) }
    }

    fun visitValues(visitor: TypeVisitor): Boolean = types.any { it.visitWith(visitor) }

    override fun equals(other: Any?): Boolean = when {
        this === other -> true
        javaClass != other?.javaClass -> false
        other !is Substitution -> false
        typeSubst != other.typeSubst -> false
        else -> true
    }

    override fun hashCode(): Int = typeSubst.hashCode()
}

private object EmptySubstitution : Substitution()

val emptySubstitution: Substitution = EmptySubstitution

/**
 * Deeply replace any [TyTypeParameter] by [subst] mapping.
 */
fun <T : TypeFoldable<T>> TypeFoldable<T>.substitute(subst: Substitution): T =
    foldWith(object : TypeFolder() {
        override fun fold(ty: Ty): Ty {
            return when {
                ty is TyTypeParameter -> subst[ty] ?: ty
                ty.needsSubst -> ty.innerFoldWith(this)
                else -> ty
            }
        }
    })

private fun <K, V> mergeMaps(map1: Map<K, V>, map2: Map<K, V>): Map<K, V> =
    when {
        map1.isEmpty() -> map2
        map2.isEmpty() -> map1
        else -> Maps.newHashMapWithExpectedSize<K, V>(map1.size + map2.size).apply {
            putAll(map1)
            putAll(map2)
        }
    }
