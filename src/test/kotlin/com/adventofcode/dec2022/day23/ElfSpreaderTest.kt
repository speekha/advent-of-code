package com.adventofcode.dec2022.day23

import com.adventofcode.actualInputList
import com.adventofcode.positioning.Position
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ElfSpreaderTest {

    val input = listOf(
        ".....",
        "..##.",
        "..#..",
        ".....",
        "..##.",
        "....."
    )

    @Test
    fun `should parse input`() {
        val spreader = ElfSpreader.fromInput(input)
        assertEquals(
            listOf(
                Elf(Position(2, 1)),
                Elf(Position(3, 1)),
                Elf(Position(2, 2)),
                Elf(Position(2, 4)),
                Elf(Position(3, 4))
            ), spreader.elves
        )
    }

    @Test
    fun `should move elves one step`() {
        val expected = listOf(
            "..##.",
            ".....",
            "..#..",
            "...#.",
            "..#..",
            "....."
        )
        val spreader = ElfSpreader.fromInput(input)
        val result = spreader.spread()
        assertEquals(ElfSpreader.fromInput(expected).elves.toSet(), spreader.elves.toSet())
        assertTrue(result)
    }

    @Test
    fun `should move elves two step`() {
        val expected = listOf(
            ".....",
            "..##.",
            ".#...",
            "....#",
            ".....",
            "..#.."
        )
        val spreader = ElfSpreader.fromInput(input)
        spreader.spread()
        val result = spreader.spread()
        assertEquals(ElfSpreader.fromInput(expected).elves.toSet(), spreader.elves.toSet())
        assertTrue(result)
    }

    @Test
    fun `should detect end of process`() {
        val expected = listOf(
            "..#..",
            "....#",
            "#....",
            "....#",
            ".....",
            "..#.."
        )
        val spreader = ElfSpreader.fromInput(input)
        spreader.spread()
        spreader.spread()
        spreader.spread()
        val result = spreader.spread()
        assertEquals(ElfSpreader.fromInput(expected).elves.toSet(), spreader.elves.toSet())
        assertFalse(result)
    }

    @Test
    fun `should count empty tiles`() {
        val input = listOf(
            "....#..",
            "..###.#",
            "#...#.#",
            ".#...##",
            "#.###..",
            "##.#.##",
            ".#..#.."
        )
        val spreader = ElfSpreader.fromInput(input)
        repeat(10) {
            spreader.spread()
        }
        assertEquals(110, spreader.countEmptyTiles())
    }

    @Test
    fun `should count actual empty tiles`() {
        val spreader = ElfSpreader.fromInput(actualInputList)
        repeat(10) {
            spreader.spread()
        }
        assertEquals(4025, spreader.countEmptyTiles())
    }


    @Test
    fun `should count needed rounds`() {
        val input = listOf(
            "....#..",
            "..###.#",
            "#...#.#",
            ".#...##",
            "#.###..",
            "##.#.##",
            ".#..#.."
        )
        val spreader = ElfSpreader.fromInput(input)
        var i = 1
        while (spreader.spread()) {
            i++
        }
        assertEquals(20, i)
    }
    @Test
    fun `should count actually needed rounds`() {
        val spreader = ElfSpreader.fromInput(actualInputList)
        var i = 1
        while (spreader.spread()) {
            i++
        }
        assertEquals(935, i)
    }
}