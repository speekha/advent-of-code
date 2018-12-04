package com.adventofcode.dec2021.day17

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ProbeNavigatorTest {

    val input = "target area: x=20..30, y=-10..-5"

    @Test
    fun `should compute position`() {
        val probe = Probe(velX = 7, velY = 2)
        probe.advance()
        assertEquals(Probe(7, 2, 6, 1), probe)
        probe.advance()
        assertEquals(Probe(13, 3, 5, 0), probe)
    }

    @Test
    fun `should load target`() {
        val targeting = TargetingSystem(input)
        assertEquals(20..30, targeting.xRange)
        assertEquals(-10..-5, targeting.yRange)
    }

    @Test
    fun `should aim high for target`() {
        val targeting = TargetingSystem(input)
        assertEquals(45, targeting.findHighestPoint())
    }


}