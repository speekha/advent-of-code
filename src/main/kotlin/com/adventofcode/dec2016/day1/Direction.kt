package com.adventofcode.dec2016.day1

enum class Direction(val x: Int, val y: Int) {
    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0);

    fun turnRight(): Direction = values()[(ordinal + 1) % values().size]
    fun turnLeft(): Direction = values()[(values().size + ordinal - 1) % values().size]
}