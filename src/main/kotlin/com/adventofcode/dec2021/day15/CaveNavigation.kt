package com.adventofcode.dec2021.day15

import com.adventofcode.positioning.Direction
import com.adventofcode.positioning.Position
import java.util.*

class CaveNavigation(input: List<String>) {

    val map = input.map { row -> row.map { cell -> cell - '0' } }

    fun lowestRiskRoute(size: Int = 1): Int {
        val queue = PriorityQueue(listOf(Option(Position(0, 0), 0)))
        val marked = Array(map.size * size) { y -> BooleanArray(map[0].size * size) { x -> x == 0 && y == 0 } }
        var position: Position
        var risk: Int
        do {
            val item = queue.poll()
            position = item.position
            risk = item.risk
            Direction.values()
                .map { position + it }
                .filter {
                    it.x in marked[0].indices && it.y in marked.indices && !marked[it.y][it.x]
                }.forEach {
                    queue.add(Option(it, risk + computeRisk(it)))
                    marked[it.y][it.x] = true
                }
        } while (position != Position(marked[0].size - 1, marked.size - 1))
        return risk
    }

    private fun computeRisk(it: Position): Int {
        val risk = map[it.y % map.size][it.x % map[0].size]
        return (((risk + it.y / map.size + it.x / map[0].size) - 1) % 9) + 1
    }

    data class Option(
        val position: Position,
        val risk: Int
    ) : Comparable<Option> {
        override fun compareTo(other: Option): Int = risk.compareTo(other.risk)
    }
}
