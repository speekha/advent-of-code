package com.adventofcode.dec2021.day04

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BingoTest {

    val input = listOf(
        "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1",
        "",
        "22 13 17 11  0",
        " 8  2 23  4 24",
        "21  9 14 16  7",
        " 6 10  3 18  5",
        " 1 12 20 15 19",
        "",
        " 3 15  0  2 22",
        " 9 18 13 17  5",
        "19  8  7 25 23",
        "20 11 10 24  4",
        "14 21 16 12  6",
        "",
        "14 21 17 24  4",
        "10 16 15  9 19",
        "18  8 23 26 20",
        "22 11 13  6  5",
        " 2  0 12  3  7"
    )

    val card = BingoCard(
        listOf(
            14, 21, 17, 24, 4,
            10, 16, 15, 9, 19,
            18, 8, 23, 26, 20,
            22, 11, 13, 6, 5,
            2, 0, 12, 3, 7
        )
    )

    @Test
    fun `should initialize game of bingo`() {
        val bingo = Bingo(input)
        assertEquals(card, bingo.cards[2])
    }

    @Test
    fun `should identify card with winning row`() {
        card.mark(14)
        card.mark(21)
        card.mark(17)
        card.mark(24)
        card.mark(4)
        assertTrue(card.isWinning())
    }

    @Test
    fun `should identify card with winning column`() {
        card.mark(21)
        card.mark(16)
        card.mark(8)
        card.mark(11)
        card.mark(0)
        assertTrue(card.isWinning())
    }

    @Test
    fun `should find winning card`() {
        val bingo = Bingo(input)
        val winner = bingo.findWinner()
        assertEquals(4512, winner.getScore(bingo.lastDraw))
    }

    @Test
    fun `should find actual winning card`() {
        val bingo = Bingo(actualInputList)
        val winner = bingo.findWinner()
        assertEquals(46920, winner.getScore(bingo.lastDraw))
    }

    @Test
    fun `should find last winning card`() {
        val bingo = Bingo(input)
        val winner = bingo.findLastWinner()
        assertEquals(1924, winner.getScore(bingo.lastDraw))
    }

    @Test
    fun `should find actual last winning card`() {
        val bingo = Bingo(actualInputList)
        val winner = bingo.findLastWinner()
        assertEquals(12635, winner.getScore(bingo.lastDraw))
    }
}