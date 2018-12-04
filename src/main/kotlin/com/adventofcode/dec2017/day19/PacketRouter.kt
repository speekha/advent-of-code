package com.adventofcode.dec2017.day19

class PacketRouter(input: List<String>) {

    private val network = initNetwork(input)

    tailrec fun traverse(state: NetworkState = NetworkState(findStartingPoint(), Direction.DOWN)): Pair<String, Int> {
        val next = computeNextState(state)
        return if (next == null) {
            state.path to state.steps + 1
        } else {
            traverse(next)
        }
    }

    private fun initNetwork(input: List<String>): List<String> {
        val len = input.maxByOrNull { it.length }?.length ?: 0
        return input.map { it.padEnd(len, ' ') }
    }

    internal fun findStartingPoint(): Position = Position(network[0].indexOf('|'), 0)

    private fun computeNextState(state: NetworkState): NetworkState? {
        val next = state.nextPosition()
        val cell = network[next.y][next.x]
        return nextState(cell, state)
    }

    private fun nextState(cell: Char, state: NetworkState): NetworkState? {
        return when (cell) {
            in 'A'..'Z' -> NetworkState(state, cell.toString())
            ' ' -> null
            '+' -> NetworkState(state, turn(state.nextPosition(), state.direction))
            else -> NetworkState(state)
        }
    }

    internal fun turn(position: Position, direction: Direction): Direction = when (direction) {
        Direction.UP, Direction.DOWN -> getHorizontalTurn(position)
        Direction.LEFT, Direction.RIGHT -> getVerticalTurn(position)
    }

    private fun getVerticalTurn(position: Position): Direction =
            if (position.y > 0 && network[position.y - 1][position.x] != ' ') Direction.UP else Direction.DOWN

    private fun getHorizontalTurn(position: Position) =
            if (position.x > 0 && network[position.y][position.x - 1] != ' ') Direction.LEFT else Direction.RIGHT

}