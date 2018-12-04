package com.adventofcode.dec2020.day03

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TobogganNavigatorTest {

    val input = listOf(
            "..##.......",
            "#...#...#..",
            ".#....#..#.",
            "..#.#...#.#",
            ".#...##..#.",
            "..#.##.....",
            ".#.#.#....#",
            ".#........#",
            "#.##...#...",
            "#...##....#",
            ".#..#...#.#")

    @Test
    fun `should count trees down the map`() {
        val navigator = TobogganNavigator()
        Assertions.assertEquals(7, navigator.countTrees(input, 3, 1))
    }

    @Test
    fun `should count valid passwords for toboggan rental policies in actual data`() {
        val navigator = TobogganNavigator()
        val input = readInputAsList()
        Assertions.assertEquals(214, navigator.countTrees(input, 3, 1))
    }

    @Test
    fun `should count trees down the map for variants`() {
        val navigator = TobogganNavigator()
        Assertions.assertEquals(2, navigator.countTrees(input, 1, 1))
        Assertions.assertEquals(7, navigator.countTrees(input, 3, 1))
        Assertions.assertEquals(3, navigator.countTrees(input, 5, 1))
        Assertions.assertEquals(4, navigator.countTrees(input, 7, 1))
        Assertions.assertEquals(2, navigator.countTrees(input, 1, 2))
    }

    @Test
    fun `should count trees down the actual map for variants`() {
        val navigator = TobogganNavigator()
        val input = readInputAsList()
        var result = 1L
        result *= navigator.countTrees(input, 1, 1)
        result *= navigator.countTrees(input, 3, 1)
        result *= navigator.countTrees(input, 5, 1)
        result *= navigator.countTrees(input, 7, 1)
        result *= navigator.countTrees(input, 1, 2)
        Assertions.assertEquals(8336352024, result)
    }
}