package com.teamoptimization

import org.http4k.client.JavaHttpClient
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.format.Jackson
import java.math.BigDecimal
import java.time.DayOfWeek

data class Forecast(val minTemp: Int, val maxTemp: Int, val description: String) {
    companion object {
        val lens = Jackson.autoBody<Forecast>().toLens()
    }
}

fun metOfficeForecast(httpClient: HttpHandler, day: Int, lat: BigDecimal, long: BigDecimal): Forecast =
    httpClient(
        Request(GET, "https://k7kic7lc35.execute-api.eu-west-2.amazonaws.com/api/forecaster/$day/$lat/$long")
    ).let { response ->
        if (response.status.successful) {
            Forecast.lens(response)
        } else {
            throw RuntimeException(response.toMessage())
        }
    }

fun metOfficeForecast(day: String, place: String) {
    val httpClient = JavaHttpClient()
    val dayNumber = DayOfWeek.valueOf(day.uppercase()).value
    val location = findLocation(httpClient, place)
    val (minTemp, maxTemp, description) = metOfficeForecast(httpClient, dayNumber, location.lat, location.long)
    println("forecaster: $place day=$day min=$minTemp max=$maxTemp description=$description")
}
