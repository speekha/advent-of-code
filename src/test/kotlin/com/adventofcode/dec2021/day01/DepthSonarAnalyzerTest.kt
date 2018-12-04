package com.adventofcode.dec2021.day01

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DepthSonarAnalyzerTest {

        val depths = ("199\n" +
                "200\n" +
                "208\n" +
                "210\n" +
                "200\n" +
                "207\n" +
                "240\n" +
                "269\n" +
                "260\n" +
                "263")
            .split("\n")
            .map { it.toInt() }

    private val actualData = actualInputList.map { it.toInt() }

    @Test
    fun `should count increasing steps`() {
        Assertions.assertEquals(7, DepthSonarAnalyzer().countIncreases(depths))
    }

    @Test
    fun `should count actual increasing steps`() {
        Assertions.assertEquals(1548, DepthSonarAnalyzer().countIncreases(actualData))
    }

    @Test
    fun `should count increasing windows`() {
        Assertions.assertEquals(5, DepthSonarAnalyzer().analyzeWindows(depths, 3))
    }


    @Test
    fun `should count actual increasing windows`() {
        Assertions.assertEquals(1589, DepthSonarAnalyzer().analyzeWindows(actualData, 3))
    }

}