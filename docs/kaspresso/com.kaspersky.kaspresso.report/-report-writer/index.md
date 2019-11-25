[kaspresso](../../index.md) / [com.kaspersky.kaspresso.report](../index.md) / [ReportWriter](./index.md)

# ReportWriter

`interface ReportWriter`

### Functions

| Name | Summary |
|---|---|
| [processTestResults](process-test-results.md) | `abstract fun processTestResults(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [AllureReportWriter](../../com.kaspersky.kaspresso.report.impl/-allure-report-writer/index.md) | `class AllureReportWriter : `[`ReportWriter`](./index.md)<br>This [com.kaspersky.kaspresso.report.ReportWriter](./index.md) processes [com.kaspersky.kaspresso.testcases.models.info.TestInfo](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md) for generating Allure's steps info JSON. After JSON generation it will be sent into special consumer which will propagate steps information further. |
