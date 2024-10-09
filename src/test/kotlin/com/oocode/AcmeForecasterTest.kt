package com.oocode

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.lens.Query
import org.http4k.lens.string
import org.junit.jupiter.api.Test
import java.time.DayOfWeek

class AcmeForecasterTest {
    @Test
    fun `converts response`() {
        val preCannedResponse =
            Response(Status.OK).body("""{"min": 11, "max": 18, "description": "Mild with occasional light rain showers"}""")
        val httpHandlerProducingPreCannedResponse: (request: Request) -> Response = { request ->
            doSomeAssertionsOnTheRequestIfYouWant(request)
            preCannedResponse
        }

        val acmeForecaster = AcmeForecaster(httpHandlerProducingPreCannedResponse)

        val forecast = acmeForecaster.forecast(DayOfWeek.FRIDAY, "Liverpool")

        assertThat(forecast, equalTo(Forecast(11, 18, "Mild with occasional light rain showers")))
    }

    private fun doSomeAssertionsOnTheRequestIfYouWant(request: Request) {
        val day = Query.string().required("day")
        assertThat(day(request), equalTo("Friday"))

        val place = Query.string().required("place")
        assertThat(place(request), equalTo("Liverpool"))
    }
}