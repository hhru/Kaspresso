[kaspresso](../index.md) / [com.malinsky.marathon.core.steps](./index.md)

## Package com.malinsky.marathon.core.steps

### Types

| Name | Summary |
|---|---|
| [StepsResultsConsumer](-steps-results-consumer/index.md) | `interface StepsResultsConsumer`<br>Interface for consumers of steps results information. |
| [StepsResultsConsumerImpl](-steps-results-consumer-impl/index.md) | `class StepsResultsConsumerImpl : TestWatcher, `[`StepsResultsConsumer`](-steps-results-consumer/index.md)<br>This [org.junit.rules.TestWatcher](#) writes information about test steps into adb shell's output through [android.app.Instrumentation.sendStatus](https://developer.android.com/reference/android/app/Instrumentation.html#sendStatus(int, android.os.Bundle)) when test ends with success or failure. When we writes unknown test events into adb shell's output they will be parsed by ddmlib as testMetrics field. |
