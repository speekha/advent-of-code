package com.adventofcode.dec2021.day03

class ReportAnalyzer {
    fun computeGammaRate(input: List<String>): Int {
        val counts = countBits(input)
        val result = counts.map { if (it > input.size / 2) 1 else 0 }
        return result.reduce { acc, i -> 2 * acc + i }
    }

    fun computeEpsilonRate(input: List<String>): Int {
        val counts = countBits(input)
        val result = counts.map { if (it < input.size / 2) 1 else 0 }
        return result.reduce { acc, i -> 2 * acc + i }
    }

    private fun countBits(input: List<String>): IntArray {
        val counts = IntArray(input[0].length) { 0 }
        input.forEach { value ->
            value.forEachIndexed { index, char ->
                if (char == '1') {
                    counts[index]++
                }
            }
        }
        return counts
    }

    fun computePowerConsumption(input: List<String>): Int {
        val counts = countBits(input)
        val gammaRate = counts.map { if (it > input.size / 2) 1 else 0 }.reduce { acc, i -> 2 * acc + i }
        val epsilonRate = counts.map { if (it < input.size / 2) 1 else 0 }.reduce { acc, i -> 2 * acc + i }
        return gammaRate * epsilonRate
    }

    tailrec fun computeOxygenGeneratorRating(input: List<String>, index: Int = 0): Int = if (input.size == 1) {
        input[0].fold(0) { acc, i -> 2 * acc + (i - '0') }
    } else {
        val (bit1, bit0) = input.partition { it[index] == '1' }
        val filtered = if (bit1.size >= bit0.size) bit1 else bit0
        computeOxygenGeneratorRating(filtered, index + 1)
    }

    tailrec fun computeCO2ScrubberRating(input: List<String>, index: Int = 0): Int = if (input.size == 1) {
        input[0].fold(0) { acc, i -> 2 * acc + (i - '0') }
    } else {
        val (bit1, bit0) = input.partition { it[index] == '1' }
        val filtered = if (bit1.size >= bit0.size) bit0 else bit1
        computeCO2ScrubberRating(filtered, index + 1)
    }

    fun computeLifeSupportRating(input: List<String>): Int = computeOxygenGeneratorRating(input) * computeCO2ScrubberRating(input)
}
