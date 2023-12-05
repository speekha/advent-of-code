package com.adventofcode.dec2023.day03

import com.adventofcode.positioning.Position

data class Part(
    val id: String,
    val row: Int,
    val position: IntRange,
    val surroundings:List<String>
) {

    val type: Char? = surroundings.flatMap { it.toList() }.firstOrNull() {  char -> char !in '0'..'9' && char != '.'  }

    val gearCoordinates: Position?

    init {
        gearCoordinates = if (type != null) {
            var gearRow = surroundings.indexOfFirst { it.contains(type) }
            var gearCol = surroundings[gearRow].indexOf(type) + position.first - 1
            gearRow += row - 1
            if (row == 0) {
                gearRow ++
            }
            if (position.first == 0) {
                gearCol++
            }
            Position(gearRow, gearCol)
        } else null
    }
    fun isEnginePart(): Boolean = type != null

}