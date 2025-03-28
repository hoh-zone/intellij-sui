package org.sui.ide.inspections.compilerV2

import org.intellij.lang.annotations.Language
import org.sui.ide.inspections.ReplaceWithMethodCallInspection
import org.sui.ide.inspections.fixes.CompilerV2Feat.INDEXING
import org.sui.ide.inspections.fixes.CompilerV2Feat.RECEIVER_STYLE_FUNCTIONS
import org.sui.utils.tests.CompilerV2Features
import org.sui.utils.tests.FileTreeBuilder
import org.sui.utils.tests.annotation.InspectionProjectTestBase

@CompilerV2Features(RECEIVER_STYLE_FUNCTIONS)
class ReplaceWithMethodCallInspectionProjectTest :
    InspectionProjectTestBase(ReplaceWithMethodCallInspection::class) {

    fun `test no warning with vector as self param with local method`() = doTest {
        namedMoveToml("MyPackage")
        sources {
            main(
                """
                module 0x1::main {
                    native fun length<T>(self: &vector<T>): u8;
                    fun main() {
                        /*caret*/length(vector[1, 2, 3]);
                    }
                }                
            """
            )
        }
    }

    fun `test no warning with vector as self param with module with wrong address`() = doTest {
        namedMoveToml("MyPackage")
        sources {
            move(
                "vector.move", """
                module 0x2::vector {
                    public native fun length<T>(self: &vector<T>): u8;
                }                
            """
            )
            main(
                """
                module 0x1::main {
                    use 0x2::vector::length;
                    fun main() {
                        /*caret*/length(vector[1, 2, 3]);
                    }
                }                
            """
            )
        }
    }

    fun `test fix if vector method from correct address module`() = doFixTest(
        {
            namedMoveToml("MyPackage")
            sources {
                move(
                    "vector.move", """
                module 0x1::vector {
                    public native fun length<T>(self: &vector<T>): u8;
                }                
            """
                )
                main(
                    """
                module 0x1::main {
                    use 0x1::vector::length;
                    fun main() {
                        <weak_warning descr="Can be replaced with method call">/*caret*/length(vector[1, 2, 3])</weak_warning>;
                    }
                }                
            """
                )
            }
        }, """
                module 0x1::main {
                    use 0x1::vector::length;
                    fun main() {
                        vector[1, 2, 3].length();
                    }
                }                
        """
    )

    fun `test fix if vector reference method from correct address module`() = doFixTest(
        {
            namedMoveToml("MyPackage")
            sources {
                move(
                    "vector.move", """
                module 0x1::vector {
                    public native fun length<T>(self: &vector<T>): u8;
                }                
            """
                )
                main(
                    """
                module 0x1::main {
                    use 0x1::vector::length;
                    fun main() {
                        <weak_warning descr="Can be replaced with method call">/*caret*/length(&vector[1, 2, 3])</weak_warning>;
                    }
                }                
            """
                )
            }
        }, """
                module 0x1::main {
                    use 0x1::vector::length;
                    fun main() {
                        vector[1, 2, 3].length();
                    }
                }                
        """
    )

    @CompilerV2Features(RECEIVER_STYLE_FUNCTIONS, INDEXING)
    fun `test replace with method call with index expr`() = doFixTest(
        {
            namedMoveToml("MyPackage")
            sources {
                move(
                    "vector.move", """
        module 0x1::vector {
            #[bytecode_instruction]
            /// Add element `e` to the end of the vector `v`.
            native public fun push_back<Element>(self: &mut vector<Element>, e: Element);
        }               
            """
                )
                main(
                    """
        module 0x1::m {
            use 0x1::vector::push_back;
            fun main() {
                let v = vector[1, 2];
                let vec = vector[];
                <weak_warning descr="Can be replaced with method call">/*caret*/push_back(&mut vec, v[0])</weak_warning>;
            }
        }
            """
                )
            }
        }, """
        module 0x1::m {
            use 0x1::vector::push_back;
            fun main() {
                let v = vector[1, 2];
                let vec = vector[];
                vec.push_back(v[0]);
            }
        }
        """
    )

    private fun doTest(code: FileTreeBuilder.() -> Unit) =
        checkByFileTree(code, checkWarn = false, checkWeakWarn = true)

    private fun doFixTest(
        before: FileTreeBuilder.() -> Unit,
        @Language("Sui Move") after: String,
    ) =
        checkFixByFileTree(
            "Replace with method call", before, after,
            checkWarn = false, checkWeakWarn = true
        )
}