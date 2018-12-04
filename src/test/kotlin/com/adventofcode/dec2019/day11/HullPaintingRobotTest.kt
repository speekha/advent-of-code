package com.adventofcode.dec2019.day11

import com.adventofcode.positioning.Direction
import com.adventofcode.dec2019.intCode.intCodeRunner
import com.adventofcode.positioning.Position
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class HullPaintingRobotTest {

    @Test
    fun `should start in up position`() = testRobot { commands, camera, robot ->
        assertEquals(Direction.UP, robot.direction)
        assertEquals(Position(0, 0), robot.position)
    }

    @Test
    fun `should start on a black panel`() = testRobot { _, camera, robot ->
        robot.start()
        assertEquals(0, camera.receive())
    }

    @Test
    fun `should process paint and turn left`() = testRobot { commands, camera, robot ->
        val job = robot.start()
        commands.sendCommands(1, 0)
        withTimeout(100) {
            assertEquals(0, camera.receive())
            assertEquals(0, camera.receive())
        }
        assertEquals(Direction.LEFT, robot.direction)
        assertEquals(Position(-1, 0), robot.position)
        commands.close()
    }

    @Test
    fun `should go around for a loop`() = testRobot { commands, camera, robot ->
        val job = robot.start()

        with(commands) {
            sendCommands(1, 0)
            sendCommands(1, 0)
            sendCommands(1, 0)
            sendCommands(0, 0)
        }
        withTimeout(100) {
            repeat(4) {
                assertEquals(0, camera.receive())
            }
            assertEquals(1, camera.receive())
        }

        assertEquals(Direction.UP, robot.direction)
        assertEquals(Position(0, 0), robot.position)
        commands.close()
    }

    @Test
    fun `should turn right`() = testRobot { commands, camera, robot ->
        val job = robot.start()

        with(commands) {
            sendCommands(1, 0)
            sendCommands(0, 0)
            sendCommands(1, 0)
            sendCommands(1, 0)
            sendCommands(0, 1)
            sendCommands(1, 0)
            sendCommands(1, 0)
        }
        withTimeout(100) {
            repeat(4) {
                assertEquals(0, camera.receive())
            }
            assertEquals(1, camera.receive())
            repeat(3) {
                assertEquals(0, camera.receive())
            }
        }

        assertEquals(Direction.LEFT, robot.direction)
        assertEquals(Position(0, -1), robot.position)
        commands.close()
    }

    @Test
    fun `should count painted panels`() = testRobot { commands, camera, robot ->
        val job = robot.start()

        with(commands) {
            sendCommands(1, 0)
            sendCommands(1, 0)
            sendCommands(1, 0)
            sendCommands(1, 0)
            sendCommands(0, 1)
            sendCommands(0, 0)
            sendCommands(1, 0)
            close()
        }
        job.join()
        assertEquals(6, robot.countPaintedPanels())
    }


    @Test
    fun `should run robot with computer`() = testRobot { commands, camera, robot ->
        val program = File("src/main/kotlin/com/adventofcode/dec2019/day11/input.txt").readLines()
        val computer = intCodeRunner(program[0], camera, commands)
        val jobs = listOf(robot.start(), computer.executeProgram())
        jobs.forEach { it.join() }
        assertEquals(1709, robot.countPaintedPanels())
    }

    @Test
    fun `should run robot with computer and start on white`() = testRobot { commands, camera, robot ->
        robot.setPanel(Position(0, 0), 1)
        val program = File("src/main/kotlin/com/adventofcode/dec2019/day11/input.txt").readLines()
        val computer = intCodeRunner(program[0], camera, commands)
        val jobs = listOf(robot.start(), computer.executeProgram())
        jobs.forEach { it.join() }
        robot.paintRegistration()
    }

    private suspend fun Channel<Long>.sendCommands(paint: Long, turn: Long) {
        send(paint)
        send(turn)
    }

    private fun testRobot(test: suspend (Channel<Long>, Channel<Long>, HullPaintingRobot) -> Unit) = runBlocking {
        val commands = Channel<Long>(10)
        val camera = Channel<Long>(10)
        val robot = HullPaintingRobot(commands, camera)
        test(commands, camera, robot).also {
            if (!commands.isClosedForSend) {
                commands.close()
            }
            if (!camera.isClosedForSend) {
                camera.close()
            }
        }
    }
}