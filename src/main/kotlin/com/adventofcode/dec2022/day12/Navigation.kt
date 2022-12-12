package com.adventofcode.dec2022.day12

import com.adventofcode.positioning.Direction
import com.adventofcode.positioning.Position
import java.util.LinkedList
import java.util.PriorityQueue

class Navigation(input: List<String>) {

    val map = parseMap(input)

    val start: Position = parseStart(input)

    val end: Position = parseEnd(input)

    fun computeRoute(optimize: Boolean = false): Int {
        val track = Array(map.size) {
            IntArray(map[it].size) {
                Int.MAX_VALUE
            }
        }
        val queue = PriorityQueue<Pair<Position, Int>> { a, b ->
            a.second.compareTo(b.second)
        }
        queue.add(start to 0)
        track[start] = 0
        while (queue.isNotEmpty()) {
            val (current, distance) = queue.poll()
            if (current == end) {
                return distance
            } else {
                Direction.values()
                    .map { current + it }
                    .filter { isReachable(it) }
                    .forEach {
                        if (optimize && map[it] == 0 && track[it] > 0) {
                            track[it] = 0
                            queue.add(it to 0)
                        } else {
                            val newDistance = distance + 1
                            if (
                                track[it] > newDistance &&
                                map[it] - map[current.y][current.x] < 2
                            ) {
                                track[it] = newDistance
                                queue.add(it to newDistance)
                            }
                        }
                    }
            }
        }
        return -1
    }

    private fun isReachable(neighbor: Position) = neighbor.y in map.indices && neighbor.x in map[neighbor.y].indices

    companion object {
        fun parseMap(input: List<String>): Array<IntArray> = input.map { row ->
            row.map {
                when (it) {
                    'S' -> 0
                    'E' -> 25
                    else -> it - 'a'
                }
            }.toIntArray()
        }.toTypedArray()

        private fun parseStart(input: List<String>): Position {
            return locateChar('S', input)
        }

        private fun parseEnd(input: List<String>): Position {
            return locateChar('E', input)
        }

        private fun locateChar(char: Char, input: List<String>): Position {
            val row = input.indices.first { input[it].contains(char) }
            val col = input[row].indexOf(char)
            return Position(col, row)
        }

        operator fun Array<IntArray>.get(position: Position) = this[position.y][position.x]

        operator fun Array<IntArray>.set(position: Position, value: Int) {
            this[position.y][position.x] = value
        }
    }
}
