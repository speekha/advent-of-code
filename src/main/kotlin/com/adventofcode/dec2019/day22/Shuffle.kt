package com.adventofcode.dec2019.day22

import java.math.BigInteger

/**
 * Represents a shuffle operation as a "ax + b" transformation.
 */
data class Shuffle(
        val a: BigInteger,
        val b: BigInteger,
        val deckSize: BigInteger
) {

    val invert = a.modInverse(deckSize)

    fun combine(shuffle: Shuffle): Shuffle =
            Shuffle(a * shuffle.a % deckSize, (b * shuffle.a + shuffle.b) % deckSize, deckSize)

    constructor(a: Long, b: Long, deckSize: Long) : this(a.toBigInteger(), b.toBigInteger(), deckSize.toBigInteger())
}