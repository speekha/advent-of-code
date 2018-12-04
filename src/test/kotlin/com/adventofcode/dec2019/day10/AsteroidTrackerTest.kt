package com.adventofcode.dec2019.day10

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.File
import java.util.stream.Stream

@Disabled
class AsteroidTrackerTest {

    companion object {
        @JvmStatic
        fun testCases() = Stream.of(
                Arguments.of(listOf(".#..#",
                        ".....",
                        "#####",
                        "....#",
                        "...##"), Position(3, 4)),
                Arguments.of(listOf("......#.#.",
                        "#..#.#....",
                        "..#######.",
                        ".#.#.###..",
                        ".#..#.....",
                        "..#....#.#",
                        "#..#....#.",
                        ".##.#..###",
                        "##...#..#.",
                        ".#....####"), Position(5, 8)),
                Arguments.of(listOf(".#..##.###...#######",
                        "##.############..##.",
                        ".#.######.########.#",
                        ".###.#######.####.#.",
                        "#####.##.#.##.###.##",
                        "..#####..#.#########",
                        "####################",
                        "#.####....###.#.#.##",
                        "##.#################",
                        "#####.##.###..####..",
                        "..######..##.#######",
                        "####.##.####...##..#",
                        ".#####..#.######.###",
                        "##...#.####X#####...",
                        "#.##########.#######",
                        ".####.#.###.###.#.##",
                        "....##.##.###..#####",
                        ".#.#.###########.###",
                        "#.#.#.#####.####.###",
                        "###.##.####.##.#..##"), Position(11, 13))
        )
    }

    @Test
    fun `should list asteroids`() {
        val input = listOf(".#..#",
                ".....",
                "#####",
                "....#",
                "...##")

        val tracker = AsteroidTracker(input)
        assertEquals(10, tracker.asteroids.size)
        assertEquals(Position(1, 0), tracker.asteroids.first())
        assertEquals(Position(4, 4), tracker.asteroids.last())
    }

    @Test
    fun `should count visible asteroids`() {
        val input = listOf(".#..#",
                ".....",
                "#####",
                "....#",
                "...##")
        val tracker = AsteroidTracker(input)
        val visibleAsteroids = tracker.findVisibleAsteroids(Position(3, 4))
        assertEquals(8, visibleAsteroids.size)
    }

    @ParameterizedTest
    @MethodSource("testCases")
    fun `should find best observation post`(input: List<String>, result: Position) {
        val tracker = AsteroidTracker(input)
        assertEquals(result, tracker.findBestObservationPost())
    }

    @Test
    fun `should find actual best observation post`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2019/day10/input.txt").readLines()
        val tracker = AsteroidTracker(input)
        assertEquals(286, tracker.findVisibleAsteroids(tracker.findBestObservationPost()).size)
    }

    @Test
    fun `should vaporize asteroids in order`() {
        val input = listOf(
                ".#....#####...#..",
                "##...##.#####..##",
                "##...#...#.#####.",
                "..#.....#...###..",
                "..#.#.....#....##")
        val tracker = AsteroidTracker(input)
        val targets = tracker.sortAsteroids(tracker.findVisibleAsteroids(Position(8, 3)))
        val order = listOf(Position(8, 1), Position(9, 0), Position(9, 1), Position(10, 0), Position(9, 2))
        for (target in order.indices) {
            assertEquals(order[target], targets[target].position, "Failure with $target:")
        }
    }

    @Test
    fun `should vaporize asteroids in order with large map`() {
        val input = listOf(".#..##.###...#######",
                "##.############..##.",
                ".#.######.########.#",
                ".###.#######.####.#.",
                "#####.##.#.##.###.##",
                "..#####..#.#########",
                "####################",
                "#.####....###.#.#.##",
                "##.#################",
                "#####.##.###..####..",
                "..######..##.#######",
                "####.##.####...##..#",
                ".#####..#.######.###",
                "##...#.####X#####...",
                "#.##########.#######",
                ".####.#.###.###.#.##",
                "....##.##.###..#####",
                ".#.#.###########.###",
                "#.#.#.#####.####.###",
                "###.##.####.##.#..##")
        val tracker = AsteroidTracker(input)
        val targets = tracker.sortAsteroids(tracker.findVisibleAsteroids(Position(11, 13)))
        val order = listOf(Position(11, 12), Position(12, 1), Position(12, 2))
        for (target in order.indices) {
            assertEquals(order[target], targets[target].position, "Failure with ${target + 1}:")
        }
        assertEquals(Position(12, 8), targets[9].position, "Failure with 10")
        assertEquals(Position(16, 0), targets[19].position, "Failure with 20")
        assertEquals(Position(16, 9), targets[49].position, "Failure with 50")
        assertEquals(Position(16, 9), targets[49].position, "Failure with 50")
        assertEquals(Position(10, 16), targets[99].position, "Failure with 100")
        assertEquals(Position(9, 6), targets[198].position, "Failure with 199")
        assertEquals(Position(8, 2), targets[199].position, "Failure with 200")
        assertEquals(Position(10, 9), targets[200].position, "Failure with 201")
    }

    @Test
    fun `should find 200th vaporized asteroid`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2019/day10/input.txt").readLines()
        val tracker = AsteroidTracker(input)
        val targets = tracker.sortAsteroids(tracker.findVisibleAsteroids(tracker.findBestObservationPost()))
        val target = targets[199].position.let { it.x * 100 + it.y }
        assertEquals(504, target)
    }
}