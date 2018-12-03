package com.adventofcode.dec2017.day18

class AssemblyProcessor(id: Long) {

    var send: AssemblyProcessor.(Long) -> Unit = { lastPlayed = it }

    var receive: AssemblyProcessor.(String) -> Int = { 1000 }

    var registers: MutableMap<String, Long> = mutableMapOf("p" to id)

    var lastPlayed = 0L

    tailrec fun execute(input: List<Instruction>, index: Int = 0) {
        if (index < input.size) {
            execute(input, index + input[index].execute(this))
        }
    }

    fun getRegister(register: String): Long = if (register.length == 1 && register[0] in 'a'..'z') {
        registers.getOrPut(register) { 0 }
    } else {
        register.toLong()
    }

    fun recoverLastSound(): Long = lastPlayed

}