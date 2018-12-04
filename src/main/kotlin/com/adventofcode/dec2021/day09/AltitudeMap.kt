package com.adventofcode.dec2021.day09

import com.adventofcode.positioning.Direction
import com.adventofcode.positioning.Position
import java.util.*

class AltitudeMap(
    val map: List<String>
) {

    fun findLowPoints(): List<Position> = map.flatMapIndexed { row: Int, s: String ->
        s.indices.map { col -> Position(col, row) }.filter { isLowPoint(it) }
    }

    fun findLowPointsValues(): List<Int> = findLowPoints().map { map[it.y][it.x] - '0' }.sorted()

    private fun isLowPoint(position: Position): Boolean = Direction.values()
        .map { position + it }
        .filter { it.x in map[0].indices && it.y in map.indices }
        .none { map[it.y][it.x] <= map[position.y][position.x] }


    fun findLowPointsRisk() = findLowPointsValues().sumOf { it + 1 }

    fun findBasins(): List<Int> {
        val lowPoints = findLowPoints()
        val basinSizes = IntArray(lowPoints.size) { 1 }
        val marking = Array(map.size) { IntArray(map[0].length) { -1 } }
        lowPoints.forEachIndexed { index, point ->
            marking[point.y][point.x] = index
        }
        val queue = LinkedList<Position>()
        queue.addAll(lowPoints)
        while (queue.isNotEmpty()) {
            val eval = queue.pollFirst()
            val basin = marking[eval.y][eval.x]
            Direction.values()
                .map { eval + it }
                .filter { it.x in map[0].indices && it.y in map.indices && marking[it.y][it.x] == -1 }
                .forEach {
                    if (map[it.y][it.x] > map[eval.y][eval.x] && map[it.y][it.x] < '9') {
                        marking[it.y][it.x] = basin
                        basinSizes[basin]++
                        queue.add(it)
                    }
                }
        }
        return basinSizes.sorted()
    }

    fun findBasinScore(): Int = findBasins().takeLast(3).reduce { acc, i -> acc * i }
}
