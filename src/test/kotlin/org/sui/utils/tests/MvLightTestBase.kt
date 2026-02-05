package org.sui.utils.tests

import com.intellij.testFramework.fixtures.BasePlatformTestCase

abstract class MvLightTestBase : BasePlatformTestCase() {
    override fun setUp() {
        super.setUp()

        val isDebugMode = this.findAnnotationInstance<DebugMode>()?.enabled ?: true
        try {
            setRegistryKey("org.move.debug.enabled", isDebugMode)
        } catch (_: Exception) {
            // Registry key may not exist in test environment
        }

        this.handleCompilerV2Annotations(project)
        this.handleNamedAddressAnnotations(project)
    }
}