package com.adventofcode.dec2020.day05

class RowParser {
    fun parse(input: String): Seat {
        val id = input
                .replace('F', '0')
                .replace('B', '1')
                .replace('L', '0')
                .replace('R', '1')
                .toInt(2)
        return Seat(id)
    }

    data class Seat(
            val row: Int,
            val col: Int
    ) {
        constructor(id: Int) : this(id / 8, id % 8)

        val id: Int = 8 * row + col
    }
}