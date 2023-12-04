package com.adventofcode.dec2023.day02

import com.adventofcode.actualInput
import com.adventofcode.actualInputList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CubeGameTest {

    val input = listOf(
        "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
        "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
        "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
        "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
        "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
    )

    @Test
    fun `should count possible games`() {
        val engine = CubeGameEngine()
        assertEquals(listOf(1, 2, 5), input.filterPossibleGames(engine, 12, 13, 14))
        assertEquals(8, input.filterPossibleGames(engine, 12, 13, 14).sum())
    }

    @Test
    fun `should count actual possible games`() {
        val engine = CubeGameEngine()
        assertEquals(2512, actualInputList.filterPossibleGames(engine, 12, 13, 14).sum())
    }

    private fun List<String>.filterPossibleGames(engine: CubeGameEngine, red: Int, green: Int, blue: Int) =
        withIndex().filter { (_, game) ->
            engine.isGamePossible(game, red, green, blue)
        }.map { it.index + 1 }

    @Test
    fun `count miminum cubes`() {
        val engine = CubeGameEngine()
        assertEquals(CubeGameEngine.Configuration(4, 2, 6), engine.getMinimalConfiguration(input[0]))
    }

    @Test
    fun `compute cube power`() {
        val engine = CubeGameEngine()
        assertEquals(2286, input.sumOf {
            engine.getMinimalConfiguration(it).score
        }
        )
    }
    @Test
    fun `compute actual cube power`() {
        val engine = CubeGameEngine()
        assertEquals(67335, actualInputList.sumOf {
            engine.getMinimalConfiguration(it).score
        }
        )
    }
}