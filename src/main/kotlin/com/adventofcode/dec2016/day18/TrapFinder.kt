package com.adventofcode.dec2016.day18

class TrapFinder {

    fun getRow(input: String): Tiles = Tiles(input.map { it == '^' })

    fun countSafeTiles(input: String, rows: Int): Int {
        var current = getRow(input)
        return (1..rows).sumOf {
            val safeTiles = current.safeTiles()
            current = current.nextRow()
            safeTiles
        }
    }
}

fun main() {
    val input = "^..^^.^^^..^^.^...^^^^^....^.^..^^^.^.^.^^...^.^.^.^.^^.....^.^^.^.^.^.^.^.^^..^^^^^...^.....^....^."
    val finder = TrapFinder()
    println("Safe tiles: ${finder.countSafeTiles(input, 40)}")
    println("Safe tiles: ${finder.countSafeTiles(input, 400000)}")
}