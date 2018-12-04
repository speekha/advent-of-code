package com.adventofcode.dec2019.intCode

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class IntCodeRunnerTest {

    @Test
    fun `should execute load program`() = testProgram("1,9,10,3,2,3,11,0,99,30,40,50") { _, _ ->
        assertMemoryState(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50)
    }

    @Test
    fun `should execute add command`() = testProgram("1,5,6,7,99,30,40,50") { _, _ ->
        executeProgram().join()
        assertMemoryState(1, 5, 6, 7, 99, 30, 40, 70)
    }

    @Test
    fun `should execute multiply command`() = testProgram("2,5,6,7,99,30,40,50") { _, _ ->
        executeProgram().join()
        assertMemoryState(2, 5, 6, 7, 99, 30, 40, 1200)
    }

    @Test
    fun `should execute full program`() = testProgram("1,9,10,3,2,3,11,0,99,30,40,50") { _, _ ->
        executeProgram().join()
        assertMemoryState(3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50)
    }

    @Test
    fun `should restore initial 1202 state`() = testProgram("1,9,10,3,2,3,11,0,99,30,40,50") { _, _ ->
        setValueInMemory(1, 12)
        setValueInMemory(2, 2)
        assertMemoryState(1, 12, 2, 3, 2, 3, 11, 0, 99, 30, 40, 50)
    }

    @Test
    fun `should execute day2 part 1`() = testFile("src/main/kotlin/com/adventofcode/dec2019/day02/input.txt") { _, _ ->
        setValueInMemory(1, 12)
        setValueInMemory(2, 2)
        executeProgram().join()
        assertEquals(8017076, memory.state[0])
    }

    @Test
    fun `should execute day2 part 2`() {
        val result = (0L..99L).asSequence().flatMap { noun ->
            (0L..99L).asSequence().map { verb ->
                testFile("src/main/kotlin/com/adventofcode/dec2019/day02/input.txt") { _, _ ->
                    setValueInMemory(1, noun)
                    setValueInMemory(2, verb)
                    executeProgram().join()
                    100 * noun + verb to memory.state[0]
                }
            }
        }.firstOrNull { it.second == 19690720L }?.let { it.first }
        assertEquals(3146L, result)
    }

    @Test
    fun `should add with immediate mode parameters`() = testProgram("1101,96,3,4,96") { _, _ ->
        executeProgram().join()
        assertMemoryState(1101, 96, 3, 4, 99)
    }

    @Test
    fun `should multiply with immediate mode parameters`() = testProgram("1002,4,3,4,33") { _, _ ->
        executeProgram().join()
        assertMemoryState(1002, 4, 3, 4, 99)
    }

    @Test
    fun `should execute save and output commands`() = testProgram("3,0,4,0,99") { input, output ->
        input.send(5)
        executeProgram().join()
        assertEquals(5, output.receive())
    }

    @Test
    fun `should handle negative numbers`() = testProgram("1101,100,-1,4,0") { _, _ ->
        executeProgram().join()
        assertMemoryState(1101, 100, -1, 4, 99)
    }

    @Test
    fun `should execute day 5 part 1`() = testFile("src/main/kotlin/com/adventofcode/dec2019/day05/input.txt") { input, output ->
        input.send(1)
        executeProgram().join()
        var result = 0L
        while (!output.isClosedForReceive) {
            result = output.receive()
        }
        assertEquals(5182797, result)
    }

    @Test
    fun `should jump if true`() = testProgram("1105,3,6,104,1,99,104,2,99") { _, output ->
        executeProgram().join()
        assertEquals(2, output.receive())
    }

    @Test
    fun `should not jump if false`() = testProgram("1105,0,6,104,1,99,104,2,99") { _, output ->
        executeProgram().join()
        assertEquals(1, output.receive())
    }

    @Test
    fun `should jump if false`() = testProgram("1106,3,6,104,1,99,104,2,99") { _, output ->
        executeProgram().join()
        assertEquals(1, output.receive())
    }

    @Test
    fun `should not jump if true`() = testProgram("1106,0,6,104,1,99,104,2,99") { _, output ->
        executeProgram().join()
        assertEquals(2, output.receive())
    }

    @Test
    fun `should store 1 if less-than`() = testProgram("1107,1,2,5,104,9,99") { _, output ->
        executeProgram().join()
        assertEquals(1, output.receive())
    }

    @Test
    fun `should store 0 if not less-than`() = testProgram("1107,2,1,5,104,9,99") { _, output ->
        executeProgram().join()
        assertEquals(0, output.receive())
    }

    @Test
    fun `should store 1 if equal`() = testProgram("1108,1,1,5,104,9,99") { _, output ->
        executeProgram().join()
        assertEquals(1, output.receive())
    }

    @Test
    fun `should store 0 if not equal`() = testProgram("1108,2,1,5,104,9,99") { _, output ->
        executeProgram().join()
        assertEquals(0, output.receive())
    }

    @Test
    fun `demo programs from day 5`() {
        class Test(val program: String, val input: Int, val output: Int)

        listOf(
                Test("3,9,8,9,10,9,4,9,99,-1,8", 8, 1),
                Test("3,9,8,9,10,9,4,9,99,-1,8", 7, 0),
                Test("3,9,7,9,10,9,4,9,99,-1,8", 8, 0),
                Test("3,9,7,9,10,9,4,9,99,-1,8", 7, 1),
                Test("3,3,1108,-1,8,3,4,3,99", 8, 1),
                Test("3,3,1108,-1,8,3,4,3,99", 7, 0),
                Test("3,3,1107,-1,8,3,4,3,99", 8, 0),
                Test("3,3,1107,-1,8,3,4,3,99", 7, 1),
                Test("3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9", 7, 1),
                Test("3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9", 0, 0),
                Test("3,3,1105,-1,9,1101,0,0,12,4,12,99,1", 0, 0),
                Test("3,3,1105,-1,9,1101,0,0,12,4,12,99,1", 5, 1),
                Test("3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31," +
                        "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104," +
                        "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99", 5, 999),
                Test("3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31," +
                        "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104," +
                        "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99", 8, 1000),
                Test("3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31," +
                        "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104," +
                        "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99", 10, 1001)
        ).forEach {
            testProgram(it.program) { input, output ->
                input.send(it.input.toLong())
                executeProgram().join()
                assertEquals(it.output.toLong(), output.receive())
            }
        }
    }

    @Test
    fun `should execute day5 part 2`() = testFile("src/main/kotlin/com/adventofcode/dec2019/day05/input.txt") { input, output ->
        input.send(5)
        executeProgram().join()
        assertEquals(12077198, output.receive())
    }

    private fun IntCodeRunner<Long, Long>.assertMemoryState(vararg listOf: Int) {
        assertEquals(listOf.withIndex().associate { it.index.toLong() to it.value.toLong() }, memory.state)
    }

    private fun <T> testFile(fileName: String, scenario: suspend IntCodeRunner<Long, Long>.(Channel<Long>, Channel<Long>) -> T): T {
        val input = File(fileName).readLines()
        return testProgram(input[0], scenario)
    }

    private fun <T> testProgram(program: String, scenario: suspend IntCodeRunner<Long, Long>.(Channel<Long>, Channel<Long>) -> T): T {
        val input = Channel<Long>(1)
        val output = Channel<Long>(100)
        val runner = intCodeRunner(program, input, output)
        return runBlocking {
            runner.scenario(input, output)
        }
    }
}