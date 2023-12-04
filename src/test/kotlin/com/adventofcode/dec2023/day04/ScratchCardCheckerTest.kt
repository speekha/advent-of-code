package com.adventofcode.dec2023.day04

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ScratchCardCheckerTest {

    val input = listOf(
                "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
                "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
                "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
                "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
                "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
                "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"
    )

    @Test
    fun `should compute card score`() {
        val checker = ScratchCardChecker()
        assertEquals(8, checker.check(input[0]))
        assertEquals(2, checker.check(input[1]))
        assertEquals(2, checker.check(input[2]))
        assertEquals(1, checker.check(input[3]))
        assertEquals(0, checker.check(input[4]))
        assertEquals(0, checker.check(input[5]))
    }

    @Test
    fun `should compute total score`() {
        val checker = ScratchCardChecker()
        assertEquals(13, checker.computeScore(input))
    }
    @Test
    fun `should compute actual total score`() {
        val checker = ScratchCardChecker()
        assertEquals(20407, checker.computeScore(actualInputList))
    }

    @Test
    fun `should count total cards`() {
        val checker = ScratchCardChecker()
        assertEquals(30, checker.countFinalCards(input))
    }
    @Test
    fun `should count actual total cards`() {
        val checker = ScratchCardChecker()
        assertEquals(23806951, checker.countFinalCards(actualInputList))
    }
}