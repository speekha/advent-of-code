package com.adventofkotlin.dec2018

import java.util.*
import kotlin.math.sqrt

class MarkPath(input: String) {

    val map: Matrix<Cell> = parseMap(input)

    val start = findCell(Cell.START)

    val target = findCell(Cell.END)

    lateinit var exploration: Matrix<Double>

    var result: Path? = null

    private fun parseMap(input: String) = input.split("\n").map { str ->
        str.map { char ->
            Cell.values().first { it.code == char.toString() }
        }.toTypedArray()
    }.toTypedArray()

    fun findShortestPath(): String {
        exploration = map.map { it.map { Double.MAX_VALUE }.toTypedArray() }.toTypedArray()
        dijkstra(start, target)
        return exportMap(result ?: error("Path not found"))
    }

    private fun dijkstra(start: Position, end: Position): Path = with(PriorityQueue<Path>()) {
        add(Path(start, start, 0.0, listOf()))
        while (isNotEmpty()) {
            markPosition()
        }
        return result ?: error("No path found")
    }

    private fun PriorityQueue<Path>.markPosition() {
        val currentPath = poll()
        val next = Direction.values()
                .map { currentPath + it }
                .filter { isPositionValid(it.end, it.cost) }
        addAll(next)
        next.forEach {
            if (map[it.end] == Cell.END) {
                clear()
                result = it
            }
            exploration[it.end] = it.cost
        }
    }

    private fun findCell(cell: Cell): Position {
        val y = map.indexOfFirst { cell in it }
        val x = map[y].indexOf(cell)
        return Position(x, y)
    }

    private fun exportMap(path: Path): String {
        val exportMap = map.map { it.copyOf() }.toTypedArray()
        exportMap[path.start] = Cell.VISITED
        path.moves.forEach { position ->
            exportMap[position] = Cell.VISITED
        }
        return exportMap.asString()
    }

    private fun <R> Matrix<R>.asString(): String = joinToString("\n") { row ->
        row.joinToString("") { cell -> cell.toString() }
    }

    inner class Path(val start: Position, val end: Position, val cost: Double, val moves: List<Position>) : Comparable<Path> {
        operator fun plus(it: Direction) = Path(start, end + it, cost + it.cost, moves + (end + it))

        //override fun compareTo(other: Path): Int = cost.compareTo(other.cost)
        override fun compareTo(other: Path): Int = (cost + distance(end, target)).compareTo(other.cost + distance(other.end, target))
    }

    private fun isPositionValid(position: Position, cost: Double) = with(position) {
        x in 0 until exploration[0].size &&
                y in 0 until exploration.size &&
                (map[position] in listOf(Cell.EMPTY, Cell.END) && exploration[position] > cost)
    }

    operator fun <R> Matrix<R>.get(position: Position) = get(position.y)[position.x]

    operator fun <R> Matrix<R>.set(position: Position, value: R) {
        get(position.y)[position.x] = value
    }

    private fun distance(a: Position, b: Position) = sqrt(square(a.x - b.x) + square(a.y - b.y))

    private fun square(a: Int) = a * a

    private fun sqrt(n: Int) = sqrt(n.toDouble())
}

typealias Matrix<R> = Array<Array<R>>

