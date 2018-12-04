package com.adventofcode.dec2019.day06

class OrbitalObject(
        val name: String
) {

    fun countOrbits(depth: Int = 0): Int = children.sumBy { it.countOrbits(depth + 1) } + depth

    var parent: OrbitalObject? = null

    val children = mutableListOf<OrbitalObject>()
}