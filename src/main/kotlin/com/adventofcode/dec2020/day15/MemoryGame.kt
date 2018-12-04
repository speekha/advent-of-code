package com.adventofcode.dec2020.day15

import java.util.*

class MemoryGame(input: String) {

    val init = LinkedList<Int>().also { it.addAll(input.split(",").map { it.toInt() }) }

    val spoken = mutableMapOf<Int, Int>()

    var last = 0

    var turn = -1

    fun next(): Int {
        val result = if (init.isNotEmpty()) {
            init.pollFirst()
        } else {
            val lastTurn = spoken[last] ?: turn
            turn - lastTurn
        }
        spoken[last] = turn
        turn++
        last = result
        return last
    }
}