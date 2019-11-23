package com.malinsky.marathon.core.steps

import com.kaspersky.kaspresso.testcases.models.TestIdentifier

interface StepsResultsConsumer {

    fun consume(testIdentifier: TestIdentifier, stepsResultsJson: String)
}