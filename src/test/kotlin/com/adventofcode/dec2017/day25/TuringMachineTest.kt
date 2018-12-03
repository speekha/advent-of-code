package com.adventofcode.dec2017.day25

import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File

class TuringMachineTest {

    val input = listOf("Begin in state A.",
            "Perform a diagnostic checksum after 6 steps.",
            "",
            "In state A:",
            "  If the current value is 0:",
            "    - Write the value 1.",
            "    - Move one slot to the right.",
            "    - Continue with state B.",
            "  If the current value is 1:",
            "    - Write the value 0.",
            "    - Move one slot to the left.",
            "    - Continue with state B.",
            "",
            "In state B:",
            "  If the current value is 0:",
            "    - Write the value 1.",
            "    - Move one slot to the left.",
            "    - Continue with state A.",
            "  If the current value is 1:",
            "    - Write the value 1.",
            "    - Move one slot to the right.",
            "    - Continue with state A.")

    @Test
    fun `should parse input`() {
        val machine = TuringMachine.fromString(input)
        val expected = TuringMachine("A", 6,
                mapOf(
                        "A" to Rule("A", Command(1, Direction.RIGHT, "B"), Command(0, Direction.LEFT, "B")),
                        "B" to Rule("B", Command(1, Direction.LEFT, "A"), Command(1, Direction.RIGHT, "A"))
                ))
        assertEquals(expected, machine)
    }

    @Test
    fun `memory checksum should start at 0`() {
        val machine = TuringMachine.fromString(input)
        assertEquals(0, machine.checksum())
        assertEquals("A", machine.state)
    }

    @Test
    fun `memory checksum should be 1 after 1 step`() {
        val machine = TuringMachine.fromString(input)
        machine.iterate(1)
        assertEquals(1, machine.checksum())
        assertEquals("B", machine.state)
    }

    @Test
    fun `memory checksum should be 2 after 2 steps`() {
        val machine = TuringMachine.fromString(input)
        machine.iterate(2)
        assertEquals(2, machine.checksum())
        assertEquals("A", machine.state)
    }

    @Test
    fun `memory checksum should be 1 after 3 steps`() {
        val machine = TuringMachine.fromString(input)
        machine.iterate(3)
        assertEquals(1, machine.checksum())
        assertEquals("B", machine.state)
    }

    @Test
    fun `memory checksum should be 2 after 4 steps`() {
        val machine = TuringMachine.fromString(input)
        machine.iterate(4)
        assertEquals(2, machine.checksum())
        assertEquals("A", machine.state)
    }

    @Test
    fun `memory checksum should be 3 after 5 steps`() {
        val machine = TuringMachine.fromString(input)
        machine.iterate(5)
        assertEquals(3, machine.checksum())
        assertEquals("B", machine.state)
    }

    @Test
    fun `memory checksum should be 3 after 6 steps`() {
        val machine = TuringMachine.fromString(input)
        machine.iterate(6)
        assertEquals(3, machine.checksum())
        assertEquals("A", machine.state)
    }

    @Test
    fun `diagnostic should be 3`() {
        val machine = TuringMachine.fromString(input)
        assertEquals(3, machine.runDiagnostic())
    }

    @Test
    fun `test real values`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2017/day25/input.txt").readLines()

        with(TuringMachine.fromString(input)) {
            assertEquals(5744, runDiagnostic())
        }
    }
}