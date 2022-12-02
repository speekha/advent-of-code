package com.adventofcode.dec2022.day02

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RoshamboTest {

    val input = listOf(
        "A Y",
        "B X",
        "C Z"
    )


    @Test
    fun `should compute score for a round`() {
        val chifumi = Rule1()
        assertEquals(8, chifumi.computeRound(input[0]))
        assertEquals(1, chifumi.computeRound(input[1]))
        assertEquals(6, chifumi.computeRound(input[2]))
    }

    @Test
    fun `should compute total score`() {
        val chifumi = Rule1()
        assertEquals(15, chifumi.computeGame(input))
    }

    @Test
    fun `should compute actual total score`() {
        val chifumi = Rule1()
        assertEquals(14163, chifumi.computeGame(actualInputList))
    }

    @Test
    fun `should compute score for a strategic round`() {
        val chifumi = Rule2()
        assertEquals(4, chifumi.computeRound(input[0]))
        assertEquals(1, chifumi.computeRound(input[1]))
        assertEquals(7, chifumi.computeRound(input[2]))
    }

    @Test
    fun `should compute strategic total score`() {
        val chifumi = Rule2()
        assertEquals(12, chifumi.computeGame(input))
    }

    @Test
    fun `should compute actual strategic total score`() {
        val chifumi = Rule2()
        assertEquals(12091, chifumi.computeGame(actualInputList))
    }
}