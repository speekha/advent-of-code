package com.adventofcode.dec2023.day10

import com.adventofcode.positioning.Direction
import com.adventofcode.positioning.Position
import java.util.LinkedList

class PipeExplorer(val map: List<String>) {
    val start: Position

    init {
        val sY = map.indexOfFirst { 'S' in it }
        val sX = map[sY].indexOf('S')
        start = Position(sX, sY)
    }

    fun findFarthestPoint(): Int {
        return findLoop().size / 2
    }

    private fun findLoop(): List<Position> {
        var current = start
        val startDirection = mapStarts(start)
        var direction = startDirection
        val loop = mutableListOf<Position>()
        do {
            loop += current
            current += direction
            direction = mapDirection(direction, map[current.y][current.x], startDirection)
        } while (current != start)
        return loop
    }

    private fun mapStarts(position: Position): Direction = when {
        map[position.y][position.x - 1] in listOf('-', 'F', 'L') -> Direction.LEFT
        map[position.y][position.x + 1] in listOf('-', '7', 'J') -> Direction.RIGHT
        map[position.y - 1][position.x] in listOf('|', 'F', '7') -> Direction.UP
        map[position.y + 1][position.x] in listOf('|', 'L', 'J') -> Direction.DOWN
        else -> error("Invalid data")
    }

    private fun mapDirection(direction: Direction, c: Char, default: Direction): Direction = when {
        c == '-' || c == '|' -> direction
        c == 'F' && direction == Direction.LEFT -> Direction.DOWN
        c == 'F' -> Direction.RIGHT
        c == 'L' && direction == Direction.LEFT -> Direction.UP
        c == 'L' -> Direction.RIGHT
        c == '7' && direction == Direction.RIGHT -> Direction.DOWN
        c == '7' -> Direction.LEFT
        c == 'J' && direction == Direction.RIGHT -> Direction.UP
        c == 'J' -> Direction.LEFT
        else -> default
    }

    fun countEnclosedTiles(): Int {
        val tiles = Array(map.size) { i ->
            Array(map[i].length) { Tile.UNKNOWN }
        }
        val loop = findLoop()
        loop.forEach { tiles[it.y][it.x] = Tile.LOOP }

        val (left, right) = walkThroughLoop(tiles)
        val leftIsOut = when {
            left == Tile.OUT -> true
            right == Tile.OUT -> false
            else -> isLeftOut(tiles)
        }
        if (leftIsOut) {
            fillRest(tiles, Tile.RIGHT)
        } else {
            fillRest(tiles, Tile.LEFT)
        }

        return tiles.sumOf { row -> row.count { tile -> tile == Tile.IN } }
    }

    private fun walkThroughLoop(tiles: Array<Array<Tile>>): Pair<Tile, Tile> {
        var current = start
        val startDirection = mapStarts(start)
        var direction = startDirection
        val loop = mutableListOf<Position>()
        var left = Tile.LEFT
        var right = Tile.RIGHT
        do {
            loop += current
            current += direction
            left = mapNeighbor(tiles, current, direction, left) { it.turnLeft() }
            right = mapNeighbor(tiles, current, direction, right) { it.turnRight() }
            direction = mapDirection(direction, map[current.y][current.x], startDirection)
            left = mapNeighbor(tiles, current, direction, left) { it.turnLeft() }
            right = mapNeighbor(tiles, current, direction, right) { it.turnRight() }
        } while (current != start)
        return Pair(left, right)
    }

    private fun mapNeighbor(tiles: Array<Array<Tile>>, current: Position, direction: Direction, default: Tile, convert: (Direction) -> Direction): Tile {
        val neighbor = current + convert(direction)
        val tile = tiles[neighbor]
        if (tile == Tile.UNKNOWN) {
            tiles[neighbor] = default
        } else if (tile == Tile.IN || tile == Tile.OUT) {
            return tile
        }
        return default
    }

    private fun isLeftOut(tiles: Array<Array<Tile>>): Boolean {
        val queue = initQueue(tiles, Tile.LEFT)
        try {
            processUnknownTiles(queue, tiles, true)
        } catch (e: java.lang.IndexOutOfBoundsException) {
            return true
        }
        return false
    }

    private fun fillRest(tiles: Array<Array<Tile>>, value: Tile) {
        val queue = initQueue(tiles, value)
        queue.forEach { tiles[it] = Tile.IN }
        processUnknownTiles(queue, tiles, false)
    }

    private fun initQueue(tiles: Array<Array<Tile>>, value: Tile) = LinkedList(tiles.indices.flatMap { y ->
        tiles[y].indices.asSequence().map { x -> Position(x, y) }.filter { (tiles[it] == value) }
    })

    private fun processUnknownTiles(queue: LinkedList<Position>, tiles: Array<Array<Tile>>, checkBounds: Boolean) {
        while (queue.isNotEmpty()) {
            val current = queue.pollFirst()
            val neighbors = Direction.values().map { current + it }
            neighbors.forEach { neighbor ->
                when (tiles[neighbor]) {
                    Tile.UNKNOWN -> {
                        tiles[neighbor] = tiles[current]
                        queue.add(neighbor)
                    }

                    Tile.OUT -> if (checkBounds) throw IndexOutOfBoundsException()
                    else -> {}
                }
            }
        }
    }


    private operator fun Array<Array<Tile>>.get(position: Position): Tile = if (position.y in indices && position.x in this[position.y].indices) {
        this[position.y][position.x]
    } else Tile.OUT

    private operator fun Array<Array<Tile>>.set(position: Position, value: Tile) {
        this[position.y][position.x] = value
    }

    enum class Tile {
        OUT, IN, UNKNOWN, LOOP, LEFT, RIGHT
    }
}
