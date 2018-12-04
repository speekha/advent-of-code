package com.adventofcode.dec2019.day22

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File

class ShufflerTest {

    @Test
    fun `should handle reverse`() {
        val shuffler = Shuffler(10)
        assertEquals(2, shuffler.executeShuffle(7, listOf("deal into new stack")))
        assertEquals(4, shuffler.executeShuffle(5, listOf("deal into new stack")))
    }

    @Test
    fun `should cut N cards`() {
        val shuffler = Shuffler(10)
        assertEquals(3, shuffler.executeShuffle(6, listOf("cut 3")))
        assertEquals(9, shuffler.executeShuffle(2, listOf("cut 3")))
        assertEquals(7, shuffler.executeShuffle(3, listOf("cut -4")))
    }

    @Test
    fun `should deal cards`() {
        val shuffler = Shuffler(10)
        assertEquals(8, shuffler.executeShuffle(6, listOf("deal with increment 3")))
        assertEquals(1, shuffler.executeShuffle(7, listOf("deal with increment 3")))
    }

    @Test
    fun `should parse deal command`() {
        val input = listOf(
                "deal with increment 7",
                "deal into new stack",
                "deal into new stack"
        )
        val shuffler = Shuffler(10)
        assertEquals(2, shuffler.executeShuffle(6, input))
    }

    @Test
    fun `should parse deal commands`() {
        val input = listOf(
                "deal into new stack",
                "cut -2",
                "deal with increment 7",
                "cut 8",
                "cut -4",
                "deal with increment 7",
                "cut 3",
                "deal with increment 9",
                "deal with increment 3",
                "cut -1"
        )
        val shuffler = Shuffler(10)
        assertEquals(0, shuffler.executeShuffle(9, input))
    }

    @Test
    fun `should reverse deal commands`() {
        val input = listOf(
                "deal into new stack",
                "cut -2",
                "deal with increment 7",
                "cut 8",
                "cut -4",
                "deal with increment 7",
                "cut 3",
                "deal with increment 9",
                "deal with increment 3",
                "cut -1"
        )
        val shuffler = Shuffler(10)
        assertEquals(9, shuffler.reverseShuffle(0, input, 1))
    }

    @Test
    fun `should parse actual deal commands`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2019/day22/input.txt").readLines()
        val shuffler = Shuffler(10007)
        assertEquals(2306, shuffler.executeShuffle(2019, input))
    }

    @Test
    fun `should reverse first deal`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2019/day22/input.txt").readLines()
        val shuffler = Shuffler(10007)
        assertEquals(2019, shuffler.reverseShuffle(2306, input, 1))
    }

    @Test
    fun `should reverse deal commands with cache`() {
        val input = listOf("deal into new stack")
        val shuffler = Shuffler(10)
        assertEquals(5, shuffler.reverseShuffle(5, input, 4))
    }

    @Test
    @Disabled
    fun `should reverse actual deal commands`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2019/day22/input.txt").readLines()
        val shuffler = Shuffler(119315717514047)
        val result = shuffler.reverseShuffle(2020, input, 101741582076661)
        assertTrue(result < 20729561914533)
        assertEquals(0, result)
    }
}