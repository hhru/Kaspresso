[kaspresso](../../index.md) / [com.malinsky.marathon.core.steps](../index.md) / [StepsResultsConsumerImpl](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`StepsResultsConsumerImpl(uiTestLogger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`)`

This [org.junit.rules.TestWatcher](#) writes information about test steps into adb shell's output
through [android.app.Instrumentation.sendStatus](https://developer.android.com/reference/android/app/Instrumentation.html#sendStatus(int, android.os.Bundle)) when test ends with success or failure.
When we writes unknown test events into adb shell's output they will be parsed by ddmlib as
testMetrics field.

This testMetrics should be processed by your's tests orchestrator (e.g. Marathon).

