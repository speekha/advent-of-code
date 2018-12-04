package com.adventofcode.dec2021.day06

class FishShoal(input: List<String>) {

    constructor(input: String) : this(input.split(","))

    private val fishes = LongArray(9) { i -> input.count { it.toInt() == i }.toLong() }

    val size: Long
        get() = fishes.sum()

    fun advanceDate(days: Int = 1) {
        repeat(days) {
            val spawning = fishes[0]
            (0..fishes.size - 2).forEach { i ->
                fishes[i] = fishes[i + 1]
            }
            fishes[6] += spawning
            fishes[8] = spawning
        }
    }

}
