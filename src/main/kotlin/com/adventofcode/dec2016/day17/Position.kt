package com.adventofcode.dec2016.day17

import com.adventofcode.dec2016.day17.Direction.*

data class Position(val row: Int, val col: Int) {
    operator fun plus(direction: Direction) = when (direction) {
        UP -> Position(row - 1, col)
        DOWN -> Position(row + 1, col)
        LEFT -> Position(row, col - 1)
        RIGHT -> Position(row, col + 1)
    }
}