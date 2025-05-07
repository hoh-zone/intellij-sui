package org.sui.utils.tests

import com.intellij.testFramework.fixtures.BasePlatformTestCase

abstract class MvLightTestBase : BasePlatformTestCase() {
    override fun setUp() {
        super.setUp()

        val isDebugMode = this.findAnnotationInstance<DebugMode>()?.enabled ?: true
        setRegistryKey("org.move.debug.enabled", isDebugMode)

        this.handleCompilerV2Annotations(project)
        this.handleNamedAddressAnnotations(project)
    }
}