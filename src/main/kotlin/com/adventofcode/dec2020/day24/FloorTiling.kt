package com.adventofcode.dec2020.day24

import com.adventofcode.positioning.Position

class FloorTiling() {

    var tiles: Floor = mutableSetOf()

    fun flipTiles(input: List<String>) {
        val floor: Floor = mutableSetOf()
        input.forEach { floor.flip(computePosition(it)) }
        tiles = floor
    }

    private fun computePosition(input: String): Position {
        var position = Position(0, 0)
        var index = 0
        while (index < input.length) {
            var key = ""
            key += input[index++]
            if (key == "n" || key == "s") {
                key += input[index++]
            }
            val neighbor = HexNeighbors.values().first { it.code == key }
            position += neighbor
        }
        return position
    }

    private fun Floor.flip(position: Position) {
        if (!remove(position)) {
            add(position)
        }
    }

    fun countBlackTiles(): Int = tiles.size

    fun iterate() {
        val neighbors = mutableMapOf<Position, Int>()
        tiles.forEach { tile ->
            HexNeighbors.values()
                .map { tile + it }
                .forEach { neighbors[it] = neighbors.getOrPut(it) { 0 } + 1 }
        }
        val flips = neighbors.entries.filter { (position, count) ->
            (position in tiles && count > 2) ||
                    (position !in tiles && count == 2)
        }.map { it.key } + tiles.filter { !neighbors.containsKey(it) }
        flips.forEach {
            tiles.flip(it)
        }
    }
}

typealias Floor = MutableSet<Position>