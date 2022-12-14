package com.adventofcode.dec2022.day14

import com.adventofcode.positioning.Neighbor
import com.adventofcode.positioning.Position
import com.adventofcode.positioning.get
import com.adventofcode.positioning.set
import kotlin.math.max
import kotlin.math.min

class SandMaze(
    input: List<String>,
    val bottomLess: Boolean = true
) {

    val xMin: Int

    val xMax: Int

    val yMin: Int = 0

    val yMax: Int

    val map: Array<Array<Tile>>

    init {
        val lines = input.map { line ->
            line.split(" -> ").map {
                val point = it.split(",")
                Position(point[0].toInt(), point[1].toInt())
            }
        }
        yMax = lines.maxOf { line -> line.maxOf { it.y } } + if (bottomLess) 0 else 2
        xMin = 500 - yMax - 2
        xMax = 500 + yMax + 2
        map = Array(yMax - yMin + 1) {
            Array(xMax - xMin + 1) {
                Tile.AIR
            }
        }
        lines.forEach { plot(it) }
        if (!bottomLess) {
            plotLine(Position(xMin, yMax), Position(xMax, yMax))
        }
    }

    private fun plot(lines: List<Position>) {
        lines.windowed(2).forEach {
            plotLine(it[0], it[1])
        }
    }

    private fun plotLine(a: Position, b: Position) {
        if (a.x == b.x) {
            for (y in min(a.y, b.y)..max(a.y, b.y)) {
                this[Position(a.x, y)] = Tile.ROCK
            }
        } else {
            for (x in min(a.x, b.x)..max(a.x, b.x)) {
                this[Position(x, a.y)] = Tile.ROCK
            }
        }
    }

    operator fun get(position: Position): Tile = map[position - Position(xMin, yMin)]

    operator fun set(position: Position, value: Tile) {
        map[position - Position(xMin, yMin)] = value
    }

    fun findFinalPosition(initialPosition: Position): Position {
        var sandPosition: Position
        var nextPosition = initialPosition
        do {
            sandPosition = nextPosition
            nextPosition = computeNextPosition(sandPosition)
        } while (nextPosition != sandPosition
            && nextPosition.x in xMin..xMax
            && nextPosition.y in yMin..yMax
        )
        return nextPosition
    }

    private val neighbors = listOf(Neighbor.BOTTOM, Neighbor.BOTTOM_LEFT, Neighbor.BOTTOM_RIGHT)

    private fun computeNextPosition(sandPosition: Position): Position =
        neighbors.map { sandPosition + it }
            .firstOrNull {
                it.x !in xMin..xMax
                        || it.y !in yMin..yMax
                        || this[it] == Tile.AIR
            } ?: sandPosition

    fun addSand(): Position = findFinalPosition(Position(500, 0)).also {
        map[it - Position(xMin, yMin)] = Tile.SAND
    }

    fun fillWithSand(): Int {
        var i = 0
        var lastSand : Position
        try {
            do {
                lastSand = addSand()
                i++
            } while (bottomLess || lastSand != Position(500, 0))
        } catch (e: IndexOutOfBoundsException) {
        }
        return i
    }

    fun print() {
        map.forEach { row ->
            row.forEach { tile ->
                print(
                    when (tile) {
                        Tile.ROCK -> "#"
                        Tile.SAND -> "O"
                        else -> " "
                    }
                )
            }
            println()
        }
    }
}

