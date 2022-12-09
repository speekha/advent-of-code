package com.adventofcode.dec2022.day06

import com.adventofcode.actualInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ComDeviceTest {

    val input = "mjqjpqmgbljsphdztnvjfqwrcgsmlb"

    @Test
    fun `should find marker`() {
        val device = ComDevice()
        assertEquals(7, device.findPacketMarker(input))
    }

    @Test
    fun `should find actual marker`() {
        val device = ComDevice()
        assertEquals(1282, device.findPacketMarker(actualInput))
    }

    @Test
    fun `should find message`() {
        val device = ComDevice()
        assertEquals(19, device.findMessageMarker(input))
    }

    @Test
    fun `should find actual message`() {
        val device = ComDevice()
        assertEquals(3513, device.findMessageMarker(actualInput))
    }
}