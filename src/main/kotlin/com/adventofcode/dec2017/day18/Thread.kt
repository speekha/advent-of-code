package com.adventofcode.dec2017.day18

import java.util.*

class Thread(
        val id: Int,
        send: AssemblyProcessor.(Long) -> Unit,
        receive: AssemblyProcessor.(String) -> Int
) {

    val program: AssemblyProcessor = AssemblyProcessor(id.toLong())
    var running: Boolean = true
    var queue: LinkedList<Long> = LinkedList()
    var index: Int = 0
    var sendCalls: Int = 0

    init {
        program.send = send
        program.receive = receive
    }

    fun process(instruction: Instruction) {
        index += instruction.execute(program)
    }

    fun resume() {
        if (queue.isNotEmpty()) {
            running = true
        }
    }

    fun updateRegisterOrBlock(reg: String) = if (queue.isNotEmpty()) {
        program.registers[reg] = queue.pollFirst()
        true
    } else {
        running = false
        false
    }
}