package com.adventofcode.positioning

import kotlin.math.abs

data class GridPosition(val row: Int, val col: Int) {

    operator fun plus(direction: Direction) = GridPosition(row + direction.y, col + direction.x)

    operator fun plus(direction: Neighbor) = GridPosition(row + direction.y, col + direction.x)

    operator fun plus(move: Move) =
        GridPosition(row + move.distance * move.direction.y, col + move.distance * move.direction.x)

    operator fun plus(direction: CardinalDirection) = GridPosition(row + direction.y, col + direction.x)

    fun distance() = abs(col) + abs(row)
}

operator fun <T> Array<Array<T>>.get(position: GridPosition) = this[position.row][position.col]

operator fun <T> Array<Array<T>>.set(position: GridPosition, value: T) {
    this[position.row][position.col] = value
}

operator fun <T> Array<Array<T>>.contains(position: GridPosition) =
    position.row in this.indices && position.col in this[position.row].indices

fun <V> Array<Array<V>>.neighborPositions(pos: GridPosition): List<GridPosition> = Neighbor.values()
    .map { pos + it }
    .filter { it in this }

fun <V> Array<Array<V>>.neighbors(pos: GridPosition): List<V> = neighborPositions(pos).map { this[it] }

fun <S, T> Array<Array<T>>.foldNeighbors(pos: GridPosition, init: S, block: (S, T) -> S): S =
    neighbors(pos).fold(init, block)

fun <S, T : S> Array<Array<T>>.reduceNeighbors(pos: GridPosition, block: (S, T) -> S): S =
    neighbors(pos).reduce(block)

fun <V> Array<Array<V>>.forEachNeighbor(pos: GridPosition, block: (V) -> Unit) =
    neighbors(pos).forEach(block)

fun <V> Array<Array<V>>.forEachNeighborIndexed(pos: GridPosition, block: (GridPosition, V) -> Unit) =
    neighborPositions(pos).forEach { block(it, this[it]) }
