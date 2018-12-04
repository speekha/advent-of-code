package com.adventofcode.dec2018.day2

import java.io.File

class Checksum {
    fun check(input: List<String>): Int {
        val processed = input.map { processKey(it) }
        return processed.count { it.doubles } * processed.count { it.triples }
    }

    private fun processKey(key: String): KeyDescriptor {
        val map = mutableMapOf<Char, Int>()
        key.forEach {
            map[it] = (map[it] ?: 0) + 1
        }
        return KeyDescriptor(map.containsValue(2), map.containsValue(3))
    }

    fun areKeysMatching(a: String, b: String): Boolean {
        var errors = 0
        a.forEachIndexed { i, char ->
            if (char != b[i]) {
                if (errors == 0) {
                    errors++
                } else {
                    return false
                }
            }
        }
        return true
    }

    fun findMixedBoxesDenominator(input: List<String>): String {
        for (i in input.indices) {
            for (j in i + 1 until input.size) {
                if (areKeysMatching(input[i], input[j])) {
                    return matchKeys(input[i], input[j])
                }
            }
        }
        error("No match found")
    }

    private fun matchKeys(a: String, b: String): String {
        return a.filterIndexed { it, char -> char == b[it] }
    }

}


fun main() {
    val input = File("src/main/kotlin/fr/adventofcode/day02/input.txt").readLines()
    val checksum = Checksum()
    println("Checksum: ${checksum.check(input)}")
    println("Match: ${checksum.findMixedBoxesDenominator(input)}")
}

data class KeyDescriptor(
    val doubles: Boolean,
    val triples: Boolean
)