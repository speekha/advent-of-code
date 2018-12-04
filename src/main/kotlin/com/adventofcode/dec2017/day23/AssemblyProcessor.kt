package com.adventofcode.dec2017.day23

class AssemblyProcessor(input: List<String>, vararg initValues: Pair<String, Long>) {

    val instructions: List<Instruction> = input.map { Instruction.parseInstruction(it) }

    var registers: MutableMap<String, Long> = initValues.asSequence().associate { it }.toMutableMap()

    var instCounter = 0

    val counter: MutableMap<String, Int> = mutableMapOf()

    tailrec fun execute(index: Int = 0) {
        instCounter++
        if (index < instructions.size && instCounter < 500000000) {
            updateCounter(instructions[index])
            execute(index + instructions[index].execute(this))
        }
    }

    fun updateCounter(instr: Instruction) {
        counter[instr::class.simpleName ?: ""] = counter.getOrDefault(instr::class.simpleName?:error("Unknown instruction class: ${instr::class}"), 0) + 1
    }

    fun getRegister(register: String): Long = if (register.length == 1 && register[0] in 'a'..'z') {
        registers.getOrPut(register) { 0 }
    } else {
        register.toLong()
    }
}