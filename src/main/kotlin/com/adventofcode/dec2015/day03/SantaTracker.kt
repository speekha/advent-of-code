package com.adventofcode.dec2015.day03

import com.adventofcode.positioning.Direction
import com.adventofcode.positioning.Position

class SantaTracker {

    var position = Position(0, 0)

    val positions = mutableListOf(position)

    fun move(travelData: String) {
        travelData.forEach {
            position = when (it) {
                '>' -> position + Direction.RIGHT
                '<' -> position + Direction.LEFT
                '^' -> position + Direction.UP
                'v' -> position + Direction.DOWN
                else -> error("Parsing error")
            }
            positions += position
        }
    }

    fun deliveredHouses() = positions.toSet()
}