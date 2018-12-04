package com.adventofcode.dec2016.day3

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TriangleValidatorTest {
    @Test
    fun `should be valid triangle`() {
        assertTrue(TriangleValidator().isValidTriangle("    5    6    7"))
    }

    @Test
    fun `should not be valid triangle`() {
        assertFalse(TriangleValidator().isValidTriangle("    5    5    10"))
    }

    @Test
    fun `  401  211  328 should be valid triangle`() {
        assertTrue(TriangleValidator().isValidTriangle("  401  211  328"))
    }

    @Test
    fun `should be invalid triangle`() {
        assertFalse(TriangleValidator().isValidTriangle("    5   10   25"))
    }

    @Test
    fun `725  312  215 should be invalid triangle`() {
        assertFalse(TriangleValidator().isValidTriangle("  725  312  215"))
    }

    @Test
    fun `should be only 1 valid`() {
        val input = "    5    5    5\n    5   10    25"
        assertEquals(1, TriangleValidator().countValidTriangles(input.split("\n")))
    }

    @Test
    fun `should read vertically`() {
        val input = "101 301 501\n" +
                "102 302 502\n" +
                "103 303 503\n" +
                "201 401 601\n" +
                "202 402 602\n" +
                "203 403 603"
        assertEquals(6, TriangleValidator().countValidTriangles(input))
    }
}

