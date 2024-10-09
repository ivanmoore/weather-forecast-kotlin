package com.oocode

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.Test
import java.time.DayOfWeek

class CachingForecasterTest {
    @Test
    fun `delegates if never been asked for forecast before`() {
        val fakeForecaster = FakeForecaster()
        val preCannedForecast = Forecast(1, 2, "cold")
        fakeForecaster.preCannedResponses[DayOfWeek.SATURDAY to "Here East"] = preCannedForecast

        val forecaster = CachingForecaster(fakeForecaster)
        val actualForecast = forecaster.forecast(DayOfWeek.SATURDAY, "Here East")

        assertThat(actualForecast, equalTo(preCannedForecast))
        assertThat(fakeForecaster.calledWith, equalTo(listOf(DayOfWeek.SATURDAY to "Here East")))
    }
}
