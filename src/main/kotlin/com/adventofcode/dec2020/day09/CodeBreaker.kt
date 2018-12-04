package com.adventofcode.dec2020.day09

class CodeBreaker {
    fun findError(data: List<Long>, preambleLength: Int): Long = pinPointError(data, preambleLength).value

    fun findWeakness(data: List<Long>, preambleLength: Int): Long {
        val error = pinPointError(data, preambleLength)
        var min = 0
        var max = 0
        var total = 0L
        do {
            if (total < error.value) {
                max++
            } else if (total > error.value) {
                min++
            }
            total = data.subList(min, max).sum()
        } while (total != error.value)
        val sublist = data.subList(min, max)
        return (sublist.minOrNull() ?: 0) + (sublist.maxOrNull() ?: 0)
    }

    private fun pinPointError(data: List<Long>, preambleLength: Int): IndexedValue<Long> = data.withIndex()
        .drop(preambleLength)
        .first { (index, value) ->
            invalidNumber(value, data.subList(index - preambleLength, index))
        }

    private fun invalidNumber(number: Long, subList: List<Long>): Boolean = subList.dropLast(1)
        .withIndex()
        .none { (index, value) -> (number - value) in subList.subList(index + 1, subList.lastIndex + 1) }
}