package com.adventofcode.dec2020.day11

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SeatManagerTest {

    val input = listOf(
        "L.LL.LL.LL",
        "LLLLLLL.LL",
        "L.L.L..L..",
        "LLLL.LL.LL",
        "L.LL.LL.LL",
        "L.LLLLL.LL",
        "..L.L.....",
        "LLLLLLLLLL",
        "L.LLLLLL.L",
        "L.LLLLL.LL"
    )

    @Test
    fun `should compute first iteration`() {
        val result = """#.##.##.##
                       |#######.##
                       |#.#.#..#..
                       |####.##.##
                       |#.##.##.##
                       |#.#####.##
                       |..#.#.....
                       |##########
                       |#.######.#
                       |#.#####.##""".trimMargin()
        val manager = SeatManager(input)
        manager.iterate(4, false)
        assertEquals(result, manager.seatsAsString)
    }

    @Test
    fun `should compute second iteration`() {
        val result = """#.LL.L#.##
                       |#LLLLLL.L#
                       |L.L.L..L..
                       |#LLL.LL.L#
                       |#.LL.LL.LL
                       |#.LLLL#.##
                       |..L.L.....
                       |#LLLLLLLL#
                       |#.LLLLLL.L
                       |#.#LLLL.##""".trimMargin()
        val manager = SeatManager(input)
        manager.iterate(4, false)
        manager.iterate(4, false)
        assertEquals(result, manager.seatsAsString)
    }

    @Test
    fun `should count occupied seats at equilibrium`() {
        val manager = SeatManager(input)
        manager.iterateUntilEquilibrium(4, false)
        assertEquals(37, manager.countOccupiedSeats())
    }

    @Test
    fun `should count occupied seats at equilibrium in actual data`() {
        val manager = SeatManager(readInputAsList())
        manager.iterateUntilEquilibrium(4, false)
        assertEquals(2406, manager.countOccupiedSeats())
    }

    @Test
    fun `should compute second iteration with higher threshold`() {
        val result = """#.LL.LL.L#
                       |#LLLLLL.LL
                       |L.L.L..L..
                       |LLLL.LL.LL
                       |L.LL.LL.LL
                       |L.LLLLL.LL
                       |..L.L.....
                       |LLLLLLLLL#
                       |#.LLLLLL.L
                       |#.LLLLL.L#""".trimMargin()
        val manager = SeatManager(input)
        manager.iterate(5, true)
        manager.iterate(5, true)
        assertEquals(result, manager.seatsAsString)
    }

    @Test
    fun `should count occupied seats at equilibrium with higher threashold in actual data`() {
        val manager = SeatManager(readInputAsList())
        manager.iterateUntilEquilibrium(5, true)
        assertEquals(2149, manager.countOccupiedSeats())
    }

}