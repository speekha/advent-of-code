package com.adventofcode.dec2016.day13

import org.junit.Assert
import org.junit.Test

class MazeNavigatorTest {

    val maze = listOf(".#.####.##",
            "..#..#...#",
            "#....##...",
            "###.#.###.",
            ".##..#..#.",
            "..##....#.",
            "#...##.###")

    @Test
    fun `should generate maze`() {
        val navigator = MazeNavigator(10)
        maze.indices.forEach { row ->
            maze[row].indices.forEach { col ->
                val cell = if (navigator.isWall(col, row)) '#' else '.'
                Assert.assertEquals(maze[row][col], cell)
            }
        }
    }

    @Test
    fun `should be 11`() {
        val navigator = MazeNavigator(10)
        Assert.assertEquals(11, navigator.findDistance(1 to 1, 7 to 4))
    }

    @Test
    fun `should be 3`() {
        val navigator = MazeNavigator(10)
        Assert.assertEquals(3, navigator.countReachable(1 to 1, 1))
    }

    @Test
    fun `should be 5`() {
        val navigator = MazeNavigator(10)
        Assert.assertEquals(5, navigator.countReachable(1 to 1, 2))
    }
}