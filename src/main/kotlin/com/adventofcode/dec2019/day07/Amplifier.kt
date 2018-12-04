package com.adventofcode.dec2019.day07

import com.adventofcode.dec2019.intCode.intCodeRunner
import kotlinx.coroutines.channels.Channel

class Amplifier(
        program: String,
        val input: Channel<Long>,
        val output: Channel<Long>
) {

    private val runner = intCodeRunner(program, input, output)

    suspend fun initPhase(phase: Long) {
        input.send(phase)
    }

    fun process() = runner.executeProgram()
}
