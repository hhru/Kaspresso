package com.kaspersky.kaspresso.testcases.api.testcase

import android.util.Log
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.logging.TestRunLoggerWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.report.BuildStepReportWatcherInterceptor
import com.kaspersky.kaspresso.interceptors.watcher.testcase.impl.screenshot.TestRunnerScreenshotWatcherInterceptor
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.report.impl.AllureReportWriter
import com.kaspersky.kaspresso.testcases.core.sections.AfterTestSection
import com.kaspersky.kaspresso.testcases.core.sections.BeforeTestSection
import com.kaspersky.kaspresso.testcases.core.sections.MainTestSection
import com.kaspersky.kaspresso.testcases.core.sections.TransformSection
import com.kaspersky.kaspresso.testcases.core.testcontext.BaseTestContext
import com.kaspersky.kaspresso.testcases.models.TestBody
import com.kaspersky.kaspresso.testcases.models.toTestIdentifier
import com.malinsky.marathon.core.steps.StepsResultsConsumerImpl
import org.junit.Rule
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * The base class for all parametrized test cases. Extend this class with a single base project-wide inheritor of
 * [TestCase] as a parent for all actual project-wide test cases. Nesting test cases are not recommended, use
 * [com.kaspersky.kaspresso.testcases.api.scenario.Scenario] instead.
 *
 * @param InitData data initialized in before section.
 * @param Data data transformed from [InitData] by special function.
 */
abstract class BaseTestCase<InitData, Data>(
    kaspressoBuilder: Kaspresso.Builder = Kaspresso.Builder.default(),
    private val dataProducer: (((InitData.() -> Unit)?) -> Data)
) {
    private val testCaseName = javaClass.simpleName

    internal lateinit var kaspresso: Kaspresso

    @get:Rule
    internal val stepsResultsConsumer = StepsResultsConsumerImpl()

    @get:Rule
    internal val testWatcher = object : TestWatcher() {
        override fun starting(description: Description) {
            super.starting(description)

            Log.i("BASE_TEST_CASE", "starting [${description.toTestIdentifier()}]")

            kaspressoBuilder.testIdentifier = description.toTestIdentifier()
            kaspressoBuilder.stepsResultsConsumers = listOf(stepsResultsConsumer)
            kaspressoBuilder.testRunWatcherInterceptors = mutableListOf(
                TestRunLoggerWatcherInterceptor(kaspressoBuilder.libLogger),
                TestRunnerScreenshotWatcherInterceptor(kaspressoBuilder.screenshots),
                BuildStepReportWatcherInterceptor(AllureReportWriter(kaspressoBuilder.stepsResultsConsumers))
            )

            kaspresso = kaspressoBuilder.build()
        }
    }

    /**
     * Starts the building of a test, sets the [BeforeTestSection] actions and returns an existing instance of
     * [AfterTestSection] to continue the building a test.
     *
     * @param testName a name of the test.
     * @param actions actions to invoke in before test section.
     * @return an existing instance of [AfterTestSection].
     */
    protected fun before(
        testName: String = testCaseName,
        actions: BaseTestContext.() -> Unit
    ): AfterTestSection<InitData, Data> {

        val testBodyBuilder = createBaseTestBodyBuilder(testName)

        return BeforeTestSection(kaspresso, testBodyBuilder).beforeTest(actions)
    }

    /**
     * Starts the building of a test from data initialization section. Sets
     * [com.kaspersky.kaspresso.testcases.core.sections.InitSection] actions and returns an existing instance of
     * [MainTestSection] to continue building a tests.
     *
     * @param testName a name of the test.
     * @param actions actions to be wrapped and invoked before the test for data initialization
     * @return an existing instance of [MainTestSection].
     */
    protected fun init(
        testName: String = testCaseName,
        actions: InitData.() -> Unit
    ): TransformSection<Data> {
        val testBodyBuilder = createBaseTestBodyBuilder(testName).apply {
            initActions = actions
        }

        return MainTestSection(kaspresso, testBodyBuilder)
    }

    private fun createBaseTestBodyBuilder(testName: String): TestBody.Builder<InitData, Data> {
        return TestBody.Builder<InitData, Data>().apply {
            this.testName = testName
            this.dataProducer = this@BaseTestCase.dataProducer
        }
    }
}