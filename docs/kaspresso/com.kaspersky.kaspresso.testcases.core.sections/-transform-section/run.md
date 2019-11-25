[kaspresso](../../index.md) / [com.kaspersky.kaspresso.testcases.core.sections](../index.md) / [TransformSection](index.md) / [run](./run.md)

# run

`abstract fun run(steps: `[`TestContext`](../../com.kaspersky.kaspresso.testcases.core.testcontext/-test-context/index.md)`<`[`Data`](index.md#Data)`>.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Runs:

1. Optional [BeforeTestSection](../-before-test-section/index.md),
2. Optional [InitSection.init](../-init-section/init.md),
3. Optional [transform](transform.md)'s sections (only if [InitSection.init](../-init-section/init.md) was called before),
4. [MainTestSection](../-main-test-section/index.md)'s steps,
5. [AfterTestSection](../-after-test-section/index.md). [AfterTestSection](../-after-test-section/index.md) is invoked even if [BeforeTestSection](../-before-test-section/index.md) or [BaseTestCase](../../com.kaspersky.kaspresso.testcases.api.testcase/-base-test-case/index.md)'s [steps](run.md#com.kaspersky.kaspresso.testcases.core.sections.TransformSection$run(kotlin.Function1((com.kaspersky.kaspresso.testcases.core.testcontext.TestContext((com.kaspersky.kaspresso.testcases.core.sections.TransformSection.Data)), kotlin.Unit)))/steps) failed.

### Parameters

`steps` - steps to run.