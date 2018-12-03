package com.adventofcode.dec2017.day3

import org.junit.Assert
import org.junit.Ignore
import org.junit.Test

class MemoryAccessorTest {

    @Test
    fun `compute positions`() {
        Assert.assertEquals(Position(0, 0), MemoryAccessor().computePosition(1))
        Assert.assertEquals(Position(1, 0), MemoryAccessor().computePosition(2))
        Assert.assertEquals(Position(-1, 1), MemoryAccessor().computePosition(5))
    }

    @Test
    fun `compute taxicab distance`() {
        Assert.assertEquals(5, MemoryAccessor().computeDistance(Position(0, 0), Position(2, 3)))
    }

    @Test
    fun `compute distances`() {
        Assert.assertEquals(0, MemoryAccessor().computeDistance(1))
        Assert.assertEquals(1, MemoryAccessor().computeDistance(2))
        Assert.assertEquals(2, MemoryAccessor().computeDistance(3))
        Assert.assertEquals(1, MemoryAccessor().computeDistance(4))
        Assert.assertEquals(2, MemoryAccessor().computeDistance(5))
        Assert.assertEquals(4, MemoryAccessor().computeDistance(21))
    }

    @Test
    fun `fill squares`() {
        Assert.assertEquals(1, MemoryAccessor().fillSquares(1))
        Assert.assertEquals(2, MemoryAccessor().fillSquares(2))
        Assert.assertEquals(4, MemoryAccessor().fillSquares(3))
        Assert.assertEquals(23, MemoryAccessor().fillSquares(20))
    }

    @Test
    fun `test real values`() {
        with(MemoryAccessor()) {
            val input = 368078
            Assert.assertEquals(371, computeDistance(input))
            Assert.assertEquals(369601, fillSquares(input))
        }
    }
}