package com.oocode

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import moo
import org.junit.jupiter.api.Test

internal class MainKtTest {
    @Test
    fun `moo is moo`() {
        assertThat(moo(), equalTo("boo"))
    }
}
