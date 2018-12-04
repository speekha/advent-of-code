package com.adventofcode.dec2018.day6

import kotlin.math.abs

data class Position(val x: Int, val y: Int) {

    operator fun plus(dir: Direction) = Position(x + dir.dx, y + dir.dy)

    operator fun minus(point: Position) = abs(x - point.x) + abs(y - point.y)
}