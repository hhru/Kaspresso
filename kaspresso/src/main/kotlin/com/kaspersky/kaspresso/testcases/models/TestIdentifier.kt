package com.kaspersky.kaspresso.testcases.models

import org.junit.runner.Description


data class TestIdentifier(
    val className: String,
    val methodName: String
)

fun Description.toTestIdentifier() = TestIdentifier(className, methodName)