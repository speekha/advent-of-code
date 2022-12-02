package com.adventofcode.dec2022.day02

interface Roshambo {

    fun computeRound(input: String): Int
    fun computeGame(input: List<String>): Int = input.sumOf { computeRound(it) }
}

class Rule1 : Roshambo {

    override fun computeRound(input: String): Int {
        val player1 = input[0] - 'A'
        val player2 = input[2] - 'X'
        val score = when (player1) {
            player2 -> 3
            (player2 + 2) % 3 -> 6
            else -> 0
        }
        return score + player2 + 1
    }
}

class Rule2 : Roshambo {

    override fun computeRound(input: String): Int {
        val player1 = input[0] - 'A'
        val score = (input[2] - 'X') * 3
        val player2 = when (score) {
            0 -> (player1 + 2) % 3
            6 -> (player1 + 1) % 3
            else -> player1
        }
        return player2 + score + 1
    }
}