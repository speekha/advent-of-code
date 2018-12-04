package com.adventofcode.dec2018.day12

import java.io.File

class Plantgrower(input: List<String>) {

    var innerState: Map<Long, Char> = mapState(input[0].substring(15, input[0].lastIndexOf('#') + 1))

    private fun mapState(input: String): Map<Long, Char> {
        return input.mapIndexed { index, char -> index.toLong() to char }
                .filter { it.second == '#' }
                .associate { it }
    }

    val state: String
        get() = ((innerState.keys.minOrNull() ?: 0)..(innerState.keys.maxOrNull() ?: 0))
                .map {
                    innerState[it] ?: '.'
                }.joinToString("")

    val savedStates = mutableMapOf<String, SavedState>()

    data class SavedState(val iteration: Long,
                          val state: Map<Long, Char>)

    val rules: Map<String, Char> = input.drop(2).associate { parseRule(it) }

    private fun parseRule(input: String): Pair<String, Char> = input
            .substring(0..4) to input.last()

    fun iterate() {
        val next = mutableMapOf<Long, Char>()

        ((innerState.keys.minOrNull() ?: 0) - 3..(innerState.keys.maxOrNull() ?: 0) + 3).forEach {
            val pattern = ((it - 2)..(it + 2)).map { innerState[it] ?: '.' }.joinToString("")
            if (rules[pattern] == '#') {
                next[it] = '#'
            }
        }

        innerState = next
    }

    fun computeTotal(generations: Long = 20): Long {
        var i = 0L
        while (i < generations) {
            iterate()
            val current = state
            val saved = savedStates[current]
            i++
            if (saved != null) {
                val period = i - saved.iteration
                val target = (generations - i) % period + saved.iteration
                val matchingState = savedStates.values.filter { it.iteration == target }[0].state
                val periods = (generations - saved.iteration) / period
                innerState = matchingState.mapKeys { it.key + periods }
                i = generations
            } else {
                savedStates[state] = SavedState(i, innerState)
            }

        }
        return innerState.keys.sum()
    }
}

fun main() {
    val input = File("src/main/kotlin/com/adventofcode/dec2018/day12/input.txt").readLines()
    var grower = Plantgrower(input)
    println("Plant sum: ${grower.computeTotal()}")
    grower = Plantgrower(input)
    println("Plant sum: ${grower.computeTotal(50000000000)}")
}