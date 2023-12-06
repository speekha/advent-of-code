package com.adventofcode.dec2023.day06

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BoatRaceTest {

    val input = listOf(
        "Time:      7  15   30",
        "Distance:  9  40  200"
    )

    @Test
    fun `should count ways to win a race`() {
        val racer = BoatRace()
        assertEquals(4, racer.countWinOptions(7, 9))
        assertEquals(8, racer.countWinOptions(15, 40))
        assertEquals(9, racer.countWinOptions(30, 200))
    }

    @Test
    fun `should compute race results`() {
        val racer = BoatRace()
        assertEquals(288, racer.computeRaceStrategy(input))
    }

    @Test
    fun `should compute actual race results`() {
        val racer = BoatRace()
        assertEquals(316800, racer.computeRaceStrategy(actualInputList))
    }


    @Test
    fun `should compute race results with kerning`() {
        val racer = BoatRace()
        assertEquals(71503, racer.computeIncreasedRaceStrategy(input))
    }

    @Test
    fun `should compute actual race results with kerning`() {
        val racer = BoatRace()
        assertEquals(45647654, racer.computeIncreasedRaceStrategy(actualInputList))
    }

}