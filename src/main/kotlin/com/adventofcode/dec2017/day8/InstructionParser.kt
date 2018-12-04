package com.adventofcode.dec2017.day8

import java.io.File

class InstructionParser {
    fun parse(input: String): Instruction {
        val tokens = input.split(" ")
        val (register, operator, step, _) = tokens
        val (left, cmp, right) = tokens.subList(4, 7)

        return Instruction(
                register,
                Operation.valueOf(operator.toUpperCase()),
                step.toInt(),
                Condition(left, right, Operator.fromString(cmp)))
    }
}

fun main() {
    val input = File("src/main/kotlin/com/adventofcode/dec2017/day8/input.txt").readLines()
    val parser = InstructionParser()
    with(CPU()) {
        processInstructions(input.map { parser.parse(it) })
        println("Highest register: ${getHighestRegister()}")
        println("Max value: ${getRange().last}")
    }
}