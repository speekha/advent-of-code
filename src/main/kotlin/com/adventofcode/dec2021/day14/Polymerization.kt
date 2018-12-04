package com.adventofcode.dec2021.day14

class Polymerization(input: List<String>) {

    var polymer = input[0].windowed(2).groupBy { it }.mapValues { it.value.size.toLong() }

    val start = input[0].first()

    val end = input[0].last()

    val rules = input.drop(2).associate {
        it.split(" -> ")
            .let { (pair, insert) -> pair to insert }
    }

    fun polymerize() {
        val nextPolymer = mutableMapOf<String, Long>()
        polymer.forEach { pair, count ->
            val insert = rules[pair]!!
            val pairA = pair[0].toString() + insert
            val pairB = insert + pair[1]
            nextPolymer[pairA] = nextPolymer.getOrDefault(pairA, 0) + count
            nextPolymer[pairB] = nextPolymer.getOrDefault(pairB, 0) + count
        }
        polymer = nextPolymer
    }

    fun computeScore(): Long {
        val groups: MutableMap<Char, Long> = mutableMapOf()
        polymer.forEach { (pair, count) ->
            groups[pair[0]] = groups.getOrDefault(pair[0], 0) + count
            groups[pair[1]] = groups.getOrDefault(pair[1], 0) + count
        }
        groups[start] = groups.getOrDefault(start, 0) + 1
        groups[end] = groups.getOrDefault(end, 0) + 1
        return ((groups.values.maxOrNull() ?: 0L) - (groups.values.minOrNull() ?: 0L)) / 2
    }
}
