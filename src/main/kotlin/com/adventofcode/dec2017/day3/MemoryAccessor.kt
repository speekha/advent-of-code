package com.adventofcode.dec2017.day3

import kotlin.math.abs

class MemoryAccessor {

    tailrec fun computePosition(i: Int, position: Position = Position(0, 0), walker: GridWalker = GridWalker()): Position =
            if (walker.index >= i)
                position
            else
                computePosition(i, walker.getNextPosition(), walker)

    tailrec fun fillSquares(limit: Int, content: Int = 1, walker: GridWalker = GridWalker()): Int {
        return if (content >= limit) {
            content
        } else {
            fillSquares(limit, walker.computeNextCellValue(), walker)
        }
    }

    fun computeDistance(i: Int): Int = computeDistance(Position(0, 0), computePosition(i))

    fun computeDistance(start: Position, end: Position) = abs(start.x - end.x) + abs(start.y - end.y)
}

fun main(args: Array<String>) {
    with(MemoryAccessor()) {
        val input = 368078
        println("Distance for $input: ${computeDistance(input)}")
        println("Next input for $input: ${fillSquares(input)}")
    }
}