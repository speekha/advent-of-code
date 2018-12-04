package com.adventofcode.dec2019.day05

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.io.File

@Disabled
class IntCodeRunnerTest {

    @Test
    fun `should execute load program`() {
        val runner = IntCodeRunner("1,9,10,3,2,3,11,0,99,30,40,50")
        assertEquals(listOf(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50), runner.state)
    }

    @Test
    fun `should execute add command`() {
        val runner = IntCodeRunner("1,9,10,3,2,3,11,0,99,30,40,50")
        runner.executeOneCommand()
        assertEquals(listOf(1, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50), runner.state)
    }

    @Test
    fun `should execute multiply command`() {
        val runner = IntCodeRunner("2,4,3,3,33")
        runner.executeOneCommand()
        assertEquals(listOf(2, 4, 3, 99, 33), runner.state)
    }

    @Test
    fun `should execute immediate mode parameters`() {
        var runner = IntCodeRunner("1002,4,3,4,33")
        runner.executeOneCommand()
        assertEquals(listOf(1002, 4, 3, 4, 99), runner.state)
        runner = IntCodeRunner("1101,96,3,4,96")
        runner.executeOneCommand()
        assertEquals(listOf(1101, 96, 3, 4, 99), runner.state)
    }

    @Test
    fun `should execute save and output commands`() {
        val runner = IntCodeRunner("3,0,4,0,99")
        runner.executeProgram(5)
        assertEquals("5", runner.output.toString())
    }

    @Test
    fun `should handle negative numbers`() {
        val runner = IntCodeRunner("1101,100,-1,4,0")
        runner.executeProgram(1)
        assertEquals(listOf(1101,100,-1,4,99), runner.state)
    }

    @Test
    fun `should execute actual diagnostics`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2019/day05/input.txt").readLines()
        val runner = IntCodeRunner(input[0])
        runner.executeProgram(1)
        assertEquals("5182797", runner.output )
    }

    @Test
    fun `should handle jump-if-true`() {
        var runner = IntCodeRunner("1005,3,7,3,1,1,1,99")
        runner.executeOneCommand(1)
        assertEquals(7, runner.index)
        runner = IntCodeRunner("1005,3,7,0,1,1,1,99")
        runner.executeOneCommand(1)
        assertEquals(3, runner.index)
    }

    @Test
    fun `should handle jump-if-false`() {
        var runner = IntCodeRunner("1006,3,7,3,1,1,1,99")
        runner.executeOneCommand(1)
        assertEquals(3, runner.index)
        runner = IntCodeRunner("1006,3,7,0,1,1,1,99")
        runner.executeOneCommand(1)
        assertEquals(7, runner.index)
    }

    @Test
    fun `should handle less-than`() {
        var runner = IntCodeRunner("1107,1,2,4,8,1,1,99")
        runner.executeOneCommand(1)
        assertEquals(listOf(1107,1,2,4,1,1,1,99), runner.state)
        runner = IntCodeRunner("1107,2,1,4,8,1,1,99")
        runner.executeOneCommand(1)
        assertEquals(listOf(1107,2,1,4,0,1,1,99), runner.state)
    }

    @Test
    fun `should handle equals`() {
        var runner = IntCodeRunner("1108,1,1,4,8,1,1,99")
        runner.executeOneCommand(1)
        assertEquals(listOf(1108,1,1,4,1,1,1,99), runner.state)
        runner = IntCodeRunner("1108,2,1,4,8,1,1,99")
        runner.executeOneCommand(1)
        assertEquals(listOf(1108,2,1,4,0,1,1,99), runner.state)
    }

    @Test
    fun `should handle new programs`() {
        var runner = IntCodeRunner("3,9,8,9,10,9,4,9,99,-1,8")
        runner.executeProgram(8)
        assertEquals("1", runner.output )
        runner = IntCodeRunner("3,9,8,9,10,9,4,9,99,-1,8")
        runner.executeProgram(7)
        assertEquals("0", runner.output )
        runner = IntCodeRunner("3,9,7,9,10,9,4,9,99,-1,8")
        runner.executeProgram(8)
        assertEquals("0", runner.output )
        runner = IntCodeRunner("3,9,7,9,10,9,4,9,99,-1,8")
        runner.executeProgram(7)
        assertEquals("1", runner.output )
        runner = IntCodeRunner("3,3,1108,-1,8,3,4,3,99")
        runner.executeProgram(8)
        assertEquals("1", runner.output )
        runner = IntCodeRunner("3,3,1108,-1,8,3,4,3,99")
        runner.executeProgram(7)
        assertEquals("0", runner.output )
        runner = IntCodeRunner("3,3,1107,-1,8,3,4,3,99")
        runner.executeProgram(8)
        assertEquals("0", runner.output )
        runner = IntCodeRunner("3,3,1107,-1,8,3,4,3,99")
        runner.executeProgram(7)
        assertEquals("1", runner.output )
        runner = IntCodeRunner("3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9")
        runner.executeProgram(7)
        assertEquals("1", runner.output )
        runner = IntCodeRunner("3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9")
        runner.executeProgram(0)
        assertEquals("0", runner.output )
        runner = IntCodeRunner("3,3,1105,-1,9,1101,0,0,12,4,12,99,1")
        runner.executeProgram(0)
        assertEquals("0", runner.output )
        runner = IntCodeRunner("3,3,1105,-1,9,1101,0,0,12,4,12,99,1")
        runner.executeProgram(5)
        assertEquals("1", runner.output )
        runner = IntCodeRunner("3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31," +
                "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104," +
                "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99")
        runner.executeProgram(5)
        assertEquals("999", runner.output )
        runner = IntCodeRunner("3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31," +
                "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104," +
                "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99")
        runner.executeProgram(8)
        assertEquals("1000", runner.output )
        runner = IntCodeRunner("3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31," +
                "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104," +
                "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99")
        runner.executeProgram(10)
        assertEquals("1001", runner.output )
    }

    @Test
    fun `should execute actual diagnostics with new operations`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2019/day05/input.txt").readLines()
        val runner = IntCodeRunner(input[0])
        runner.executeProgram(5)
        assertEquals("12077198", runner.output )
    }
}