package com.oocode

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.http4k.core.Method
import org.http4k.core.Request
import org.junit.jupiter.api.Test
import java.time.DayOfWeek

class FakeForecasterHttpHandlerTest {
    @Test
    fun `would often not have a test for this sort of fake - example use is AcmeForecasterUsingFakeForecasterHttpHandlerTest`() {
        val fakeForecasterHttpHandler = FakeForecasterHttpHandler()
        val preCannedForecast = Forecast(1, 2, "cold")
        fakeForecasterHttpHandler.preCannedResponses[DayOfWeek.SATURDAY to "Liverpool"] = preCannedForecast

        val response = fakeForecasterHttpHandler(Request(Method.GET, "http://anything?day=SATURDAY&place=Liverpool"))

        assertThat(response.bodyString(), equalTo("""{"min":"1","max":"2","description":"cold"}"""))
        assertThat(fakeForecasterHttpHandler.calledWith, equalTo(listOf(DayOfWeek.SATURDAY to "Liverpool")))
    }
}
