package com.adventofcode.dec2017.day24

import com.adventofcode.time
import java.io.File

fun main(args: Array<String>) = time {
    val input = File("src/main/kotlin/com/adventofcode/dec2017/day24/input.txt").readLines()

    with(BridgeBuilder(input)) {
        println("Strongest bridge: ${findStrongestBridge()}")
        println("Longest bridge: ${computeBridgeScore(findLongestBridge())}")
    }
}