package com.adventofcode.dec2018.day12

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlantGrowerTest {

    val input = listOf(
        "initial state: #..#.#..##......###...###",
        "",
        "...## => #",
        "..#.. => #",
        ".#... => #",
        ".#.#. => #",
        ".#.## => #",
        ".##.. => #",
        ".#### => #",
        "#.#.# => #",
        "#.### => #",
        "##.#. => #",
        "##.## => #",
        "###.. => #",
        "###.# => #",
        "####. => #"
    )

    val results = listOf(
        "0: ...#..#.#..##......###...###...........",
        " 1: ...#...#....#.....#..#..#..#...........",
        " 2: ...##..##...##....#..#..#..##..........",
        " 3: ..#.#...#..#.#....#..#..#...#..........",
        " 4: ...#.#..#...#.#...#..#..##..##.........",
        " 5: ....#...##...#.#..#..#...#...#.........",
        " 6: ....##.#.#....#...#..##..##..##........",
        " 7: ...#..###.#...##..#...#...#...#........",
        " 8: ...#....##.#.#.#..##..##..##..##.......",
        " 9: ...##..#..#####....#...#...#...#.......",
        "10: ..#.#..#...#.##....##..##..##..##......",
        "11: ...#...##...#.#...#.#...#...#...#......",
        "12: ...##.#.#....#.#...#.#..##..##..##.....",
        "13: ..#..###.#....#.#...#....#...#...#.....",
        "14: ..#....##.#....#.#..##...##..##..##....",
        "15: ..##..#..#.#....#....#..#.#...#...#....",
        "16: .#.#..#...#.#...##...#...#.#..##..##...",
        "17: ..#...##...#.#.#.#...##...#....#...#...",
        "18: ..##.#.#....#####.#.#.#...##...##..##..",
        "19: .#..###.#..#.#.#######.#.#.#..#.#...#..",
        "20: .#....##....#####...#######....#.#..##."
    )

    @Test
    fun `should extract initial state`() {
        val grower = Plantgrower(input)
        val expected = results[0].substring(results[0].indexOf('#'))
        assertEquals(expected, grower.state.padEnd(expected.length, '.'))
    }

    @Test
    fun `should extract rules`() {
        val grower = Plantgrower(input)
        assertEquals(14, grower.rules.size)
        assertEquals('#', grower.rules["...##"])
        assertEquals('#', grower.rules["..#.."])
    }

    @Test
    fun `should compute states`() {
        val grower = Plantgrower(input)
        results.drop(1).forEach { it ->
            grower.iterate()
            val expected = it.substring(it.indexOf('#'))
            assertEquals(expected, grower.state.padEnd(expected.length, '.'))
        }
    }

    @Test
    fun `should compute final sum`() {
        val grower = Plantgrower(input)
        assertEquals(325, grower.computeTotal())
    }

    @Test
    fun `should compute actual final sum`() {
        val input = readInputAsList()
        val grower = Plantgrower(input)
        assertEquals(2444, grower.computeTotal())
    }

    @Test
    fun `should compute actual final sum for 50000000 generations`() {
        val input = readInputAsList()
        val grower = Plantgrower(input)
        assertEquals(750000000697, grower.computeTotal(50000000000))
    }
}