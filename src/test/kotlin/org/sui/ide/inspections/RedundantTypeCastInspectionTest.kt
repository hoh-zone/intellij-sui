package org.sui.ide.inspections

import org.sui.utils.tests.annotation.InspectionTestBase

class RedundantTypeCastInspectionTest : InspectionTestBase(RedundantTypeCastInspection::class) {
    fun `test no error in correct cast from integer`() = checkByText(
        """
module 0x1::main {
    fun main() {
        (1 as u64);
    }
}        
    """
    )

    fun `test no error in correct cast from u8 to u64`() = checkByText(
        """
module 0x1::main {
    fun main() {
        (1u8 as u64);
    }
}        
    """
    )

    fun `test no cast needed from u64 to u64`() = checkByText(
        """
module 0x1::main {
    fun main() {
        (1u64 <warning descr="No cast needed">as u64</warning>);
    }
}        
    """
    )

    fun `test no error in msl`() = checkByText(
        """
module 0x1::main {
    spec module {
        (1u64 as u64);
    }
}        
    """
    )
}
