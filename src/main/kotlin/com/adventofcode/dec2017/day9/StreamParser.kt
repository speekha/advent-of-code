package com.adventofcode.dec2017.day9

import java.io.File

class StreamParser {

    fun removeIgnored(input: String) = input.replace(Regex("!."), "")

    fun removeGarbage(input: String) = removeIgnored(input).replace(Regex("<[^>]*>"), "<>")

    fun score(input: String): Int {
        var score = 0
        var level = 0
        val sanitized = removeGarbage(input)
        sanitized.indices.forEach { i ->
            when (sanitized[i]) {
                '{' -> level++
                '}' -> {
                    score += level
                    level--
                }
            }
            if (level < 0) {
                level = 0
            }
        }
        return score
    }

    fun garbageCount(input: String): Int {
        return removeIgnored(input).length - removeGarbage(input).length
    }
}

fun main(args: Array<String>) {
    val input = File("src/main/kotlin/com/adventofcode/dec2017/day9/input.txt").readLines()[0]
    with(StreamParser()) {
        println("Score: ${score(input)}")
        println("Garbage characters: ${garbageCount(input)}")
    }
}