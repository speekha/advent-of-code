package com.adventofcode.dec2022.day11

import com.adventofcode.lcm

class MonkeyManager(
    input: String,
    private val worryDivisor: Int = 3
) {

    val monkeys = parseMonkeys(input)

    private fun parseMonkeys(input: String): List<Monkey> = input
        .split("\n\n")
        .map { Monkey(it) }

    fun processInspections(inspections: Int) {
        val lcm = lcm(*monkeys.map { it.divisor }.toLongArray())
        repeat(inspections) {
            monkeys.forEach { monkey ->
                while (monkey.hasItem()) {
                    val (item, next) = monkey.processItem(worryDivisor, lcm)
                    monkeys[next].addItem(item)
                }
            }
        }
    }

    fun computeWorryLevel() = monkeys.sortedByDescending { it.inspections }
        .take(2)
        .fold(1L) { acc, monkey -> acc * monkey.inspections.toLong() }
}