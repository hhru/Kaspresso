package com.malinsky.marathon.core.steps

import android.os.Bundle
import android.util.Log
import androidx.test.internal.runner.listener.InstrumentationResultPrinter.*
import androidx.test.platform.app.InstrumentationRegistry
import com.kaspersky.kaspresso.testcases.models.TestIdentifier
import com.kaspersky.kaspresso.testcases.models.toTestIdentifier
import org.junit.AssumptionViolatedException
import org.junit.rules.TestWatcher
import org.junit.runner.Description


class StepsResultsConsumerImpl : TestWatcher(), StepsResultsConsumer {

    companion object {
        private const val INSTRUMENTATION_KEY_STEPS_RESULTS_JSON = "marathon.stepsResultsJson"
        private const val EMPTY_STEPS_RESULTS_JSON = "[]"
    }


    private val stepsResultsJsonMap = mutableMapOf<TestIdentifier, String>()


    override fun consume(testIdentifier: TestIdentifier, stepsResultsJson: String) {
        Log.i("STEPS CONSUMER", "consume [$testIdentifier]")
        stepsResultsJsonMap[testIdentifier] = stepsResultsJson
    }


    override fun failed(e: Throwable, description: Description) {
        super.failed(e, description)
        val testIdentifier = description.toTestIdentifier()
        Log.i("STEPS CONSUMER", "failed [$testIdentifier]")
        sendStatus(REPORT_VALUE_RESULT_FAILURE, description)
    }

    override fun skipped(e: AssumptionViolatedException, description: Description) {
        super.skipped(e, description)
        val testIdentifier = description.toTestIdentifier()
        Log.i("STEPS CONSUMER", "skipped [$testIdentifier]")
        sendStatus(REPORT_VALUE_RESULT_ASSUMPTION_FAILURE, description)
    }

    override fun succeeded(description: Description) {
        super.succeeded(description)
        val testIdentifier = description.toTestIdentifier()
        Log.i("STEPS CONSUMER", "succeeded [$testIdentifier]")
        sendStatus(REPORT_VALUE_RESULT_OK, description)
    }


    private fun sendStatus(code: Int, description: Description) {
        val stepsResultsJson = stepsResultsJsonMap[description.toTestIdentifier()]
            ?: EMPTY_STEPS_RESULTS_JSON
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