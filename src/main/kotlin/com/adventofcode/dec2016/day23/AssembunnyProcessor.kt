package com.adventofcode.dec2016.day23

class AssembunnyProcessor(
        a: Int = 0,
        b: Int = 0,
        c: Int = 0,
        d: Int = 0,
        input: List<String>
) {

    var registers: MutableMap<String, Int> = mutableMapOf("a" to a, "b" to b, "c" to c, "d" to d)

    val instructions = input.map { mapInstruction(it) }.toTypedArray()

    fun run() {
        var index = 0
        while (index < instructions.size) {
            index += process(instructions[index], index)
        }
    }

    fun mapInstruction(input: String): Instruction {
        val tokens = input.split(" ")
        return when (tokens[0]) {
            "cpy" -> Cpy(tokens[1], tokens[2])
            "inc" -> Inc(tokens[1])
            "dec" -> Dec(tokens[1])
            "jnz" -> Jnz(tokens[1], tokens[2])
            "tgl" -> Tgl(tokens[1])
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

    private fun parseValue(arg: String) = registers.getOrElse(arg) { arg.toInt() }

    private fun toggle(instruction: Instruction): Instruction = when (instruction) {
        is Inc -> Dec(instruction.x)
        is Dec -> Inc(instruction.x)
        is Tgl -> Inc(instruction.x)
        is Jnz -> Cpy(instruction.x, instruction.y)
        is Cpy -> Jnz(instruction.x, instruction.y)
    }
}

fun main() {
    val input = listOf(
            "cpy a b",
            "dec b",
            "cpy a d",
            "cpy 0 a",
            "cpy b c",
            "inc a",
            "dec c",
            "jnz c -2",
            "dec d",
            "jnz d -5",
            "dec b",
            "cpy b c",
            "cpy c d",
            "dec d",
            "inc c",
            "jnz d -2",
            "tgl c",
            "cpy -16 c",
            "jnz 1 c",
            "cpy 96 c",
            "jnz 91 d",
            "inc a",
            "inc d",
            "jnz d -2",
            "inc c",
            "jnz c -5")
    with(AssembunnyProcessor(a = 7, input = input)) {
        run()
        println("a = ${registers["a"]}")
    }

    with(AssembunnyProcessor(a = 12, input = input)) {
        run()
        println("a = ${registers["a"]}")
    }

}