package com.adventofcode.dec2022.day04

class Assignment(
    private val range1: IntRange,
    private val range2: IntRange
) {

    fun isCompleteOverlap(): Boolean = (range1.first >= range2.first && range1.last <= range2.last) ||
            (range1.first <= range2.first && range1.last >= range2.last)

    fun isPartialOverlap(): Boolean = range1.intersect(range2).isNotEmpty()
}

fun Assignment(input: String): Assignment {
    val (range1, range2) = input.split(",")
    return Assignment(range1.toRange(), range2.toRange())
}

private fun String.toRange(): IntRange {
    val (start, end) = split("-").map { it.toInt() }
    return start..end
}