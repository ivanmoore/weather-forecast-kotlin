package com.oocode

import java.time.DayOfWeek

class FakeForecaster : Forecaster {
    val calledWith = mutableListOf<Pair<DayOfWeek, String>>()
    val preCannedResponses = mutableMapOf<Pair<DayOfWeek, String>, Forecast>()

    override fun forecast(day: DayOfWeek, place: String): Forecast {
        calledWith.add(day to place)
        return preCannedResponses[day to place]!!
    }
}