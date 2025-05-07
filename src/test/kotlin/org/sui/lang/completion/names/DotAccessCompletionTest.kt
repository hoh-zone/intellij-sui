package org.sui.lang.completion.names

import org.sui.utils.tests.completion.CompletionTestCase

class DotAccessCompletionTest : CompletionTestCase() {
    fun `test dot access for struct reference`() = doSingleCompletion(
        """
        module M {
            struct Frobnicate {
                vec: vector<u8>
            }
            fun main(frob: &Frobnicate) {
                frob.v/*caret*/
            }
        }
    """, """
        module M {
            struct Frobnicate {
                vec: vector<u8>
            }
            fun main(frob: &Frobnicate) {
                frob.vec/*caret*/
            }
        }
    """
    )

    fun `test dot access for mutable struct reference`() = doSingleCompletion(
        """
        module M {
            struct Frobnicate {
                vec: vector<u8>
            }
            fun main(frob: &mut Frobnicate) {
                frob.v/*caret*/
            }
        }
    """, """
        module M {
            struct Frobnicate {
                vec: vector<u8>
            }
            fun main(frob: &mut Frobnicate) {
                frob.vec/*caret*/
            }
        }
    """
    )

    fun `test borrow global dot field in test_only module`() = checkContainsCompletion(
        listOf("mint_cap", "burn_cap"),
        """
#[test_only]            
module 0x1::M {
    struct Caps has key { mint_cap: u8, burn_cap: u8 }
    fun main() {
        borrow_global<Caps>(@0x1)./*caret*/
    }
}            
        """
    )

    fun `test chained dot access`() = doSingleCompletion(
        """
        module 0x1::m {
            struct Pool { field: u8 }
            fun main(pool: &mut Pool) {
                pool./*caret*/
                pool.field
            }
        }        
    """, """
        module 0x1::m {
            struct Pool { field: u8 }
            fun main(pool: &mut Pool) {
                pool.field/*caret*/
                pool.field
            }
        }        
    """
    )

    fun `test fields are not available from another module`() = checkNoCompletion(
        """
        module 0x1::m {
            struct S { field: u8 }
        }
        module 0x1::main {
            use 0x1::m::S;
            fun main(s: S) {
                s.fi/*caret*/
            }
        }
    """
    )
}
