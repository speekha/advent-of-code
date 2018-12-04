package com.adventofcode.dec2016.day17

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MazeExplorerTest {

    @Test
    fun `should find shortest path for first pass`() {
        val pathFinder = MazeExplorer("ihgpwlah")
        Assertions.assertEquals("DDRRRD", pathFinder.findShortestPath())
    }

    @Test
    fun `should find shortest path for second pass`() {
        val pathFinder = MazeExplorer("kglvqrro")
        Assertions.assertEquals("DDUDRLRRUDRD", pathFinder.findShortestPath())
    }

    @Test
    fun `should find shortest path for third pass`() {
        val pathFinder = MazeExplorer("ulqzkmiv")
        Assertions.assertEquals("DRURDRUDDLLDLUURRDULRLDUUDDDRR", pathFinder.findShortestPath())
    }

    @Test
    fun `should find shortest path for actual pass`() {
        val pathFinder = MazeExplorer("gdjjyniy")
        Assertions.assertEquals("DUDDRLRRRD", pathFinder.findShortestPath())
    }

    @Test
    fun `should find longest path for first pass`() {
        val pathFinder = MazeExplorer("ihgpwlah")
        Assertions.assertEquals(370, pathFinder.findLongestPath())
    }

    @Test
    fun `should find longest path for second pass`() {
        val pathFinder = MazeExplorer("kglvqrro")
        Assertions.assertEquals(492, pathFinder.findLongestPath())
    }

    @Test
    fun `should find longest path for third pass`() {
        val pathFinder = MazeExplorer("ulqzkmiv")
        Assertions.assertEquals(830, pathFinder.findLongestPath())
    }

    @Test
    fun `should find longest path for actual pass`() {
        val pathFinder = MazeExplorer("gdjjyniy")
        Assertions.assertEquals(578, pathFinder.findLongestPath())
    }

}