package org.sui.ide.lineMarkers

import org.sui.utils.tests.lineMarkers.LineMarkerProviderTestBase

class ItemSpecsLineMarkerProviderTest : LineMarkerProviderTestBase() {
    fun `test no specifications`() = doTestByText(
        """
        module 0x1::m {
            fun call() {}
        }
        module 0x1::m2 {}
        module 0x1::m3 {}
    """
    )

    fun `test item specifications`() = doTestByText(
        """
        module 0x1::m {  // - Has specifications  
            fun call() {}  // - Has specifications  
            
        }
        spec 0x1::m {
            spec call {}
        }
    """
    )
}
