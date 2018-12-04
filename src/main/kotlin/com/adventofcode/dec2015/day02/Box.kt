package com.adventofcode.dec2015.day02

data class Box(
    val l: Int,
    val w: Int,
    val h: Int
) {
    val surfaceArea: Int
        get() = 2 * l * w + 2 * w * h + 2 * h * l

    val requiredPaper: Int
        get() {
            val smallest = listOf(l, w, h).sorted().dropLast(1)
            return surfaceArea + smallest[0] * smallest[1]
        }
    val requiredRibbon: Int
        get() {
            val smallest = listOf(l, w, h).sorted().dropLast(1)
            return 2 * smallest.sum() + l * w * h
        }
}

fun Box(input: String): Box {
    val (l, w, h) = input.split("x").map { it.toInt() }
    return Box(l, w, h)
}