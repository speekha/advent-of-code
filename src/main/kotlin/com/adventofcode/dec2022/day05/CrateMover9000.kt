package com.adventofcode.dec2022.day05

import java.util.*

class CrateMover9000(
    piles: Array<LinkedList<Char>>
) : CrateMover(piles) {
    constructor(input: List<String>) : this(parseCrates(input))

    override fun moveCrates(number: Int, from: Int, to: Int) {
        repeat(number) {
            moveCrate(from, to)
        }
    }

    private fun moveCrate(from: Int, to: Int) {
        val crate = piles[from].pop()
        piles[to].push(crate)
    }
}
