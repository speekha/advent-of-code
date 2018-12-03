package com.adventofcode.dec2017.day8

import org.junit.Assert.*
import org.junit.Ignore
import org.junit.Test

class InstructionParserTest {

    val inputs = listOf("b inc 5 if a > 1",
            "a inc 1 if b < 5",
            "c dec -10 if a >= 1",
            "c inc -20 if c == 10")

    @Test
    fun `should parse inc instruction`() {
        val input = "b inc 5 if a > 1"
        val parser = InstructionParser()
        val instruction = parser.parse(input)
        assertEquals("b", instruction.targetRegister)
        assertEquals(Operation.INC, instruction.operation)
        assertEquals(5, instruction.step)
        assertEquals(Condition("a", "1", Operator.GREATER_THAN), instruction.condition)
    }

    @Test
    fun `should parse dec instruction`() {
        val input = "c dec -10 if a >= 1"
        val parser = InstructionParser()
        val instruction = parser.parse(input)
        assertEquals("c", instruction.targetRegister)
        assertEquals(Operation.DEC, instruction.operation)
        assertEquals(-10, instruction.step)
        assertEquals(Condition("a", "1", Operator.GREATER_OR_EQUAL), instruction.condition)
    }

    @Test
    fun `should validate conditions`() {
        val cpu = CPU(mapOf("a" to 1))
        assertTrue(cpu.checkCondition(Condition("a", "1", Operator.EQUALS)))
        assertFalse(cpu.checkCondition(Condition("a", "0", Operator.EQUALS)))
        assertTrue(cpu.checkCondition(Condition("a", "0", Operator.NOT_EQUALS)))
        assertFalse(cpu.checkCondition(Condition("a", "1", Operator.NOT_EQUALS)))
        assertTrue(cpu.checkCondition(Condition("a", "0", Operator.GREATER_THAN)))
        assertFalse(cpu.checkCondition(Condition("a", "1", Operator.GREATER_THAN)))
        assertFalse(cpu.checkCondition(Condition("a", "2", Operator.GREATER_THAN)))
        assertTrue(cpu.checkCondition(Condition("a", "0", Operator.GREATER_OR_EQUAL)))
        assertTrue(cpu.checkCondition(Condition("a", "1", Operator.GREATER_OR_EQUAL)))
        assertFalse(cpu.checkCondition(Condition("a", "2", Operator.GREATER_OR_EQUAL)))
        assertTrue(cpu.checkCondition(Condition("a", "2", Operator.LESS_THAN)))
        assertFalse(cpu.checkCondition(Condition("a", "1", Operator.LESS_THAN)))
        assertFalse(cpu.checkCondition(Condition("a", "1", Operator.LESS_THAN)))
        assertTrue(cpu.checkCondition(Condition("a", "2", Operator.LESS_OR_EQUAL)))
        assertTrue(cpu.checkCondition(Condition("a", "1", Operator.LESS_OR_EQUAL)))
        assertFalse(cpu.checkCondition(Condition("a", "0", Operator.LESS_OR_EQUAL)))
    }

    @Test
    fun `should process instructions`() {
        val parser = InstructionParser()
        val cpu = CPU()
        cpu.processInstructions(inputs.map { parser.parse(it) })
        assertEquals(1, cpu.getRegisterOrInit("a"))
        assertEquals(0, cpu.getRegisterOrInit("b"))
        assertEquals(-10, cpu.getRegisterOrInit("c"))
    }

    @Test
    fun `should be 1`() {
        val parser = InstructionParser()
        val cpu = CPU()
        cpu.processInstructions(inputs.map { parser.parse(it) })
        assertEquals(1, cpu.getHighestRegister().value)
    }

    @Test
    fun `should be 10`() {
        val parser = InstructionParser()
        val cpu = CPU()
        cpu.processInstructions(inputs.map { parser.parse(it) })
        assertEquals(10, cpu.getRange().last)
    }
}