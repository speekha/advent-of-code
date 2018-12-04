package com.adventofcode.dec2019.day22

import java.math.BigInteger

class Shuffler(deck: Long) {

    private val deckSize = deck.toBigInteger()

    fun executeShuffle(index: Long, commands: List<String>): Long {
        val fullShuffle = compileShuffle(commands)
        var result = applyShuffle(index.toBigInteger(), fullShuffle)
        if (result < BigInteger.ZERO) {
            result += deckSize
        }
        return result.toLong()
    }

    fun reverseShuffle(index: Long, commands: List<String>, times: Long): Long {
        val fullShuffle = compileShuffle(commands)
        var result = index.toBigInteger()
        val cache = mutableMapOf<BigInteger, Long>()
        for (i in 1..times) {
            result = revertShuffle(result, fullShuffle)
            val previous = cache[result]
            if (previous != null) {
                val modulo = i - previous
                return cache.filter { it .value == (times - previous) % modulo + previous }.keys.first().toLong()
            } else {
                cache[result] = i
            }
        }
        if (result < BigInteger.ZERO) {
            result += deckSize
        }
        return result.toLong()
    }

    private fun compileShuffle(commands: List<String>): Shuffle = commands
            .map { parseCommand(it) }
            .fold(Shuffle(BigInteger.ONE, BigInteger.ZERO, deckSize)) { acc, shuffle ->
                acc.combine(shuffle)
            }

    private fun parseCommand(shuffle: String): Shuffle = when {
        shuffle.contains("stack") -> Shuffle(deckSize - BigInteger.ONE, deckSize - BigInteger.ONE, deckSize)
        shuffle.contains("cut") -> Shuffle(BigInteger.ONE, (deckSize - shuffle.drop(shuffle.lastIndexOf(' ') + 1).toBigInteger()), deckSize)
        shuffle.contains("increment") -> Shuffle(shuffle.drop(shuffle.lastIndexOf(' ') + 1).toBigInteger(), BigInteger.ZERO, deckSize)
        else -> error("Unexpected command: $shuffle")
    }

    private fun applyShuffle(index: BigInteger, shuffle: Shuffle): BigInteger =
            (index * shuffle.a + shuffle.b + deckSize) % deckSize

    private fun revertShuffle(index: BigInteger, shuffle: Shuffle): BigInteger =
            (index - shuffle.b + deckSize) * shuffle.invert % deckSize
}
