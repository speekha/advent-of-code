package com.adventofcode.dec2018.day13

enum class Direction(val dX: Int, val dY: Int) {
    UP(0, -1), RIGHT(1, 0), DOWN(0, 1), LEFT(-1, 0);

    operator fun plus(inc: Int) = Direction.values()[(ordinal + inc + values().size) % values().size]

    operator fun minus(dec: Int) = Direction.values()[(ordinal + values().size - dec) % values().size]

    operator fun plus(c: Char): Direction = when (this) {
        UP -> when (c) {
            '\\' -> LEFT
            '/' -> RIGHT
            else -> UP
        }
        DOWN -> when (c) {
            '\\' -> RIGHT
            '/' -> LEFT
            else -> DOWN
        }
        LEFT-> when (c) {
            '\\' -> UP
            '/' -> DOWN
            else -> LEFT
        }
        RIGHT -> when (c) {
            '\\' -> DOWN
            '/' -> UP
            else -> RIGHT
        }
    }

}
