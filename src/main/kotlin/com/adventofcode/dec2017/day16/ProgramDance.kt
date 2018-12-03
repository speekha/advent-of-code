package com.adventofcode.dec2017.day16

import com.adventofcode.time
import java.io.File

class ProgramDance(count: Int) {

    private var dancers = CharArray(count) { 'a' + it }

    private val cache = mutableMapOf<String, Int>()

    fun positions(): String = dancers.joinToString("")

    fun parse(input: String): DanceMove {
        return when {
            input.startsWith("s") -> Spin(input.substring(1).toInt())
            input.startsWith("x") -> Exchange(input.substring(1).split("/"))
            input.startsWith("p") -> Swap(input[1], input[3])
            else -> Idle
        }
    }

    fun move(move: DanceMove) {
        dancers = move.moveDancers(dancers)
    }

    fun doDance(input: List<String>, rounds: Int = 1) {
        val moves = input.map { parse(it) }
        cache.clear()
        cache[positions()] = 0
        doMoves(moves, rounds)
    }

    private fun doMoves(moves: List<DanceMove>, rounds: Int) = (1..rounds).forEach { i ->
        moves.forEach { move(it) }
        cache[positions()]?.let { previous ->
            restoreStateFromCache(previous + convertToPositionInCycle(rounds - i, i - previous))
            return
        }
        cache[positions()] = i
    }

    private fun convertToPositionInCycle(target: Int, period: Int) = target % period

    private fun restoreStateFromCache(index: Int) {
        dancers = cache.asSequence()
                .first { it.value == index }
                .key.toCharArray()
    }
}