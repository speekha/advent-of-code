package com.adventofcode.dec2018.day3

import org.junit.Assert.*
import org.junit.Test
import java.io.File

class FabricMapperTest {


    val input = listOf(
            "#1 @ 1,3: 4x4",
            "#2 @ 3,1: 4x4",
            "#3 @ 5,5: 2x2")

    @Test
    fun `should map claims`() {
        val mapper = FabricMapper(input)
        assertEquals(listOf(
                Claim("#1", 1, 3, 4, 4),
                Claim("#2", 3, 1, 4, 4),
                Claim("#3", 5, 5, 2, 2)
        ), mapper.claims)
    }

    @Test
    fun `should find limits`() {
        val mapper = FabricMapper(input)
        assertEquals(8, mapper.width)
        assertEquals(8, mapper.height)
    }

    @Test
    fun `should count overlaps`() {
        val mapper = FabricMapper(input)
        assertEquals(4, mapper.overlap)
    }

    @Test
    fun `should count actual overlaps`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2018/day3/input.txt").readLines()
        val mapper = FabricMapper(input)
        assertEquals(111630, mapper.overlap)
    }

    @Test
    fun `should find non-overlapping claim`() {
        val input = listOf(
                "#1 @ 1,3: 4x4",
                "#2 @ 3,1: 4x4",
                "#4 @ 1,3: 4x4",
                "#3 @ 5,5: 2x2")
        val mapper = FabricMapper(input)
        assertEquals("#3", mapper.findNonOverLappingClaim())
    }

    @Test
    fun `should find actual non-overlapping claim`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2018/day3/input.txt").readLines()
        val mapper = FabricMapper(input)
        assertEquals("#724", mapper.findNonOverLappingClaim())
    }

    @Test
    fun `test claims overlapping`() {
        assertTrue(Claim("", 0, 0, 1, 1) intersects Claim("", 0, 0, 1, 1))
        assertTrue(Claim("", 0, 0, 2, 2) intersects Claim("", 1, 1, 2, 2))
        assertTrue(Claim("", 1, 1, 2, 2) intersects Claim("", 0, 0, 2, 2))
        assertTrue(Claim("", 0, 1, 2, 2) intersects Claim("", 1, 0, 2, 2))
        assertTrue(Claim("", 1, 0, 2, 2) intersects Claim("", 0, 1, 2, 2))
        assertTrue(Claim("", 1, 1, 1, 1) intersects Claim("", 0, 0, 3, 3))
        assertTrue(Claim("", 0, 0, 3, 3) intersects Claim("", 1, 1, 1, 1))
    }
}