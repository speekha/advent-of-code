package com.adventofcode.dec2018.day6

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CoordinateMapperTest {

    val input = listOf("1, 1",
            "1, 6",
            "8, 3",
            "3, 4",
            "5, 5",
            "8, 9")

    @Test
    fun `should find grid size`() {
        val mapper = CoordinateMapper(input)
        assertEquals(1, mapper.xMin)
        assertEquals(8, mapper.xMax)
        assertEquals(1, mapper.yMin)
        assertEquals(9, mapper.yMax)
    }

    @Test
    fun `should find largest finite area`() {
        val mapper = CoordinateMapper(input)
        assertEquals(17, mapper.findLargestFiniteArea())
    }

    @Test
    fun `should find actual largest finite area`() {
        val input = readInputAsList()
        val mapper = CoordinateMapper(input)
        assertEquals(2342, mapper.findLargestFiniteArea())
    }

    @Test
    fun `should find largest safe area`() {
        val mapper = CoordinateMapper(input)
        assertEquals(16, mapper.findLargestSafeArea(32))
    }


    @Test
    fun `should find actual largest safe area`() {
        val input = readInputAsList()
        val mapper = CoordinateMapper(input)
        assertEquals(43302, mapper.findLargestSafeArea(10000))
    }
}