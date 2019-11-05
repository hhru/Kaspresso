[kaspresso](../../index.md) / [com.kaspersky.kaspresso.kaspresso](../index.md) / [Kaspresso](./index.md)

# Kaspresso

`data class Kaspresso`

The storage of all Kaspresso preferences and entities, such as [AdbServer](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md), [Device](../../com.kaspersky.kaspresso.device/-device/index.md) and different interceptors.

### Types

| Name | Summary |
|---|---|
| [Builder](-builder/index.md) | `class Builder`<br>The class for [Kaspresso](./index.md) configuration and initialization. This is the way to set Kaspresso preferences. |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Kaspresso(libLogger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`, testLogger: `[`UiTestLogger`](../../com.kaspersky.kaspresso.logger/-ui-test-logger.md)`, adbServer: `[`AdbServer`](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md)`, device: `[`Device`](../../com.kaspersky.kaspresso.device/-device/index.md)`, params: `[`Params`](../../com.kaspersky.kaspresso.params/-params/index.md)`, viewActionWatcherInterceptors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ViewActionWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.view/-view-action-watcher-interceptor/index.md)`>, viewAssertionWatcherInterceptors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ViewAssertionWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.view/-view-assertion-watcher-interceptor/index.md)`>, atomWatcherInterceptors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`AtomWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.view/-atom-watcher-interceptor/index.md)`>, webAssertionWatcherInterceptors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`WebAssertionWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.view/-web-assertion-watcher-interceptor/index.md)`>, viewBehaviorInterceptors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`ViewBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behavior/-view-behavior-interceptor.md)`>, dataBehaviorInterceptors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`DataBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behavior/-data-behavior-interceptor.md)`>, webBehaviorInterceptors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`WebBehaviorInterceptor`](../../com.kaspersky.kaspresso.interceptors.behavior/-web-behavior-interceptor.md)`>, stepWatcherInterceptors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`StepWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-step-watcher-interceptor/index.md)`>, testRunWatcherInterceptors: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`TestRunWatcherInterceptor`](../../com.kaspersky.kaspresso.interceptors.watcher.testcase/-test-run-watcher-interceptor/index.md)`>)`<br>The storage of all Kaspresso preferences and entities, such as [AdbServer](../../com.kaspersky.kaspresso.device.server/-adb-server/index.md), [Device](../../com.kaspersky.kaspresso.device/-device/index.md) and different interceptors. |
