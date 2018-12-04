package com.adventofcode.dec2018.day10

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class MessageReaderTest {

    @Test
    fun `should parse rows`() {
        val reader = MessageReader(input)
        Assertions.assertEquals(MessageReader.SkyPoint(9, 1, 0, 2), reader.initialPositions[0])
    }

    @Test
    fun `should render sky`() {
        val reader = MessageReader(input)
        Assertions.assertEquals(render0, reader.renderState())
    }

    @Test
    fun `should render sky at t=1`() {
        val reader = MessageReader(input)
        reader.iterate()
        Assertions.assertEquals(render1, reader.renderState())
    }

    @Test
    fun `should render message`() {
        val reader = MessageReader(input)
        Assertions.assertEquals(3 to renderFinal, reader.waitForMessage())
    }

    @Test
    fun `should render actual message`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2018/day10/input.txt").readLines()
        val reader = MessageReader(input)
        Assertions.assertEquals(10144 to renderFinalActual, reader.waitForMessage())
    }

    companion object {
        val input = listOf(
            "position=< 9,  1> velocity=< 0,  2>",
            "position=< 7,  0> velocity=<-1,  0>",
            "position=< 3, -2> velocity=<-1,  1>",
            "position=< 6, 10> velocity=<-2, -1>",
            "position=< 2, -4> velocity=< 2,  2>",
            "position=<-6, 10> velocity=< 2, -2>",
            "position=< 1,  8> velocity=< 1, -1>",
            "position=< 1,  7> velocity=< 1,  0>",
            "position=<-3, 11> velocity=< 1, -2>",
            "position=< 7,  6> velocity=<-1, -1>",
            "position=<-2,  3> velocity=< 1,  0>",
            "position=<-4,  3> velocity=< 2,  0>",
            "position=<10, -3> velocity=<-1,  1>",
            "position=< 5, 11> velocity=< 1, -2>",
            "position=< 4,  7> velocity=< 0, -1>",
            "position=< 8, -2> velocity=< 0,  1>",
            "position=<15,  0> velocity=<-2,  0>",
            "position=< 1,  6> velocity=< 1,  0>",
            "position=< 8,  9> velocity=< 0, -1>",
            "position=< 3,  3> velocity=<-1,  1>",
            "position=< 0,  5> velocity=< 0, -1>",
            "position=<-2,  2> velocity=< 2,  0>",
            "position=< 5, -2> velocity=< 1,  2>",
            "position=< 1,  4> velocity=< 2,  1>",
            "position=<-2,  7> velocity=< 2, -2>",
            "position=< 3,  6> velocity=<-1, -1>",
            "position=< 5,  0> velocity=< 1,  0>",
            "position=<-6,  0> velocity=< 2,  0>",
            "position=< 5,  9> velocity=< 1, -2>",
            "position=<14,  7> velocity=<-2,  0>",
            "position=<-3,  6> velocity=< 2, -1>"
        )

        val render0 = listOf(
            "........#.............",
            "................#.....",
            ".........#.#..#.......",
            "......................",
            "#..........#.#.......#",
            "...............#......",
            "....#.................",
            "..#.#....#............",
            ".......#..............",
            "......#...............",
            "...#...#.#...#........",
            "....#..#..#.........#.",
            ".......#..............",
            "...........#..#.......",
            "#...........#.........",
            "...#.......#.........."
        ).joinToString("\n")

        val render1 = listOf(
            "......................",
            "......................",
            "..........#....#......",
            "........#.....#.......",
            "..#.........#......#..",
            "......................",
            "......#...............",
            "....##.........#......",
            "......#.#.............",
            ".....##.##..#.........",
            "........#.#...........",
            "........#...#.....#...",
            "..#...........#.......",
            "....#.....#.#.........",
            "......................",
            "......................"
        ).joinToString("\n")

        val renderFinal = listOf(
            "#...#..###",
            "#...#...#.",
            "#...#...#.",
            "#####...#.",
            "#...#...#.",
            "#...#...#.",
            "#...#...#.",
            "#...#..###"
        ).joinToString("\n")

        val renderFinalActual = listOf(
            ".####....####...#.......######..#.......#....#...####...######",
            "#....#..#....#..#............#..#.......#....#..#....#..#.....",
            "#.......#.......#............#..#.......#....#..#.......#.....",
            "#.......#.......#...........#...#.......#....#..#.......#.....",
            "#.......#.......#..........#....#.......######..#.......#####.",
            "#..###..#..###..#.........#.....#.......#....#..#.......#.....",
            "#....#..#....#..#........#......#.......#....#..#.......#.....",
            "#....#..#....#..#.......#.......#.......#....#..#.......#.....",
            "#...##..#...##..#.......#.......#.......#....#..#....#..#.....",
            ".###.#...###.#..######..######..######..#....#...####...######"
        ).joinToString("\n")
    }
}

