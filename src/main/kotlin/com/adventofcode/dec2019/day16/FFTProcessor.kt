package com.adventofcode.dec2019.day16

import kotlin.math.abs

class FFTProcessor(
        private val repeat: Int,
        private val length: Int
) {

    fun process(input: String): String = input.indices.map {
        applyPattern(input, it)
    }.joinToString("")

    private fun applyPattern(input: String, indexA: Int): Char = input.asSequence().drop(indexA).mapIndexed { indexC, it ->
        when (((indexC + indexA + 1) / (indexA + 1)) % 4) {
            1 -> it - '0'
            3 -> '0' - it
            else -> 0
        }
    }.sum().toString().last()

    fun calculate(input: String, phases: Int): String = (1..phases).fold(input) { acc, phase ->
        process(acc)
    }.take(length)


    fun calculateWithOffset(input: String, phases: Int): String {
        val offset = input.take(7).toInt()
        val values = IntArray(input.length * repeat - offset) {
            input[(it + offset) % input.length] - '0'
        }

        repeat(phases) {
            processEndArray(values)
        }

        return values.take(length).joinToString("")
    }

    private fun processEndArray(values: IntArray) {
        for (i in values.lastIndex downTo 1) {
            values[i - 1] += values[i]
        }
        for (i in values.indices) {
            values[i] = abs(values[i]) % 10
        }
    }

}
