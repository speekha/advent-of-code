package com.adventofcode.dec2017.day25

data class Rule(
        val id: String,
        val ifZero: Command,
        val ifOne: Command
) {
    companion object {
        fun fromString(input: List<String>): Rule {
            val id = getLastToken(input[0])
            val ifZero = Command.fromString(input.subList(2, 5))
            val ifOne = Command.fromString(input.subList(6, 9))
            return Rule(id, ifZero, ifOne)
        }
    }
}