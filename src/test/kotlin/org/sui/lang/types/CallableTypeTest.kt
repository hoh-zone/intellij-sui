package org.sui.lang.types

import org.intellij.lang.annotations.Language
import org.sui.ide.inspections.fixes.CompilerV2Feat.RECEIVER_STYLE_FUNCTIONS
import org.sui.ide.presentation.text
import org.sui.lang.core.psi.MvMethodCall
import org.sui.lang.core.psi.ext.MvCallable
import org.sui.lang.core.psi.ext.isMsl
import org.sui.lang.core.types.infer.inference
import org.sui.utils.tests.CompilerV2Features
import org.sui.utils.tests.InlineFile
import org.sui.utils.tests.base.findElementAndDataInEditor
import org.sui.utils.tests.types.TypificationTestCase

class CallableTypeTest : TypificationTestCase() {
    @CompilerV2Features(RECEIVER_STYLE_FUNCTIONS)
    fun `test infer method type`() = testMethodType(
        """
        module 0x1::main {
            struct S<T> { field: T }
            fun receiver<T, U>(self: &S<T>, param: U): U {
                param
            }
            fun main(s: S<u8>) {
                s.receiver(1);
                   //^ fn(&S<u8>, integer) -> integer
            }
        }        
    """
    )

    @CompilerV2Features(RECEIVER_STYLE_FUNCTIONS)
    fun `test infer method type not enough parameters`() = testMethodType(
        """
        module 0x1::main {
            struct S<T> { field: T }
            fun receiver<T, U, Z>(self: &S<T>, param: U): Z {
                param
            }
            fun main(s: S<u8>) {
                s.receiver();
                   //^ fn(&S<u8>, <unknown>) -> ?Z
            }
        }        
    """
    )

    @CompilerV2Features(RECEIVER_STYLE_FUNCTIONS)
    fun `test infer method type cannot autoborrow unknown function`() = testMethodType(
        """
        module 0x1::main {
            struct S<T> { field: T }
            fun receiver<T, U>(self: &mut S<T>, param: U): U {
                param
            }
            fun main(s: &S<u8>) {
                s.receiver(1);
                   //^ fn(<unknown>, <unknown>) -> <unknown>
            }
        }        
    """
    )
    //)

    private fun testMethodType(@Language("Sui Move") code: String) = testCallableType<MvMethodCall>(code)

    private inline fun <reified T : MvCallable> testCallableType(@Language("Sui Move") code: String) {
        InlineFile(myFixture, code, "main.move")
        val (callable, data) = myFixture.findElementAndDataInEditor<T>()
        val expectedType = data.trim()

        val msl = callable.isMsl()
        val inference = callable.inference(msl) ?: error("No inference at caret element")

        val actualType = inference.getCallableType(callable)?.text() ?: "null"
        check(actualType == expectedType) {
            "Type mismatch. \n" +
                    "    expected: $expectedType, \n    found: $actualType"
        }
    }
}