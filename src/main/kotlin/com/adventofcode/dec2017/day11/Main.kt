package com.adventofcode.dec2017.day11

import java.io.File

fun main() {
    val input = File("src/main/kotlin/com/adventofcode/dec2017/day11/input.txt").readLines()[0]
    with(GridMapper()) {
        println("Score: ${computeDistance(input)}")
        println("Max: ${findFurthestPoint(input)}")
    }
}