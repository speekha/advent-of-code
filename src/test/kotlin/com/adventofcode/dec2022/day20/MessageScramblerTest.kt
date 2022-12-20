package com.adventofcode.dec2022.day20

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MessageScramblerTest {

    val input = listOf("1", "2", "-3", "3", "-2", "0", "4").map { it.toLong() }

    @Test
    fun `should scramble message`() {
        val scrambler = MessageScrambler(input)
        assertEquals(listOf(1L, 2L, -3L, 4L, 0L, 3L, -2L), scrambler.scramble())
    }

    @Test
    fun `should find coordinates`() {
        val scrambler = MessageScrambler(input)
        scrambler.scramble()
        assertEquals(listOf(4L, -3L, 2L), scrambler.findCoordinates())
        assertEquals(3, scrambler.findCoordinates().sum())
    }

    @Test
    fun `should find actual coordinates`() {
        val scrambler = MessageScrambler(actualInputList.map { it.toLong() })
        scrambler.scramble()
        assertEquals(23321, scrambler.findCoordinates().sum())
    }


    @Test
    fun `should apply additional scrambling`() {
        val scrambler = MessageScrambler(input.map { it * 811589153 })
        repeat(10) {
            scrambler.scramble()
        }
        assertEquals(listOf(811589153L, 2434767459, -1623178306), scrambler.findCoordinates())
        assertEquals(1623178306, scrambler.findCoordinates().sum())
    }
    @Test
    fun `should apply additional scrambling for actual coordinates`() {
        val scrambler = MessageScrambler(actualInputList.map { it.toLong() * 811589153 })
        repeat(10) {
            scrambler.scramble()
        }
        assertEquals(1428396909280, scrambler.findCoordinates().sum())
    }
}