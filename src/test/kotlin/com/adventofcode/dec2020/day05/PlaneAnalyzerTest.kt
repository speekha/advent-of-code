package com.adventofcode.dec2020.day05

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PlaneAnalyzerTest {

    @Test
    fun `find highest seat ID`() {
        val analyzer = PlaneAnalyzer(readInputAsList())
        Assertions.assertEquals(987, analyzer.findHighestSeat())
    }

    @Test
    fun `find missing seat ID`() {
        val analyzer = PlaneAnalyzer(readInputAsList())
        Assertions.assertEquals(603, analyzer.findMissingSeat())
    }
}