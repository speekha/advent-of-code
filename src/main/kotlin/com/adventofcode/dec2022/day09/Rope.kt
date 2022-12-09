package com.adventofcode.dec2022.day09

import com.adventofcode.positioning.Direction
import com.adventofcode.positioning.Position
import kotlin.math.abs
import kotlin.math.max

class Rope(size: Int = 2) {

    private val knots: Array<Position> = Array(size) { Position(0, 0) }

    val tail: Position
        get() = knots.last()

    val tailPositions: MutableSet<Position> = mutableSetOf(tail)

    fun moveHead(direction: Direction, steps: Int = 1) {
        repeat(steps) {
            knots[0] = knots[0] + direction
            moveRope()
        }
    }

    private fun moveRope() {
        knots.indices.drop(1).forEach { i ->
            val diff = knots[i - 1] - knots[i]
            if (max(abs(diff.x), abs(diff.y)) > 1) {
                val move = Position(diff.x.coerceIn(-1..1), diff.y.coerceIn(-1..1))
                knots[i] += move
                tailPositions += tail
            }
        }
    }

    fun moveHead(directions: List<String>) {
        directions.forEach {
            val (dir, distance) = it.split(" ")
            val direction = when (dir) {
                "L" -> Direction.LEFT
                "R" -> Direction.RIGHT
                "U" -> Direction.UP
                "D" -> Direction.DOWN
                else -> error("Invalid direction")
            }
            moveHead(direction, distance.toInt())
        }
    }
}