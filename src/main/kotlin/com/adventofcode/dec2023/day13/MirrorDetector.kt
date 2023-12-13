package com.adventofcode.dec2023.day13

import kotlin.math.min

class MirrorDetector(input: String) {

    val patterns: List<List<String>> = input.split("\n\n").map { it.split("\n") }

    fun detectReflectors(): Int = patterns.indices.sumOf { detectReflector(patterns[it], flip(patterns[it])) }

    private fun detectReflector(pattern: List<String>, flipped: List<String>): Int = detectHorizontalReflector(flipped) + 100 * detectHorizontalReflector(pattern)

    fun detectHorizontalReflector(input: List<String>, exclude: Int = 0): Int {
        var i = -1
        loop@
        while (i < input.size - 2) {
            i++
            if (i == exclude - 1) {
                continue@loop
            }
            val range = min(i, input.size - i - 2)
            for (j in 0..range) {
                if (!isReflection(input[i - j], input[i + j + 1])) {
                    continue@loop
                }
            }
            return i + 1
        }

        return 0
    }

    private fun isReflection(a: String, b: String) = a == b

    fun detectReflectorsWithSmudges(): Int = patterns.indices.sumOf { i ->
        detectReflectorsWithSmudges(patterns[i])
    }

    private fun detectReflectorsWithSmudges(p: List<String>): Int {
        var horizontal = 0
        val f = flip(p)
        val original = detectReflector(p, f)
        loop@
        for (i in 0 .. p.lastIndex) {
            for (j in 0 .. f.lastIndex) {
                val pattern = invert(p, i, j)
                horizontal = 100 * detectHorizontalReflector(pattern, original / 100)
                if (horizontal != 0) {
                    break@loop
                }
                val flipped = invert(f, j, i)
                horizontal = detectHorizontalReflector(flipped, original)
                if (horizontal != 0) {
                    break@loop
                }
            }
        }
        return horizontal
    }

    private fun invert(pattern: List<String>, i: Int, j: Int): List<String> {
        return if (i !in pattern.indices || j !in pattern[0].indices) {
            pattern
        } else {
            pattern.indices.map { k ->
                if (i == k) {
                    pattern[i].substring(0 until j) + invert(pattern[i][j]) + pattern[i].substring(j + 1..pattern[i].lastIndex)
                } else {
                    pattern[k]
                }
            }
        }
    }

    private fun invert(c: Char) = if (c == '.') '#' else '.'
}

fun flip(input: List<String>): List<String> {
    return input[0].indices.map { i -> input.map { it[i] }.joinToString("") }
}