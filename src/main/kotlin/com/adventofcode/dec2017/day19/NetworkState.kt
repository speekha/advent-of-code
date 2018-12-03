package com.adventofcode.dec2017.day19

data class NetworkState(
        val position: Position,
        val direction: Direction,
        val steps: Int = 0,
        val path: String = ""
) {
    constructor(state: NetworkState, newStep: String = ""): this(state.nextPosition(), state.direction, state.steps + 1, state.path + newStep)

    constructor(state: NetworkState, direction: Direction): this(state.nextPosition(), direction, state.steps + 1, state.path)

    fun nextPosition(): Position = position + direction
}