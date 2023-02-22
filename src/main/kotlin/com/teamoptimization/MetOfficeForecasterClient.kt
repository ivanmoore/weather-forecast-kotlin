package com.teamoptimization

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.http4k.client.OkHttp
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import java.math.BigDecimal

data class Forecast(val minTemp: Int, val maxTemp: Int, val description: String)

open class MetOfficeForecasterClient {
    private val mapper = jacksonObjectMapper()
    private val httpClient: HttpHandler = OkHttp()

    fun forecast(day: Int, lat: BigDecimal, long: BigDecimal): Forecast {
        val response = httpClient(Request(Method.GET, "http://localhost:9232/forecaster/$day/$lat/$long"))
        return mapper.readValue(response.body.stream, Forecast::class.java)
    }
}