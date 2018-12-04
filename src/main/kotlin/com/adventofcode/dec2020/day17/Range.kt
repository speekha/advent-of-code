package com.adventofcode.dec2020.day17

class Range(a: Int, b: Int) : Iterable<Int> {
    var min: Int = a
        set(value) {
            field = kotlin.math.min(field, value)
        }

    var max: Int = b
        set(value) {
            field = kotlin.math.max(field, value)
        }

    override fun iterator(): Iterator<Int> = (min..max).iterator()

    fun extended() = Range(min - 1, max + 1)
}
