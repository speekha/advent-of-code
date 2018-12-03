package com.adventofcode.dec2017.day16

import com.adventofcode.time
import java.io.File

fun main(args: Array<String>) = time {
    val input = File("src/main/kotlin/com/adventofcode/dec2017/day16/input.txt").readLines()[0].split(",")
    with(ProgramDance(16)) {
        doDance(input)
        println("Final positions: ${positions()}")
    }
    with(ProgramDance(16)) {
        doDance(input, 1000000000)
        println("Final positions: ${positions()}")
    }
}