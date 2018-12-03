package com.adventofcode.dec2017.day3

enum class Direction(val x: Int, val y: Int) {
    UP(0, 1),
    RIGHT(1, 0),
    DOWN(0, -1),
    LEFT(-1, 0);

    fun turnRight(): Direction = values()[(ordinal + 1) % values().size]
    fun turnLeft(): Direction = values()[(values().size + ordinal - 1) % values().size]
}