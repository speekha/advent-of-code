package com.adventofcode.dec2017.day3

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MemoryAccessorTest {

    @Test
    fun `compute positions`() {
        Assertions.assertEquals(Position(0, 0), MemoryAccessor().computePosition(1))
        Assertions.assertEquals(Position(1, 0), MemoryAccessor().computePosition(2))
        Assertions.assertEquals(Position(-1, 1), MemoryAccessor().computePosition(5))
    }

    @Test
    fun `compute taxicab distance`() {
        Assertions.assertEquals(5, MemoryAccessor().computeDistance(Position(0, 0), Position(2, 3)))
    }

    @Test
    fun `compute distances`() {
        Assertions.assertEquals(0, MemoryAccessor().computeDistance(1))
        Assertions.assertEquals(1, MemoryAccessor().computeDistance(2))
        Assertions.assertEquals(2, MemoryAccessor().computeDistance(3))
        Assertions.assertEquals(1, MemoryAccessor().computeDistance(4))
        Assertions.assertEquals(2, MemoryAccessor().computeDistance(5))
        Assertions.assertEquals(4, MemoryAccessor().computeDistance(21))
    }

    @Test
    fun `fill squares`() {
        Assertions.assertEquals(1, MemoryAccessor().fillSquares(1))
        Assertions.assertEquals(2, MemoryAccessor().fillSquares(2))
        Assertions.assertEquals(4, MemoryAccessor().fillSquares(3))
        Assertions.assertEquals(23, MemoryAccessor().fillSquares(20))
    }

    @Test
    fun `test real values`() {
        with(MemoryAccessor()) {
            val input = 368078
            Assertions.assertEquals(371, computeDistance(input))
            Assertions.assertEquals(369601, fillSquares(input))
        }
    }
}