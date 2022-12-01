package com.adventofcode.positioning

import kotlin.math.abs

data class Position(val x: Int, val y: Int) {

    operator fun plus(direction: Orientation) = Position(x + direction.x, y + direction.y)

    operator fun plus(direction: Position) = Position(x + direction.x, y + direction.y)

    operator fun plus(move: Move) = Position(x + move.distance * move.direction.x, y + move.distance * move.direction.y)

    operator fun times(value: Int) = Position(x * value, y * value)

    fun distance() = abs(x) + abs(y)
}