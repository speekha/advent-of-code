package com.adventofcode.dec2016.day12

class AssembunnyProcessor(
        a: Int = 0,
        b: Int = 0,
        c: Int = 0,
        d: Int = 0
) {

    var registers: MutableMap<String, Int> = mutableMapOf("a" to a, "b" to b, "c" to c, "d" to d)

    fun processWithWhile(input: List<String>) {
        var index = 0
        while (index < input.size) {
            index += process(input[index])
        }
    }

    tailrec fun process(input: List<String>, index: Int = 0) {
        if (index < input.size) {
            return process(input, index + process(input[index]))
        }
    }

    private fun process(input: String): Int {
        val tokens = input.split(" ")
        val instruction = Instruction.valueOf(tokens[0])
        when (instruction) {
            Instruction.cpy -> cpy(tokens[1], tokens[2])
            Instruction.inc -> registers[tokens[1]] = registers.getOrDefault(tokens[1], 0) + 1
            Instruction.dec -> registers[tokens[1]] = registers.getOrDefault(tokens[1], 0) - 1
            Instruction.jnz -> if (registers.getOrElse(tokens[1]) { tokens[1].toInt() } != 0) return tokens[2].toInt()
        }
        return 1
    }

    fun cpy(source: String, dest: String) {
        val input = registers.getOrElse(source) { source.toInt() }
        registers[dest] = input
    }

}

fun main(args: Array<String>) {
    val input = listOf("cpy 1 a",
            "cpy 1 b",
            "cpy 26 d",
            "jnz c 2",
            "jnz 1 5",
            "cpy 7 c",
            "inc d",
            "dec c",
            "jnz c -2",
            "cpy a c",
            "inc a",
            "dec b",
            "jnz b -2",
            "cpy c b",
            "dec d",
            "jnz d -6",
            "cpy 16 c",
            "cpy 17 d",
            "inc a",
            "dec d",
            "jnz d -2",
            "dec c",
            "jnz c -5")
    with(AssembunnyProcessor()) {
        process(input)
        println("a = ${registers["a"]}")
    }

    with(AssembunnyProcessor(c = 1)) {
        process(input)
        println("a = ${registers["a"]}")
    }

}