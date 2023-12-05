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
        assertEquals(listOf(467, 35, 633, 617, 592, 755, 664, 598), parser.parts)
        assertEquals(4361, parser.parts.sum())
    }

    @Test
    fun `should find exception`() {
        var parser = SchematicParser(
            listOf(
                "152.78..........671.....936.......................*..........14...............................575.=.........214..519.....787.739........*...",
                "...*....591......................514*155..........807...............516.............23...5#.......250.531...................*......-..71...."
            )
        )
        println()
        println("Parts : " + parser.parts)
        assertEquals(3558, parser.parts.sum())
        parser = SchematicParser(
            listOf(
                "............409..........784...578...802......64..............................486.248..............177....................369...............",
                ".....-939..........524#...#....=.......*.........+......90.................................76..615..-..@.....961..........\$.......*........."
            )
        )
        assertEquals(3558, parser.parts.sum())
    }

    @Test
    fun `should count actual engine parts`() {
        val parser = SchematicParser(actualInputList)
        // 547806 < x < 558036, !557409
        assertEquals(1, parser.parts.sum())
    }

}