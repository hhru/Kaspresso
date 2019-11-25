[kaspresso](../index.md) / [com.kaspersky.kaspresso.report.impl](./index.md)

## Package com.kaspersky.kaspresso.report.impl

### Types

| Name | Summary |
|---|---|
| [AllureReportWriter](-allure-report-writer/index.md) | `class AllureReportWriter : `[`ReportWriter`](../com.kaspersky.kaspresso.report/-report-writer/index.md)<br>This [com.kaspersky.kaspresso.report.ReportWriter](../com.kaspersky.kaspresso.report/-report-writer/index.md) processes [com.kaspersky.kaspresso.testcases.models.info.TestInfo](../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md) for generating Allure's steps info JSON. After JSON generation it will be sent into special consumer which will propagate steps information further. |
| [StepInfoConverter](-step-info-converter/index.md) | `class StepInfoConverter`<br>Converts [com.kaspersky.kaspresso.testcases.models.info.StepInfo](../com.kaspersky.kaspresso.testcases.models.info/-step-info/index.md) into [io.qameta.allure.model.StepResult](#) JSON model. |
