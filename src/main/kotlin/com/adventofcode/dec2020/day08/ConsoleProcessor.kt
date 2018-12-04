package com.adventofcode.dec2020.day08

class ConsoleProcessor(input: List<String>) {

    val instructions: Array<Instruction> = parseInstructions(input)

    var index = 0

    var accumulator = 0

    fun runOnce(): Boolean {
        index = 0
        accumulator = 0
        val executed = mutableSetOf<Int>()
        while (!executed.contains(index) && index in instructions.indices) {
            executed += index
            executeCurrentInstruction()
        }
        return index == instructions.size
    }

    private fun executeCurrentInstruction() {
        val instruction = instructions[index]
        if (instruction.command == Command.ACC) {
            accumulator += instruction.argument
        }
        index = instruction.next
    }

    fun debugProgram(): Int = instructions.asSequence().first { instruction ->
        (instruction.command == Command.NOP || instruction.command == Command.JMP) && testFix(instruction)
    }.index

    private fun testFix(instruction: Instruction): Boolean = try {
        instructions[instruction.index] = swapInstructions(instruction)
        runOnce()
    } finally {
        instructions[instruction.index] = instruction
    }

    private fun swapInstructions(instruction: Instruction) = when (instruction.command) {
        Command.JMP -> instruction.copy(command = Command.NOP)
        Command.NOP -> instruction.copy(command = Command.JMP)
        else -> instruction
    }

    companion object {
        private fun parseInstructions(input: List<String>): Array<Instruction> = input.mapIndexed { index, line ->
            Instruction(index, line)
        }.toTypedArray()
    }
}
