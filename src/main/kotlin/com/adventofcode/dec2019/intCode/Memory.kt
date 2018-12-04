package com.adventofcode.dec2019.intCode

class Memory(program: String) {

    val state: MutableMap<Long, Long> = program.split(",")
            .mapIndexed { position, value -> position.toLong() to value.toLong() }
            .associate { it }
            .toMutableMap()

    internal var index = 0L

    internal var relativeBase = 0L

    val isOutOfMemory: Boolean
        get() = index > state.keys.maxOrNull() ?: error("Empty memory")

    fun setValueInMemory(address: Long, value: Long) {
        state[address] = value
    }

    fun getValue(address: Long, mode: Char): Long = when (mode) {
        '0' -> readState(readState(index + address))
        '1' -> readState(index + address)
        '2' -> readState(relativeBase + readState(index + address))
        else -> error("Unsupported mode $mode")
    }

    private fun readState(address: Long) = state.getOrDefault(address, 0)

    fun saveValue(address: Long, value: Long, mode: Char) {
        when (mode) {
            '0' -> state[readState(index + address)] = value
            '2' -> state[readState(index + address) + relativeBase] = value
            else -> error("Unsupported mode $mode")
        }
    }

    fun incrementRelativeBase(inc: Long) {
        relativeBase += inc
    }

    fun getInstruction(io: IOInterface<*, *>): Instruction =
            instruction(state[index] ?: error("Out of memory"), this, io)

    fun jump(increment: Int) {
        index += increment
    }

    fun goTo(address: Long) {
        index = address
    }

    fun getInstructionCode(): Int = parseOpCode(state[index] ?: error("Out of memory"))
}
