[kaspresso](../../index.md) / [com.malinsky.marathon.core.steps](../index.md) / [StepsResultsConsumerImpl](./index.md)

# StepsResultsConsumerImpl

`class StepsResultsConsumerImpl : TestWatcher, `[`StepsResultsConsumer`](../-steps-results-consumer/index.md)

This [org.junit.rules.TestWatcher](#) writes information about test steps into adb shell's output
through [android.app.Instrumentation.sendStatus](https://developer.android.com/reference/android/app/Instrumentation.html#sendStatus(int, android.os.Bundle)) when test ends with success or failure.
When we writes unknown test events into adb shell's output they will be parsed by ddmlib as
testMetrics field.

This testMetrics should be processed by your's tests orchestrator (e.g. Marathon).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `StepsResultsConsumerImpl(logger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)` = UiTestLoggerImpl(LOG_TAG))`<br>This [org.junit.rules.TestWatcher](#) writes information about test steps into adb shell's output through [android.app.Instrumentation.sendStatus](https://developer.android.com/reference/android/app/Instrumentation.html#sendStatus(int, android.os.Bundle)) when test ends with success or failure. When we writes unknown test events into adb shell's output they will be parsed by ddmlib as testMetrics field. |

### Functions

| Name | Summary |
|---|---|
| [consume](consume.md) | `fun consume(testIdentifier: `[`TestIdentifier`](../../com.kaspersky.kaspresso.testcases.models/-test-identifier/index.md)`, stepsResultsJson: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Method to send information about steps in JSON format into consumer to propagate it further correctly. |
| [failed](failed.md) | `fun failed(e: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`, description: Description): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [skipped](skipped.md) | `fun skipped(e: AssumptionViolatedException, description: Description): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [succeeded](succeeded.md) | `fun succeeded(description: Description): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
