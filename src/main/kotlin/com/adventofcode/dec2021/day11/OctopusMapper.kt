package com.adventofcode.dec2021.day11

import com.adventofcode.positioning.Neighbor
import com.adventofcode.positioning.Position

class OctopusMapper(input: List<String>) {

    private val energies = input.map { row ->
        row.map { it - '0' }.toTypedArray()
    }.toTypedArray()

    fun getState(): String = energies.joinToString("\n") { it.joinToString("") }

    fun iterate(): Int {
        var flashCount = 0
        val flashes = mutableSetOf<Position>()
        var increasing = energies.indices.flatMap { row ->
            energies[row].indices.map { col ->
                Position(row, col)
            }
        }
        while (increasing.isNotEmpty()) {
            val nextBatch = mutableListOf<Position>()
            increasing.forEach { position ->
                if (position !in flashes) {
                    energies[position]++
                    if (energies[position] > 9) {
                        energies[position] = 0
                        flashes += position
                        flashCount++
                        val neighbors = Neighbor.values().map { position + it }.filter { it.x in 0..9 && it.y in 0..9 }
                        nextBatch += neighbors
                    }
                }
            }
            increasing = nextBatch
        }
        return flashCount
    }

    fun findSimultaneousFlash(): Int {
        var i = 1
        while (iterate() < 100) {
            i++
        }
        return i
    }
}

private operator fun Array<Array<Int>>.set(position: Position, value: Int) {
    this[position.x][position.y] = value
}

private operator fun Array<Array<Int>>.get(position: Position): Int = this[position.x][position.y]
