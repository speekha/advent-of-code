package com.adventofcode.dec2018.day9

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MarbleGameTest {

    @Test
    fun `should compute high score`() {
        val game = MarbleGame(9)
        repeat(25) {
            game.play()
        }
        Assertions.assertEquals(32, game.winningScore)
    }

    @Test
    fun `should compute actual score`() {
        val game = MarbleGame(411)
        repeat(71170) {
            game.play()
        }
        Assertions.assertEquals(425688, game.winningScore)
    }

    @Test
    fun `should compute actual score for extended game`() {
        val game = MarbleGame(411)
        repeat(7117000) {
            game.play()
        }
        Assertions.assertEquals(3526561003, game.winningScore)
    }
}