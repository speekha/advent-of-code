package com.adventofcode.dec2020.day17

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ConwayCubesTester {

    val input = listOf(
        ".#.",
        "..#",
        "###"
    )

    @Test
    fun `should load initial state`() {
        val cubes = ConwayCubes(input)
        assertEquals(0, cubes.ranges[0].min)
        assertEquals(2, cubes.ranges[0].max)
        assertEquals(0, cubes.ranges[1].min)
        assertEquals(2, cubes.ranges[1].max)
        assertEquals(0, cubes.ranges[2].min)
        assertEquals(0, cubes.ranges[2].max)
        assertEquals(5, cubes.countActiveCubes())
    }

    @Test
    fun `should count neighbors properly`() {
        val cubes = ConwayCubes(input)
        assertEquals(1, cubes.countNeighbors(listOf(0, 0, 0)))
        assertEquals(1, cubes.countNeighbors(listOf(0, 2, 0)))
        assertEquals(2, cubes.countNeighbors(listOf(2, 2, 0)))
        assertEquals(3, cubes.countNeighbors(listOf(2, 1, 0)))
    }

    @Test
    fun `should compute first cycle`() {
        val cubes = ConwayCubes(input)
        cubes.iterate()
        assertEquals(11, cubes.countActiveCubes())
    }

    @Test
    fun `should compute first six cycles`() {
        val cubes = ConwayCubes(input)
        repeat(6) {
            cubes.iterate()
        }
        assertEquals(112, cubes.countActiveCubes())
    }

    @Test
    fun `should compute first actual six cycles`() {
        val cubes = ConwayCubes(readInputAsList())
        repeat(6) {
            cubes.iterate()
        }
        assertEquals(395, cubes.countActiveCubes())
    }

    @Test
    fun `should compute first cycle in 4D`() {
        val cubes = ConwayCubes(input, 4)
        cubes.iterate()
        assertEquals(29, cubes.countActiveCubes())
    }

    @Test
    fun `should compute first six cycles in 4D`() {
        val cubes = ConwayCubes(input, 4)
        repeat(6) {
            cubes.iterate()
        }
        assertEquals(848, cubes.countActiveCubes())
    }

    @Test
    fun `should compute first actual six cycles in 4D`() {
        val cubes = ConwayCubes(readInputAsList(), 4)
        repeat(6) {
            cubes.iterate()
        }
        assertEquals(2296, cubes.countActiveCubes())
    }
}
