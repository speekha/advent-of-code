package com.adventofcode.dec2015.day07

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CircuitEmulatorTest {

    val input = listOf(
        "123 -> x",
        "456 -> y",
        "x AND y -> d",
        "x OR y -> e",
        "x LSHIFT 2 -> f",
        "y RSHIFT 2 -> g",
        "NOT x -> h",
        "NOT y -> i"
    )

    @Test
    fun `should parse circuit`() {
        val emulator = CircuitEmulator(input)
        assertEquals(setOf("d", "e", "f", "g", "h", "i", "x", "y"), emulator.gates.keys)
        assertTrue(emulator.gates["x"] is Constant)
        assertTrue(emulator.gates["y"] is Constant)
        assertTrue(emulator.gates["d"] is And)
        assertTrue(emulator.gates["e"] is Or)
        assertTrue(emulator.gates["f"] is LeftShift)
        assertTrue(emulator.gates["g"] is RightShift)
        assertTrue(emulator.gates["h"] is Not)
        assertTrue(emulator.gates["i"] is Not)
        assertEquals(8, emulator.gates.size)
    }

    @Test
    fun `should emulate circuit`() {
        val emulator = CircuitEmulator(input)
        assertEquals(8, emulator.gates.size)
        assertEquals(72, emulator.getWire("d"))
        assertEquals(507, emulator.getWire("e"))
        assertEquals(492, emulator.getWire("f"))
        assertEquals(114, emulator.getWire("g"))
        assertEquals(65412, emulator.getWire("h"))
        assertEquals(65079, emulator.getWire("i"))
        assertEquals(123, emulator.getWire("x"))
        assertEquals(456, emulator.getWire("y"))
    }

    @Test
    fun `should emulate actual circuit`() {
        val emulator = CircuitEmulator(this@CircuitEmulatorTest.readInputAsList())
        assertEquals(956, emulator.getWire("a"))
    }

    @Test
    fun `should emulate actual circuit with override`() {
        val emulator = CircuitEmulator(this@CircuitEmulatorTest.readInputAsList().map {
            it.replace("14146 -> b", "956 -> b")
        })
        assertEquals(40149, emulator.getWire("a"))
    }

}