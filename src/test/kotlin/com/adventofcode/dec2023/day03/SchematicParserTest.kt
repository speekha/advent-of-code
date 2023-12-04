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
        assertEquals(listOf(467, 35, 633, 617, 592, 755,664, 598), parser.parts)
        assertEquals(4361, parser.parts.sum())
    }

    @Test
    fun `should count actual engine parts`() {
        val parser = SchematicParser(actualInputList)
        // > 547806
        assertEquals(547806, parser.parts.sum())
    }

}