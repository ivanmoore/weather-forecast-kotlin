package com.oocode

import org.junit.jupiter.api.Test

class CachingForecasterTest {
    @Test
    fun `what would be a good first test`() {
        val fakeForecaster = FakeForecaster()
        val forecaster = CachingForecaster(fakeForecaster)
    }
}
