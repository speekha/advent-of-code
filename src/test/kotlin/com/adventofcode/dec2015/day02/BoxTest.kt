package com.adventofcode.dec2015.day02

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BoxTest {

    @Test
    fun `should compute required paper`() {
        Assertions.assertEquals(58, Box(2, 3, 4).requiredPaper)
        Assertions.assertEquals(43, Box(1, 1, 10).requiredPaper)
    }

    @Test
    fun `should compute required paper from data`() {
        Assertions.assertEquals(58, Box("2x3x4").requiredPaper)
        Assertions.assertEquals(43, Box("1x1x10").requiredPaper)
    }

    @Test
    fun `should compute total required paper`() {
        val total = readInputAsList().sumOf {
            Box(it).requiredPaper
        }
        Assertions.assertEquals(1606483, total)
    }

    @Test
    fun `should compute required ribbon from data`() {
        Assertions.assertEquals(34, Box("2x3x4").requiredRibbon)
        Assertions.assertEquals(14, Box("1x1x10").requiredRibbon)
    }

    @Test
    fun `should compute total required ribbon from data`() {
        val total = readInputAsList().sumOf {
            Box(it).requiredRibbon
        }
        Assertions.assertEquals(3842356, total)
    }
}