package com.adventofcode.dec2017.day3

class GridWalker {

    var index = 1
    var dir = Direction.RIGHT
    var sideLength = 1
    var sideIndex = 0
    private var position = Position(0, 0)
    private val memory = mutableMapOf(position to 1)

    private val adjacentPositions = (-1..1).flatMap { i ->
        (-1..1).map { j ->
            Position(i, j)
        }
    }.filter { it != Position(0, 0) }

    fun computeNextCellValue(): Int {
        val pos: Position = getNextPosition()
        val value = adjacentPositions.sumBy { memory[pos + it] ?: 0 }
        memory[pos] = value
        return value
    }

    fun getNextPosition(): Position {
        handleTurns()
        sideIndex++
        index++
        return move()
    }

    private fun handleTurns() {
        if (shouldTurn()) {
            dir = dir.turnLeft()
            sideIndex = 0
            if (dir == Direction.LEFT || dir == Direction.RIGHT) {
                sideLength++
            }
        }
    }

    private fun shouldTurn() = sideIndex == sideLength

    private fun move() = position.go(dir, 1).also { position = it }
}