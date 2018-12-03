package com.adventofcode.dec2017.day8

enum class Operator(val symbol: String) {
    EQUALS("=="),
    NOT_EQUALS("!="),
    GREATER_THAN(">"),
    GREATER_OR_EQUAL(">="),
    LESS_THAN("<"),
    LESS_OR_EQUAL("<=");

    companion object {
        fun fromString(input: String): Operator {
            return values().first { it.symbol == input }
        }
    }
}