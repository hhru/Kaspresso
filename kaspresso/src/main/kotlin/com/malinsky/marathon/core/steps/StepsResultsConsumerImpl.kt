package com.malinsky.marathon.core.steps

import android.os.Bundle
import androidx.test.internal.runner.listener.InstrumentationResultPrinter.REPORT_KEY_NAME_CLASS
import androidx.test.internal.runner.listener.InstrumentationResultPrinter.REPORT_KEY_NAME_TEST
import androidx.test.internal.runner.listener.InstrumentationResultPrinter.REPORT_VALUE_RESULT_ASSUMPTION_FAILURE
import androidx.test.internal.runner.listener.InstrumentationResultPrinter.REPORT_VALUE_RESULT_FAILURE
import androidx.test.internal.runner.listener.InstrumentationResultPrinter.REPORT_VALUE_RESULT_OK
import androidx.test.platform.app.InstrumentationRegistry
import com.kaspersky.kaspresso.logger.UiTestLogger
import com.kaspersky.kaspresso.testcases.models.TestIdentifier
import com.kaspersky.kaspresso.testcases.models.extensions.toTestIdentifier
import org.junit.AssumptionViolatedException
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * This [org.junit.rules.TestWatcher] writes information about test steps into adb shell's output
 * through [android.app.Instrumentation.sendStatus] when test ends with success or failure.
 * When we writes unknown test events into adb shell's output they will be parsed by ddmlib as
 * <a href="https://android.googlesource.com/platform/tools/base/+/master/ddmlib/src/main/java/com/android/ddmlib/testrunner/InstrumentationResultParser.java#358">testMetrics</a> field.
 *
 * This testMetrics should be processed by your's tests orchestrator (e.g. <a href="https://github.com/Malinskiy/marathon">Marathon</a>).
 */
class StepsResultsConsumerImpl(
    private val uiTestLogger: UiTestLogger
) : TestWatcher(), StepsResultsConsumer {

    companion object {
        private const val INSTRUMENTATION_KEY_STEPS_RESULTS_JSON = "marathon.stepsResultsJson"
        private const val EMPTY_STEPS_RESULTS_JSON = "[]"
    }

    private var stepsResultsJson = EMPTY_STEPS_RESULTS_JSON

    override fun consume(testIdentifier: TestIdentifier, stepsResultsJson: String) {
        uiTestLogger.d("Consume steps json from $testIdentifier")
        this.stepsResultsJson = stepsResultsJson
    }

    override fun failed(e: Throwable, description: Description) {
        super.failed(e, description)

        val testIdentifier = description.toTestIdentifier()
        uiTestLogger.d("$testIdentifier failed")
        sendStatus(REPORT_VALUE_RESULT_FAILURE, description)
    }

    override fun skipped(e: AssumptionViolatedException, description: Description) {
        super.skipped(e, description)

        val testIdentifier = description.toTestIdentifier()
        uiTestLogger.d("$testIdentifier skipped")
        sendStatus(REPORT_VALUE_RESULT_ASSUMPTION_FAILURE, description)
    }

    override fun succeeded(description: Description) {
        super.succeeded(description)

        val testIdentifier = description.toTestIdentifier()
        uiTestLogger.d("$testIdentifier succeeded")
        sendStatus(REPORT_VALUE_RESULT_OK, description)
    }

    private fun sendStatus(code: Int, description: Description) {
        InstrumentationRegistry.getInstrumentation()
            .sendStatus(code, createBundle(description, stepsResultsJson))
    }

    private fun createBundle(description: Description, stepsResultsJson: String): Bundle {
        return Bundle().apply {
            putString(REPORT_KEY_NAME_CLASS, description.className)
            putString(REPORT_KEY_NAME_TEST, description.methodName)
            putString(INSTRUMENTATION_KEY_STEPS_RESULTS_JSON, stepsResultsJson)
        }
    }
}