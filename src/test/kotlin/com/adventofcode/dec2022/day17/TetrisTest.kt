package com.adventofcode.dec2022.day17

import com.adventofcode.actualInput
import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TetrisTest {

    val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"

    @Test
    fun `should compute result of first rock`() {
        val tetris = Tetris(input)
        assertEquals(1L, tetris.dropRocks(1))
    }

    @Test
    fun `should compute result of second rock`() {
        val tetris = Tetris(input)
        assertEquals(4, tetris.dropRocks(2))
    }

    @Test
    fun `should compute result of third rock`() {
        val tetris = Tetris(input)
        assertEquals(6, tetris.dropRocks(3))
    }

    @Test
    fun `should compute result of fourth rock`() {
        val tetris = Tetris(input)
        assertEquals(7, tetris.dropRocks(4))
    }

    @Test
    fun `should compute result of fifth rock`() {
        val tetris = Tetris(input)
        assertEquals(9, tetris.dropRocks(5))
    }

    @Test
    fun `should compute result of 2022 rocks`() {
        val tetris = Tetris(input)
        assertEquals(3068, tetris.dropRocks(2022))
    }

    @Test
    fun `should compute result of 2022 actual rocks`() {
        val tetris = Tetris(actualInput)
        assertEquals(3135, tetris.dropRocks(2022))
    }

    @Test
    fun `should compute result of 1000000000000 rocks`() {
        val tetris = Tetris(input)
        assertEquals(1514285714288, tetris.dropRocks(1000000000000))
    }

    @Test
    fun `should compute result of 1000000000000 actual rocks`() {
        val tetris = Tetris(actualInput)
        // Too low
        assertEquals(1569054441243, tetris.dropRocks(1000000000000))
    }
}