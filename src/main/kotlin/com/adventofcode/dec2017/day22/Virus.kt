package com.adventofcode.dec2017.day22

class Virus(input: List<String>) {

    val infectedCells = map(input)

    var position = Position(0, 0)

    var direction = Direction.UP

    var infections = 0

    companion object {
        fun map(input: List<String>): MutableMap<Position, CellStatus> {
            val offset = input[0].length / 2
            return input.indices.asSequence().flatMap { row ->
                input[row].indices.asSequence()
                        .map { col ->
                            Position(col - offset, row - offset) to convert(input[row][col])
                        }
            }.associate { it }
                    .toMutableMap()
        }

        private fun convert(char: Char) = when (char) {
            '#' -> CellStatus.INFECTED
            else -> CellStatus.CLEAN
        }
    }

    fun viewMap(size: Int) = (-size / 2..size / 2).map { y ->
        (-size / 2..size / 2).map { x ->
            when (infectedCells.getOrDefault(Position(x, y), CellStatus.CLEAN)) {
                CellStatus.INFECTED -> '#'
                CellStatus.WEAKENED -> 'W'
                CellStatus.FLAGGED -> 'F'
                else -> '.'
            }
        }.joinToString("")
    }

    fun simpleInfectionBurst() {
        val status = infectedCells.getOrDefault(position, CellStatus.CLEAN)
        updateDirection(status)
        invertCellState(status)
        position += direction
    }

    fun evolvedInfectionBurst() {
        val status = infectedCells.getOrDefault(position, CellStatus.CLEAN)
        updateDirection(status)
        updateCellState(status)
        position += direction
    }

    private fun updateDirection(status: CellStatus) {
        direction = when (status) {
            CellStatus.CLEAN -> direction.turnLeft()
            CellStatus.INFECTED -> direction.turnRight()
            CellStatus.FLAGGED -> direction.reverse()
            else -> direction
        }
    }

    private fun invertCellState(status: CellStatus) {
        when (status) {
            CellStatus.INFECTED -> infectedCells.remove(position)
            CellStatus.CLEAN -> {
                infectedCells[position] = CellStatus.INFECTED
                infections++
            }
            else -> {
            }
        }
    }

    private fun updateCellState(status: CellStatus) {
        when (status) {
            CellStatus.CLEAN -> infectedCells[position] = CellStatus.WEAKENED
            CellStatus.WEAKENED -> {
                infectedCells[position] = CellStatus.INFECTED
                infections++
            }
            CellStatus.INFECTED -> infectedCells[position] = CellStatus.FLAGGED
            else -> infectedCells[position] = CellStatus.CLEAN
        }
    }
}