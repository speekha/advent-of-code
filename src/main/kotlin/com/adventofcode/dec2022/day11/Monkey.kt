package com.adventofcode.dec2022.day11

import java.util.LinkedList

class Monkey(
    val items: LinkedList<Long>,
    private val operation: (Long) -> Long,
    val divisor: Long,
    private val nextIfTrue: Int,
    private val nextIfFalse: Int
) {

    var inspections = 0

    fun processItem(worryDivisor: Int, lcm: Long): Pair<Long, Int> {
        inspections++
        var item = items.pop()
        item = operation(item)
        item /= worryDivisor
        return (item % lcm) to if (item % divisor == 0L) nextIfTrue else nextIfFalse
    }

    fun hasItem(): Boolean {
        return items.isNotEmpty()
    }

    fun addItem(item: Long) {
        items.add(item)
    }

    constructor(input: String) : this(
        LinkedList<Long>().apply { addAll(input.split("\n")[1].drop(18).split(", ").map { it.toLong() }) },
        input.split("\n")[2].drop(19).toOperation(),
        input.split("\n")[3].drop(21).toLong(),
        input.split("\n")[4].drop(29).toInt(),
        input.split("\n")[5].drop(30).toInt()
    )

    companion object {
        fun String.toOperation(): (Long) -> Long {
            val (_, operator, operand2) = this.split(" ")
            return when {
                this == "old * old" -> { x -> x * x }
                operator == "*" -> { x -> x * operand2.toInt() }
                operator == "+" -> { x -> x + operand2.toInt() }
                else -> throw UnsupportedOperationException(this)
            }
        }
    }
}
