package org.sui.ide.inspections.imports

import org.intellij.lang.annotations.Language
import org.sui.ide.inspections.MvUnresolvedReferenceInspection
import org.sui.utils.tests.FileTreeBuilder
import org.sui.utils.tests.annotation.InspectionProjectTestBase

class AutoImportFixTreeProjectTest : InspectionProjectTestBase(MvUnresolvedReferenceInspection::class) {
    fun `test import method from another file`() = checkAutoImportFixByText(
        {
            namedMoveToml("MyModule")
            sources {
                move(
                    "Mod.move", """
            module 0x1::Mod {
                public fun call() {}
            }    
            """
                )
                move(
                    "Main.move", """
            module 0x1::Main {
                fun main() {
                    <error descr="Unresolved function: `call`">/*caret*/call</error>();
                }
            }    
            """
                )
            }
        }, """
            module 0x1::Main {
                use 0x1::Mod::call;

                fun main() {
                    call();
                }
            }    
        """
    )

    fun `test auto import from local dependency`() = checkAutoImportFixByText(
        {
            dir("move-stdlib") {
                moveToml(
                    """
                [package]
                name = "MoveStdlib"    
                """
                )
                sources {
                    move(
                        "Mod.move",
                        """
                        module 0x1::Mod {
                            public fun call() {}
                        }
                        """
                    )

                }
            }
            moveToml(
                """
            [package]
            name = "MyModule"        
            [dependencies]
            MoveStdlib = { local = "./move-stdlib" }    
            """
            )
            sources {
                move(
                    "Main.move", """
            module 0x1::Main {
                fun main() {
                    <error descr="Unresolved function: `call`">/*caret*/call</error>();
                }
            }    
            """
                )
            }
        }, """
            module 0x1::Main {
                use 0x1::Mod::call;

                fun main() {
                    call();
                }
            }    
        """
    )

    fun `test auto import from git dependency`() = checkAutoImportFixByText(
        {
            dotMove {
                git("https://github.com/aptos-labs/hello.git", "main") {
                    namedMoveToml("MoveStdlib")
                    sources {
                        move(
                            "Mod.move",
                            """
                        module 0x1::Mod {
                            public fun call() {}
                        }
                        """
                        )
                    }
                }
            }
            moveToml(
                """
            [package]
            name = "MyModule"        
            [dependencies.MoveStdlib]
            git = "https://github.com/aptos-labs/hello.git"
            rev = "main"
            """
            )
            sources {
                move(
                    "Main.move", """
            module 0x1::Main {
                fun main() {
                    <error descr="Unresolved function: `call`">/*caret*/call</error>();
                }
            }    
            """
                )
            }
        }, """
            module 0x1::Main {
                use 0x1::Mod::call;

                fun main() {
                    call();
                }
            }    
        """
    )

    private fun checkAutoImportFixByText(
        before: FileTreeBuilder.() -> Unit,
        @Language("Sui Move") after: String,
    ) = doTest { checkFixByFileTree(AutoImportFix.NAME, before, after) }

    private inline fun doTest(action: () -> Unit) {
        val inspection = inspection as MvUnresolvedReferenceInspection
        val defaultValue = inspection.ignoreWithoutQuickFix
        try {
            inspection.ignoreWithoutQuickFix = false
            action()
        } finally {
            inspection.ignoreWithoutQuickFix = defaultValue
        }
    }
}
