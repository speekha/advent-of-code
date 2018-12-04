package com.adventofcode.dec2016.day6

import java.io.File

class MessageErrorCorrector {
    fun restoreMessage(input: List<String>): String = restoreMessageWithCriteria(input) {
        groupBy { it }.maxByOrNull { it.value.size }?.key
    }

    fun restoreMessageWithObfuscation(input: List<String>): String = restoreMessageWithCriteria(input) {
        groupBy { it }.minByOrNull { it.value.size }?.key
    }

    private fun restoreMessageWithCriteria(input: List<String>, selector: List<Char>.() -> Char?) =
            (0 until input[0].length)
                    .map { pos ->
                        input.map { it[pos] }
                                .selector()
                    }.filter { it != null }
                    .joinToString("")
}

fun main() {
    val input = File("src/main/kotlin/com/adventofcode/dec2016/day6/input.txt").readLines()
    println("Testing : ${input.size}")
    with(MessageErrorCorrector()) {
        println("Restored message: ${restoreMessage(input)}")
        println("Restored message: ${restoreMessageWithObfuscation(input)}")
    }
}