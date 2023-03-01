package com.teamoptimization

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.http4k.client.OkHttp
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import java.math.BigDecimal

data class Location(val lat: BigDecimal, val long: BigDecimal)

open class LocatorClient {
    private val mapper = jacksonObjectMapper()
    private val httpClient: HttpHandler = OkHttp()

    fun locationOf(locationName: String): Location {
        val response = httpClient(Request(Method.GET, "https://jg2uit3u5j.execute-api.eu-west-2.amazonaws.com/api/location/$locationName"))
        return mapper.readValue(response.body.stream, Location::class.java)
    }
}