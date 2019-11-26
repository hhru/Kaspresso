package com.malinsky.marathon.core.steps

import android.os.Bundle
import androidx.test.internal.runner.listener.InstrumentationResultPrinter.REPORT_KEY_NAME_CLASS
import androidx.test.internal.runner.listener.InstrumentationResultPrinter.REPORT_KEY_NAME_TEST
import androidx.test.internal.runner.listener.InstrumentationResultPrinter.REPORT_KEY_NUM_TOTAL
import androidx.test.internal.runner.listener.InstrumentationResultPrinter.REPORT_KEY_STACK
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
import java.io.PrintWriter
import java.io.StringWriter

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

        // copied from InstrumentationResultPrinter.MAX_TRACE_SIZE
        private const val MAX_TRACE_SIZE = 32 * 1024
    }

    private var stepsResultsJson = EMPTY_STEPS_RESULTS_JSON

    override fun consume(testIdentifier: TestIdentifier, stepsResultsJson: String) {
        uiTestLogger.d("Consume steps json from $testIdentifier | is steps json empty: ${stepsResultsJson == EMPTY_STEPS_RESULTS_JSON}")
        this.stepsResultsJson = stepsResultsJson
    }

    override fun failed(e: Throwable, description: Description) {
        super.failed(e, description)

        val testIdentifier = description.toTestIdentifier()
        uiTestLogger.d("$testIdentifier failed | is steps json empty: ${stepsResultsJson == EMPTY_STEPS_RESULTS_JSON}")
        sendStatus(REPORT_VALUE_RESULT_FAILURE, description, e)
    }

    override fun skipped(e: AssumptionViolatedException, description: Description) {
        super.skipped(e, description)

        val testIdentifier = description.toTestIdentifier()
        uiTestLogger.d("$testIdentifier skipped | is steps json empty: ${stepsResultsJson == EMPTY_STEPS_RESULTS_JSON}")
        sendStatus(REPORT_VALUE_RESULT_ASSUMPTION_FAILURE, description, e)
    }

    override fun succeeded(description: Description) {
        super.succeeded(description)

        val testIdentifier = description.toTestIdentifier()
        uiTestLogger.d("$testIdentifier succeeded | is steps json empty: ${stepsResultsJson == EMPTY_STEPS_RESULTS_JSON}")
        sendStatus(REPORT_VALUE_RESULT_OK, description)
    }

    private fun sendStatus(code: Int, description: Description, throwable: Throwable? = null) {
        InstrumentationRegistry.getInstrumentation()
            .sendStatus(code, createBundle(description, stepsResultsJson, throwable))
    }

    private fun createBundle(
        description: Description,
        stepsResultsJson: String,
        throwable: Throwable?
    ): Bundle {
        return Bundle().apply {
            putString(REPORT_KEY_NAME_CLASS, description.className)
            putString(REPORT_KEY_NAME_TEST, description.methodName)
            putInt(REPORT_KEY_NUM_TOTAL, description.testCount())
            throwable?.let { putString(REPORT_KEY_STACK, throwable.getTrace()) }
            putString(INSTRUMENTATION_KEY_STEPS_RESULTS_JSON, stepsResultsJson)
        }
    }


    private fun Throwable.getTrace(): String {
        val stringWriter = StringWriter()
        val writer = PrintWriter(stringWriter)
        printStackTrace(writer)
        val trace = stringWriter.toString()

        return trace.takeIf { it.length < MAX_TRACE_SIZE } ?: trace.substring(0, MAX_TRACE_SIZE)
    }

}