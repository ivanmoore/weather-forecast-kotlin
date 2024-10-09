package com.teamoptimization

import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.format.Jackson

data class AcmeForecastingClientResult(val min: String, val max: String, val description: String) {
    companion object {
        val lens = Jackson.autoBody<AcmeForecastingClientResult>().toLens()
    }
}

fun acmeForecast(httpClient: HttpHandler, day: String, place: String): AcmeForecastingClientResult =
    httpClient(
        Request(Method.GET, "https://pqjbv9i19c.execute-api.eu-west-2.amazonaws.com/api/forecast?place=$place&day=$day")
    ).let { response ->
        if (response.status.successful) {
            AcmeForecastingClientResult.lens(response)
        } else {
            throw RuntimeException(response.toMessage())
        }
    }
