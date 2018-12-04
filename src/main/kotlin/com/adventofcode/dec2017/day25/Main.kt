package com.adventofcode.dec2017.day25

import com.adventofcode.time
import java.io.File

fun getLastToken(input: String): String = input.split(" ").last().dropLast(1)


fun main() = time {
    val input = File("src/main/kotlin/com/adventofcode/dec2017/day25/input.txt").readLines()

    with(TuringMachine.fromString(input)) {
        println("Diagnostic checksum: ${runDiagnostic()}")
    }
}