package com.adventofcode.dec2017.day10

class KnotHasher(period: Int, val rounds: Int = 1) {

    var index = 0

    val string = IntArray(period) { it }

    var skip = 0

    fun simpleHash(groups: IntArray): Int {
        sparseHash(groups)
        return string[0] * string[1]
    }

    fun digestToString(input: String): String {
        val xor = digest(input)
        return xor.joinToString("") { String.format("%02x", it) }
    }

    fun digest(input: String): List<Int> {
        sparseHash((input.toByteArray().map { it.toInt() } + listOf(17, 31, 73, 47, 23)).toIntArray())
        return denseHash()
    }

    fun sparseHash(groups: IntArray) {
        for (i in 1..rounds) {
            processList(groups)
        }
    }

    fun denseHash() = (0..15).map {
        string.toList()
                .subList(it * 16, (it + 1) * 16)
                .reduce { a, b -> a xor b }
    }

    fun processList(groups: IntArray) {
        groups.forEach {
            for (i in 0..((it - 1) / 2)) {
                swap(index + i, index + it - i - 1)
            }
            index += it + skip % string.size
            skip++
        }
    }

    private fun swap(a: Int, b: Int) {
        val left = string[a % string.size]
        val right = string[b % string.size]
        string[a % string.size] = right
        string[b % string.size] = left
    }

}