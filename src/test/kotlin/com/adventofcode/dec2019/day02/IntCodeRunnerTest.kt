package com.adventofcode.dec2019.day02

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
        val runner = IntCodeRunner("1,9,10,3,2,3,11,0,99,30,40,50")
        runner.executeOneCommand()
        runner.executeOneCommand()
        assertEquals(listOf(3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50), runner.state)
    }

    @Test
    fun `should execute full program`() {
        val runner = IntCodeRunner("1,9,10,3,2,3,11,0,99,30,40,50")
        runner.executeProgram()
        assertEquals(listOf(3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50), runner.state)
    }


    @Test
    fun `should restore initial 1202 state`() {
        val runner = IntCodeRunner("1,9,10,3,2,3,11,0,99,30,40,50")
        runner.restore1202State()
        assertEquals(listOf(1, 12, 2, 3, 2, 3, 11, 0, 99, 30, 40, 50), runner.state)
    }

    @Test
    fun `should compute actual result`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2019/day02/input.txt").readLines()
        val runner = IntCodeRunner(input[0])
        runner.restore1202State()
        runner.executeProgram()
        assertEquals(8017076, runner.state[0])
    }

    @Test
    fun `should find input for 19690720`() {
        for (noun in 0..99) {
            for (verb in 0..99) {
                val input = File("src/main/kotlin/com/adventofcode/dec2019/day02/input.txt").readLines()
                val runner = IntCodeRunner(input[0])
                runner.initState(noun, verb)
                runner.executeProgram()
                if (runner.state[0] == 19690720) {
                    assertEquals(3146, 100 * noun +verb)
                    return
                }
            }
        }
        fail<Unit>("No result found")
    }
}