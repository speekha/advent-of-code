package com.adventofcode.dec2020.day22

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CardGameTest {

    val input = listOf(
        "Player 1:",
        "9",
        "2",
        "6",
        "3",
        "1",
        "",
        "Player 2:",
        "5",
        "8",
        "4",
        "7",
        "10"
    )

    @Test
    fun `should load cards`() {
        val game = CardGame(input)
        assertEquals(listOf(listOf(9, 2, 6, 3, 1), listOf(5, 8, 4, 7, 10)), game.playerDecks)
    }

    @Test
    fun `should play the game`() {
        val game = CardGame(input)
        game.playRegularGame()
        assertEquals(306, game.computeWinnerScore())
    }

    @Test
    fun `should play the actual game`() {
        val game = CardGame(readInputAsList())
        game.playRegularGame()
        assertEquals(34566, game.computeWinnerScore())
    }

    @Test
    fun `should play the recursive game and stop unending games`() {
        val game = CardGame(
            listOf(
                "Player 1:",
                "43",
                "19",
                "",
                "Player 2:",
                "2",
                "29",
                "14"
            )
        )
        game.playRecursiveGame()
        assertEquals(0, game.winner)
    }

    @Test
    fun `should play the recursive game`() {
        val game = CardGame(input)
        game.playRecursiveGame()
        assertEquals(1, game.winner)
        assertEquals(291, game.computeWinnerScore())
    }

    @Test
    fun `should play the actual recursive game`() {
        val game = CardGame(readInputAsList())
        game.playRecursiveGame()
        assertEquals(31854, game.computeWinnerScore())
    }
}