package com.adventofcode.dec2017.day18

import com.adventofcode.time
import java.io.File

class Scheduler {

    val threads = arrayOf(
            initThread(0),
            initThread(1))

    var current = 0

    private fun initThread(id: Int) = Thread(id, { send(id, it) }, { receive(id, it) })

    fun process(input: List<Instruction>) {
        while (threads.any { it.running }) {
            processInstruction(current, getCurrentInstruction(input, current))
            updateCurrentThread()
        }
    }

    private fun processInstruction(current: Int, input: Instruction) {
        threads[current].process(input)
    }

    private fun updateCurrentThread() {
        if (!threads[current].running) {
            current = ((current + 1) % threads.size)
            threads[current].resume()
        }
    }

    private fun getCurrentInstruction(input: List<Instruction>, current: Int) = input[threads[current].index]

    private fun send(id: Int, value: Long) {
        threads.filter { it.id != id }.forEach { it.queue.add(value) }
        threads[id].sendCalls++
    }

    private fun receive(id: Int, reg: String): Int = with(threads[id]) {
        if (updateRegisterOrBlock(reg)) 1 else 0
    }

    fun getSentValues(i: Int) = threads[i].sendCalls
}

