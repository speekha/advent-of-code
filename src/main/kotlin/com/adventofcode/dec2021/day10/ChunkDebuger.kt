package com.adventofcode.dec2021.day10

import java.util.*

class ChunkDebuger {

    val pairs = listOf("(" to ")", "[" to "]", "{" to "}", "<" to ">")

    fun completeLine(chunk: String): String {
        val stack = LinkedList<Char>()
        chunk.forEach { c ->
            when (c) {
                '(', '[', '{', '<' -> stack.push(matchDelimiter(c))
                ')', ']', '}', '>' -> if (stack.pop() != c) {
                    throw MismatchException(c)
                }
            }
        }
        return stack.joinToString("")
    }

    private fun matchDelimiter(start: Char) = when (start) {
        '(' -> ')'
        '[' -> ']'
        '{' -> '}'
        '<' -> '>'
        else -> '0'
    }

    fun scoreCorruptedLines(input: List<String>): Int = input.sumOf {
        try {
            completeLine(it)
            0
        } catch (e: MismatchException) {
            scoreInvalidCharacter(e.invalidCharacter)
        }
    }

    private fun scoreInvalidCharacter(invalidCharacter: Char): Int = when (invalidCharacter) {
        ')' -> 3
        ']' -> 57
        '}' -> 1197
        '>' -> 25137
        else -> 0
    }

    fun scoreIncompleteLine(input: String): Long = completeLine(input).fold(0L) { acc, c ->
        try {
            5 * acc + scoreCharacter(c)
        } catch (e: MismatchException) {
            acc
        }
    }

    private fun scoreCharacter(c: Char): Int = when (c) {
        ')' -> 1
        ']' -> 2
        '}' -> 3
        '>' -> 4
        else -> 0
    }

    fun findMiddleScore(input: List<String>): Long {
        val scores = input.mapNotNull {
            try {
                scoreIncompleteLine(it).takeIf { score -> score != null }
            } catch (e: MismatchException) {
                null
            }
        }
            .sorted()
        return scores[scores.size / 2]
    }
}

class MismatchException(val invalidCharacter: Char) : Exception()
