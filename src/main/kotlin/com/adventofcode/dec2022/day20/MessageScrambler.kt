package com.adventofcode.dec2022.day20

import kotlin.math.max
import kotlin.math.min

class MessageScrambler(
    val input: List<Long>
) {

    private val indexes = IntArray(input.size) { it }

    fun scramble(): List<Long> {
        input.withIndex().forEach { (index, value) ->
            val fromIndex = indexes[index]
            val toIndex = moveNumber(fromIndex, value)
            if (fromIndex != toIndex) {
                val range = min(fromIndex + 1, toIndex)..max(toIndex, fromIndex - 1)
                val inc = if (fromIndex < toIndex) -1 else 1
                indexes.indices.filter { indexes[it] in range }.forEach {
                    indexes[it] += inc
                }
                indexes[index] = toIndex
            }
        }
        return rebuildList()
    }

    private fun moveNumber(
        currentIndex: Int,
        value: Long
    ): Int {
        var newIndex = (currentIndex + value) % (input.size - 1)
        if (newIndex <= 0) {
            newIndex += input.size - 1
        }
        return newIndex.toInt()
    }

    private fun rebuildList(): List<Long> {
        val result = LongArray(input.size) { 0 }
        indexes.forEach {
            result[indexes[it]] = input[it]
        }
        return result.toList()
    }

    fun findCoordinates(): List<Long> {
        val scrambled = rebuildList()
        val indexZero = scrambled.indexOf(0)
        return listOf(1000, 2000, 3000).map {
            scrambled[(indexZero + it) % input.size]
        }
    }
}
