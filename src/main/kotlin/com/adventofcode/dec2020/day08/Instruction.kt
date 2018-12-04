package com.adventofcode.dec2020.day08

data class Instruction(
    val index: Int,
    val command: Command,
    val argument: Int
) {
    val next: Int = index + (if (command == Command.JMP) argument else 1)

    override fun toString(): String = "$index: $command $argument"
}

fun Instruction(index: Int, data: String): Instruction {
    val (instruction, argument) = data.split(" ")
    return Instruction(index, Command.valueOf(instruction.toUpperCase()), argument.toInt())
}

enum class Command {
    ACC, JMP, NOP
}