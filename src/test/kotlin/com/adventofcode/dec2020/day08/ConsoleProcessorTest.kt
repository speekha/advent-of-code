package com.adventofcode.dec2020.day08

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ConsoleProcessorTest {

    val input = listOf(
        "nop +0",
        "acc +1",
        "jmp +4",
        "acc +3",
        "jmp -3",
        "acc -99",
        "acc +1",
        "jmp -4",
        "acc +6"
    )

    @Test
    fun `should run program`() {
        val cpu = ConsoleProcessor(input)
        cpu.runOnce()
        assertEquals(5, cpu.accumulator)
    }

    @Test
    fun `should run actual program`() {
        val cpu = ConsoleProcessor(readInputAsList())
        cpu.runOnce()
        assertEquals(1420, cpu.accumulator)
    }

    @Test
    fun `should debug program`() {
        val cpu = ConsoleProcessor(input)
        assertEquals(7, cpu.debugProgram())
        assertEquals(8, cpu.accumulator)
    }

    @Test
    fun `should debug actual program`() {
        val cpu = ConsoleProcessor(readInputAsList())
        assertEquals(274, cpu.debugProgram())
        assertEquals(1245, cpu.accumulator)
    }
}