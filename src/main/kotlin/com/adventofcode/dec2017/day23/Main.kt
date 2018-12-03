package com.adventofcode.dec2017.day23

import com.adventofcode.time
import java.io.File

fun main(args: Array<String>) = time {
    var input = File("src/main/kotlin/com/adventofcode/dec2017/day23/input.txt").readLines()

    with(AssemblyProcessor(input)) {
        execute()
        println("Number of multiplications: ${counter[Mul::class.simpleName]}")
    }

    input = File("src/main/kotlin/com/adventofcode/dec2017/day23/optimized.txt").readLines()
    with(AssemblyProcessor(input, "a" to 1)) {
        execute()
        println("Value of h: ${getRegister("h")}")
    }
}