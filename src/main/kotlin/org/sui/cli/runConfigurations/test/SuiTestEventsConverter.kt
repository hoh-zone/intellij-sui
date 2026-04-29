package org.sui.cli.runConfigurations.test

import com.intellij.execution.testframework.TestConsoleProperties
import com.intellij.execution.testframework.sm.ServiceMessageBuilder
import com.intellij.execution.testframework.sm.runner.OutputToGeneralTestEventsConverter
import com.intellij.openapi.util.Key
import jetbrains.buildServer.messages.serviceMessages.ServiceMessageVisitor

private typealias NodeId = String

sealed class TestLine {
    object StartTests : TestLine()

    data class Pass(val moduleName: String, val testName: String) : TestLine()
    data class Fail(val moduleName: String, val testName: String) : TestLine()
}

class TestLineParser {
    fun parse(textLine: String): TestLine? {
        val trimmedLine = textLine.trim()
        return when {
            trimmedLine == "Running Move unit tests" -> TestLine.StartTests

            textLine.startsWith("[ PASS    ]") -> {
                val testFqName = textLine.split(" ").lastOrNull() ?: return null

                val parts = testFqName.split("::")
                val address = parts.getOrNull(0) ?: return null
                val moduleName = parts.getOrNull(1) ?: return null
                val testName = parts.getOrNull(2) ?: return null

                TestLine.Pass("$address::$moduleName", testName)
            }
            textLine.startsWith("[ FAIL    ]") -> {
                val testFqName = textLine.split(" ").lastOrNull() ?: return null

                val parts = testFqName.split("::")
                val address = parts.getOrNull(0) ?: return null
                val moduleName = parts.getOrNull(1) ?: return null
                val testName = parts.getOrNull(2) ?: return null

                TestLine.Fail("$address::$moduleName", testName)
            }

            else -> null
        }
    }

}

class SuiTestEventsConverter(
    testFrameworkName: String,
    consoleProperties: TestConsoleProperties
) : OutputToGeneralTestEventsConverter(testFrameworkName, consoleProperties) {

    private val linesParser = TestLineParser()

    override fun processServiceMessages(
        textLine: String,
        outputType: Key<*>,
        visitor: ServiceMessageVisitor
    ): Boolean {
        val testLine = linesParser.parse(textLine) ?: return false
        if (testLine is TestLine.StartTests) {
            super.processServiceMessages(
                ServiceMessageBuilder.testsStarted().toString(),
                outputType,
                visitor
            )
            return true
        }
        val messages = createServiceMessagesFor(testLine) ?: return false
        for (message in messages) {
            super.processServiceMessages(message.toString(), outputType, visitor)
        }
        return false
    }

    private fun createServiceMessagesFor(testLine: TestLine): List<ServiceMessageBuilder>? {
        return when (testLine) {
            is TestLine.Pass -> {
                val test = "${testLine.moduleName}::${testLine.testName}"
                listOf(
                    createTestStartedMessage(test),
                    createTestFinishedMessage(test)
                )
            }
            is TestLine.Fail -> {
                val test = "${testLine.moduleName}::${testLine.testName}"
                listOf(
                    createTestStartedMessage(test),
                    createTestFailedMessage(test),
                    createTestFinishedMessage(test)
                )
            }
            else -> null
        }
    }

    companion object {
        private const val ROOT_SUITE: String = "0"
        private const val NAME_SEPARATOR: String = "::"

        private val NodeId.parent: NodeId
            get() {
                val parent = substringBeforeLast(NAME_SEPARATOR)
                return if (this == parent) ROOT_SUITE else parent
            }

        private fun createTestStartedMessage(test: NodeId): ServiceMessageBuilder =
            ServiceMessageBuilder.testStarted(test)
                .addAttribute("nodeId", test)
                .addAttribute("parentNodeId", test.parent)
                .addAttribute("locationHint", SuiTestLocator.getTestUrl(test))

        private fun createTestFinishedMessage(testId: NodeId): ServiceMessageBuilder =
            ServiceMessageBuilder.testFinished(testId)
                .addAttribute("nodeId", testId)

        private fun createTestFailedMessage(testId: NodeId): ServiceMessageBuilder =
            ServiceMessageBuilder.testFailed(testId)
                .addAttribute("nodeId", testId)
                .addAttribute("message", "")
    }
}
