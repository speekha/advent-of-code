package com.adventofcode.dec2021.day04

class Bingo(input: List<String>) {

    var round = 0

    val lastDraw: Int
        get() = draws[round - 1]

    private val draws = input[0].split(",").map { it.toInt() }

    val cards: List<BingoCard> = readCards(input.drop(1))

    private fun readCards(list: List<String>): List<BingoCard> = list.chunked(6).map { grid ->
        val values = grid.drop(1).flatMap { row ->
            row.split(" ").filter { it.isNotBlank() }.map { it.toInt() }
        }
        BingoCard(values)
    }

    fun findWinner(): BingoCard {
        while (cards.none { it.isWinning() }) {
            val draw = draws[round++]
            cards.forEach { it.mark(draw) }
        }
        return cards.first { it.isWinning() }
    }

    fun findLastWinner(): BingoCard {
        val cardList = this.cards.toMutableList()
        while (cardList.size > 1) {
            val draw = draws[round++]
            cardList.forEach { it.mark(draw) }
            cardList.removeIf { it.isWinning() }
        }
        val card = cardList[0]
        while (!card.isWinning()) {
            val draw = draws[round++]
            card.mark(draw)
        }
        return card
    }

}
