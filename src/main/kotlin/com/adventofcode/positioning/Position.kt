package com.adventofcode.positioning

import javax.print.attribute.standard.MediaSize.Other
import kotlin.math.abs

data class Position(val x: Int, val y: Int) {

    operator fun plus(direction: Orientation) = Position(x + direction.x, y + direction.y)

    operator fun plus(direction: Position) = Position(x + direction.x, y + direction.y)

    operator fun plus(move: Move) = Position(x + move.distance * move.direction.x, y + move.distance * move.direction.y)

    operator fun times(value: Int) = Position(x * value, y * value)

    fun distance() = abs(x) + abs(y)

    fun distance(destination: Position) = (this - destination).distance()

    operator fun minus(other: Position): Position = Position(x - other.x, y - other.y)
}

operator fun <T> Array<Array<T>>.get(position: Position) = this[position.y][position.x]

operator fun <T> Array<Array<T>>.set(position: Position, value: T) {
    this[position.y][position.x] = value
}