package com.adventofcode.dec2017.day18

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class SchedulerTest {

    val input = listOf("snd 1",
            "snd 2",
            "snd p",
            "rcv a",
            "rcv b",
            "rcv c",
            "snd 1",
            "rcv d",
            "rcv d"
            ).map { Instruction.parseInstruction(it) }

    @Test
    fun `should run until deadlock`() {
        val scheduler = Scheduler()
        scheduler.process(input)
        assertEquals(4, scheduler.getSentValues(0))
        assertEquals(4, scheduler.getSentValues(1))
    }

    @Test
    fun `test real values`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2017/day18/input.txt").readLines()
        with(Scheduler()) {
            process(input.map { Instruction.parseInstruction(it) })
            assertEquals(6096, getSentValues(0))
            assertEquals(5969, getSentValues(1))
        }
    }
}