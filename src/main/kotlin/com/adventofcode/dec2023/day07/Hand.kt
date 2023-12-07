package com.adventofcode.dec2023.day07

import kotlin.math.min

class Hand(
    label: String,
    private val useJokers: Boolean = false
) : Comparable<Hand> {

    private val cards: List<Card> = label.map { char ->
        if (useJokers) {
            Card.values().last { it.code == char }
        } else {
            Card.values().first { it.code == char }
        }
    }

    override operator fun compareTo(other: Hand): Int {
        val current = getConfiguration(cards)
        val opponent = getConfiguration(other.cards)

        return (0 until min(current.size, opponent.size))
            .firstOrNull { i -> current[i] != opponent[i] }
            ?.let { i -> current[i] - opponent[i] }
            ?: (0..4)
                .firstOrNull { i -> cards[i] != other.cards[i] }
                ?.let { i -> other.cards[i].compareTo(cards[i]) }
            ?: 0
    }

    private fun getConfiguration(cardList: List<Card>): IntArray {
        val groupings = cardList.filter { it != Card.Joker }.groupBy { it }.map { it.value.size }.sortedDescending().toIntArray()
        if (groupings.isEmpty()) {
            return intArrayOf(5)
        }
        val jokers = cardList.count { it == Card.Joker }
        groupings[0] += jokers
        return groupings
    }
}
