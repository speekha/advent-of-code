package com.adventofcode.dec2023.day16

import com.adventofcode.positioning.Direction
import com.adventofcode.positioning.Direction.*
import com.adventofcode.positioning.Position
import java.util.*

class BeamTracer(
    val map: List<String>
) {

    fun countEnergizedTiles(init: Beam = Beam(Position(0, 0), RIGHT)): Int {
        val queue = LinkedList<Beam>()
        val energyMap = Array(map.size) { i -> Array<MutableList<Direction>>(map[i].length) { mutableListOf() } }
        queue.add(init)
        energyMap.addBeam(init)
        while (queue.isNotEmpty()) {
            queue.addAll(energyMap.getOutput(queue.pollFirst()))
        }
        return energyMap.sumOf { row -> row.count { tile -> tile.isNotEmpty() } }
    }

    private fun Array<Array<MutableList<Direction>>>.addBeam(beam: Beam) {
        this[beam.position.y][beam.position.x] += beam.direction
    }

    private fun Array<Array<MutableList<Direction>>>.getOutput(beam: Beam): List<Beam> = when (val tile = map[beam.position.y][beam.position.x]) {
        '.' -> listOf(beam.direction)
        '/' -> listOf(reflectRight(beam.direction))
        '\\' -> listOf(reflectLeft(beam.direction))
        '|' -> split(beam.direction, UP, DOWN)
        '-' -> split(beam.direction, LEFT, RIGHT)
        else -> error("Invalid tile: $tile")
    }.map { Beam(beam.position + it, it) }.filter {
        it.position in this && it.direction !in this[it.position]
    }.onEach { addBeam(it) }

    private operator fun <T : Any> Array<Array<T>>.contains(position: Position) = position.y in indices && position.x in this[position.y].indices

    private operator fun <T : Any> Array<Array<T>>.get(position: Position) = this[position.y][position.x]

    private fun reflectRight(direction: Direction): Direction = when (direction) {
        UP -> RIGHT
        RIGHT -> UP
        DOWN -> LEFT
        LEFT -> DOWN
    }

    private fun reflectLeft(direction: Direction): Direction = when (direction) {
        UP -> LEFT
        RIGHT -> DOWN
        DOWN -> RIGHT
        LEFT -> UP
    }

    private fun split(direction: Direction, vararg directions: Direction): List<Direction> =
        if (direction in directions) listOf(direction) else listOf(direction - 1, direction + 1)

    fun optimizeEnergy(): Int = (horizontalEdges() + verticalEdges()).maxOf { countEnergizedTiles(it) }

    private fun verticalEdges() = map[0].indices.flatMap {
        listOf(
            Beam(Position(it, 0), DOWN),
            Beam(Position(it, map.lastIndex), UP)
        )
    }

    private fun horizontalEdges() = map.indices.flatMap {
        listOf(
            Beam(Position(0, it), RIGHT),
            Beam(Position(map[it].lastIndex, it), LEFT)
        )
    }
}
