package com.adventofcode.dec2015.day05

class NiceNaughtyChecker {

    fun isNice2014(name: String): Boolean {
        val forbiddenValues = setOf("ab", "cd", "pq", "xy")
        val vowels = name.count { it in "aeiou" } >= 3
        val doubles = name.windowed(2).any { it[0] == it[1] }
        val forbidden = name.windowed(2).none { it in forbiddenValues }
        return vowels && doubles && forbidden
    }

    fun isNice2015(name: String): Boolean {
        val doubles = name
            .windowed(2)
            .withIndex()
            .groupBy { it.value }
            .any { (_, value) ->
                val base = value.first().index
                value.size > 1 && value.drop(1).any { it.index != base + 1 }
            }

        val repeats = name.windowed(3).any { it[0] == it[2] }
        return doubles && repeats
    }
}