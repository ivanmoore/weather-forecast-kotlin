package com.teamoptimization

import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.format.Jackson

data class ForecastingResult(val name: String, val value: String) {
    companion object {
        val lens = Jackson.autoBody<ForecastingResult>().toLens()
    }
}

fun navyForecast(httpClient: HttpHandler, day: String, place: String, itemName: String): ForecastingResult =
    httpClient(
        Request(
            Method.GET,
            "https://sdaxdtn6ha.execute-api.eu-west-2.amazonaws.com/api/forecasting/$day/$place/$itemName"
        )
    ).let { response ->
        if (response.status.successful) {
            ForecastingResult.lens(response)
        } else {
            throw RuntimeException(response.toMessage())
        }
    }
