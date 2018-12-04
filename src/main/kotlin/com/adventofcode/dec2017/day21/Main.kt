package com.adventofcode.dec2017.day21

import java.io.File

fun main() {
    val input = File("src/main/kotlin/com/adventofcode/dec2017/day21/input.txt").readLines()
    with(FractalGenerator(input)) {
        for(i in 1..5) {
            iterate()
        }
        println("Active pixels: ${sumActivePixels()}")
        for(i in 6..18) {
            iterate()
        }
        println("Active pixels: ${sumActivePixels()}")
    }
}