package com.adventofcode.dec2016.day1

data class Position(val x: Int, val y: Int) {

    fun go(direction: Direction, steps: Int): Position = Position(x + steps * direction.x, y + steps * direction.y)

}
