package com.adventofcode.dec2018.day7

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class IkeaAssemblerTest {

    val input = listOf(
            "Step C must be finished before step A can begin.",
            "Step C must be finished before step F can begin.",
            "Step A must be finished before step B can begin.",
            "Step A must be finished before step D can begin.",
            "Step B must be finished before step E can begin.",
            "Step D must be finished before step E can begin.",
            "Step F must be finished before step E can begin.")

    @Test
    fun `should parse steps`() {
        val assembler = IkeaAssembler(input)
        assertEquals(Instruction("A", mutableSetOf("C"), mutableSetOf("B", "D")), assembler["A"])
        assertEquals(Instruction("B", mutableSetOf("A"), mutableSetOf("E")), assembler["B"])
        assertEquals(Instruction("C", mutableSetOf(), mutableSetOf("A", "F")), assembler["C"])
        assertEquals(Instruction("D", mutableSetOf("A"), mutableSetOf("E")), assembler["D"])
        assertEquals(Instruction("E", mutableSetOf("D", "B", "F"), mutableSetOf()), assembler["E"])
        assertEquals(Instruction("F", mutableSetOf("C"), mutableSetOf("E")), assembler["F"])
    }

    @Test
    fun `should sort steps`() {
        val assembler = IkeaAssembler(input)
        assertEquals("CABDFE", assembler.assemble())
    }

    @Test
    fun `should sort actual steps`() {
        val input = readInputAsList()
        val assembler = IkeaAssembler(input)
        assertEquals("BFLNGIRUSJXEHKQPVTYOCZDWMA", assembler.assemble())
    }

    @Test
    fun `should optimize assembly`() {
        val assembler = IkeaAssembler(input)
        assertEquals(15, assembler.optimizeAssembly(2, 1))
    }

    @Test
    fun `should optimize actual assembly`() {
        val input = readInputAsList()
        val assembler = IkeaAssembler(input)
        assertEquals(880, assembler.optimizeAssembly(5, 61))
    }
}