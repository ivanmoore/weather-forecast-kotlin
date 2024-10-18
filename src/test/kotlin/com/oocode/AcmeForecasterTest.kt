package com.oocode

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.junit.jupiter.api.Test
import java.time.DayOfWeek

class AcmeForecasterTest {
    @Test
    fun `converts response`() {
        val preCannedResponse =
            Response(Status.OK).body("""{"min": 11, "max": 18, "description": "Mild with occasional light rain showers"}""")
        val httpHandlerProducingPreCannedResponse: HttpHandler = { request ->
            doSomeAssertionsOnTheRequestIfYouWant(request)
            preCannedResponse
        }

        val acmeForecaster = AcmeForecaster(httpHandlerProducingPreCannedResponse)

        val forecast = acmeForecaster.forecast(DayOfWeek.FRIDAY, "Liverpool")

        assertThat(forecast, equalTo(Forecast(11, 18, "Mild with occasional light rain showers")))
    }

    private fun doSomeAssertionsOnTheRequestIfYouWant(request: Request) {
        assertThat(request.query("day"), equalTo("Friday"))
        assertThat(request.query("place"), equalTo("Liverpool"))
    }
}