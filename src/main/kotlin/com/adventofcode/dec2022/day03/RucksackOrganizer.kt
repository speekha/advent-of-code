package com.adventofcode.dec2022.day03

class RucksackOrganizer {
    fun addPriorities(input: List<String>): Int = input.sumOf {
        computePriority(Rucksack(it).findMistake())
    }

    fun computeBadgePriorities(input: List<String>): Int =
        input.chunked(3)
            .sumOf { computePriority(computeBadge(it)) }

    fun computeBadge(input: List<String>): Char =
        input.map { it.toSet() }
            .reduce { acc, item -> acc.intersect(item) }
            .first()

    private fun computePriority(item: Char) = when (item) {
        in 'a'..'z' -> item - 'a' + 1
        in 'A'..'Z' -> item - 'A' + 27
        else -> 0
    }

}