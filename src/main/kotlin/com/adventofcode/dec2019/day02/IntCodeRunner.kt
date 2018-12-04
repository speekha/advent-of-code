package com.adventofcode.dec2019.day02

import java.io.File

class IntCodeRunner(program: String) {

    val state: MutableList<Int> =
            program.split(",")
                    .map { it.toInt() }
                    .toMutableList()

    private var index = 0

    fun executeOneCommand() {
        val opCode = state[index]
        val args = intArrayOf(state[index + 1], state[index + 2], state[index + 3])
        when (opCode) {
            1 -> state[args[2]] = state[args[0]] + state[args[1]]
            2 -> state[args[2]] = state[args[0]] * state[args[1]]
        }
        index += 4
    }

    fun executeProgram() {
        while(state[index] != 99) {
            executeOneCommand()
        }
    }

    fun restore1202State() = initState(12, 2)

    fun initState(noun: Int, verb: Int) {
        state[1] = noun
        state[2] = verb
    }

}

fun main() {
    val input = File("src/main/kotlin/com/adventofcode/dec2019/day02/input.txt").readLines()
    val runner = IntCodeRunner(input[0])
    runner.restore1202State()
    runner.executeProgram()
    val result = runner.state[0]
    println("Initial value: $result")
}