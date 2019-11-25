[kaspresso](../../index.md) / [com.kaspersky.kaspresso.report.impl](../index.md) / [AllureReportWriter](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`AllureReportWriter(stepsResultsConsumers: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`StepsResultsConsumer`](../../com.malinsky.marathon.core.steps/-steps-results-consumer/index.md)`>)`

This [com.kaspersky.kaspresso.report.ReportWriter](../../com.kaspersky.kaspresso.report/-report-writer/index.md) processes [com.kaspersky.kaspresso.testcases.models.info.TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)
for generating Allure's steps info JSON.
After JSON generation it will be sent into special consumer which will propagate steps information further.

