package com.adventofcode.dec2018.day9

class MarbleGame(players: Int) {

    class Marble(val value: Long) {
        var next: Marble = this
        var previous: Marble = this

        fun insert(addition: Marble): Marble {
            addition.next = next
            addition.previous = this
            next.previous = addition
            next = addition
            return addition
        }

        fun remove(): Marble {
            next.previous = previous
            previous.next = next
            return next
        }
    }

    val circle: Marble = Marble(0).apply {
        next = this
        previous = this
    }

    var round: Long = 0

    var currentMarble : Marble = circle

    var currentPlayer = -1

    val scores = LongArray(players) { 0 }

    val winningScore: Long
        get() = scores.maxOrNull() ?: 0

    fun formatGame(): String {
        return "[${formatCurrentPlayer()}] ${formatMarbles()}"
    }

    private fun formatMarbles(): String {
        val marble = currentMarble.value
        return concatMarbles().replace(" $marble ", "($marble)")
    }

    private fun concatMarbles(): String {
        var marble = circle
        val result = StringBuilder()
        do {
            result.append(" ${marble.value} ".run { substring(length - 3) })
            marble = marble.next
        } while(marble != circle)
        return result.toString()
    }

    private fun formatCurrentPlayer(): String = if (currentPlayer == -1) "-" else (currentPlayer + 1).toString()

    fun play() {
        round++
        currentPlayer = (currentPlayer + 1) % scores.size
        if (round % 23L == 0L) {
            repeat(7) {
                currentMarble = currentMarble.previous
            }
            scores[currentPlayer] += currentMarble.value + round
            currentMarble = currentMarble.remove()
        } else {
            currentMarble = currentMarble.next.insert(Marble(round))
        }
    }
}

fun main() {
    val game = MarbleGame(411)
    repeat(71170) {
        game.play()
    }
    println("High score: ${game.winningScore}")
    repeat(99 * 71170) {
        game.play()
    }
    println("High score: ${game.winningScore}")
}
