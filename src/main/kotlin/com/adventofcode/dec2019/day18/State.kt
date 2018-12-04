package com.adventofcode.dec2019.day18

data class State(
        val positions: List<Cell>,
        val collectedKeys: Set<Char>,
        val distance: Int
) : Comparable<State> {

    override fun compareTo(other: State): Int = distance.compareTo(other.distance)

    operator fun plus(target: Target): State {
        val newPositions = positions.replacePosition(target.index, Cell.Key(target.key))
        return State(newPositions, collectedKeys + target.key, distance + target.distance)
    }

    private fun List<Cell>.replacePosition(index: Int, key: Cell.Key) = toMutableList().apply {
        this[index] = key
    }
}
