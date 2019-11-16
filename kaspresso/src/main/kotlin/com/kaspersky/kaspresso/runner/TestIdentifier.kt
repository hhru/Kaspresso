package com.kaspersky.kaspresso.runner


data class TestIdentifier(
    val className: String,
    val testName: String
) {

    fun toTest(): Test {
        val pkg = className.substringBeforeLast(".")
        val className = className.substringAfterLast(".")
        val methodName = testName
        return Test(pkg, className, methodName, emptyList())
    }

}