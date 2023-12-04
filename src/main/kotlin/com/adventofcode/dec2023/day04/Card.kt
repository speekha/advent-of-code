package com.adventofcode.dec2023.day04

data class Card(
    val winning: List<Int>,
    val played: List<Int>,
    var instances: Int = 1
) {

    val matches = played.filter { it in winning }

    fun score(): Int {
        val matches = matches.count()
        return if (matches > 0) 1 shl (matches - 1) else 0
    }
}

fun Card(input: String): Card {
    val (winning, played) = input.dropWhile { it != ':' }
        .drop(1)
        .split("|")
        .map {
            it.trim().split("[ ]+".toRegex()).map { it.toInt() }
        }
    return Card(winning, played)
}
