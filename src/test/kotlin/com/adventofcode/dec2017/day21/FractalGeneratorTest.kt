package com.adventofcode.dec2017.day21

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

class FractalGeneratorTest {

    val input = listOf("../.# => ##./#../...",
            ".#./..#/### => #..#/..../..../#..#")

    @Test
    fun `should parse input`() {
        val array = arrayOf(
                booleanArrayOf(false, true, false),
                booleanArrayOf(false, false, true),
                booleanArrayOf(true, true, true)
        )
        Assertions.assertArrayEquals(array, FractalGenerator.parse(".#./..#/###"))
    }

    @Test
    fun `initial pattern should match`() {
        val generator = FractalGenerator(emptyList())
        val pattern = Pattern(".#./..#/###", FractalGenerator.parse((".#./..#/###")))
        Assertions.assertTrue(pattern.match(generator.pixels))
    }

    @Test
    fun `should flip horizontally`() {
        assertEquals("../#.", FractalGenerator.flipHorizontally("../.#"))
    }

    @Test
    fun `should flip vertically`() {
        assertEquals(".#/..", FractalGenerator.flipVertically("../.#"))
    }

    @Test
    fun `should flip diagonnaly`() {
        assertEquals(".#/..", FractalGenerator.flipDiagonally("../#."))
    }

    @Test
    fun `should rotate`() {
        assertEquals(".../#../#..", FractalGenerator.flipDiagonally(FractalGenerator.flipVertically(".../.../.##")))
        assertEquals("..#/..#/...", FractalGenerator.flipDiagonally(FractalGenerator.flipHorizontally(".../.../.##")))
    }

    @Test
    fun `should init rules properly with flips`() {
        val generator = FractalGenerator(listOf(input[0]))
        assertTrue(generator.transformations.containsKey("../.#"))
        assertTrue(generator.transformations.containsKey("../#."))
        assertTrue(generator.transformations.containsKey(".#/.."))
        assertTrue(generator.transformations.containsKey("#./.."))
        assertEquals(4, generator.transformations.size)
    }

    @Test
    fun `should init rules properly with rotations`() {
        val generator = FractalGenerator(listOf(input[1]))
        assertTrue(generator.transformations.containsKey(".#./..#/###"))
        assertTrue(generator.transformations.containsKey("#../#.#/##."))
        assertEquals(8, generator.transformations.size)
    }

    @Test
    fun `should extract subarray`() {
        val generator = FractalGenerator(emptyList())
        generator.pixels = FractalGenerator.parse(listOf(
                "#..#",
                "....",
                "....",
                "#..#").joinToString("/"))
        val extract = arrayOf(booleanArrayOf(true, false), booleanArrayOf(false, false))
        assertArrayEquals(extract, generator.extract(0, 0, 2))
    }

    @Test
    fun `should process initial grid`() {
        val step1 = FractalGenerator.parse(listOf(
                "#..#",
                "....",
                "....",
                "#..#").joinToString("/"))
        val generator = FractalGenerator(input)
        generator.iterate()
        assertArrayEquals(step1, generator.pixels)
    }

    @Test
    fun `should process second step`() {
        val step2 = FractalGenerator.parse(listOf(
                "##.##.",
                "#..#..",
                "......",
                "##.##.",
                "#..#..",
                "......").joinToString("/"))
        val generator = FractalGenerator(input)
        generator.iterate()
        generator.iterate()
        assertArrayEquals(step2, generator.pixels)
    }

    @Test
    fun `should have 12 active pixels`() {
        val generator = FractalGenerator(input)
        generator.iterate()
        generator.iterate()
        assertEquals(12, generator.sumActivePixels())
    }

    @Test
    fun `test real values`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2017/day21/input.txt").readLines()
        with(FractalGenerator(input)) {
            for(i in 1..5) {
                iterate()
            }
            assertEquals(110, sumActivePixels())
            for(i in 6..18) {
                iterate()
            }
            assertEquals(1277716, sumActivePixels())
        }
    }
}