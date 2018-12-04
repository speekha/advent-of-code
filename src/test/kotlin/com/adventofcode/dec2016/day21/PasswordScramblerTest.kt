package com.adventofcode.dec2016.day21

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class PasswordScramblerTest {

    @Test
    fun `should swap positions`() {
        val scrambler = PasswordScrambler(listOf(SwapPosition(4, 0)))
        assertEquals("ebcda", scrambler.scramble("abcde"))
    }

    @Test
    fun `should swap letters`() {
        val scrambler = PasswordScrambler(listOf(SwapLetters('b', 'd')))
        assertEquals("edcba", scrambler.scramble("ebcda"))
    }

    @Test
    fun `should reverse ranges`() {
        val scrambler = PasswordScrambler(listOf(Reverse(0, 4)))
        assertEquals("abcde", scrambler.scramble("edcba"))
    }

    @Test
    fun `should rotate left`() {
        val scrambler = PasswordScrambler(listOf(Rotate(1, LEFT)))
        assertEquals("bcdea", scrambler.scramble("abcde"))
    }

    @Test
    fun `should rotate right`() {
        val scrambler = PasswordScrambler(listOf(Rotate(1, RIGHT)))
        assertEquals("eabcd", scrambler.scramble("abcde"))
    }

    @Test
    fun `should rotate based on letter position`() {
        val scrambler = PasswordScrambler(listOf(RotateForLetter('b')))
        assertEquals("ecabd", scrambler.scramble("abdec"))
    }

    @Test
    fun `should rotate based on letter in last position`() {
        val scrambler = PasswordScrambler(listOf(RotateForLetter('d')))
        assertEquals("decab", scrambler.scramble("ecabd"))
    }

    @Test
    fun `should move letters`() {
        val scrambler = PasswordScrambler(listOf(Move(3, 0)))
        assertEquals("abdec", scrambler.scramble("bdeac"))
    }

    @Test
    fun `should process instruction list`() {
        val instructions = listOf(
                SwapPosition(4, 0),
                SwapLetters('d', 'b'),
                Reverse(0, 4),
                Rotate(1, LEFT),
                Move(1, 4),
                Move(3, 0),
                RotateForLetter('b'),
                RotateForLetter('d')
        )
        val scrambler = PasswordScrambler(instructions)
        assertEquals("decab", scrambler.scramble("abcde"))
    }

    @Test
    fun `should parse text instructions`() {
        val instructions = listOf(
                "swap position 4 with position 0",
                "swap letter d with letter b",
                "reverse positions 0 through 4",
                "rotate left 1 step",
                "move position 1 to position 4",
                "move position 3 to position 0",
                "rotate based on position of letter b",
                "rotate based on position of letter d")
        val scrambler = PasswordScrambler.parse(instructions)
        assertEquals("decab", scrambler.scramble("abcde"))

    }

    @Test
    fun `should actual instructions`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2016/day21/input.txt").readLines()
        val scrambler = PasswordScrambler.parse(input)
        assertEquals("fdhbcgea", scrambler.scramble("abcdefgh"))
    }

    @Test
    fun `should revert swap positions`() {

        val scrambler = PasswordScrambler(listOf(SwapPosition(4, 0).reverse()))
        assertEquals("ebcda", scrambler.scramble("abcde"))
    }
    @Test
    fun `should reverse swap letters`() {
        val scrambler = PasswordScrambler(listOf(SwapLetters('b', 'd').reverse()))
        assertEquals("edcba", scrambler.scramble("ebcda"))
    }

    @Test
    fun `should reverse reverse ranges`() {
        val scrambler = PasswordScrambler(listOf(Reverse(0, 4).reverse()))
        assertEquals("abcde", scrambler.scramble("edcba"))
    }

    @Test
    fun `should reverse rotate left`() {
        val scrambler = PasswordScrambler(listOf(Rotate(1, LEFT).reverse()))
        assertEquals("abcde", scrambler.scramble("bcdea"))
    }

    @Test
    fun `should reverse rotate right`() {
        val scrambler = PasswordScrambler(listOf(Rotate(1, RIGHT).reverse()))
        assertEquals("abcde", scrambler.scramble("eabcd"))
    }

    @Test
    fun `should reverse rotate based on letter position`() {
        val scrambler = PasswordScrambler(listOf(RotateForLetter('b').reverse()))
        assertEquals("abdec", scrambler.scramble("ecabd"))
    }

    @Test
    fun `should reverse rotate based on letter in last position`() {
        val scrambler = PasswordScrambler(listOf(RotateForLetter('d').reverse()))
        assertEquals("ecabd", scrambler.scramble("decab"))
    }

    @Test
    fun `should reverse move letters`() {
        val scrambler = PasswordScrambler(listOf(Move(3, 0).reverse()))
        assertEquals("bdeac", scrambler.scramble("abdec"))
    }

    @Test
    fun `should descramble password`() {
        val instructions = listOf(
                SwapPosition(4, 0),
                SwapLetters('d', 'b'),
                Reverse(0, 4),
                Rotate(1, LEFT),
                Move(1, 4),
                Move(3, 0),
                RotateForLetter('b'),
                RotateForLetter('d')
        )
        val scrambler = PasswordScrambler(instructions)
        assertEquals("abcde", scrambler.descramble("decab"))
    }


    @Test
    fun `should descramble actual password`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2016/day21/input.txt").readLines()
        val scrambler = PasswordScrambler.parse(input)
        assertEquals("egfbcadh", scrambler.descramble("fbgdceah"))

    }
}