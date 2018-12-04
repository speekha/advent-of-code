package com.adventofcode.dec2017.day13

import com.adventofcode.time
import java.io.File

fun main() = time {
    val input = File("src/main/kotlin/com/adventofcode/dec2017/day13/input.txt").readLines()
    with(PacketSmuggler(input)) {
        println("Severity: ${computeSeverity()}")
        println("Delay: ${avoidScanners()}")
    }
}