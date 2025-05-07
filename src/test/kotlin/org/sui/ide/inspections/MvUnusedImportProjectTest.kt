package org.sui.ide.inspections

import org.sui.utils.tests.annotation.InspectionProjectTestBase

class MvUnusedImportProjectTest : InspectionProjectTestBase(MvUnusedImportInspection::class) {
    fun `test no error if import used in spec module`() = checkWarningsByFileTree {
        namedMoveToml("Mira")
        sources {
            move(
                "string.move", """
    module 0x1::string { public fun utf8(v: vector<u8> ) {} }
            """
            )
            move(
                "basket.move", """
    module 0x1::basket {
        use 0x1::string;
    }
            """
            )
            move(
                "basket.spec.move", """
    spec 0x1::basket {
        spec module {
            let _a = string::utf8(b"Hello");/*caret*/
        }
    }
            """
            )
        }
    }

    fun `test no error if import used in module but imported in spec`() = checkWarningsByFileTree {
        namedMoveToml("Mira")
        sources {
            move(
                "string.move", """
    module 0x1::string { public fun utf8(v: vector<u8> ) {} }
            """
            )
            move(
                "basket.move", """
    module 0x1::basket {
        fun call() {
            let _a = string::utf8(b"Hello");
        }
    }
            """
            )
            move(
                "basket.spec.move", """
    spec 0x1::basket {
        use 0x1::string;/*caret*/
    }
            """
            )
        }
    }
}
