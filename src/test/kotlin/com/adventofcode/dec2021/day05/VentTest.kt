package com.adventofcode.dec2021.day05

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class VentTest {

    val input = listOf(
        "0,9 -> 5,9",
        "8,0 -> 0,8",
        "9,4 -> 3,4",
        "2,2 -> 2,1",
        "7,0 -> 7,4",
        "6,4 -> 2,0",
        "0,9 -> 2,9",
        "3,4 -> 1,4",
        "0,0 -> 8,8",
        "5,5 -> 8,2"
    )

    @Test
    fun `should parse input`() {
        val vents = VentDetector(input)
        assertEquals(VentLine(Point(0, 9), Point(5, 9)), vents.vents[0])
    }

    @Test
    fun `should count intersections`() {
        val vents = VentDetector(input)
        assertEquals(5, vents.countIntersections())
    }

    @Test
    fun `should count actual intersections`() {
        val vents = VentDetector(actualInputList)
        assertEquals(6397, vents.countIntersections())
    }

    @Test
    fun `should count all intersections`() {
        val vents = VentDetector(input)
        assertEquals(12, vents.countAllIntersections())
    }

    @Test
    fun `should count all actual intersections`() {
        val vents = VentDetector(actualInputList)
        assertEquals(22335, vents.countAllIntersections())
    }

}