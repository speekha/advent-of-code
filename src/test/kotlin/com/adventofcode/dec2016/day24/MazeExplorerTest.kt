package com.adventofcode.dec2016.day24

import org.junit.Assert
import org.junit.Test

class MazeExplorerTest {

    val input = listOf(
            "###########",
            "#0.1.....2#",
            "#.#######.#",
            "#4.......3#",
            "###########")

    @Test
    fun `should find shortest path`() {
        val explorer = MazeExplorer(input)
        Assert.assertEquals(14, explorer.findShortestRoute())
    }

//    @Test
//    fun `should calculate graph`() {
//        val distances = Array<Array<Int>>
//        val explorer = MazeExplorer(input)
//
//        Assert.assertEquals(14, explorer.findShortestRoute())
//
//    }

}