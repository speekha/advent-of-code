package com.adventofcode.dec2017.day3

data class Position(val x: Int, val y: Int) {

    fun go(direction: Direction, steps: Int): Position = Position(x + steps * direction.x, y + steps * direction.y)

    operator fun plus(it: Position) = Position(x + it.x, y + it.y)

}
