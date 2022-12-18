package com.adventofcode.dec2022.day18

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DropletAnalyzerTest {

    private val input = listOf("2,2,2",
            "1,2,2",
            "3,2,2",
            "2,1,2",
            "2,3,2",
            "2,2,1",
            "2,2,3",
            "2,2,4",
            "2,2,6",
            "1,2,5",
            "3,2,5",
            "2,1,5",
            "2,3,5")

    @Test
    fun `should count sides on single cube`() {
        val input = listOf("1,1,1")
        val analyzer = DropletAnalyzer()
        assertEquals(6, analyzer.countFacets(input))
    }

    @Test
    fun `should count sides on two cubes`() {
        val input = listOf("1,1,1", "1,1,2")
        val analyzer = DropletAnalyzer()
        assertEquals(10, analyzer.countFacets(input))
    }

    @Test
    fun `should count sides on three cubes in a row`() {
        val input = listOf("1,1,1", "1,1,2", "1,1,3")
        val analyzer = DropletAnalyzer()
        assertEquals(14, analyzer.countFacets(input))
    }


    @Test
    fun `should count sides on two separate cubes`() {
        val input = listOf("1,1,1", "1,1,3")
        val analyzer = DropletAnalyzer()
        assertEquals(12, analyzer.countFacets(input))
    }

    @Test
    fun `should count sides on example`() {
        val analyzer = DropletAnalyzer()
        assertEquals(64, analyzer.countFacets(input))
    }

    @Test
    fun `should count sides on actual data`() {
        val analyzer = DropletAnalyzer()
        assertEquals(4244, analyzer.countFacets(actualInputList))
    }

    @Test
    fun `should only count ouside facets on example`() {
        val analyzer = DropletAnalyzer()
        assertEquals(58, analyzer.countFacets(input, allFacets = false))
    }

    @Test
    fun `should only count ouside facets on actual data`() {
        val analyzer = DropletAnalyzer()
        assertEquals(2460, analyzer.countFacets(actualInputList, allFacets = false))
    }

}