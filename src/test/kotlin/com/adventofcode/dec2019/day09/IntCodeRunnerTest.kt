package com.adventofcode.dec2019.day09

import com.adventofcode.dec2019.intCode.intCodeRunner
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.receiveOrNull
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class IntCodeRunnerTest {

    @Test
    fun `should handle relative parameters`() {
        runBlocking {
            val program = "109,1,99"
            val input = Channel<Long>()
            val output = Channel<Long>(100)
            val runner = intCodeRunner(program, input, output)
            runner.executeProgram().join()
            assertEquals(1, runner.memory.relativeBase)
        }
    }

    @Test
    fun `should save in relative positions`() {
        runBlocking {
            val input = Channel<Long>(2)
            val output = Channel<Long>(100)
            input.send(9)
            input.send(8)
            val runner = intCodeRunner("109,1,3,7,203,7,99,0,0", input, output)
            runner.executeProgram().join()
            assertEquals(stateOf(109, 1, 3, 7, 203, 7, 99, 9, 8), runner.memory.state.toString())
        }
    }

    @Test
    fun `should output itself`() {
        val program = "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99"
        val input = Channel<Long>()
        val output = Channel<Long>(100)
        val runner = intCodeRunner(program, input, output)
        runBlocking {
            runner.executeProgram()
            val result = mutableListOf<Long>()
            var value: Long?
            do {
                value = output.receiveCatching().getOrNull()?.also { result += it }
            } while (value != null)
            assertEquals(program, result.joinToString(","))
        }
    }

    @Test
    fun `should output a 16-digit number`() = testProgram("1102,34915192,34915192,7,4,7,99,0", 1219070632396864)

    @Test
    fun `should output large number`() = testProgram("104,1125899906842624,99", 1125899906842624)

    @Test
    fun `should output BOOST code in test mode`() {
        val program = File("src/main/kotlin/com/adventofcode/dec2019/day09/input.txt").readLines()
        testProgram(program[0], listOf(1L), 3454977209)
    }

    @Test
    fun `should output BOOST code in sensor mode`() {
        val program = File("src/main/kotlin/com/adventofcode/dec2019/day09/input.txt").readLines()
        testProgram(program[0], listOf(2), 50120)
    }

    private fun testProgram(program: String, result: Long) = testProgram(program, listOf(), result)

    private fun testProgram(program: String, inputs: List<Long>, result: Long) {
        val input = Channel<Long>(1)
        val output = Channel<Long>(100)
        val runner = intCodeRunner(program, input, output)
        runBlocking {
            inputs.forEach { input.send(it) }
            runner.executeProgram()
            val value = output.receive()
            assertEquals(result, value)
        }
    }

    private fun stateOf(vararg data: Int) = data.withIndex().associate { (index, value) -> index to value }.toString()
}