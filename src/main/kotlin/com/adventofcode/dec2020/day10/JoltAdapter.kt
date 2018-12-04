package com.adventofcode.dec2020.day10

class JoltAdapter(input: List<String>) {

    private val adapters = input.map { it.toInt() }.sorted()

    val maxJolt: Int = (adapters.maxOrNull() ?: error("Missing configuration")) + 3

    val outletValues: List<Int> = listOf(0) + adapters + maxJolt

    fun computeCombinedDifferences(): Int {
        val diffs = outletValues.windowed(2).map { (left, right) -> right - left }.groupBy { it }
        val diff1 = (diffs[1] ?: error("No 1 difference")).size
        val diff3 = (diffs[3] ?: error("No 3 difference")).size
        return diff1 * diff3
    }

    fun countPossibleArrangements(): Long {
        val combinations = LongArray(outletValues.size)
        combinations[0] = 1
        (1..combinations.lastIndex).forEach { i ->
            ((i - 3).coerceAtLeast(0) until i).forEach { offset ->
                if (outletValues[offset] >= outletValues[i] - 3) {
                    combinations[i] += combinations[offset]
                }
            }
        }
        return combinations.last()
    }
}
