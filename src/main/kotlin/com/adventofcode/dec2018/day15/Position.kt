package com.adventofcode.dec2018.day15

import kotlin.math.abs

data class Position(
        val x: Int,
        val y: Int) {

    operator fun plus(direction: Direction) = Position(x + direction.dX, y + direction.dY)

    operator fun minus(position: Position): Int = abs(position.x - x) + abs(position.y - y)
}
