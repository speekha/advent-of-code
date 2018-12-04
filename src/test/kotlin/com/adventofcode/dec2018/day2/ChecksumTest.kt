package com.adventofcode.dec2018.day2

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ChecksumTest {

    val input = listOf("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab")

    val input2 = listOf(
        "abcde",
        "fghij",
        "klmno",
        "pqrst",
        "fguij",
        "axcye",
        "wvxyz"
    )

    @Test
    fun `should compute checksum`() {
        val checksum = Checksum()
        assertEquals(12, checksum.check(input))
    }

    @Test
    fun `should compute actual checksum`() {
        val input = readInputAsList()
        val checksum = Checksum()
        assertEquals(9633, checksum.check(input))
    }

    @Test
    fun `should find matching keys`() {
        val checksum = Checksum()
        assertTrue(checksum.areKeysMatching("fghij", "fguij"))
    }

    @Test
    fun `should find non matching keys`() {
        val checksum = Checksum()
        assertFalse(checksum.areKeysMatching("abcde", "fguij"))
    }

    @Test
    fun `should find mixed up boxes`() {
        val checksum = Checksum()
        assertEquals("fgij", checksum.findMixedBoxesDenominator(input2))
    }
    @Test
    fun `should find actual mixed up boxes`() {
        val input = readInputAsList()
        val checksum = Checksum()
        assertEquals("lujnogabetpmsydyfcovzixaw", checksum.findMixedBoxesDenominator(input))
    }
}