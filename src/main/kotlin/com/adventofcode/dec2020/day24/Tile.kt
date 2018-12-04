package com.adventofcode.dec2020.day24

import com.adventofcode.positioning.Position

enum class Tile {
    WHITE, BLACK;

    fun flip(): Tile = values()[1 - ordinal]
}

enum class HexNeighbors(val code: String, val x: Int, val y: Int) {
    EAST("e", 2, 0),
    SOUTH_EAST("se", 1, 1),
    SOUTH_WEST("sw", -1, 1),
    WEST("w", -2, 0),
    NORTH_WEST("nw", -1, -1),
    NORTH_EAST("ne", 1, -1)
}

operator fun Position.plus(neighbor: HexNeighbors) = Position(x + neighbor.x, y + neighbor.y)