package com.adventofcode.dec2019.day05

import java.io.File

class IntCodeRunner(program: String) {

    val state: MutableList<Int> = program.split(",").map { it.toInt() }.toMutableList()

    var output = ""

    internal var index = 0

    fun executeOneCommand(input: Int = 0) {
        val instruction = state[index].toString().padStart(5, '0')

        val opCode = instruction.substring(3..4)
        index += when (opCode) {
            "01" -> add(instruction)
            "02" -> multiply(instruction)
            "03" -> save(input)
            "04" -> outputValue(instruction)
            "05" -> jumpIfTrue(instruction)
            "06" -> jumpIfFalse(instruction)
            "07" -> lessThan(instruction)
            "08" -> ifEqual(instruction)
            else -> error("Unsupported operation")
        }
    }

    private fun ifEqual(instruction: String): Int {
        saveValue(
                index + 3,
                if (getValue(index + 1, instruction[2]) == getValue(index + 2, instruction[1])) 1 else 0
        )
        return 4
    }

    private fun lessThan(instruction: String): Int {
        saveValue(
                index + 3,
                if (getValue(index + 1, instruction[2]) < getValue(index + 2, instruction[1])) 1 else 0
        )
        return 4
    }

    private fun jumpIfTrue(instruction: String): Int =
            if (getValue(index + 1, instruction[2]) != 0) getValue(index + 2, instruction[1]) - index else 3

    private fun jumpIfFalse(instruction: String): Int =
            if (getValue(index + 1, instruction[2]) == 0) getValue(index + 2, instruction[1]) - index else 3

    private fun outputValue(instruction: String): Int {
        output = getValue(index + 1, instruction[2]).toString()
        return 2
    }

    private fun save(input: Int): Int {
        saveValue(index + 1, input)
        return 2
    }

    private fun multiply(instruction: String): Int {
        saveValue(index + 3, getValue(index + 1, instruction[2]) * getValue(index + 2, instruction[1]))
        return 4
    }

    private fun add(instruction: String): Int {
        saveValue(index + 3, getValue(index + 1, instruction[2]) + getValue(index + 2, instruction[1]))
        return 4
    }

    private fun getValue(index: Int, mode: Char = '0'): Int {
        return when (mode) {
            '0' -> state[state[index]]
            '1' -> state[index]
            else -> error("Unsupported mode")
        }
    }

    private fun saveValue(index: Int, input: Int) {
        state[state[index]] = input
    }

    fun executeProgram(input: Int = 0) {
        while (state[index] != 99) {
            executeOneCommand(input)
        }
    }
}

fun main() {
    val input = File("src/main/kotlin/com/adventofcode/dec2019/day05/input.txt").readLines()
    var runner = IntCodeRunner(input[0])
    runner.executeProgram(1)
    println("Output value: ${runner.output}")
    runner = IntCodeRunner(input[0])
    runner.executeProgram(5)
    println("Output value: ${runner.output}")
}