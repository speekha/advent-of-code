package com.adventofcode.dec2017.day5

import java.io.File

class InstructionProcessor {
    fun countStepsToExit(input: List<String>): Int {
        val jumps = input.map { it.toInt() }.toMutableList()

        tailrec fun countSteps(index: Int = 0, inc: Int = 1) : Int {
            val jump = jumps[index] + index
            jumps[index]++
            return if (jump !in 0 until input.size) inc else countSteps(jump, 1 + inc)
        }

        return countSteps()
    }

    fun countSpecialStepsToExit(input: List<String>): Int {
        val jumps = input.map { it.toInt() }.toMutableList()

        tailrec fun countSteps(index: Int = 0, inc: Int = 1) : Int {
            val jump = jumps[index]
            val newIndex = jump + index
            if (jump >= 3) {
                jumps[index]--
            } else {
                jumps[index]++
            }
            return if (newIndex !in 0 until input.size) inc else countSteps(newIndex, 1 + inc)
        }

        return  countSteps()

    }
}

fun main() {
    val input = File("src/main/kotlin/com/adventofcode/dec2017/day5/input.txt").readLines()
    with(InstructionProcessor()) {
        println("Steps to exit: ${countStepsToExit(input)}")
        println("Special steps to exit: ${countSpecialStepsToExit(input)}")
    }
}