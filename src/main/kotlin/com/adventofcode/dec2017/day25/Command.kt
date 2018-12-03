package com.adventofcode.dec2017.day25

data class Command(
        val write: Int,
        val move: Direction,
        val next: String
) {
    companion object {
        fun fromString(input: List<String>): Command {
            val write = getLastToken(input[0]).toInt()
            val dir = Direction.valueOf(getLastToken(input[1]).toUpperCase())
            val next = getLastToken(input[2])
            return Command(write, dir, next)
        }
    }
}