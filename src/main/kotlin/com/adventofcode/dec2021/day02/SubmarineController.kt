package com.adventofcode.dec2021.day02

class SubmarineController {


    private var position = SubmarineState(0, 0, 0)

    fun navigate(commands: List<String>): SubmarineState {
        commands.forEach { command ->
            position = computeNextPosition(position, command)
        }
        return position
    }

    private fun computeNextPosition(position: SubmarineState, command: String): SubmarineState {
        val (direction, value) = command.split(" ")
        val increment = value.toInt()
        return when (direction) {
            "forward" -> SubmarineState(position.position + increment, position.depth + increment * position.aim, position.aim)
            "down" -> SubmarineState(position.position, position.depth, position.aim + increment)
            "up" -> SubmarineState(position.position, position.depth , position.aim - increment)
            else -> position
        }
    }

}
