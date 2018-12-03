package com.adventofcode.dec2017.day23

import org.junit.Assert
import org.junit.Test

class PrimeScreenerTest {

    @Test
    fun `test real values`() {
        with(PrimeScreener(a = 1)) {
            Assert.assertEquals(915, execute())
        }
    }
}