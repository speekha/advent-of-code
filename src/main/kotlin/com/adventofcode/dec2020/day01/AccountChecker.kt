package com.adventofcode.dec2020.day01

class AccountChecker(val input: List<Int>) {
    fun computeError(factors: Int): Int = if (factors == 2) {
        findRemaining(2020) ?: error("Not found")
    } else {
        input.asSequence()
                .mapNotNull { findRemaining(2020 - it)?.times(it)?.also { println("Triplet for $it") } }
                .first()
    }

    private fun findRemaining(total: Int): Int? = input.firstOrNull { (total - it) in input }?.let { number ->
        println("Found $number and ${total - number} for total $total")
        number * (total - number)
    }

}
