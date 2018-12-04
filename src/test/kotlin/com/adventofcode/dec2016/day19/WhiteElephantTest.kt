package com.adventofcode.dec2016.day19

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class WhiteElephantTest {

    @Test
    fun `should find winner`() {
        val game = WhiteElephantGame(5)
        Assertions.assertEquals(3, game.computeWinner())
    }

    @Test
    fun `should find actual winner`() {
        val game = WhiteElephantGame(3017957)
        Assertions.assertEquals(1841611, game.computeWinner())
    }

    @Test
    fun `should find winner with second rule`() {
        val game = WhiteElephantGame(5)
        Assertions.assertEquals(2, game.computeWinner(false))
    }

    @Test
    fun `should find actual winner with second rule`() {
        val game = WhiteElephantGame(3017957)
        Assertions.assertEquals(1423634, game.computeWinner(false))
    }

}