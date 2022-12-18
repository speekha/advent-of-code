package com.adventofcode.dec2022.day18

import com.adventofcode.dec2017.day20.Coordinates

enum class Sides(val direction: Coordinates) {
    LEFT(Coordinates(-1, 0, 0)),
    RIGHT(Coordinates(1, 0, 0)),
    BOTTOM(Coordinates(0, -1, 0)),
    TOP(Coordinates(0, 1, 0)),
    FRONT(Coordinates(0, 0, -1)),
    BACK(Coordinates(0, 0, 1))
}