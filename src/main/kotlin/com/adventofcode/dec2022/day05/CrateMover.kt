package com.adventofcode.dec2022.day05

import java.util.*

abstract class CrateMover(
    val piles: Array<LinkedList<Char>>
) {
    override fun toString(): String =
        piles.joinToString(separator = ", ", prefix = "[", postfix = "]") {
            it.toString()
        }

    fun moveCrates(moves: List<String>) {
        moves.forEach { input ->
            val tokens = input.split(" ")
            val number = tokens[1].toInt()
            val from = tokens[3].toInt() - 1
            val to = tokens[5].toInt() - 1
            moveCrates(number, from, to)
        }
    }

    abstract fun moveCrates(number: Int, from: Int, to: Int)

    fun getMessage(): String = piles.joinToString(separator = "") { it.first.toString() }
}

fun parseCrates(input: List<String>) = Array(
    input.last()
        .split(" ")
        .count { it.isNotEmpty() }
) { LinkedList<Char>() }.also { piles ->
    input.reversed().drop(1).forEach { row ->
        for (i in piles.indices) {
            val crate = if (row.length > i * 4 + 1) row[i * 4 + 1] else ' '
            if (crate != ' ') {
                piles[i].push(crate)
            }
        }
    }
}