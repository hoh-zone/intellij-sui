/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.utils.tests.resolve

import com.intellij.openapi.vfs.VirtualFile

/**
 * Result type for test resolution checks.
 */
sealed class TestResolveResult {
    object Ok : TestResolveResult()
    data class Err(val message: String) : TestResolveResult()
}

/**
 * Check if a resolved file matches the expected file path.
 *
 * @param file The resolved virtual file
 * @param expectedPath The expected file path (can be relative or use special prefixes)
 * @param findFile Function to find a file by path
 * @return TestResolveResult indicating success or failure with error message
 */
fun checkResolvedFile(
    file: VirtualFile,
    expectedPath: String,
    findFile: (String) -> VirtualFile?
): TestResolveResult {
    val normalizedExpected = expectedPath.trimStart('/')

    // Check if the file path ends with the expected path
    if (file.path.endsWith(normalizedExpected)) {
        return TestResolveResult.Ok
    }

    // Try to find the expected file and compare
    val expectedFile = findFile(expectedPath)
    if (expectedFile != null && file.path == expectedFile.path) {
        return TestResolveResult.Ok
    }

    return TestResolveResult.Err(
        "Expected file path to end with '$expectedPath', but got '${file.path}'"
    )
}
