package com.adventofcode.dec2016.day17

import java.security.MessageDigest

data class MazeState(
        val position: Position,
        val state: String,
        private val doors: List<Boolean> = md5(state).map { it in 'b'..'f' }) {

    fun isDoorOpen(direction: Direction): Boolean {
        return doors[direction.ordinal]
    }

    fun nextPositions(): List<MazeState> = Direction.values()
            .filter { isDoorOpen(it) && isPositionValid(position + it) }
            .map {
                MazeState(position + it, state + it.toString()[0])
            }

    private fun isPositionValid(next: Position): Boolean = next.row in 0..3 && next.col in 0..3

    companion object {

        private val digest: MessageDigest = MessageDigest.getInstance("MD5")

        private fun md5(state: String) = digest.digest(state.toByteArray()).take(2).joinToString("") { String.format("%02x", it) }

    }
}