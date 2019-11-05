[kaspresso](../../index.md) / [com.kaspersky.kaspresso.params](../index.md) / [FlakySafetyParams](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`FlakySafetyParams(timeoutMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = DEFAULT_TIMEOUT_MS, intervalMs: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = DEFAULT_INTERVAL_MS, allowedExceptions: `[`MutableSet`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-set/index.html)`<`[`Class`](https://developer.android.com/reference/java/lang/Class.html)`<out `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`>> = mutableSetOf(
            PerformException::class.java,
            NoMatchingViewException::class.java,
            AssertionError::class.java
        ))`

The class that holds all the necessary for [com.kaspersky.kaspresso.flakysafety.FlakySafetyProviderImpl](../../com.kaspersky.kaspresso.flakysafety/-flaky-safety-provider-impl/index.md) parameters.

