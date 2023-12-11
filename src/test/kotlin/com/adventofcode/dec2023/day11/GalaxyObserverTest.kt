package com.adventofcode.dec2023.day11

import com.adventofcode.actualInputList
import kotlin.test.Test
import kotlin.test.assertEquals

class GalaxyObserverTest {

    val input = listOf(
            "...#......",
            ".......#..",
            "#.........",
            "..........",
            "......#...",
            ".#........",
            ".........#",
            "..........",
            ".......#..",
            "#...#....."
    )

    @Test
    fun `should compute galaxy distances`() {
        val observer = GalaxyObserver(input)
        assertEquals(9, observer.galaxies.size)
        assertEquals(374, observer.sumUpDistances(2))
    }

    @Test
    fun `should compute actual galaxy distances`() {
        val observer = GalaxyObserver(actualInputList)
        // > 9287743
        assertEquals(9329143, observer.sumUpDistances(2))
    }


    @Test
    fun `should compute expanded galaxy distances`() {
        val observer = GalaxyObserver(input)
        assertEquals(1030, observer.sumUpDistances(10))
    }
    @Test
    fun `should compute actual expanded galaxy distances`() {
        val observer = GalaxyObserver(actualInputList)
        // < 710675618476
        assertEquals(710674907809, observer.sumUpDistances(1000000))
    }
}