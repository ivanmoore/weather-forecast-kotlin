package com.oocode

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test
import java.time.DayOfWeek

class AcmeForecasterUsingFakeForecasterHttpHandlerTest {
    @Test
    fun `converts response`() {
        val fakeForecasterHttpHandler = FakeForecasterHttpHandler()
        val preCannedForecast = Forecast(1, 2, "cold")
        fakeForecasterHttpHandler.preCannedResponses[DayOfWeek.SATURDAY to "Here East"] = preCannedForecast

        val fakeForecaster = AcmeForecaster(fakeForecasterHttpHandler)
        val returnedForecast = fakeForecaster.forecast(DayOfWeek.SATURDAY, "Here East")

        assertThat(returnedForecast, equalTo(preCannedForecast))
        assertThat(fakeForecasterHttpHandler.calledWith, equalTo(listOf(DayOfWeek.SATURDAY to "Here East")))
    }
}
