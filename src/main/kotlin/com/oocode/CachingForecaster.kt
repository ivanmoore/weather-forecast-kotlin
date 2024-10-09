package com.oocode

import java.time.DayOfWeek

class CachingForecaster(private val delegate: Forecaster) : Forecaster {
    override fun forecast(day: DayOfWeek, place: String): Forecast {
        TODO()
    }
}