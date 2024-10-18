package com.oocode

import com.oocode.FakeForecasterHttpHandler.AcmeForecastingResult.Companion.lens
import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.with
import org.http4k.format.Jackson
import java.time.DayOfWeek

class FakeForecasterHttpHandler : HttpHandler {
    val calledWith = mutableListOf<Pair<DayOfWeek, String>>()
    val preCannedResponses = mutableMapOf<Pair<DayOfWeek, String>, Forecast>()

    override fun invoke(request: Request): Response {
        val dayOfWeek = DayOfWeek.valueOf(request.query("day")!!.uppercase())
        val place = request.query("place")
        val key = dayOfWeek to place!!
        calledWith.add(key)
        val forecast = preCannedResponses[key]!!
        return Response(OK).with(
            lens of AcmeForecastingResult(
                forecast.min.toString(),
                forecast.max.toString(),
                forecast.description
            )
        )
    }

    private data class AcmeForecastingResult(val min: String, val max: String, val description: String) {
        companion object {
            val lens = Jackson.autoBody<AcmeForecastingResult>().toLens()
        }
    }
}