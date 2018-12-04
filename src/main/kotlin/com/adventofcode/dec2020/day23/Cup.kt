package com.adventofcode.dec2020.day23

data class Cup(
    val value: Int
) {
    var next: Cup? = null
}
