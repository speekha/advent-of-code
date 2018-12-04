package com.adventofcode.dec2016.day1

class MapNavigator {
    fun calculateJourneyDestinationDistance(input: String): Int {
        val steps: List<String> = input.split(", ")
        var pos = Position(0, 0)
        var dir = Direction.NORTH
        steps.forEach {
            dir = if (it[0] == 'L') dir.turnLeft() else dir.turnRight()
            pos = pos.go(dir, it.substring(1).toInt())
        }
        return calculateTaxicabDistance(Position(0, 0), pos)
    }

    fun calculateHQDistance(input: String): Int {
        val steps: List<String> = input.split(", ")
        var posList = listOf(Position(0, 0))
        var dir = Direction.NORTH
        steps.forEach {
            dir = if (it[0] == 'L') dir.turnLeft() else dir.turnRight()
            (1..it.substring(1).toInt()).forEach {
                val nextPos = posList.last().go(dir, 1)
                if (posList.contains(nextPos)) {
                    return calculateTaxicabDistance(Position(0, 0), nextPos)
                }
                posList += nextPos
            }
        }
        return 0
    }

    fun calculateTaxicabDistance(start: Position, end: Position) = Math.abs(start.x - end.x) + Math.abs(start.y - end.y)
}

fun main() {
    val input = "R4, R3, L3, L2, L1, R1, L1, R2, R3, L5, L5, R4, L4, R2, R4, L3, R3, L3, R3, R4, R2, L1, R2, L3, L2, L1, R3, R5, L1, L4, R2, L4, R3, R1, R2, L5, R2, L189, R5, L5, R52, R3, L1, R4, R5, R1, R4, L1, L3, R2, L2, L3, R4, R3, L2, L5, R4, R5, L2, R2, L1, L3, R3, L4, R4, R5, L1, L1, R3, L5, L2, R76, R2, R2, L1, L3, R189, L3, L4, L1, L3, R5, R4, L1, R1, L1, L1, R2, L4, R2, L5, L5, L5, R2, L4, L5, R4, R4, R5, L5, R3, L1, L3, L1, L1, L3, L4, R5, L3, R5, R3, R3, L5, L5, R3, R4, L3, R3, R1, R3, R2, R2, L1, R1, L3, L3, L3, L1, R2, L1, R4, R4, L1, L1, R3, R3, R4, R1, L5, L2, R2, R3, R2, L3, R4, L5, R1, R4, R5, R4, L4, R1, L3, R1, R3, L2, L3, R1, L2, R3, L3, L1, L3, R4, L4, L5, R3, R5, R4, R1, L2, R3, R5, L5, L4, L1, L1"
    with(MapNavigator()) {
        println("Distance #1 : ${calculateJourneyDestinationDistance(input)}")
        println("Distance #1 : ${calculateHQDistance(input)}")
    }
}