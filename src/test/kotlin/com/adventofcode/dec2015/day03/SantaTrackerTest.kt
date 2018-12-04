package com.adventofcode.dec2015.day03

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SantaTrackerTest {

    @Test
    fun `should track Sant's position`() {
        countHouses(">", 2)
        countHouses("^>v<", 4)
        countHouses("^v^v^v^v^v", 2)
    }

    fun countHouses(input: String, count: Int) {
        val tracker = SantaTracker()
        tracker.move(input)
        assertEquals(count, tracker.deliveredHouses().size)
    }

    @Test
    fun `should count houses in actual data`() {
        countHouses(readInputAsList()[0], 2572)
    }

    @Test
    fun `should count houses with robot in actual data`() {
        val input = readInputAsList()[0]
        val santa = SantaTracker()
        santa.move(input.filterIndexed { index, _ -> index % 2 == 0 })
        val robo = SantaTracker()
        robo.move(input.filterIndexed { index, _ -> index % 2 == 1 })
        assertEquals(2631, (santa.deliveredHouses() + robo.deliveredHouses()).size)
    }
}