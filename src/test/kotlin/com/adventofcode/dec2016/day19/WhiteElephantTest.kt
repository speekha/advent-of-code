package com.adventofcode.dec2016.day19

import org.junit.Assert
import org.junit.Test

class WhiteElephantTest {

    @Test
    fun `should find winner`() {
        val game = WhiteElephantGame(5)
        Assert.assertEquals(3, game.computeWinner())
    }

    @Test
    fun `should find actual winner`() {
        val game = WhiteElephantGame(3017957)
        Assert.assertEquals(1841611, game.computeWinner())
    }

    @Test
    fun `should find winner with second rule`() {
        val game = WhiteElephantGame(5)
        Assert.assertEquals(2, game.computeWinner(false))
    }

    @Test
    fun `should find actual winner with second rule`() {
        val game = WhiteElephantGame(3017957)
        Assert.assertEquals(1423634, game.computeWinner(false))
    }

}