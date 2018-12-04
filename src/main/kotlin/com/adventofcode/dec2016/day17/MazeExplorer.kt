package com.adventofcode.dec2016.day17

import java.util.*

class MazeExplorer(val pass: String) {

    fun findShortestPath(): String {
        val start: MazeState = MazeState(Position(0, 0), pass)
        val queue = LinkedList<MazeState>().apply {
            add(start)
        }
        while (queue.isNotEmpty()) {
            val room = queue.pop()
            val nextPositions = room.nextPositions()
            nextPositions.firstOrNull { it.position == Position(3, 3) }?.let {
                return it.state.substring(pass.length)
            }
            queue += nextPositions
        }
        error("Path not found")
    }

    fun findLongestPath(): Int {
        val start = MazeState(Position(0, 0), pass)
        var longest = -1
        val queue = LinkedList<MazeState>().apply {
            add(start)
        }
        while (queue.isNotEmpty()) {
            val room = queue.pop()
                val nextPositions = room.nextPositions()
                nextPositions.firstOrNull { it.position == Position(3, 3) }?.let {
                    longest = it.state.length - pass.length
                }
                queue += nextPositions.filter { it.position != Position(3, 3) }
        }
        return longest
    }
}

fun main() {
    val mazeExplorer = MazeExplorer("gdjjyniy")
    println("Shortest path is: ${mazeExplorer.findShortestPath()}")
    println("Longest path is: ${mazeExplorer.findLongestPath()}")
}