/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.utils.tests

import com.intellij.openapi.util.Disposer
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.util.registry.Registry
import com.intellij.testFramework.PlatformTestUtil
import com.intellij.testFramework.UsefulTestCase
import java.lang.reflect.Method
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Find an annotation instance on the current test method or class.
 * Searches first on the test method, then on the test class.
 */
inline fun <reified T : Annotation> UsefulTestCase.findAnnotationInstance(): T? {
    val testMethod = findTestMethod() ?: return findAnnotationOnClass()
    return testMethod.getAnnotation(T::class.java) ?: findAnnotationOnClass()
}

/**
 * Find all annotation instances of the given type on the current test method or class.
 */
inline fun <reified T : Annotation> UsefulTestCase.findAnnotationInstances(): List<T> {
    val result = mutableListOf<T>()

    // Check class annotations (including repeatable container)
    val classAnnotations = this::class.java.getAnnotationsByType(T::class.java)
    result.addAll(classAnnotations)

    // Check method annotations
    val testMethod = findTestMethod()
    if (testMethod != null) {
        val methodAnnotations = testMethod.getAnnotationsByType(T::class.java)
        result.addAll(methodAnnotations)
    }

    return result
}

@PublishedApi
internal inline fun <reified T : Annotation> UsefulTestCase.findAnnotationOnClass(): T? {
    return this::class.java.getAnnotation(T::class.java)
}

@PublishedApi
internal fun UsefulTestCase.findTestMethod(): Method? {
    return try {
        this::class.java.getMethod(name)
    } catch (e: NoSuchMethodException) {
        null
    }
}

/**
 * Set a registry key value for the duration of the test.
 * The original value will be restored after the test completes.
 */
fun UsefulTestCase.setRegistryKey(key: String, value: Boolean) {
    val registryValue = Registry.get(key)
    val oldValue = registryValue.asBoolean()
    registryValue.setValue(value)

    // Use Disposer to restore value - the proper way in IntelliJ
    Disposer.register(testRootDisposable) {
        registryValue.setValue(oldValue)
    }
}

/**
 * Set a registry key value for the duration of the test.
 */
fun UsefulTestCase.setRegistryKey(key: String, value: String) {
    val registryValue = Registry.get(key)
    val oldValue = registryValue.asString()
    registryValue.setValue(value)

    Disposer.register(testRootDisposable) {
        registryValue.setValue(oldValue)
    }
}

/**
 * Wait for the countdown latch to finish within the specified timeout.
 * @return true if the latch counted down to zero, false if the timeout elapsed
 */
fun CountDownLatch.waitFinished(timeoutMs: Long): Boolean {
    return await(timeoutMs, TimeUnit.MILLISECONDS)
}

/**
 * Run a condition check with invocation events dispatching until it returns true or times out.
 * @param errorMessage Error message to throw if the condition is not met
 * @param timeoutMs Timeout in milliseconds (default 10 seconds)
 * @param condition The condition to check
 */
fun runWithInvocationEventsDispatching(
    errorMessage: String,
    timeoutMs: Long = 10000,
    condition: () -> Boolean
) {
    val startTime = System.currentTimeMillis()
    while (System.currentTimeMillis() - startTime < timeoutMs) {
        PlatformTestUtil.dispatchAllEventsInIdeEventQueue()
        if (condition()) {
            return
        }
        Thread.sleep(50)
    }
    error(errorMessage)
}
