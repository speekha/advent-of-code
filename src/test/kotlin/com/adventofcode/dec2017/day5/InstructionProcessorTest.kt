package com.adventofcode.dec2017.day5

import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File

class InstructionProcessorTest {

    @Test
    fun `should reach exit in 3 steps`() {
        val input = listOf("0",
                "3",
                "0",
                "1")
        assertEquals(3, InstructionProcessor().countStepsToExit(input))
    }

    @Test
    fun `should reach exit in 5 steps`() {
        val input = listOf("0",
                "3",
                "0",
                "1",
                "-3")
        assertEquals(5, InstructionProcessor().countStepsToExit(input))
    }

    @Test

    fun `should reach exit in 10 steps`() {
        val input = listOf("0",
                "3",
                "0",
                "1",
                "-3")
        assertEquals(10, InstructionProcessor().countSpecialStepsToExit(input))
    }

    @Test
    fun `test real values`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2017/day5/input.txt").readLines()
        with(InstructionProcessor()) {
            assertEquals(359348, countStepsToExit(input))
            assertEquals(27688760, countSpecialStepsToExit(input))
        }
    }
}