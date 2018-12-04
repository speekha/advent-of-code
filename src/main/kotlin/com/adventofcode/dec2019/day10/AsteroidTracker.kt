package com.adventofcode.dec2019.day10

import java.util.Comparator
import kotlin.math.abs

class AsteroidTracker(input: List<String>) {

    internal val asteroids: List<Position> = mapAsteroids(input)

    private fun mapAsteroids(input: List<String>): List<Position> = input.mapIndexed { y, row ->
        row.mapIndexedNotNull { x, c -> Position(x, y).takeIf { c != '.' } }
    }.flatten()

    internal fun findVisibleAsteroids(position: Position): Map<Pair<Double, Boolean>, List<Target>> =
            asteroids.filter { it != position }
                    .map { it.computeOrientation(position) }
                    .groupBy { it.orientation to (it.dX > 0) }

    fun sortAsteroids(input: Map<Pair<Double, Boolean>, List<Target>>) =
            input.entries
                    .sortedWith(comparator())
                    .map { it.value.minByOrNull { abs(it.dX) + abs(it.dY) } ?: error("No min found") }

    private fun comparator(): Comparator<Map.Entry<Pair<Double, Boolean>, List<Target>>> {
        return kotlin.Comparator { o1, o2 ->
            when {
                o1.key.second == o2.key.second -> o1.key.first.compareTo(o2.key.first)
                o1.key.second -> 1
                else -> -1
            }
        }
    }

    fun findBestObservationPost(): Position {
        return asteroids.maxByOrNull { findVisibleAsteroids(it).size } ?: error("Position not found")
    }

}

