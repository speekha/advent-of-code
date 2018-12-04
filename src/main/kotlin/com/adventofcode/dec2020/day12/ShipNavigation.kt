package com.adventofcode.dec2020.day12

import com.adventofcode.positioning.CardinalDirection
import com.adventofcode.positioning.Move
import com.adventofcode.positioning.Position

class ShipNavigation {

    var orientation = CardinalDirection.EAST

    var position = Position(0, 0)

    var wayPoint = Position(10, -1)

    fun navigate(input: List<String>) {
        input.forEach {
            val distance = it.drop(1).toInt()
            when (it[0]) {
                'N' -> position += Move(CardinalDirection.NORTH, distance)
                'E' -> position += Move(CardinalDirection.EAST, distance)
                'S' -> position += Move(CardinalDirection.SOUTH, distance)
                'W' -> position += Move(CardinalDirection.WEST, distance)
                'F' -> position += Move(orientation, distance)
                'L' -> repeat(distance / 90) { orientation = orientation.turnLeft() }
                'R' -> repeat(distance / 90) { orientation = orientation.turnRight() }
                else -> error("Unknown instruction ${it[0]}")
            }
        }
    }

    fun navigateWayPoint(input: List<String>) {
        input.forEach {
            val distance = it.drop(1).toInt()
            when (it[0]) {
                'N' -> wayPoint += Move(CardinalDirection.NORTH, distance)
                'E' -> wayPoint += Move(CardinalDirection.EAST, distance)
                'S' -> wayPoint += Move(CardinalDirection.SOUTH, distance)
                'W' -> wayPoint += Move(CardinalDirection.WEST, distance)
                'F' -> position += wayPoint * distance
                'L' -> repeat(distance / 90) { rotateWayPointLeft() }
                'R' -> repeat(distance / 90) { rotateWayPointRight() }
            }
        }
    }

    private fun rotateWayPointLeft() {
        wayPoint = Position(wayPoint.y, -wayPoint.x)
    }

    private fun rotateWayPointRight() {
        wayPoint = Position(-wayPoint.y, wayPoint.x)
    }
}
