package com.adventofcode.dec2020.day22

import java.util.*

class CardGame(input: List<String>) {

    val playerDecks: Deck = parseInput(input)

    private fun parseInput(input: List<String>) = mutableListOf<LinkedList<Int>>().also { decks ->
        var deck: LinkedList<Int> = LinkedList()
        input.filter { !it.startsWith("Player") }.forEach {
            if (it.isEmpty()) {
                decks.add(deck)
                deck = LinkedList()
            } else {
                deck.add(it.toInt())
            }
        }
        decks.add(deck)
    }

    var winner: Int = -1

    fun computeWinnerScore(): Int =
        playerDecks[winner].reversed().foldIndexed(0) { index, acc, value ->
            acc + (index + 1) * value
        }

    fun playRegularGame() {
        while (playerDecks.none { it.isEmpty() }) {
            val cards = playerDecks.pickCards()
            val roundWinner = cards.indices.maxByOrNull { cards[it] } ?: -1
            playerDecks[roundWinner] += cards.sortedDescending()
        }
        winner = playerDecks.indexOfFirst { it.isNotEmpty() }
    }

    fun playRecursiveGame() {
        winner = RecursiveGame(playerDecks).play()
    }

    private class RecursiveGame(
        private val decks: Deck,
        private val history: MutableSet<String> = mutableSetOf()
    ) {

        fun play(): Int = try {
            while (running()) {
                checkInfiniteRecursion {
                    val (roundWinner, cards) = playRound()
                    decks[roundWinner] += cards
                }
            }
            decks.indexOfFirst { it.isNotEmpty() }
        } catch (e: InfinitRecursionException) {
            0
        }

        private fun running(): Boolean = decks.none { it.isEmpty() }

        private fun checkInfiniteRecursion(executeRound: () -> Unit) {
            val hash = decks.computeHash()
            if (hash in history) {
                throw InfinitRecursionException()
            } else {
                history += hash
                executeRound()
            }
        }

        private fun Deck.computeHash(): String = joinToString("|") { it.joinToString() }

        private fun playRound(): Pair<Int, List<Int>> {
            val cards = decks.pickCards()
            val roundWinner = if (cards.withIndex().any { (index, value) -> decks[index].size < value }) {
                cards.withIndex().maxByOrNull { it.value }?.index ?: -1
            } else {
                subGame(cards).play()
            }
            return roundWinner to listOf(cards[roundWinner], cards[1 - roundWinner])
        }

        private fun subGame(cards: List<Int>) = RecursiveGame(
            decks.mapIndexed { index, deck -> LinkedList(deck.take(cards[index])) },
            mutableSetOf<String>().apply { addAll(history) }
        )
    }
}

typealias Deck = List<LinkedList<Int>>

fun Deck.pickCards() = map { it.pollFirst() }