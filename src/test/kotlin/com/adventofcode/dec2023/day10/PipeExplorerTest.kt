package com.adventofcode.dec2023.day10

import com.adventofcode.actualInputList
import com.adventofcode.positioning.Position
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PipeExplorerTest {

    val input = listOf(
        "-L|F7",
        "7S-7|",
        "L|7||",
        "-L-J|",
        "L|-JF"
    )

    @Test
    fun `should find starting point`() {
        val explorer = PipeExplorer(input)
        assertEquals(Position(1, 1), explorer.start)
    }

    @Test
    fun `should navigate to farthest point`() {
        val explorer = PipeExplorer(input)
        assertEquals(4, explorer.findFarthestPoint())
    }

    @Test
    fun `should navigate to actual farthest point`() {
        val explorer = PipeExplorer(actualInputList)
        assertEquals(6890, explorer.findFarthestPoint())
    }


    @Test
    fun `should count enclosed tiles`() {
        val explorer = PipeExplorer(input)
        assertEquals(1, explorer.countEnclosedTiles())
    }

    @Test
    fun `should process larger example`() {
        val explorer = PipeExplorer(
            listOf(
                "..........",
                ".S------7.",
                ".|F----7|.",
                ".||OOOO||.",
                ".||OOOO||.",
                ".|L-7F-J|.",
                ".|II||II|.",
                ".L--JL--J.",
                ".........."
            )
        )
        assertEquals(4, explorer.countEnclosedTiles())
    }

    @Test
    fun `should process even larger example`() {
        val explorer = PipeExplorer(
            listOf(
                ".F----7F7F7F7F-7....",
                ".|F--7||||||||FJ....",
                ".||.FJ||||||||L7....",
                "FJL7L7LJLJ||LJ.L-7..",
                "L--J.L7...LJS7F-7L7.",
                "....F-J..F7FJ|L7L7L7",
                "....L7.F7||L7|.L7L7|",
                ".....|FJLJ|FJ|F7|.LJ",
                "....FJL-7.||.||||...",
                "....L---J.LJ.LJLJ..."
            )
        )
        assertEquals(8, explorer.countEnclosedTiles())
    }

    @Test
    fun `should count actual enclosed tiles`() {
        val explorer = PipeExplorer(actualInputList)
        assertEquals(453, explorer.countEnclosedTiles())
    }
}