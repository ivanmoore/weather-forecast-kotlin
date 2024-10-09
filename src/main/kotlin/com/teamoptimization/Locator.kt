package com.teamoptimization

import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.format.Jackson
import java.math.BigDecimal

data class Location(val lat: BigDecimal, val long: BigDecimal) {
    companion object {
        val lens = Jackson.autoBody<Location>().toLens()
    }
}

fun findLocation(httpClient: HttpHandler, locationName: String): Location =
    httpClient(
        Request(GET, "https://jg2uit3u5j.execute-api.eu-west-2.amazonaws.com/api/location/$locationName")
    ).let { response ->
        if (response.status.successful) {
            Location.lens(response)
        } else {
            throw RuntimeException(response.toMessage())
        }
    }
