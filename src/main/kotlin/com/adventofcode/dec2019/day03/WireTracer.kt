package com.adventofcode.dec2019.day03

import com.adventofcode.positioning.Direction
import com.adventofcode.positioning.Position
import java.io.File

class WireTracer(input: List<String>) {

    val wires: List<List<Position>> = input.map { convertPath(it) }

    private fun convertPath(path: String): List<Position> {
        val result = mutableListOf(Position(0, 0))
        val segments = path.split(",")
        segments.forEach {
            val dir = when (it[0]) {
                'U' -> Direction.UP
                'D' -> Direction.DOWN
                'L' -> Direction.LEFT
                'R' -> Direction.RIGHT
                else -> error("Unknown direction")
            }
            val distance = it.substring(1).toInt()
            repeat(distance) {
                result += result.last() + dir
            }
        }
        return result
    }

    fun findDistanceToClosestIntersection() = findClosestIntersection().distance()

    fun findClosestIntersection(): Position {
        val wire1 = wires[0].drop(1).groupBy { it.distance() }
        val wire2 = wires[1].drop(1).groupBy { it.distance() }
        for (dist in wire1.keys) {
            val points = wire1[dist]
            val match = points?.firstOrNull { wire2[dist]?.contains(it) ?: false }
            if (match != null) {
                return match
            }
        }
        error("No match found")
    }

    fun findshortestPathToIntersection(): Int {
        val min = findClosestIntersection().distance()
        val other = wires[1].drop(1)
                .mapIndexed { index, position -> position to index + 1 }
                .associate { it }
        return wires[0].drop(1).mapIndexedNotNull { index, position ->
            other[position]?.let { index + it + 1 to position }
        }.minByOrNull { it.first }?.first ?: error("No intersection found")
    }
}

fun main() {
    val input = File("src/main/kotlin/com/adventofcode/dec2019/day03/input.txt").readLines()
    val runner = WireTracer(input)
    var result = runner.findClosestIntersection().distance()
    println("Closest intersection: $result")
    result = runner.findshortestPathToIntersection()
    println("Shortest path to intersection: $result")
}