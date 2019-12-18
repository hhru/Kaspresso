[kaspresso](../../index.md) / [com.kaspersky.kaspresso.enricher](../index.md) / [MainSectionEnricher](index.md) / [beforeMainSectionRun](./before-main-section-run.md)

# beforeMainSectionRun

`open fun beforeMainSectionRun(testInfo: `[`TestInfo`](../../com.kaspersky.kaspresso.testcases.models.info/-test-info/index.md)`, testContext: `[`TestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)`<`[`Data`](index.md#Data)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

This method will be invoked right before execution of "run" block in your test case.

### Parameters

`testInfo` -
* test information, such as test identifier

`testContext` -
* context of your test with data.
