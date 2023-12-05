package com.adventofcode.dec2022.day24

import com.adventofcode.positioning.Direction
import com.adventofcode.positioning.Position
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BlizzardMazeTest {

    @Test
    fun `should parse input`() {
        val input = listOf(
            "#.#####",
            "#.....#",
            "#>....#",
            "#.....#",
            "#...v.#",
            "#.....#",
            "#####.#"
        )
        val maze = BlizzardMaze(input)
        assertEquals(
            listOf(
                Blizzard(Direction.RIGHT, Position(0, 1)),
                Blizzard(Direction.DOWN, Position(3, 3)),
            ), maze.blizzards
        )
    }

    @Test
    fun `should find shortest path`() {

        val input = listOf(
            "#.######",
            "#>>.<^<#",
            "#.<..<<#",
            "#>v.><>#",
            "#<^v^^>#",
            "######.#"
        )
        val maze = BlizzardMaze(input)
        assertEquals(18, maze.findPath())
    }
}