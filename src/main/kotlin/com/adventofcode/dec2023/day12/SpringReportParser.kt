package com.adventofcode.dec2023.day12

class SpringReportParser {
    fun countArrangements(input: String): Long {
        val (symbols, numbers) = input.split(" ")
        val damaged = numbers.split(",").map { it.toInt() }
        return countCombinations(symbols, damaged)
    }

    fun countUnfoldedArrangements(input: String): Long {
        val (symbols, numbers) = input.split(" ")
        val damaged = numbers.split(",").map { it.toInt() }
        return countCombinations((1..5).map { symbols }.joinToString("?"), (1..5).flatMap { damaged })
    }

    private fun countCombinations(symbols: String, damaged: List<Int>): Long {
        var cache = mapOf(Configuration(false, listOf()) to 1L)
        var i = 0
        while (i < symbols.length) {
            cache = mutableMapOf<Configuration, Long>().apply {
                when (val c = symbols[i]) {
                    '.' -> addSafeSpring(cache, damaged)
                    '#' -> addDamagedSpring(cache, damaged)
                    '?' -> {
                        addSafeSpring(cache, damaged)
                        addDamagedSpring(cache, damaged)
                    }

                    else -> error("unexpected character: $c")
                }
            }
            i++
        }
        return cache.entries.sumOf { (key, value) -> if (key.groups == damaged) value else 0 }
    }

    private fun MutableMap<Configuration, Long>.addSafeSpring(previous: Map<Configuration, Long>, target: List<Int>) {
        previous.entries.forEach { (key, value) ->
            val configuration = key.copy(openLastGroup = false)
            if (target.startsWith(configuration.groups)) {
                this[configuration] = (this[configuration] ?: 0) + value
            }
        }
    }

    private fun MutableMap<Configuration, Long>.addDamagedSpring(previous: Map<Configuration, Long>, target: List<Int>) {
        previous.entries.forEach { (key, value) ->
            val configuration = Configuration(
                true, if (key.openLastGroup) {
                    key.groups.dropLast(1) + (key.groups.last() + 1)
                } else {
                    key.groups + 1
                }
            )
            if (target.startsWith(configuration.groups)) {
                this[configuration] = (this[configuration] ?: 0) + value
            }
        }
    }


    private fun List<Int>.startsWith(prefix: List<Int>) = prefix.isEmpty() ||
            (size >= prefix.size && (prefix.dropLast(1) == take(prefix.size - 1) && prefix.last() <= this[prefix.size - 1]))

    data class Configuration(
        val openLastGroup: Boolean,
        val groups: List<Int>
    )
}
