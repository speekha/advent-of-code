package com.adventofcode.dec2023.day09

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class OasisTest {
    val input = listOf(
            "0 3 6 9 12 15",
            "1 3 6 10 15 21",
            "10 13 16 21 30 45")

    @Test
    fun `should extrapolate sequences to the right`() {
        val oasis = Oasis()
        assertEquals(18, oasis.extrapolateLast(input[0]))
        assertEquals(28, oasis.extrapolateLast(input[1]))
        assertEquals(68, oasis.extrapolateLast(input[2]))
    }
    @Test
    fun `should extrapolate actual sequences to the right`() {
        val oasis = Oasis()
        assertEquals(1757008019, actualInputList.sumOf { oasis.extrapolateLast(it) })
    }


    @Test
    fun `should extrapolate sequences to the left`() {
        val oasis = Oasis()
        assertEquals(-3, oasis.extrapolateFirst(input[0]))
        assertEquals(0, oasis.extrapolateFirst(input[1]))
        assertEquals(5, oasis.extrapolateFirst(input[2]))
    }

    @Test
    fun `should extrapolate actual sequences to the left`() {
        val oasis = Oasis()
        assertEquals(995, actualInputList.sumOf { oasis.extrapolateFirst(it) })
    }
}