package com.adventofcode.dec2019.intCode

import kotlinx.coroutines.channels.Channel

class IOInterface<Input, Output>(
        val input: Channel<Input>,
        val output: Channel<Output>,
        val convertInput: (Input) -> Long,
        val convertOutput: (Long) -> Output
) {
    suspend fun readChannel(): Long {
        return convertInput(input.receive())
    }

    suspend fun writeChannel(data: Long) {
        output.send(convertOutput(data))
    }

    fun close() {
        output.close()
    }
}