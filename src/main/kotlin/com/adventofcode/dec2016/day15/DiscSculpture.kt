package com.adventofcode.dec2016.day15

data class DiscSculpture(val discs: List<Disc>) {

    constructor(vararg discList: Disc) : this(discList.asList())

    fun getDiscPosition(discId:Int, time: Int): Int {
        val disc = discs[discId]
        return (disc.initialPosition + time) % disc.slots
    }

    fun dropCapsule(time: Int): Boolean = discs.indices.all { getDiscPosition(it, time + it + 1) == 0 }

    fun calculateDropTime(): Int = generateSequence(0) { it + 1 }.first { dropCapsule(it) }

}
