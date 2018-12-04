package com.adventofcode.dec2018.day11

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FuelManagerTest {

    @Test
    fun `should compute power levels`() {
        var manager = FuelManager(8)
        assertEquals(4, manager.getPower(3, 5))
        manager = FuelManager(57)
        assertEquals(-5, manager.getPower(122, 79))
        manager = FuelManager(39)
        assertEquals(0, manager.getPower(217, 196))
        manager = FuelManager(71)
        assertEquals(4, manager.getPower(101, 153))
    }

    @Test
    fun `should find highest cell group`() {
        val manager = FuelManager(18)
        assertEquals(33 to 45, manager.findHighestPowerGroup())
    }

    @Test
    fun `should find actual highest cell group`() {
        val manager = FuelManager(8772)
        assertEquals(235 to 31, manager.findHighestPowerGroup())
    }

    @Test
    fun `should find highest and largest cell group`() {
        val manager = FuelManager(18)
        assertEquals(Triple(90, 269, 16), manager.findHighestVariablePowerGroup())
    }

    @Test
    fun `should find actual highest and largest cell group`() {
        val manager = FuelManager(8772)
        assertEquals(Triple(241, 65, 10), manager.findHighestVariablePowerGroup())
    }
}