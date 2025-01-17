package com.kaspersky.kaspresso.enricher

import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

/**
 * Special object for enriching 'run'-block functionality.
 * With this object you can add some additional test steps for each TestCase, that has this enricher, like this:
 *
 * <code>
 * class MyMainSectionEnricher : MainSectionEnricher<TestCaseData> {
 *
 *     override fun beforeMainSectionRun(testInfo: TestInfo, testContext: TestContext<TestCaseData>) {
 *         with(testContext) {
 *             step("New step before 'run' block") {
 *                step("Nested step inside") {
 *                    // do additional stuff
 *                }
 *             }
 *         }
 *     }
 * }
 * </code>
 *
 * @param Data - The same data type as in your [com.kaspersky.kaspresso.testcases.api.testcase.BaseTestCase].
 */
interface MainSectionEnricher<Data> {

    /**
     * This method will be invoked right before execution of "run" block in your test case.
     *
     * @param testInfo - test information, such as test identifier
     * @param testContext - context of your test with data.
     */
    fun beforeMainSectionRun(testInfo: TestInfo, testContext: TestContext<Data>) = Unit

    /**
     * This method will be invoked right after execution of "run" block in your test case.
     *
     * @param testInfo - test information, such as test identifier
     * @param testContext - context of your test with data.
     */
    fun afterMainSectionRun(testInfo: TestInfo, testContext: TestContext<Data>) = Unit

}