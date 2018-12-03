package com.adventofcode.dec2016.day9

import org.junit.Assert
import org.junit.Test

class FileExpanderTest {

    @Test
    fun `should not decompress anything`() {
        val input = "ADVENT"
        Assert.assertEquals(input, FileExpander(input).decompress())
    }

    @Test
    fun `should repeat a letter`() {
        val input = "A(1x5)BC"
        Assert.assertEquals("ABBBBBC", FileExpander(input).decompress())
    }

    @Test
    fun `should repeat a block`() {
        val input = "(3x3)XYZ"
        Assert.assertEquals("XYZXYZXYZ", FileExpander(input).decompress())
    }

    @Test
    fun `should repeat two blocks`() {
        val input = "A(2x2)BCD(2x2)EFG"
        Assert.assertEquals("ABCBCDEFEFG", FileExpander(input).decompress())
    }

    @Test
    fun `should repeat a block with parenthesis`() {
        val input = "(6x1)(1x3)A"
        Assert.assertEquals("(1x3)A", FileExpander(input).decompress())
    }

    @Test
    fun `should repeat a complex block`() {
        val input = "X(8x2)(3x3)ABCY"
        Assert.assertEquals("X(3x3)ABC(3x3)ABCY", FileExpander(input).decompress())
    }

    @Test
    fun `should ignore blank spaces`() {
        val input = "ADV ENT"
        Assert.assertEquals("ADVENT", FileExpander(input).decompress())
    }

    @Test
    fun `should decompress nested blocks`() {
        Assert.assertEquals(9, FileExpander("(3x3)XYZ").decompressedSize())
        Assert.assertEquals(20, FileExpander("X(8x2)(3x3)ABCY").decompressedSize())
        Assert.assertEquals(241920, FileExpander("(27x12)(20x12)(13x14)(7x10)(1x12)A").decompressedSize())
        Assert.assertEquals(445, FileExpander("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN").decompressedSize())
    }
}