package com.adventofcode.dec2017.day6

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MemoryManagerTest {

    @Test
    fun `test redistribution`() {
        assertEquals(1, MemoryManager().reallocationRemainder(0, 4, 2 to 7), "0")
        assertEquals(1, MemoryManager().reallocationRemainder(1, 4, 2 to 7), "1")
        assertEquals(0, MemoryManager().reallocationRemainder(2, 4, 2 to 7), "2")
        assertEquals(1, MemoryManager().reallocationRemainder(3, 4, 2 to 7), "3")
    }

    @Test
    fun `redistribution should be 2 4 1 2`() {
        val input = intArrayOf(0, 2, 7, 0)
        assertArrayEquals(intArrayOf(2, 4, 1, 2), MemoryManager().redistributeMemory(input))
    }

    @Test
    fun `redistribution should be 3 1 2 3`() {
        val input = intArrayOf(2, 4, 1, 2)
        assertArrayEquals(intArrayOf(3, 1, 2, 3), MemoryManager().redistributeMemory(input))
    }

    @Test
    fun `redistribution should be 0 2 3 4`() {
        val input = intArrayOf(3, 1, 2, 3)
        assertArrayEquals(intArrayOf(0, 2, 3, 4), MemoryManager().redistributeMemory(input))
    }

    @Test
    fun `redistribution should be 1 3 4 1`() {
        val input = intArrayOf(0, 2, 3, 4)
        assertArrayEquals(intArrayOf(1, 3, 4, 1), MemoryManager().redistributeMemory(input))
    }

    @Test
    fun `redistribution should be 2 4 1 2 again`() {
        val input = intArrayOf(1, 3, 4, 1)
        assertArrayEquals(intArrayOf(2, 4, 1, 2), MemoryManager().redistributeMemory(input))
    }

    @Test
    fun `find infinite loop`() {
        val input = intArrayOf(0, 2, 7, 0)
        assertEquals(5, MemoryManager().redistributeMemoryCompletely(input))
    }

    @Test
    fun `count redistribution cycle length`() {
        val input = intArrayOf(0, 2, 7, 0)
        assertEquals(4, MemoryManager().findRedistributionCycle(input))
    }

    @Test
    fun `test real values`() {
        val input = "14 0 15 12 11 11 3 5 1 6 8 4 9 1 8 4".split(" ").map { it.toInt() }.toIntArray()
        with(MemoryManager()) {
            assertEquals(11137, redistributeMemoryCompletely(input))
            assertEquals(1037, findRedistributionCycle(input))
        }
    }
}