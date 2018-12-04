package com.adventofcode.dec2021.day16

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BitStreamTest {

        val input = "D2FE28"

    @Test
    fun `should read bit stream`() {
        val stream = BitsStream(input)
        assertEquals("110100101111111000101000", (0..23).joinToString("") { stream.readBit().toString() })
    }

    @Test
    fun `should read number`() {
        val stream = BitsStream(input)
        assertEquals(6, stream.readValue(3))
        assertEquals(4, stream.readValue(3))

    }
}