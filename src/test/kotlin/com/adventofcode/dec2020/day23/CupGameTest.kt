package com.adventofcode.dec2020.day23

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class CupGameTest {

    val input = "389125467"

    @Test
    fun `should initialize cups`() {
        val game = CupGame(input)
        assertEquals(9, game.cups.size)
        assertEquals(3, game.current.value)
        assertEquals(input, game.configuration)
    }

    @Test
    fun `should move cups`() {
        val game = CupGame(input)
        game.move()
        assertEquals("289154673", game.configuration)
        assertEquals("54673289", game.labels)
    }

    @Test
    fun `should do 10 move`() {
        val game = CupGame(input)
        repeat(10) {
            game.move()
        }
        assertEquals("837419265", game.configuration)
        assertEquals("92658374", game.labels)
    }

    @Test
    fun `should do 100 moves`() {
        val game = CupGame(input)
        repeat(100) {
            game.move()
        }
        assertEquals("67384529", game.labels)
    }

    val actualInput = "643719258"

    @Test
    fun `should do 100 actual moves`() {
        val game = CupGame(actualInput)
        repeat(100) {
            game.move()
        }
        assertEquals("54896723", game.labels)
    }

    @Test
    @Disabled("A bit long")
    fun `should do 10 000 000 moves`() {
        val game = CupGame(input, true)
        repeat(10_000_000) {
            game.move()
        }
        val one = game.cups[1] ?: error("1 not found")
        val starCups = listOf(one.next, one.next?.next).mapNotNull { it?.value?.toLong() }
        assertEquals(149245887792, starCups.reduce { acc, l -> acc * l })
    }

    @Test
    @Disabled("A bit long")
    fun `should do 10 000 000 actual moves`() {
        val game = CupGame(actualInput, true)
        repeat(10_000_000) {
            game.move()
        }
        val one = game.cups[1] ?: error("1 not found")
        val starCups = listOf(one.next, one.next?.next).mapNotNull { it?.value?.toLong() }
        assertEquals(146304752384, starCups.reduce { acc, l -> acc * l })
    }
}