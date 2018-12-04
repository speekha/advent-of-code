package com.adventofcode.dec2021.day17

class TargetingSystem(input: String) {
    val xRange: IntRange
    val yRange: IntRange

    init {
        val x = input.drop(15).takeWhile { it != ' ' }.dropLast(1).split("..").map { it.toInt() }
        val y = input.substring(input.indexOf("y=") + 2).split("..").map { it.toInt() }
        xRange = x[0]..x[1]
        yRange = y[0]..y[1]
    }

    fun findHighestPoint(): Int {
        return 0
    }
}
