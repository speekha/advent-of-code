package com.adventofcode.dec2023.day09

class Oasis {
    fun extrapolateLast(sequence: String): Int = extrapolate(sequence) { stage, acc -> stage.last() + acc }

    fun extrapolateFirst(sequence: String): Int = extrapolate(sequence) { stage, acc -> stage.first() - acc }

    private fun extrapolate(sequence: String, transform: (List<Int>, Int) -> Int): Int = computeHistory(sequence).reversed().fold(0) { acc, value -> transform(value, acc) }

    private fun computeHistory(s: String): MutableList<MutableList<Int>> {
        val values = s.split(" ").map { it.toInt() }.toMutableList()
        val stages = mutableListOf(values)
        while (stages.last().any { it != 0 }) {
            stages += stages.last().windowed(2).map { it[1] - it[0] }.toMutableList()
        }
        return stages
    }

}
