package com.oocode

import java.time.DayOfWeek

data class Forecast(val min: Int, val max: Int, val description: String)

interface Forecaster {
    fun forecast(day: DayOfWeek, place: String) : Forecast
}