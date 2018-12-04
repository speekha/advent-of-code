package com.adventofcode.dec2019.day17

import com.adventofcode.dec2019.intCode.IntCodeRunner
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.receiveOrNull
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class ScaffoldCalibratorTest {

    private val input1 = "..#..........\n" +
            "..#..........\n" +
            "##O####...###\n" +
            "#.#...#...#.#\n" +
            "##O###O###O##\n" +
            "..#...#...#..\n" +
            "..#####...^..\n" +
            "\n"

    private val input2 = "#######...#####\n" +
            "#.....#...#...#\n" +
            "#.....#...#...#\n" +
            "......#...#...#\n" +
            "......#...###.#\n" +
            "......#.....#.#\n" +
            "^########...#.#\n" +
            "......#.#...#.#\n" +
            "......#########\n" +
            "........#...#..\n" +
            "....#########..\n" +
            "....#...#......\n" +
            "....#...#......\n" +
            "....#...#......\n" +
            "....#####......\n" +
            "\n"

    @Test
    fun `should read camera output`() = testScenario(input1) { _, _ ->
        readCamera()
        assertEquals(input1.split("\n").filter { it.isNotEmpty() }, scaffoldState)
    }

    @Test
    fun `should calibrate scaffolds`() = testScenario(input1) { _, _ ->
        readCamera()
        assertEquals(76, computeAlignmentParameters())
    }

    @Test
    fun `should calibrate scaffolds with vacuum robot`() = testScenario { camera, control ->
        val program = File("src/main/kotlin/com/adventofcode/dec2019/day17/input.txt").readLines()
        val computer = IntCodeRunner(program[0], control, camera, { it.toLong() }, { it })
        computer.executeProgram()
        readCamera()
        assertEquals(5724, computeAlignmentParameters())
    }

    @Test
    fun `should drive robot`() = testScenario { camera, control ->
        val commands = "A,A,B,C,B,C,B,C"
        sendCommands(commands)
        control.close()
        val result = mutableListOf<Char>()
        while (!control.isClosedForReceive) {
            control.receiveCatching().getOrNull()?.let {
                result += it
                print(it)
            }
        }
        assertEquals((commands + "\n").map { it }, result)
    }

    @Test
    fun `should follow path`() = testScenario(input2) { camera, control ->
        readCamera()
        val path = generatePath()
        assertEquals(listOf("R,8", "R,8", "R,4", "R,4", "R,8", "L,6", "L,2", "R,4", "R,4", "R,8", "R,8", "R,8", "L,6", "L,2"), path)
    }

    @Test
    fun `should split path in separate commands`() = testScenario(input2) { camera, control ->
        val commands = this.splitPath(listOf("R,8", "R,8", "R,4", "R,4", "R,8", "L,6", "L,2", "R,4", "R,4", "R,8", "R,8", "R,8", "L,6", "L,2"))
        assertEquals(listOf("A,C,B,C,A,B", "R,8,R,8", "R,8,L,6,L,2", "R,4,R,4"), commands)
    }

    @Test
    fun `should drive actual robot`() = testScenario { camera, control ->
        val program = File("src/main/kotlin/com/adventofcode/dec2019/day17/input.txt").readLines()
        val computer = IntCodeRunner(program[0], control, camera, { it.toLong() }, { it })
        computer.setValueInMemory(0, 2)
        val job = computer.executeProgram()
        traverseScaffolding()
        var result = 0L
        while (!camera.isClosedForReceive) {
            result = camera.receive()
            if (result < 256) {
                print(result.toChar())
            } else {
                print(result)
            }
        }
        assertEquals(732985, result)
    }

    private fun testScenario(input: String? = null, scenario: suspend ScaffoldCalibrator.(Channel<Long>, Channel<Char>) -> Unit) = runBlocking {
        val camera = Channel<Long>(input?.length ?: 100)
        val control = Channel<Char>(100)
        val calibrator = ScaffoldCalibrator(camera, control)
        input?.run {
            forEach { camera.send(it.toLong()) }
            camera.close()
        }
        calibrator.scenario(camera, control)
    }

}