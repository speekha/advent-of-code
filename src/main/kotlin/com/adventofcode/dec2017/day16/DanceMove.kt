package com.adventofcode.dec2017.day16

sealed class DanceMove {
    abstract fun moveDancers(dancers: CharArray): CharArray
}

data class Spin(private val number: Int) : DanceMove() {
    override fun moveDancers(dancers: CharArray) = (dancers.takeLast(number) + dancers.take(dancers.size - number)).toCharArray()
}

data class Exchange(val a: Int, val b: Int) : DanceMove() {
    constructor(input: List<String>) : this(input[0].toInt(), input[1].toInt())

    override fun moveDancers(dancers: CharArray): CharArray {
        val after = dancers.copyOf()
        after[a] = dancers[b]
        after[b] = dancers[a]
        return after
    }
}

data class Swap(val a: Char, val b: Char) : DanceMove() {
    override fun moveDancers(dancers: CharArray): CharArray = CharArray(dancers.size) {
        when (dancers[it]) {
            a -> b
            b -> a
            else -> dancers[it]
        }
    }
}

object Idle : DanceMove() {
    override fun moveDancers(dancers: CharArray) = dancers.copyOf()
}