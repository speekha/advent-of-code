package com.adventofcode.dec2016.day18

data class Tiles(private val traps: List<Boolean>) {

    constructor(vararg isTrapped: Boolean) : this(isTrapped.asList())

    fun isTrapped(index: Int): Boolean = index >= 0 && index < traps.size && traps[index]

    fun nextRow(): Tiles = Tiles(traps.indices.map {
        isTrapped(it - 1) xor isTrapped(it + 1)
    })

    override fun toString(): String {
        return traps.joinToString("") { if (it) "^" else "." }
    }

    fun safeTiles(): Int = traps.count { !it }
}
