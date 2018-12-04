package com.adventofcode.dec2016.day8

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TwoFactorAuthenticatorTest {

    @Test
    fun `should draw rect`() {
        val output = "###....\n" +
                "###...\n" +
                "......."
        val puzzle = TwoFactorAuthenticator(7, 3)
        puzzle.drawRect(3, 2)
        compareOutput(output, puzzle)
    }

    @Test
    fun `should rotate column properly`() {
        val output = "#.#....\n" +
                "###....\n" +
                ".#....."
        val puzzle = TwoFactorAuthenticator(7, 3)
        puzzle.drawRect(3, 2)
        puzzle.rotateColumn(1, 1)
        compareOutput(output, puzzle)
    }

    @Test
    fun `should rotate row properly`() {
        val output = "....#.#\n" +
                "###....\n" +
                ".#....."
        val puzzle = TwoFactorAuthenticator(7, 3)
        puzzle.drawRect(3, 2)
        puzzle.rotateColumn(1, 1)
        puzzle.rotateRow(0, 4)
        compareOutput(output, puzzle)
    }

    @Test
    fun `should be in final state`() {
        val output = ".#..#.#\n" +
                "#.#....\n" +
                ".#....."
        val puzzle = TwoFactorAuthenticator(7, 3)
        puzzle.drawRect(3, 2)
        puzzle.rotateColumn(1, 1)
        puzzle.rotateRow(0, 4)
        puzzle.rotateColumn(1, 1)
        compareOutput(output, puzzle)
    }

    @Test
    fun `should have  lit pixels`() {
        val puzzle = TwoFactorAuthenticator(7, 3)
        puzzle.drawRect(3, 2)
        puzzle.rotateColumn(1, 1)
        puzzle.rotateRow(0, 4)
        puzzle.rotateColumn(1, 1)
        assertEquals(6, puzzle.countPixels())
    }

    @Test
    fun `should parse scenario`() {
        val input = "rect 3x2\n" +
                "rotate column x=1 by 1\n" +
                "rotate row y=0 by 4\n" +
                "rotate column x=1 by 1"
        val output = ".#..#.#\n" +
                "#.#....\n" +
                ".#....."

        val puzzle = TwoFactorAuthenticator(7, 3)
        puzzle.parseInstructions(input.split("\n"))

        compareOutput(output, puzzle)
    }

    fun compareOutput(output: String, puzzle: TwoFactorAuthenticator) {
        try {

            val screen = output.split("\n")
            assertTrue(screen.indices.all { y ->
                screen[y].indices.all { x ->
                    puzzle.screen[x][y] xor (screen[y][x] == '.')
                }
            })
        } catch (e: Throwable) {
            puzzle.printDisplay()
            throw e
        }
    }
}