package com.adventofcode.dec2017.day18

import com.adventofcode.time
import java.io.File

fun main() = time {
    val input = File("src/main/kotlin/com/adventofcode/dec2017/day18/input.txt").readLines()

    with(AssemblyProcessor(0)) {
        execute(input.map { Instruction.parseInstruction(it) })
        println("Last sound: ${recoverLastSound()}")
    }

    with(Scheduler()) {
        process(input.map { Instruction.parseInstruction(it) })
        println("Send values (0): ${getSentValues(0)}")
        println("Send values (1): ${getSentValues(1)}")
    }
}