package com.adventofcode.dec2022.day17

import com.adventofcode.positioning.Position


enum class Shapes(val blocks: Array<Position>) {

    A(arrayOf(Position(0, 0), Position(1, 0), Position(2, 0), Position(3, 0))),
    B(arrayOf(Position(1, 0), Position(0, 1), Position(1, 1), Position(2, 1), Position(1, 2))),
    C(arrayOf(Position(2, 0), Position(2, 1), Position(2, 2), Position(1, 2), Position(0, 2))),
    D(arrayOf(Position(0, 0), Position(0, 1), Position(0, 2), Position(0, 3))),
    E(arrayOf(Position(0, 0), Position(1, 0), Position(0, 1), Position(1, 1)));

    val height: Int = blocks.maxOf { it.y } - blocks.minOf { it.y } + 1
}