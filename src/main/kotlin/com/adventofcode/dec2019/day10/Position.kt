package com.adventofcode.dec2019.day10

data class Position(val x: Int, val y: Int) {
    fun computeOrientation(position: Position) =
            Target((y -position.y) / (x - position.x).toDouble(), position.x - x, position.y - y, this)
}