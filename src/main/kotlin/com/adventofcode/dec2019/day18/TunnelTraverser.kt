package com.adventofcode.dec2019.day18

import com.adventofcode.positioning.Position
import java.util.*


class TunnelTraverser(input: List<String>) {

    val keys = mutableMapOf<Char, Position>()

    internal lateinit var entrances: List<Position>

    internal val cache = mutableMapOf<String, Int>()

    internal val map: Array<Array<Cell>> = input.mapIndexed { y, row ->
        row.mapIndexed { x, c ->
            parseCell(c, x, y)
        }.toTypedArray()
    }.toTypedArray()


    private fun parseCell(c: Char, x: Int, y: Int): Cell = when (c) {
        '.' -> Cell.Empty
        '#' -> Cell.Wall
        '@' -> Cell.Entrance.also { entrances = listOf(Position(x, y)) }
        in 'a'..'z' -> Cell.Key(c).also { keys[c] = Position(x, y) }
        in 'A'..'Z' -> Cell.Door(c)
        else -> error("Unknown type")
    }

    fun collectAllKeys(): Int {
        val explorers = entrances.map { Explorer(map, it, keys) }
        val queue = PriorityQueue<State>()
        queue.add(State(explorers.map { Cell.Entrance }, setOf(), 0))
        cache.clear()
        while (queue.peek().collectedKeys.size < keys.size) {
            val state = queue.poll()
            if (cache[getCacheKey(state.collectedKeys, state.positions)] != null) {
                continue
            }
            computeNextTargets(state, explorers, queue).forEach {
                queue.add(it)
            }
        }
        return queue.peek().distance
    }

    private fun computeNextTargets(state: State, explorers: List<Explorer>, queue: PriorityQueue<State>): List<State> {
        cache[getCacheKey(state.collectedKeys, state.positions)] = state.distance
        return explorers.mapIndexed { index, explorer ->
            explorer.listTargets(state, index).map {
                state + it
            }
        }.flatten()
    }

    private fun getCacheKey(keys: Set<Char>, positions: List<Cell>) = keys.sorted().joinToString("", postfix = "-") + positions.joinToString("-")

    fun divideAndConquer() {
        val (x, y) = entrances[0]
        (-1..1).forEach {
            map[y][x + it] = Cell.Wall
            map[y + it][x] = Cell.Wall
        }
        entrances = listOf(
                Position(x - 1, y - 1),
                Position(x + 1, y - 1),
                Position(x - 1, y + 1),
                Position(x + 1, y + 1)
        )
        entrances.forEach { map[it.y][it.x] = Cell.Entrance }
    }

}
