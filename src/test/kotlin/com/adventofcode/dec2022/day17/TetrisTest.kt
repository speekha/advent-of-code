package com.adventofcode.dec2022.day17

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TetrisTest {

    val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"

    @Test
    fun `should start with empty chamber`() {
        assertEquals(0, Tetris(input).height)
    }

    @Test
    fun `should compute result of first rock`() {
        val tetris = Tetris(input)
        tetris.dropRock()
        tetris.print()
        assertEquals(1, tetris.height)
    }

    @Test
    fun `should compute result of second rock`() {
        val tetris = Tetris(input)
        tetris.dropRock()
        tetris.dropRock()
        tetris.print()
        assertEquals(4, tetris.height)
    }

    @Test
    fun `should compute result of third rock`() {
        val tetris = Tetris(input)
        repeat(3) {
            tetris.dropRock()
        }
        tetris.print()
        assertEquals(6, tetris.height)
    }

    @Test
    fun `should compute result of fourth rock`() {
        val tetris = Tetris(input)
        repeat(4) {
            tetris.dropRock()
        }
        tetris.print()
        assertEquals(7, tetris.height)
    }

    @Test
    fun `should compute result of fifth rock`() {
        val tetris = Tetris(input)
        repeat(5) {
            tetris.dropRock()
        }
        tetris.print()
        assertEquals(9, tetris.height)
    }

    @Test
    fun `should compute result of 2022 rocks`() {
        val tetris = Tetris(input)
        repeat(2022) {
            tetris.dropRock()
        }
        assertEquals(3068, tetris.height)
    }
}