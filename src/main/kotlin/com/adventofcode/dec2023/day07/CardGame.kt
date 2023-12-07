package com.adventofcode.dec2023.day07

class CardGame {
    fun computeWinnings(input: List<String>, useJoker: Boolean = false): Int {
        val players = input.map {
            val (cards, bid) = it.split(" ")
            Player(Hand(cards, useJoker), bid.toInt())
        }
        return players.sortedBy { it.hand }.withIndex().sumOf { (index, value) -> (index + 1) * value.bid }
    }

}
