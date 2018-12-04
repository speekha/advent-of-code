package com.adventofcode.dec2015.day01

class FloorComputer {
    fun compute(input: String): Int {
        return input.fold(0) { total, char ->
            when (char) {
                '(' -> total + 1
                ')' -> total - 1
                else -> error("Parsing error")
            }
        }
    }

    fun accessBasement(input: String): Int = input.length - discardPositiveChanges(input, 0)

    private tailrec fun discardPositiveChanges(input: String, start: Int): Int = if (start == -1) {
        input.length
    } else {
        val diff = when (input[0]) {
            '(' -> 1
            ')' -> -1
            else -> error("Parsing error")
        }
        discardPositiveChanges(input.drop(1), start + diff)
    }
}