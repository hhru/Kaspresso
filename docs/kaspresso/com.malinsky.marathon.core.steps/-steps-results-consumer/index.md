[kaspresso](../../index.md) / [com.malinsky.marathon.core.steps](../index.md) / [StepsResultsConsumer](./index.md)

# StepsResultsConsumer

`interface StepsResultsConsumer`

Interface for consumers of steps results information.

### Functions

| Name | Summary |
|---|---|
| [consume](consume.md) | `abstract fun consume(testIdentifier: `[`TestIdentifier`](../../com.kaspersky.kaspresso.testcases.models/-test-identifier/index.md)`, stepsResultsJson: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Method to send information about steps in JSON format into consumer to propagate it further correctly. |

### Inheritors

| Name | Summary |
|---|---|
| [StepsResultsConsumerImpl](../-steps-results-consumer-impl/index.md) | `class StepsResultsConsumerImpl : TestWatcher, `[`StepsResultsConsumer`](./index.md)<br>This [org.junit.rules.TestWatcher](#) writes information about test steps into adb shell's output through [android.app.Instrumentation.sendStatus](https://developer.android.com/reference/android/app/Instrumentation.html#sendStatus(int, android.os.Bundle)) when test ends with success or failure. When we writes unknown test events into adb shell's output they will be parsed by ddmlib as testMetrics field. |
