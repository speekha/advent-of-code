package com.adventofcode.dec2023.day04

class ScratchCardChecker {
    fun check(input: String): Int {
        val card = Card(input)
        return card.score()
    }

    fun computeScore(input: List<String>): Int = input.sumOf { check(it) }
    fun countFinalCards(input: List<String>): Int {
        val cards = input.map { Card(it) }
        cards.forEachIndexed { index, card ->
            val matches = card.matches.count()
            (1..matches).forEach { i ->
                cards[index + i].instances += card.instances
            }
        }
        return cards.sumOf { it.instances }
    }
}
