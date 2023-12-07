package com.adventofcode.dec2023.day07

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.DefaultAsserter.assertTrue
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CardGameTest {

    val input = listOf(
        "32T3K 765",
        "T55J5 684",
        "KK677 28",
        "KTJJT 220",
        "QQQJA 483"
    )

    @Test
    fun `should rank hands`() {
        assertTrue(Hand("QQQJA") > Hand("KK677"))
        assertTrue(Hand("QQQJA") > Hand("T55J5"))
        assertTrue(Hand("KK677") > Hand("KTJJT"))
    }

    @Test
    fun `should compute winnings`() {
        val game = CardGame()
        assertEquals(6440, game.computeWinnings(input))
    }

    @Test
    fun `should compute actual winnings`() {
        val game = CardGame()
        assertEquals(246163188, game.computeWinnings(actualInputList))
    }

    @Test
    fun `should rank hands with jokers`() {
        assertTrue(Hand("QQQJA", true) > Hand("QQKKK", true))
    }

    @Test
    fun `should compute winnings with jokers`() {
        val game = CardGame()
        assertEquals(5905, game.computeWinnings(input, true))
    }

    @Test
    fun `should compute actual winnings with jokers`() {
        val game = CardGame()
        assertEquals(245794069, game.computeWinnings(actualInputList, true))
    }


}