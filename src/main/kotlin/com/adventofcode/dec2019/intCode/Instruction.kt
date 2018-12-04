package com.adventofcode.dec2019.intCode

import com.adventofcode.debug

sealed class Instruction(
        val opCode: Int,
        val modes: CharSequence,
        protected val memory: Memory
) {

    abstract val params: Int

    open suspend fun execute() {
        saveValue(3,
                performOperation(getValue(1, modes[0]), getValue(2, modes[1])),
                modes[2])

        memory.jump(params + 1)
    }

    open fun performOperation(a: Long, b: Long) = 0L

    fun saveValue(address: Long, value: Long, mode: Char) = memory.saveValue(address, value, mode)

    fun getValue(index: Long, mode: Char): Long = memory.getValue(index, mode)

    override fun toString(): String {
        return javaClass.simpleName.toUpperCase()
    }

    class Add(modes: CharSequence, memory: Memory) : Instruction(1, modes, memory) {
        override val params = 3

        override fun performOperation(a: Long, b: Long) = a + b
    }

    class Multiply(modes: CharSequence, memory: Memory) : Instruction(2, modes, memory) {
        override val params = 3

        override fun performOperation(a: Long, b: Long) = a * b
    }

    class Read(
            modes: CharSequence,
            memory: Memory,
            private val io: IOInterface<*, *>
    ) : Instruction(3, modes, memory) {
        override val params = 1

        override suspend fun execute() {
            val data = io.readChannel()
            debug("Read: $data")
            saveValue(1, data, modes[0])
            memory.jump(params + 1)
        }
    }

    class Write(
            modes: CharSequence,
            memory: Memory,
            private val io: IOInterface<*, *>
    ) : Instruction(4, modes, memory) {
        override val params = 1

        override suspend fun execute() {
            val data = getValue(1, modes[0])
            debug("Write: $data")
            io.writeChannel(data)
            memory.jump(params + 1)
        }
    }

    class Jump(modes: CharSequence, memory: Memory, val predicate: (Long) -> Boolean) : Instruction(5, modes, memory) {
        override val params = 2

        override suspend fun execute() {
            if (predicate(getValue(1, modes[0])))
                memory.goTo(getValue(2, modes[1]))
            else
                memory.jump(params + 1)
        }
    }

    class LessThan(modes: CharSequence, memory: Memory) : Instruction(7, modes, memory) {
        override val params = 3

        override fun performOperation(a: Long, b: Long) = if (a < b) 1L else 0L
    }

    class Equals(modes: CharSequence, memory: Memory) : Instruction(8, modes, memory) {
        override val params = 3

        override fun performOperation(a: Long, b: Long) = if (getValue(1, modes[0]) == getValue(2, modes[1])) 1L else 0L
    }

    class IncRelative(modes: CharSequence, memory: Memory) : Instruction(9, modes, memory) {
        override val params = 1

        override suspend fun execute() {
            memory.incrementRelativeBase(getValue(1, modes[0]))
            memory.jump(params + 1)
        }
    }

    class Exit(memory: Memory) : Instruction(99, "", memory) {
        override val params = 0
    }
}

fun instruction(compoundCommand: Long, memory: Memory, io: IOInterface<*, *>): Instruction {
    val modes = compoundCommand.toString().padStart(5, '0').take(3).reversed()
    return when (val opCode = compoundCommand.toInt() % 100) {
        1 -> Instruction.Add(modes, memory)
        2 -> Instruction.Multiply(modes, memory)
        3 -> Instruction.Read(modes, memory, io)
        4 -> Instruction.Write(modes, memory, io)
        5 -> Instruction.Jump(modes, memory) { it != 0L }
        6 -> Instruction.Jump(modes, memory) { it == 0L }
        7 -> Instruction.LessThan(modes, memory)
        8 -> Instruction.Equals(modes, memory)
        9 -> Instruction.IncRelative(modes, memory)
        99 -> Instruction.Exit(memory)
        else -> error("Unknown instruction $opCode")
    }
}

fun parseOpCode(opCode: Long) = opCode.toInt() % 100