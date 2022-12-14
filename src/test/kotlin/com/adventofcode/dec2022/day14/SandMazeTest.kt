package com.adventofcode.dec2022.day14

import com.adventofcode.actualInputList
import com.adventofcode.positioning.Position
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SandMazeTest {


    val input = listOf(
        "498,4 -> 498,6 -> 496,6",
        "503,4 -> 502,4 -> 502,9 -> 494,9"
    )

    @Test
    fun `should parse maze`() {
        val maze = SandMaze(input)
        assertEquals(20, maze.map.sumOf { row -> row.count { it == Tile.ROCK } })
        maze.print()
    }

    @Test
    fun `should compute final position for sand`() {
        val maze = SandMaze(input)
        assertEquals(Position(500, 8), maze.findFinalPosition(Position(500, 0)))
    }

    @Test
    fun `should add sand to maze`() {
        val maze = SandMaze(input)
        maze.addSand()
        assertEquals(21, maze.map.sumOf { row -> row.count { it != Tile.AIR } })
        maze.print()
    }

    @Test
    fun `should detect when sand leaves maze`() {
        val maze = SandMaze(input)
        val steps = maze.fillWithSand()
        maze.print()
        assertEquals(24, steps)
    }

    @Test
    fun `should detect when sand leaves actual maze`() {
        val maze = SandMaze(actualInputList)
        val steps = maze.fillWithSand()
        assertEquals(1016, steps)
    }

    @Test
    fun `should detect when source is blocked`() {
        val maze = SandMaze(input, false)
        val steps = maze.fillWithSand()
        maze.print()
        assertEquals(93, steps)
    }

    @Test
    fun `should detect when actual source is blocked`() {
        val maze = SandMaze(actualInputList, false)
        val steps = maze.fillWithSand()
        assertEquals(25402, steps)
    }
}