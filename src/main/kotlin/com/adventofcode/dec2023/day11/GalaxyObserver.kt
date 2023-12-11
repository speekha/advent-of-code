package com.adventofcode.dec2023.day11

import com.adventofcode.positioning.Position
import kotlin.math.abs

class GalaxyObserver(input: List<String>) {

    val galaxies: List<Position> = input.indices.flatMap { row ->
        input[row].indices.filter { col -> input[row][col] == '#' }.map { col -> Position(col, row) }
    }

    fun processEmptyZones(expansionRate: Long): List<Pair<Long, Long>> {
        val minX = galaxies.minOf { it.x }
        val maxX = galaxies.maxOf { it.x }
        val minY = galaxies.minOf { it.y }
        val maxY = galaxies.maxOf { it.y }
        var emptyXRanges = listOf(minX..maxX)
        var emptyYRanges = listOf(minY..maxY)
        galaxies.forEach {
            emptyXRanges = splitRange(emptyXRanges, it.x)
            emptyYRanges = splitRange(emptyYRanges, it.y)
        }
        val emptyRows = emptyYRanges.flatMap { it.toList() }
        val emptyCols = emptyXRanges.flatMap { it.toList() }
        return galaxies.map { pos ->
            Pair(pos.x + (expansionRate - 1) * emptyCols.count { it < pos.x }, pos.y + (expansionRate - 1) * emptyRows.count { it < pos.y })
        }
    }

    private fun splitRange(emptyRanges: List<IntRange>, x: Int): List<IntRange> {
        val empty = emptyRanges.indexOfFirst { x in it }
        val result = emptyRanges.toMutableList()
        if (empty != -1) {
            val splitRange: List<IntRange> = listOf(
                emptyRanges[empty].first..<x,
                (x + 1)..emptyRanges[empty].last
            )
            result.removeAt(empty)
            result += splitRange.filter { !it.isEmpty() }
        }
        return result
    }

    fun sumUpDistances(expansionRate: Int): Long {
        val expanded = processEmptyZones(expansionRate.toLong())
        return expanded.indices.sumOf { i ->
            (i + 1..expanded.lastIndex).sumOf { j ->
                val a = expanded[i]
                val b = expanded[j]
                abs(a.first - b.first) + abs(a.second - b.second)
            }
        }
    }
}
