package com.adventofkotlin.dec2018

enum class Direction(
        val cost: Double,
        val dX: Int,
        val dY: Int) {
    UP(1.0, 0, -1),
    UP_RIGHT(1.5, 1, -1),
    RIGHT(1.0, 1, 0),
    DOWN_RIGHT(1.5, 1, 1),
    DOWN(1.0, 0, 1),
    DOWN_LEFT(1.5, -1, 1),
    LEFT(1.0, -1, 0),
    UP_LEFT(1.5, -1, -1)
}