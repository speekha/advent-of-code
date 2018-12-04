package com.adventofcode.dec2015.day01

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FloorComputerTest {

    val computer = FloorComputer()

    @Test
    fun `should compute floor`() {
        assertEquals(0, computer.compute("(())"))
        assertEquals(0, computer.compute("()()"))
        assertEquals(3, computer.compute("((("))
        assertEquals(3, computer.compute("(()(()("))
    }

    @Test
    fun `should compute actual floor`() {
        assertEquals(74, computer.compute(readInputAsList()[0]))
    }

    @Test
    fun `should access basement`() {
        assertEquals(1, computer.accessBasement(")"))
        assertEquals(5, computer.accessBasement("()())"))
    }

    @Test
    fun `should access actual basement`() {
        assertEquals(1795, computer.accessBasement(readInputAsList()[0]))
    }
}