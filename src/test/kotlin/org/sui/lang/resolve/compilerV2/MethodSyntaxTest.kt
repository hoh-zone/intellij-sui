package org.sui.lang.resolve.compilerV2

import org.sui.ide.inspections.fixes.CompilerV2Feat.RECEIVER_STYLE_FUNCTIONS
import org.sui.utils.tests.CompilerV2Features
import org.sui.utils.tests.resolve.ResolveTestCase

/**
 * Tests for method syntax (https://move-book.com/reference/method-syntax):
 * - Method resolution with receiver (functions in defining module)
 * - use fun at module level and block scope - see method_syntax.move parser test
 *
 * Note: use fun alias resolution in method calls requires integration with
 * processMethodResolveVariants - currently only defining module functions are resolved.
 */
@CompilerV2Features(RECEIVER_STYLE_FUNCTIONS)
class MethodSyntaxTest : ResolveTestCase() {

    fun `test resolve method from defining module`() = checkByCode(
        """
        module 0x1::main {
            struct S { x: u64 }
            fun foo(self: &S): u64 {
               //X
                self.x
            }
            fun main(s: S) {
                s.foo()
                  //^
            }
        }
        """
    )

    fun `test chained method calls`() = checkByCode(
        """
        module 0x1::main {
            struct Outer has copy, drop { inner: u64 }
            struct Inner has copy, drop { x: u64 }
            fun get_inner(self: Outer): Inner { Inner { x: self.inner } }
            fun get_x(self: &Inner): u64 { self.x }

            fun main(o: Outer) {
                o.get_inner().get_x()
                  //^
            }
        }
        """
    )
}
