package com.adventofcode.dec2022.day10

import com.adventofcode.debug
import com.adventofcode.dec2019.intCode.instruction

class Display(
    val instructions: List<String>
) {

    var x: Int = 1
        private set

    private var current = 0

    private var pending: String? = null

    private var pendingValue = 1

    var tick = 0
        private set

    fun runInstructions(ticks: Int) {
        repeat(ticks) {
            tick++
            x = pendingValue
            pending?.let { inProgress ->
                pendingValue = x + inProgress.drop(5).toInt()
                debug("$tick: Finish processing (x = $x)")
                pending = null
            } ?: processInstruction()
        }
    }

    private fun processInstruction() {
        if (current < instructions.size) {
            val instruction = instructions[current]
            debug("$tick: $instruction")
            if (instruction.startsWith("addx")) {
                pending = instruction
            }
            current++
        }
    }
}
