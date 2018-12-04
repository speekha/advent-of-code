package com.adventofcode.dec2017.day11

import com.adventofcode.dec2017.day11.Direction.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GridMapperTest {

    @Test
    fun `should parse path`() {
        val input = "n,ne,se,s,sw,nw"
        assertEquals(listOf(n, ne, se, s, sw, nw),
                GridMapper().parsePath(input))
    }

    @Test
    fun `should be 3`() {
        val input = "ne,ne,ne"
        assertEquals(3, GridMapper().computeDistance(input))
    }

    @Test
    fun `should be 0`() {
        val input = "ne,ne,sw,sw"
        assertEquals(0, GridMapper().computeDistance(input))
    }

    @Test
    fun `should be 2`() {
        val input = "ne,ne,s,s"
        assertEquals(2, GridMapper().computeDistance(input))
    }

    @Test
    fun `should be 3 too`() {
        val input = "se,sw,se,sw,sw"
        assertEquals(3, GridMapper().computeDistance(input))
    }

    @Test
    fun `max should be 3`() {
        val input = "ne,ne,ne"
        assertEquals(3, GridMapper().findFurthestPoint(input))
    }

    @Test
    fun `max should be 2`() {
        val input = "ne,ne,sw,sw"
        assertEquals(2, GridMapper().findFurthestPoint(input))
    }

    @Test
    fun `max should be 2 too`() {
        val input = "ne,ne,s,s"
        assertEquals(2, GridMapper().findFurthestPoint(input))
    }

    @Test
    fun `max should be 3 too`() {
        val input = "se,sw,se,sw,sw"
        assertEquals(3, GridMapper().findFurthestPoint(input))
    }

}