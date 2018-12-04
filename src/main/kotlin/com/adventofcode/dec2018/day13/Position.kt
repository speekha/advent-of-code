package com.adventofcode.dec2018.day13

data class Position(
        val x: Int,
        val y: Int) {
    operator fun plus(direction: Direction) = Position(x + direction.dX, y + direction.dY)
}
