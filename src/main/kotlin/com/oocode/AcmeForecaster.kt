package com.oocode

import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.format.Jackson
import java.time.DayOfWeek

class AcmeForecaster(private val httpHandler: HttpHandler) : Forecaster {
    override fun forecast(day: DayOfWeek, place: String): Forecast {
        val dayAsString = day.toString().lowercase().replaceFirstChar { it.uppercase() }
        val forecast = acmeForecast(dayAsString, place)
        return Forecast(forecast.min.toInt(), forecast.max.toInt(), forecast.description)
    }

    private fun acmeForecast(day: String, place: String): AcmeForecastingClientResult =
        httpHandler(
            Request(GET, "https://pqjbv9i19c.execute-api.eu-west-2.amazonaws.com/api/forecast?place=$place&day=$day")
        ).let { response ->
            if (response.status.successful) {
                AcmeForecastingClientResult.lens(response)
            } else {
                throw RuntimeException(response.toMessage())
            }
        }

    private data class AcmeForecastingClientResult(val min: String, val max: String, val description: String) {
        companion object {
            val lens = Jackson.autoBody<AcmeForecastingClientResult>().toLens()
        }
    }
}
