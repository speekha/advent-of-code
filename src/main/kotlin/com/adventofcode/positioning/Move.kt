package com.adventofcode.positioning

interface Orientation {
        val x: Int
        val y: Int
}

data class Move(
        val direction: Orientation,
        val distance: Int
)