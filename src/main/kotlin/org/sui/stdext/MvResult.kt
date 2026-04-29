/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.stdext

sealed class MvResult<out T, out E> {
    data class Ok<T>(val ok: T) : MvResult<T, Nothing>()
    data class Err<E>(val err: E) : MvResult<Nothing, E>()
}

inline fun <T, E> MvResult<T, E>.unwrapOrElse(op: (E) -> T): T = when (this) {
    is MvResult.Ok -> ok
    is MvResult.Err -> op(err)
}

fun <T, E: Throwable> MvResult<T, E>.unwrapOrThrow(): T = when (this) {
    is MvResult.Ok -> ok
    is MvResult.Err -> throw err
}
