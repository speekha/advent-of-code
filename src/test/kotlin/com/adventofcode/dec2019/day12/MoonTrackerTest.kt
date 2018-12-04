package com.adventofcode.dec2019.day12

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File

@Disabled
class MoonTrackerTest {

    val input = listOf("<x=-1, y=0, z=2>",
            "<x=2, y=-10, z=-7>",
            "<x=4, y=-8, z=8>",
            "<x=3, y=5, z=-1>")

    @Test
    fun `should parse input`() {
        val tracker = MoonTracker(input)
        assertEquals(listOf(
                Moon(-1, 0, 2, 0, 0, 0),
                Moon(2, -10, -7, 0, 0, 0),
                Moon(4, -8, 8, 0, 0, 0),
                Moon(3, 5, -1, 0, 0, 0)
        ), tracker.moons)
    }

    @Test
    fun `should iterate one step`() {
        val tracker = MoonTracker(input)
        tracker.iterate()
        assertEquals(listOf(
                Moon(2, -1, 1, 3, -1, -1),
                Moon(3, -7, -4, 1, 3, 3),
                Moon(1, -7, 5, -3, 1, -3),
                Moon(2, 2, 0, -1, -3, 1)
        ), tracker.moons)
    }

    @Test
    fun `should iterate two steps`() {
        val tracker = MoonTracker(input)
        tracker.iterate()
        tracker.iterate()
        assertEquals(listOf(
                Moon(5, -3, -1, 3, -2, -2),
                Moon(1, -2, 2, -2, 5, 6),
                Moon(1, -4, -1, 0, 3, -6),
                Moon(1, -4, 2, -1, -6, 2)
        ), tracker.moons)
    }

    @Test
    fun `should commpute total energy`() {
        val tracker = MoonTracker(input)
        repeat(10) {
            tracker.iterate()
        }
        assertEquals(179, tracker.computeTotalEnergy())
    }

    @Test
    fun `should commpute actual total energy`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2019/day12/input.txt").readLines()
        val tracker = MoonTracker(input)
        repeat(1000) {
            tracker.iterate()
        }
        assertEquals(9743, tracker.computeTotalEnergy())
    }

    @Test
    fun `should find loop length`() {
        val tracker = MoonTracker(input)
        assertEquals(2772, tracker.findLoopLength())
    }

    @Test
    fun `should find actual loop length`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2019/day12/input.txt").readLines()
        val tracker = MoonTracker(input)
        assertEquals(288684633706728, tracker.findLoopLength())
    }

}