package com.adventofcode.dec2020.day11

import com.adventofcode.positioning.*

class SeatManager(input: List<String>) {

    var seats: Grid = input.map { row ->
        row.map {
            when (it) {
                '.' -> Tile.FLOOR
                'L' -> Tile.EMPTY
                '#' -> Tile.OCCUPIED
                else -> error("Unkown character: $it")
            }
        }.toTypedArray()
    }.toTypedArray()

    val seatsAsString: String
        get() = seats.joinToString("\n") { row ->
            row.joinToString("") { it.code }
        }

    fun iterate(threshold: Int, extended: Boolean) {
        seats = computeNextGrid(threshold, extended)
    }

    fun iterateUntilEquilibrium(threshold: Int, extended: Boolean) {
        var next = seats
        do {
            seats = next
            next = computeNextGrid(threshold, extended)
        } while (!compare(seats, next))
        seats = next
    }

    fun countOccupiedSeats() = seats.indices.sumBy { row ->
        seats[row].indices.sumBy { col ->
            if (seats[row][col] == Tile.OCCUPIED) 1 else 0
        }
    }

    private fun compare(seats: Grid, next: Grid): Boolean = seats.indices.all { row ->
        seats[row].indices.all { col ->
            seats[row][col] == next[row][col]
        }
    }

    private fun computeNextGrid(threshold: Int, extended: Boolean) = Array(seats.size) { row ->
        Array(seats[row].size) { col ->
            computeNextTileState(GridPosition(row, col), threshold, extended)
        }
    }

    private fun computeNextTileState(pos: GridPosition, threshold: Int, extended: Boolean): Tile {
        if (seats[pos] == Tile.EMPTY) {
            val occupiedNeighbors = countOccupiedNeighbors(pos, extended)
            if (occupiedNeighbors == 0) {
                return Tile.OCCUPIED
            }
        } else if (seats[pos] == Tile.OCCUPIED) {
            val occupiedNeighbors = countOccupiedNeighbors(pos, extended)
            if (occupiedNeighbors >= threshold) {
                return Tile.EMPTY
            }
        }
        return seats[pos]
    }

    private fun countOccupiedNeighbors(pos: GridPosition, extended: Boolean): Int = if (extended) {
        Neighbor.values().sumBy {
            var neighbor = pos + it
            while (neighbor in seats && seats[neighbor] == Tile.FLOOR) {
                neighbor += it
            }
            if (neighbor in seats && seats[neighbor] == Tile.OCCUPIED) 1 else 0
        }
    } else {
        seats.foldNeighbors(pos, 0) { acc, tile ->
            acc + if (tile == Tile.OCCUPIED) 1 else 0
        }
    }
}

typealias Grid = Array<Array<Tile>>

enum class Tile(val code: String) {
    FLOOR("."), EMPTY("L"), OCCUPIED("#")
}