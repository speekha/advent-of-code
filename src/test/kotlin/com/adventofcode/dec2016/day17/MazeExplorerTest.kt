package com.adventofcode.dec2016.day17

import org.junit.Assert
import org.junit.Test

class MazeExplorerTest {

    @Test
    fun `should find shortest path for first pass`() {
        val pathFinder = MazeExplorer("ihgpwlah")
        Assert.assertEquals("DDRRRD", pathFinder.findShortestPath())
    }

    @Test
    fun `should find shortest path for second pass`() {
        val pathFinder = MazeExplorer("kglvqrro")
        Assert.assertEquals("DDUDRLRRUDRD", pathFinder.findShortestPath())
    }

    @Test
    fun `should find shortest path for third pass`() {
        val pathFinder = MazeExplorer("ulqzkmiv")
        Assert.assertEquals("DRURDRUDDLLDLUURRDULRLDUUDDDRR", pathFinder.findShortestPath())
    }

    @Test
    fun `should find shortest path for actual pass`() {
        val pathFinder = MazeExplorer("gdjjyniy")
        Assert.assertEquals("DUDDRLRRRD", pathFinder.findShortestPath())
    }

    @Test
    fun `should find longest path for first pass`() {
        val pathFinder = MazeExplorer("ihgpwlah")
        Assert.assertEquals(370, pathFinder.findLongestPath())
    }

    @Test
    fun `should find longest path for second pass`() {
        val pathFinder = MazeExplorer("kglvqrro")
        Assert.assertEquals(492, pathFinder.findLongestPath())
    }

    @Test
    fun `should find longest path for third pass`() {
        val pathFinder = MazeExplorer("ulqzkmiv")
        Assert.assertEquals(830, pathFinder.findLongestPath())
    }

    @Test
    fun `should find longest path for actual pass`() {
        val pathFinder = MazeExplorer("gdjjyniy")
        Assert.assertEquals(578, pathFinder.findLongestPath())
    }

}