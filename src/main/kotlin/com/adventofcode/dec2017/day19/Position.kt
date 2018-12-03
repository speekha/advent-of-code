package com.adventofcode.dec2017.day19

data class Position(val x: Int, val y: Int) {
    operator fun plus(direction: Direction) = Position(x + direction.x, y + direction.y)
}