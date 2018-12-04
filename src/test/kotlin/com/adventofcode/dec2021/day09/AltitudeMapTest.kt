package com.adventofcode.dec2021.day09

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AltitudeMapTest {

    val input = listOf(
        "2199943210",
        "3987894921",
        "9856789892",
        "8767896789",
        "9899965678"
    )

    @Test
    fun `should find low points`() {
        val map = AltitudeMap(input)
        assertEquals(listOf(0, 1, 5, 5), map.findLowPointsValues())
    }

    @Test
    fun `should compute risk for low points`() {
        val map = AltitudeMap(input)
        assertEquals(15, map.findLowPointsRisk())
    }

    @Test
    fun `should compute actual risk for low points`() {
        val map = AltitudeMap(actualInputList)
        assertEquals(524, map.findLowPointsRisk())
    }

    @Test
    fun `should find basins`() {
        val map = AltitudeMap(input)
        assertEquals(listOf(3,9,9,14), map.findBasins())
    }

    @Test
    fun `should find basin score`() {
        val map = AltitudeMap(input)
        assertEquals(1134, map.findBasinScore())
    }

    @Test
    fun `should find actual basin score`() {
        val map = AltitudeMap(actualInputList)
        assertEquals(1235430, map.findBasinScore())
    }
}