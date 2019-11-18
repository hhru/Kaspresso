package com.malinskiy.marathon.steps

import android.app.Instrumentation
import android.os.Bundle
import android.util.Log
import androidx.test.internal.runner.listener.InstrumentationResultPrinter
import androidx.test.internal.runner.listener.InstrumentationRunListener
import com.kaspersky.kaspresso.runner.TestIdentifier
import com.kaspersky.kaspresso.runner.toTestIdentifier
import org.junit.runner.Description
import org.junit.runner.notification.Failure


class StepsRunListener : InstrumentationRunListener() {

    companion object {
        private const val LOG_TAG = "StepsRunListener"
        private const val INSTRUMENTATION_STATUS_KEY_STEPS_RESULTS_JSON =
            "marathon.stepsResultsJson"
        private const val EMPTY_STEPS_RESULT_JSON = "[]"

        internal var instance: StepsRunListener? = null
    }

    private val stepsJsonMap = mutableMapOf<TestIdentifier, String>()

    private var currentNum = 0
    private var currentTestIdentifier: TestIdentifier? = null


    override fun testStarted(description: Description) {
        super.testStarted(description)

        instance = this
        ++currentNum
        currentTestIdentifier = description.toTestIdentifier()
        log("testStarted | testIdentifier: ${description.toTestIdentifier()}, currentNum: $currentNum")
    }

    override fun testAssumptionFailure(failure: Failure) {
        super.testAssumptionFailure(failure)

        log("testAssumptionFailure | testIdentifier: ${failure.description.toTestIdentifier()}, currentNum: $currentNum")
        sendTestStatus(
            InstrumentationResultPrinter.REPORT_VALUE_RESULT_ASSUMPTION_FAILURE,
            failure.description,
            currentNum
        )
    }

    override fun testFailure(failure: Failure) {
        super.testFailure(failure)

        log("testFailure | testIdentifier: ${failure.description.toTestIdentifier()}, currentNum: $currentNum")
        sendTestStatus(
            InstrumentationResultPrinter.REPORT_VALUE_RESULT_FAILURE,
            failure.description,
            currentNum
        )
    }

    override fun testFinished(description: Description) {
        super.testFinished(description)

        log("testFinished | testIdentifier: ${description.toTestIdentifier()}, currentNum: $currentNum")
        sendTestStatus(InstrumentationResultPrinter.REPORT_VALUE_RESULT_OK, description, currentNum)
    }

    override fun testIgnored(description: Description) {
        super.testIgnored(description)

        log("testIgnored | testIdentifier: ${description.toTestIdentifier()}, currentNum: $currentNum")
        sendTestStatus(
            InstrumentationResultPrinter.REPORT_VALUE_RESULT_IGNORED,
            description,
            currentNum
        )
    }


    fun attachStepsJson(stepsJson: String) {
        log("attachStepsJson | testIdentifier: $currentTestIdentifier")
        currentTestIdentifier?.let { testIdentifier ->
            log("Attach steps json for $testIdentifier")

            stepsJsonMap[testIdentifier] = stepsJson
        }
    }


    private fun sendTestStatus(statusCode: Int, description: Description, currentNum: Int) {
        sendStatus(statusCode, createStatusBundle(description, currentNum))
        currentTestIdentifier = null
    }

    private fun createStatusBundle(description: Description, currentNum: Int): Bundle {
        val stepsJson = stepsJsonMap[description.toTestIdentifier()] ?: EMPTY_STEPS_RESULT_JSON
        log("Create status bundle [identifier: ${description.toTestIdentifier()}, json: $stepsJson]")
        return Bundle().apply {
            putString(
                Instrumentation.REPORT_KEY_IDENTIFIER,
                InstrumentationResultPrinter.REPORT_VALUE_ID
            )
            putString(InstrumentationResultPrinter.REPORT_KEY_NAME_CLASS, description.className)
            putString(InstrumentationResultPrinter.REPORT_KEY_NAME_TEST, description.methodName)
            putInt(InstrumentationResultPrinter.REPORT_KEY_NUM_TOTAL, description.testCount())
            putInt(InstrumentationResultPrinter.REPORT_KEY_NUM_CURRENT, currentNum)

            putString(
                INSTRUMENTATION_STATUS_KEY_STEPS_RESULTS_JSON,
                stepsJsonMap[description.toTestIdentifier()] ?: EMPTY_STEPS_RESULT_JSON
            )
        }
    }


    private fun log(message: String) {
        Log.i(LOG_TAG, "this: $this | $message")
    }

}