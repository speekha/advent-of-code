package com.adventofcode.dec2019.day18

import com.adventofcode.positioning.Direction
import com.adventofcode.positioning.Position
import java.util.*

class Explorer(
        private val map: Array<Array<Cell>>,
        private val entrance: Position,
        private val keys: Map<Char, Position>
) {

    val distances: Map<Cell, Map<Char, Link>> = listDistances()

    fun listDistances(): Map<Cell, Map<Char, Link>> {
        val distances = mutableMapOf<Cell, Map<Char, Link>>()
        distances[Cell.Entrance] = listReachableKeys(entrance)
        keys.forEach {
            distances[map[it.value]] = listReachableKeys(it.value)
        }
        return distances
    }

    private fun listReachableKeys(start: Position): Map<Char, Link> {
        val targets = mutableMapOf<Char, Link>()
        val tracker = Array(map.size) { Array(map[it].size) { false } }
        val queue = LinkedList<Pair<Position, Link>>()
        queue.add(start to Link(0, emptyList()))
        tracker[start] = true
        while (queue.isNotEmpty()) {
            val (pos, link) = queue.pop()
            Direction.values().forEach { dir ->
                val neighbor = pos + dir
                if (!tracker[neighbor] && isValid(neighbor)) {
                    tracker[neighbor] = true
                    when (val cell = map[neighbor]) {
                        Cell.Entrance, Cell.Empty -> queue.add(neighbor to Link(link.distance + 1, link.doors))
                        is Cell.Door -> queue.add(
                            neighbor to Link(
                                link.distance + 1,
                                link.doors + cell.name.lowercaseChar()
                            )
                        )

                        is Cell.Key -> {
                            targets[cell.name] = Link(link.distance + 1, link.doors)
                            queue.add(neighbor to Link(link.distance + 1, link.doors))
                        }

                        else -> {}
                    }
                }
            }
        }
        return targets
    }

    private fun isValid(pos: Position) = pos.y < map.size && pos.y >= 0 && pos.x >= 0 && pos.x < map[pos.y].size

    operator fun <T> Array<Array<T>>.get(position: Position) = this[position.y][position.x]

    operator fun <T> Array<Array<T>>.set(position: Position, value: T) {
        this[position.y][position.x] = value
    }

    fun listTargets(state: State, index: Int) = distances[state.positions[index]]?.filter { (key, value) ->
        key !in state.collectedKeys
                && value.doors.all { it in state.collectedKeys }
    }?.map { Target(index, it.key, it.value.distance) } ?: error("No target found for ${state.positions[index]}")
}
