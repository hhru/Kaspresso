package com.kaspersky.kaspresso.report.impl

import com.google.gson.Gson
import com.kaspersky.kaspresso.report.ReportWriter
import com.kaspersky.kaspresso.testcases.models.info.TestInfo
import com.malinskiy.marathon.steps.StepsResultsProducer

/**
 * This [com.kaspersky.kaspresso.report.ReportWriter] processes [com.kaspersky.kaspresso.testcases.models.info.TestInfo]
 * for generating LogCat logs with <a href="https://docs.qameta.io/allure/#_steps">Allure's steps</a> info JSON.
 *
 * I/KASPRESSO: ---------------------------------------------------------------------------
 * I/KASPRESSO: TEST PASSED
 * I/KASPRESSO: ---------------------------------------------------------------------------
 * I/KASPRESSO: #AllureStepsInfoJson#: [{"attachments":[],"name":"My step 1","parameters":[],"stage":"finished","start":1568790287246,"status":"passed", "steps":[],"stop":1568790288184}]
 *
 * This logs should be processed by your's tests orchestrator (e.g. <a href="https://github.com/Malinskiy/marathon">Marathon</a>).
 */
class AllureReportWriter(
    private val stepsResultsProducer: StepsResultsProducer
) : ReportWriter {

    private val stepInfoConverter: StepInfoConverter by lazy { StepInfoConverter() }
    private val gson: Gson by lazy { Gson() }

    override fun processTestResults(testInfo: TestInfo) {
        val steps = testInfo.stepInfos.map { stepInfoConverter.convert(it) }
        val stepsJson = gson.toJson(steps)

        stepsResultsProducer.attachStepsResults(stepsJson)
    }
}