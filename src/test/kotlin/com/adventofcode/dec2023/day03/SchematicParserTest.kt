package com.adventofcode.dec2023.day03

import com.adventofcode.actualInputList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SchematicParserTest {

    val input = listOf(
        "467..114..",
        "...*......",
        "..35..633.",
        "......#...",
        "617*......",
        ".....+.58.",
        "..592.....",
        "......755.",
        "...\$.*....",
        ".664.598.."
    )

    @Test
    fun `should count engine parts`() {
        val parser = SchematicParser(input)
        assertEquals(listOf(467, 35, 633, 617, 592, 755, 664, 598), parser.parts.map { it.id.toInt() })
        assertEquals(4361, parser.parts.sumOf { it.id.toInt() })
    }

    @Test
    fun `should count actual engine parts`() {
        val parser = SchematicParser(actualInputList)
        // 547806 < x < 558036, !557409
        assertEquals(556367, parser.parts.sumOf { it.id.toInt() })
    }

    @Test
    fun `should compute gears ratios`() {
        val parser = SchematicParser(input)
        assertEquals(467835, parser.computeGearRatios())
    }

    @Test
    fun `should compute actual gears ratios`() {
        val parser = SchematicParser(actualInputList)
        assertEquals(89471771, parser.computeGearRatios())
    }
}