package com.kaspersky.kaspresso.runner

import org.junit.runner.Description


fun Description.toTestIdentifier() =
    TestIdentifier(
        this.className,
        this.methodName
    )