package com.malinskiy.marathon.steps


class StepsResultsProducerImpl : StepsResultsProducer {

    override fun attachStepsResults(stepsResultsJson: String) {
        StepsRunListener.instance?.attachStepsJson(stepsResultsJson)
    }

}