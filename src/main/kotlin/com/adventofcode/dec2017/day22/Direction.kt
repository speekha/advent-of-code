package com.adventofcode.dec2017.day22

enum class Direction(val x: Int, val y: Int) {
    UP(0, -1),
    RIGHT(1, 0),
    DOWN(0, 1),
    LEFT(-1, 0);

    fun turnRight() = values()[(ordinal + 1) % 4]

    fun reverse() = values()[(ordinal + 2) % 4]

    fun turnLeft() = values()[(ordinal + 3) % 4]
}