package com.adventofcode.dec2019.intCode

import kotlinx.coroutines.channels.Channel
import java.lang.StringBuilder

class Parser {
    fun parse(program: String): String {
        val memory = Memory(program)
        val io = IOInterface<Long, Long>(Channel(), Channel(), { it }, { it })
        val result = StringBuilder()
        while (!memory.isOutOfMemory) {
            try {
                val instruction = memory.getInstruction(io)
                result.append(String.format("%03d %s", memory.index, instruction))
                (1..instruction.params).forEach {
                    result.append(" ").append(memory.getParameter(it, instruction.modes))
                }
                result.append("\n")
                memory.jump(instruction.params + 1)
            } catch (e: Throwable) {
                result.append(String.format("%03d %05d\n", memory.index, memory.getInstructionCode()))
                memory.jump(1)
            }
        }
        return result.toString()
    }

    private fun Memory.getParameter(offset: Int, modes: CharSequence): String {
        val mode = when (modes[offset - 1]) {
            '2' -> "@+"
            '0' -> "@"
            else -> ""
        }
        val value = state[index + offset].toString()
        return "$mode$value"
    }
}
