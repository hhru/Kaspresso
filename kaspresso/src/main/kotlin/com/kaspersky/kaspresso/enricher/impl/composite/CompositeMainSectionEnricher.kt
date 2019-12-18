package com.kaspersky.kaspresso.enricher.impl.composite

import com.kaspersky.kaspresso.enricher.MainSectionEnricher
import com.kaspersky.kaspresso.internal.extensions.other.forEachSafely
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.kaspersky.kaspresso.testcases.models.info.TestInfo

class CompositeMainSectionEnricher<Data>(
    private val mainSectionEnrichers: List<MainSectionEnricher<Data>>,
    private val exceptions: MutableList<Throwable>
) : MainSectionEnricher<Data> {

    override fun beforeMainSectionRun(testInfo: TestInfo, testContext: TestContext<Data>) {
        mainSectionEnrichers.forEachSafely(exceptions) { it.beforeMainSectionRun(testInfo, testContext) }
    }

    override fun afterMainSectionRun(testInfo: TestInfo, testContext: TestContext<Data>) {
        mainSectionEnrichers.forEachSafely(exceptions) { it.afterMainSectionRun(testInfo, testContext) }
    }

}