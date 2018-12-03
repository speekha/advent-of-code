package com.adventofcode.dec2016.day16

import org.junit.Assert.assertEquals
import org.junit.Test

class RandomDataGeneratorTest {

    val generator = RandomDataGenerator("10000")

    @Test
    fun `should iterate data properly`() {
        assertEquals("100", generator.iterate("1"))
        assertEquals("001", generator.iterate("0"))
        assertEquals("11111000000", generator.iterate("11111"))
        assertEquals("1111000010100101011110000", generator.iterate("111100001010"))
    }

    @Test
    fun `should calculate checksum iteration`() {
        assertEquals("110101", generator.checksumStep("110010110100"))
        assertEquals("100", generator.checksumStep("110101"))
    }

    @Test
    fun `should calculate full checksum`() {
        assertEquals("100", generator.checksum("110010110100"))
        assertEquals("100", generator.checksum("110010110100101011101", 12))
    }

    @Test
    fun `should generate random data and calculate corresponding checksum`() {
        assertEquals("01100", generator.fillDrive(20))
    }

    @Test
    fun `should solve real data`() {
        val generator = RandomDataGenerator("00101000101111010")
        assertEquals("10010100110011100", generator.fillDrive(272))
        assertEquals("01100100101101100", generator.fillDrive(35651584))
    }
}