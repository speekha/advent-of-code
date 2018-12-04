package com.adventofcode.dec2021.day05

data class Point(
    val x: Int,
    val y: Int) {

    operator fun plus(increment: Point) = Point(x + increment.x, y + increment.y)
}
