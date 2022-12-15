package com.adventofcode.dec2022.day15

import kotlin.math.max
import kotlin.math.min

typealias Range = List<IntRange>

fun emptyRange() = emptyList<IntRange>()

fun Range.addRange(range: IntRange): Range = if (any { it.canBeMerged(range) }) {
    val (merging, others) = partition { it.canBeMerged(range) }
    others + merging.fold(range) { a, b -> merge(a, b) }
} else {
    this + range
}

private operator fun Range.plus(range: IntRange): Range = mutableListOf<IntRange>().also {
    it.addAll(this)
    it.add(range)
}

private fun merge(a: IntRange, b: IntRange): IntRange = min(a.first, b.first)..max(a.last, b.last)

private fun IntRange.canBeMerged(range: IntRange) = range.first in first..last + 1
        || range.last in first - 1..last
        || first in range.first..range.last
        || last in range.first..range.last
