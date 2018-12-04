package com.adventofcode.dec2016.day25

class AssembunnyProcessor(
        input: List<String>,
        val output: List<Int>
) {

    var registers: MutableMap<String, Int> = initRegisters()

    val instructions = input.map { mapInstruction(it) }.toTypedArray()

    val out: MutableList<Int> = mutableListOf()

    private fun initRegisters(a: Int = 0, b: Int = 0, c: Int = 0, d: Int = 0) = mutableMapOf("a" to a, "b" to b, "c" to c, "d" to d)

    fun reset(a: Int = 0, b: Int = 0, c: Int = 0, d: Int = 0) {
        registers = initRegisters(a, b, c, d)
        out.clear()
    }

    fun run(): Boolean {
        println("\na = " + registers["a"])
        var index = 0
        while (index < instructions.size && matchOutput() && output.size > out.size) {
            index += process(instructions[index], index)
        }
        return matchOutput()
    }

    private fun matchOutput(): Boolean = output.take(out.size) == out

    fun mapInstruction(input: String): Instruction {
        val tokens = input.split(" ")
        return when (tokens[0]) {
            "cpy" -> Cpy(tokens[1], tokens[2])
            "inc" -> Inc(tokens[1])
            "dec" -> Dec(tokens[1])
            "jnz" -> Jnz(tokens[1], tokens[2])
            "tgl" -> Tgl(tokens[1])
            "out" -> Out(tokens[1])
            else -> error("Unknown instruction")
        }
    }

    private fun process(instruction: Instruction, index: Int): Int {
        try {
//            println("$index: $instruction / $registers")
            when (instruction) {
                is Cpy -> cpy(instruction.x, instruction.y)
                is Inc -> registers[instruction.x] = registers.getOrDefault(instruction.x, 0) + 1
                is Dec -> registers[instruction.x] = registers.getOrDefault(instruction.x, 0) - 1
                is Jnz -> if (parseValue(instruction.x) != 0) return parseValue(instruction.y)
                is Tgl -> toggle(instruction.x, index)
                is Out -> transmit(instruction.x)
            }
        } catch (e: Exception) {
            println("invalid instruction")
        }
        return 1
    }

    private fun cpy(source: String, dest: String) {
        val input = parseValue(source)
        registers[dest] = input
    }

    private fun toggle(arg: String, index: Int) {
        val offset = parseValue(arg)
        instructions[index + offset] = toggle(instructions[index + offset])
    }

    private fun transmit(x: String) {
        val value = parseValue(x)
//        print(" $value")
        out += value
    }

    private fun parseValue(arg: String) = registers.getOrElse(arg) { arg.toInt() }

    private fun toggle(instruction: Instruction): Instruction = when (instruction) {
        is Inc -> Dec(instruction.x)
        is Dec -> Inc(instruction.x)
        is Tgl -> Inc(instruction.x)
        is Jnz -> Cpy(instruction.x, instruction.y)
        is Cpy -> Jnz(instruction.x, instruction.y)
        is Out -> instruction
    }
}

fun main() {
    val input = listOf(
            "cpy a d",
            "cpy 7 c",
            "cpy 365 b",
            "inc d",
            "dec b",
            "jnz b -2",
            "dec c",
            "jnz c -5",
            "cpy d a",
            "jnz 0 0",
            "cpy a b",
            "cpy 0 a",
            "cpy 2 c",
            "jnz b 2",
            "jnz 1 6",
            "dec b",
            "dec c",
            "jnz c -4",
            "inc a",
            "jnz 1 -7",
            "cpy 2 b",
            "jnz c 2",
            "jnz 1 4",
            "dec b",
            "dec c",
            "jnz 1 -4",
            "jnz 0 0",
            "out b",
            "jnz a -19",
            "jnz 1 -21"
    )
    val processor = AssembunnyProcessor(input = input, output = listOf(0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1))
    val minStart = (0..Int.MAX_VALUE).firstOrNull {
        processor.reset(a = it)
        processor.run()
    }
    println("Match for $minStart")
}