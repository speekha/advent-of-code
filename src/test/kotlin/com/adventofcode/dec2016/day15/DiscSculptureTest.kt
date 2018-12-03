package com.adventofcode.dec2016.day15

import org.junit.Assert.*
import org.junit.Test

class DiscSculptureTest {

    val sculpture = DiscSculpture(Disc(5, 4), Disc(2, 1))

    val input = listOf(
            "Disc #1 has 5 positions; at time=0, it is at position 4.",
            "Disc #2 has 2 positions; at time=0, it is at position 1."
    )

    @Test
    fun `should find the disc position a certain time`() {
        assertEquals(4, sculpture.getDiscPosition(0, 0))
        assertEquals(0, sculpture.getDiscPosition(0, 1))
        assertEquals(0, sculpture.getDiscPosition(0, 6))
        assertEquals(1, sculpture.getDiscPosition(1, 0))
        assertEquals(0, sculpture.getDiscPosition(1, 1))
        assertEquals(0, sculpture.getDiscPosition(1, 7))
    }

    @Test
    fun `should detect if capsule falls through`() {
        assertFalse(sculpture.dropCapsule(0))
        assertTrue(sculpture.dropCapsule(5))
    }

    @Test
    fun `should calculate first opportunity`() {
        assertEquals(5, sculpture.calculateDropTime())
    }

    @Test
    fun `should parse text input`() {
        assertEquals(sculpture, parseFile(input))
    }
}