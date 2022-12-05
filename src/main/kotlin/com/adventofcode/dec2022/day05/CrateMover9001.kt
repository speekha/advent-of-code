package com.adventofcode.dec2022.day05

import java.util.*

class CrateMover9001(
    piles: Array<LinkedList<Char>>
) : CrateMover(piles) {
    constructor(input: List<String>) : this(parseCrates(input))

    override fun moveCrates(number: Int, from: Int, to: Int) {
        val crates = piles[from].take(number)
        crates.reversed().forEach { crate ->
            piles[from].pop()
            piles[to].push(crate)
        }
    }
}
