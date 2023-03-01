package com.teamoptimization

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.http4k.client.OkHttp
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import java.time.DayOfWeek

data class ForecastingResult(val name: String, val value: String)

open class NavyForecastingClient {
    private val mapper = jacksonObjectMapper()
    private val httpClient: HttpHandler = OkHttp()

    fun desc(day: DayOfWeek, place: String): String {
        return forecastingResult(day, place, "desc").value
    }
    fun min(day: DayOfWeek, place: String): String {
        return forecastingResult(day, place, "min").value
    }
    fun max(day: DayOfWeek, place: String): String {
        return forecastingResult(day, place, "max").value
    }

    private fun forecastingResult(day: DayOfWeek, place: String, itemName: String): ForecastingResult {
        val response = httpClient(Request(Method.GET,
            "https://sdaxdtn6ha.execute-api.eu-west-2.amazonaws.com/api/forecasting/${day.toString().lowercase().capitalize()}/$place/$itemName"))
        return mapper.readValue(response.body.stream, ForecastingResult::class.java)
    }
}