package com.kaspersky.kaspresso.runner

import android.app.Instrumentation
import android.os.Bundle
import android.util.Log
import androidx.test.internal.runner.listener.InstrumentationResultPrinter.*
import androidx.test.internal.runner.listener.InstrumentationRunListener
import org.junit.runner.Description
import org.junit.runner.Result
import org.junit.runner.notification.Failure


// TODO -- refactor this
class MyListener : InstrumentationRunListener() {

    companion object {
        private const val LOG_TAG = "MyListener"
        private const val INSTRUMENTATION_STATUS_KEY_STEPS_RESULTS_JSON = "marathon.stepsResultsJson"
        private const val EMPTY_STEPS_RESULT_JSON = "[]"

        var instance: MyListener? = null
    }

    private val stepsJsonMap = mutableMapOf<Test, String>()
    private var currentNum = 0
    private var currentTest: Test? = null


    override fun testRunStarted(description: Description) {
        super.testRunStarted(description)

        if (description.methodName == null) {
            Log.i(LOG_TAG, "null method name -> skip")
            return
        }
        instance = this 

        val testIdentifier = description.toTestIdentifier()
        val test = testIdentifier.toTest()
        currentTest = test

        Log.i(
            LOG_TAG,
            "testRunStarted | description: $description, testIdentifier: $testIdentifier, test: $test"
        )
    }

    override fun testStarted(description: Description) {
        super.testStarted(description)
        instance = this 
        ++currentNum

        val testIdentifier = description.toTestIdentifier()
        val test = testIdentifier.toTest()

        currentTest = test
        Log.i(
            LOG_TAG,
            "testStarted | description: $description, testIdentifier: $testIdentifier, test: $test"
        )
    }

    override fun testAssumptionFailure(failure: Failure) {
        super.testAssumptionFailure(failure)
        instance = this 

        val description = failure.description
        val testIdentifier = description.toTestIdentifier()
        val test = testIdentifier.toTest()

        currentTest = test
        Log.i(
            LOG_TAG,
            "testAssumptionFailure | failure: $failure | description: $description, testIdentifier: $testIdentifier, test: $test"
        )

        sendStatus(
            REPORT_VALUE_RESULT_ASSUMPTION_FAILURE,
            Bundle().apply {
                putString(Instrumentation.REPORT_KEY_IDENTIFIER, REPORT_VALUE_ID)
                putString(REPORT_KEY_NAME_CLASS, description.className)
                putString(REPORT_KEY_NAME_TEST, description.methodName)
                putInt(REPORT_KEY_NUM_TOTAL, description.testCount())
                putInt(REPORT_KEY_NUM_CURRENT, currentNum)
                putString(
                    INSTRUMENTATION_STATUS_KEY_STEPS_RESULTS_JSON,
                    stepsJsonMap[test] ?: EMPTY_STEPS_RESULT_JSON
                )
            }
        )
    }

    override fun testFailure(failure: Failure) {
        super.testFailure(failure)
        instance = this 

        val description = failure.description
        val testIdentifier = description.toTestIdentifier()
        val test = testIdentifier.toTest()

        Log.i(
            LOG_TAG,
            "testAssumptionFailure | failure: $failure | description: $description, testIdentifier: $testIdentifier, test: $test"
        )

        currentTest = test
        sendStatus(
            REPORT_VALUE_RESULT_FAILURE,
            Bundle().apply {
                putString(Instrumentation.REPORT_KEY_IDENTIFIER, REPORT_VALUE_ID)
                putString(REPORT_KEY_NAME_CLASS, description.className)
                putString(REPORT_KEY_NAME_TEST, description.methodName)
                putInt(REPORT_KEY_NUM_TOTAL, description.testCount())
                putInt(REPORT_KEY_NUM_CURRENT, currentNum)
                putString(
                    INSTRUMENTATION_STATUS_KEY_STEPS_RESULTS_JSON,
                    stepsJsonMap[test] ?: EMPTY_STEPS_RESULT_JSON
                )
            }
        )
    }

    override fun testFinished(description: Description) {
        super.testFinished(description)
        instance = this 

        val testIdentifier = description.toTestIdentifier()
        val test = testIdentifier.toTest()

        Log.i(
            LOG_TAG,
            "testFinished | description: $description, testIdentifier: $testIdentifier, test: $test"
        )

        currentTest = test
        sendStatus(
            REPORT_VALUE_RESULT_OK,
            Bundle().apply {
                putString(Instrumentation.REPORT_KEY_IDENTIFIER, REPORT_VALUE_ID)
                putString(REPORT_KEY_NAME_CLASS, description.className)
                putString(REPORT_KEY_NAME_TEST, description.methodName)
                putInt(REPORT_KEY_NUM_TOTAL, description.testCount())
                putInt(REPORT_KEY_NUM_CURRENT, currentNum)
                putString(
                    INSTRUMENTATION_STATUS_KEY_STEPS_RESULTS_JSON,
                    stepsJsonMap[test] ?: EMPTY_STEPS_RESULT_JSON
                )
            }
        )
    }

    override fun testIgnored(description: Description) {
        super.testIgnored(description)
        instance = this 

        val testIdentifier = description.toTestIdentifier()
        val test = testIdentifier.toTest()

        Log.i(
            LOG_TAG,
            "testIgnored | description: $description, testIdentifier: $testIdentifier, test: $test"
        )

        sendStatus(
            REPORT_VALUE_RESULT_OK,
            Bundle().apply {
                putString(Instrumentation.REPORT_KEY_IDENTIFIER, REPORT_VALUE_ID)
                putString(REPORT_KEY_NAME_CLASS, description.className)
                putString(REPORT_KEY_NAME_TEST, description.methodName)
                putInt(REPORT_KEY_NUM_TOTAL, description.testCount())
                putInt(REPORT_KEY_NUM_CURRENT, currentNum)
                putString(
                    INSTRUMENTATION_STATUS_KEY_STEPS_RESULTS_JSON,
                    stepsJsonMap[test] ?: EMPTY_STEPS_RESULT_JSON
                )
            }
        )
    }

    override fun testRunFinished(result: Result) {
        super.testRunFinished(result)
        instance = this 

        val resultDescription = """
        result.failureCount = ${result.failureCount}
        result.runCount = ${result.runCount}
        result.ignoreCount = ${result.ignoreCount}
        result.failures?.isNotEmpty = ${result.failures?.isNotEmpty()}
        result.wasSuccessful() = ${result.wasSuccessful()}
        result.runTime = ${result.runTime}
        """


        Log.i(LOG_TAG, "testRunFinished ---- \n $resultDescription")
    }

    fun attachStepsJson(stepsJson: String) {
        currentTest?.let {
            Log.i(LOG_TAG, "Attach steps json for $it")

            stepsJsonMap[it] = stepsJson
        }
    }

}