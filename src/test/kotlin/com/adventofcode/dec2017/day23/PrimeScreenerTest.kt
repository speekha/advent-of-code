package com.adventofcode.dec2017.day23

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PrimeScreenerTest {

    @Test
    fun `test real values`() {
        with(PrimeScreener(a = 1)) {
            Assertions.assertEquals(915, execute())
        }
    }
}