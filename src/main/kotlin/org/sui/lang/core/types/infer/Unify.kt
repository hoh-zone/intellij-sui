/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.lang.core.types.infer

interface NodeOrValue
interface Node : NodeOrValue {
    var parent: NodeOrValue
}

data class VarValue<out V>(val value: V?, val rank: Int) : NodeOrValue
