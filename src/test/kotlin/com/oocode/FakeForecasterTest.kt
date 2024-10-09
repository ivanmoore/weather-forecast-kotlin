package com.oocode

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test
import java.time.DayOfWeek

class FakeForecasterTest {
    @Test
    fun `would not usually have a test for this sort of Fake, but here as an example of its use`() {
        val fakeForecaster = FakeForecaster()
        val preCannedForecast = Forecast(1, 2, "cold")
        fakeForecaster.preCannedResponses[DayOfWeek.SATURDAY to "Here East"] = preCannedForecast

        val returnedForecast = fakeForecaster.forecast(DayOfWeek.SATURDAY, "Here East")

        assertThat(returnedForecast, equalTo(preCannedForecast))
        assertThat(fakeForecaster.calledWith, equalTo(listOf(DayOfWeek.SATURDAY to "Here East")))
    }
}
