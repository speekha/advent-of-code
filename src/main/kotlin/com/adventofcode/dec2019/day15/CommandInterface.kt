package com.adventofcode.dec2019.day15

import com.adventofcode.debug
import com.adventofcode.positioning.CardinalDirection
import com.adventofcode.positioning.Position
import kotlinx.coroutines.channels.Channel

class CommandInterface(
        private val commands: Channel<CardinalDirection>,
        private val results: Channel<CellType>
) {

    var oxygen: Position? = null

    var position = Position(0, 0)

    val map = mutableMapOf<Position, CellType>().also { it[position] = CellType.EMPTY }

    suspend fun move(direction: CardinalDirection) {
        debug("Move $direction")
        commands.send(direction)
        val destination = position + direction
        val cell = results.receive()
        debug(" : Result $cell")
        map[destination] = cell
        if (cell != CellType.WALL) {
            position = destination
        }
        if (cell == CellType.OXYGEN) {
            oxygen = destination
        }

    }

    companion object {
        fun convertDirection(direction: CardinalDirection) = when (direction) {
            CardinalDirection.NORTH -> 1L
            CardinalDirection.SOUTH -> 2L
            CardinalDirection.WEST -> 3L
            CardinalDirection.EAST -> 4L
        }

        fun convertCell(cell: Long) = CellType.values()[ cell.toInt()]
    }
}
