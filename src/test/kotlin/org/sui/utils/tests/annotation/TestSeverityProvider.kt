/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.sui.utils.tests.annotation

import com.intellij.codeInsight.daemon.impl.HighlightInfoType
import com.intellij.codeInsight.daemon.impl.SeveritiesProvider
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.colors.TextAttributesKey

/**
 * Test implementation of SeveritiesProvider for registering custom severities in tests.
 */
class TestSeverityProvider(
    private val severities: List<HighlightSeverity>
) : SeveritiesProvider() {

    override fun getSeveritiesHighlightInfoTypes(): List<HighlightInfoType> {
        return severities.map { severity ->
            object : HighlightInfoType {
                override fun getSeverity(psiElement: com.intellij.psi.PsiElement?): HighlightSeverity = severity

                override fun getAttributesKey(): TextAttributesKey =
                    TextAttributesKey.createTextAttributesKey("TEST_${severity.name}")
            }
        }
    }
}
