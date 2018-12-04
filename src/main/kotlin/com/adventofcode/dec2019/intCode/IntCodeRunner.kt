package com.adventofcode.dec2019.intCode

import com.adventofcode.debug
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class IntCodeRunner<Input, Output>(
        program: String,
        input: Channel<Input>,
        output: Channel<Output>,
        convertInput: (Input) -> Long,
        convertOutput: (Long) -> Output
) : CoroutineScope {

    override val coroutineContext: CoroutineContext = Job() + Dispatchers.IO

    internal val memory = Memory(program)

    private val io = IOInterface(input, output, convertInput, convertOutput)

    fun setValueInMemory(address: Long, value: Long) = memory.setValueInMemory(address, value)

    fun executeProgram() = launch {
        while (memory.getInstructionCode() != 99) {
            executeOneCommand()
        }
        debug("Closing channel")
        io.close()
    }

    private suspend fun executeOneCommand() {
        val instruction = memory.getInstruction(io)
        instruction.execute()
    }

    private fun getValue(index: Long, mode: Char): Long = memory.getValue(index, mode)

    private fun saveValue(address: Long, value: Long, mode: Char) = memory.saveValue(address, value, mode)
}

fun intCodeRunner(program: String, input: Channel<Long>, output: Channel<Long>) = IntCodeRunner(program, input, output, { it }, { it })