package org.sui.ide.inspections.imports

import org.sui.ide.utils.imports.ImportCandidateCollector
import org.sui.lang.core.psi.MvPath
import org.sui.lang.core.resolve.ref.MvReferenceElement
import org.sui.utils.tests.FileTreeBuilder
import org.sui.utils.tests.MvProjectTestBase
import org.sui.utils.tests.base.findElementWithDataAndOffsetInEditor

class ImportCandidatesTest : MvProjectTestBase() {
    fun `test cannot import test function`() = checkCandidates {
        namedMoveToml("Package")
        sources {
            move(
                "m1.move", """
module 0x1::m1 {
    #[test]
    public fun test_a() {}
}  
            """
            )
            main(
                """
module 0x1::main {
    #[test_only]
    fun main() {
        test_a();
        //^ []
    }
}
            """
            )
        }
    }

    fun `test cannot import private function`() = checkCandidates {
        namedMoveToml("Package")
        sources {
            move(
                "m1.move", """
module 0x1::m1 {
    fun call() {}
}  
            """
            )
            main(
                """
module 0x1::main {
    fun main() {
        call();
        //^ []
    }
}
            """
            )
        }
    }

    fun `test import public function`() = checkCandidates {
        namedMoveToml("Package")
        sources {
            move(
                "m1.move", """
module 0x1::m1 {
    public fun call() {}
}  
            """
            )
            main(
                """
module 0x1::main {
    fun main() {
        call();
        //^ 0x1::m1::call
    }
}
            """
            )
        }
    }

//    fun `test import entry function`() = checkImportCandidates {
//        namedMoveToml("Package")
//        sources {
//            move(
//                "m1.move", """
//module 0x1::m1 {
//    entry fun call() {}
//}
//            """
//            )
//            main(
//                """
//module 0x1::main {
//    entry fun main() {
//        call();
//        //^ 0x1::m1::call
//    }
//}
//            """
//            )
//        }
//    }

    fun `test import friend function`() = checkCandidates {
        namedMoveToml("Package")
        sources {
            move(
                "m1.move", """
module 0x1::m1 {
    friend 0x1::main;
    public(friend) fun call() {}
}  
            """
            )
            main(
                """
module 0x1::main {
    fun main() {
        call();
        //^ 0x1::m1::call
    }
}
            """
            )
        }
    }

    fun `test cannot import friend function if not a friend`() = checkCandidates {
        namedMoveToml("Package")
        sources {
            move(
                "m1.move", """
module 0x1::m1 {
    public(friend) fun call() {}
}  
            """
            )
            main(
                """
module 0x1::main {
    fun main() {
        call();
        //^ []
    }
}
            """
            )
        }
    }

    // TODO: test
//    fun `test not duplicate public method from dependency package included twice with different versions`() =
//        checkCandidates {
//            dir("sui_framework") {
//                namedMoveToml("SuiFramework")
//                sources {
//                    main(
//                        """
//                        module 0x1::m {
//                            public fun sui_call() {}
//                        }
//                    """
//                    )
//                }
//            }
//            dir("sui_framework_new") {
//                namedMoveToml("SuiFramework")
//                sources {
//                    main(
//                        """
//                        module 0x1::m {
//                            public fun sui_call() {}
//                        }
//                    """
//                    )
//                }
//            }
//            dir("bin_steps") {
//                moveToml(
//                    """
//                    [package]
//                    name = "BinSteps"
//
//                    [dependencies.SuiFramework]
//                    local = "../sui_framework"
//                """
//                )
//                sources { }
//            }
//            moveToml(
//                """
//                [package]
//                name = "MyPackage"
//
//                [dependencies.SuiFramework]
//                local = "./sui_framework_new"
//
//                [dependencies.BinSteps]
//                local = "./bin_steps"
//            """
//            )
//            sources {
//                main(
//                    """
//                    module 0x1::mm {
//                        public fun main() {
//                            sui_call();
//                            //^ [0x1::m::sui_call]
//                        }
//                    }
//                """
//                )
//            }
//        }

    fun checkCandidates(
        tree: FileTreeBuilder.() -> Unit
    ) {
        testProject(tree)

        val refClass = MvReferenceElement::class.java
        val (refElement, data, _) =
            myFixture.findElementWithDataAndOffsetInEditor(refClass, "^")
        val path = refElement as? MvPath ?: error("no path at caret")
        val targetName = path.referenceName ?: error("No name for reference element")
        val importContext = ImportContext.from(path, true) ?: error("no import context")
        val candidates =
            ImportCandidateCollector
                .getImportCandidates(importContext, targetName)  //.Companion.from(path)
                .map { it.qualName.editorText() }
        if (data == "[]") {
            check(candidates.isEmpty()) { "Non-empty candidates: $candidates" }
            return
        }

        val expectedCandidates = data.split(',')
            .takeIf { it.isNotEmpty() } ?: error("Invalid candidate set")
        check(candidates == expectedCandidates) {
            "Expected candidates: $expectedCandidates, actual: $candidates"
        }
    }
}
