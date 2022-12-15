package com.adventofcode.dec2022.day15

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RangeTest {

    @Test
    fun `should merge empty range`() {
        var ranges = emptyRange()
        ranges = ranges.addRange(1..3)
        assertEquals(listOf(1..3), ranges)
    }


    @Test
    fun `should merge non intersecting ranges`() {
        var ranges = emptyRange()
        ranges = ranges.addRange(1..3)
        ranges = ranges.addRange(6..8)
        assertEquals(listOf(1..3, 6..8), ranges)
    }

    @Test
    fun `should merge intersecting ranges`() {
        var ranges = emptyRange()
        ranges = ranges.addRange(1..3)
        ranges = ranges.addRange(2..8)
        assertEquals(listOf(1..8), ranges)
    }

    @Test
    fun `should merge consecutive ranges`() {
        var ranges = emptyRange()
        ranges = ranges.addRange(1..3)
        ranges = ranges.addRange(4..8)
        assertEquals(listOf(1..8), ranges)
    }

    @Test
    fun `should merge all intersecting ranges`() {
        var ranges = emptyRange()
        ranges = ranges.addRange(1..3)
        ranges = ranges.addRange(6..8)
        ranges = ranges.addRange(4..5)
        assertEquals(listOf(1..8), ranges)
    }


}