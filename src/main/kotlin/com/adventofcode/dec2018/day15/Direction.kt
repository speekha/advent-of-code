package com.adventofcode.dec2018.day15

enum class Direction(val dX: Int, val dY: Int) {
    UP(0, -1), LEFT(-1, 0),RIGHT(1, 0), DOWN(0, 1);
}
