package com.adventofcode.dec2021.day03

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ReportAnalyzerTest {

    private val input = listOf(
        "00100",
        "11110",
        "10110",
        "10111",
        "10101",
        "01111",
        "00111",
        "11100",
        "10000",
        "11001",
        "00010",
        "01010"
    )

    @Test
    fun `should compute power consumption`() {
        val analyzer = ReportAnalyzer()
        Assertions.assertEquals(22, analyzer.computeGammaRate(input))
        Assertions.assertEquals(9, analyzer.computeEpsilonRate(input))
        Assertions.assertEquals(198, analyzer.computePowerConsumption(input))
    }

    @Test
    fun `should compute actual power consumption`() {
        val analyzer = ReportAnalyzer()
        Assertions.assertEquals(4118544, analyzer.computePowerConsumption(actualInputList))
    }

    @Test
    fun `should compute life support rating`() {
        val analyzer = ReportAnalyzer()
        Assertions.assertEquals(23, analyzer.computeOxygenGeneratorRating(input))
        Assertions.assertEquals(10, analyzer.computeCO2ScrubberRating(input))
        Assertions.assertEquals(230, analyzer.computeLifeSupportRating(input))
    }

    @Test
    fun `should compute actual life support rating`() {
        val analyzer = ReportAnalyzer()
        Assertions.assertEquals(3832770, analyzer.computeLifeSupportRating(actualInputList))
    }
}