package com.adventofcode.dec2019.day15

import com.adventofcode.positioning.CardinalDirection
import com.adventofcode.positioning.Position
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class CommandInterfaceTest {

    @Test
    fun `should convert commands properly`() {
        val inputs = listOf(CardinalDirection.NORTH, CardinalDirection.SOUTH, CardinalDirection.WEST, CardinalDirection.EAST)
        inputs.forEachIndexed { index, move ->
            assertEquals((index + 1).toLong(), CommandInterface.convertDirection(move))
        }
    }

    @Test
    fun `should convert wall properly`() {
        val inputs = listOf(CellType.WALL, CellType.EMPTY, CellType.OXYGEN)
        inputs.forEachIndexed { index, move ->
            assertEquals(move, CommandInterface.convertCell(index.toLong()))
        }
    }

    @Test
    fun `should send proper commands`() {
        runBlocking {
            val commands = Channel<CardinalDirection>(4)
            val result = Channel<CellType>(4)
            val robot = CommandInterface(commands, result)
            val inputs = listOf(CardinalDirection.NORTH, CardinalDirection.SOUTH, CardinalDirection.WEST, CardinalDirection.EAST)
            inputs.forEach {
                result.send(CellType.WALL)
                robot.move(it)
                withTimeout(10) { assertEquals(it, commands.receive()) }
            }
        }
    }

    @Test
    fun `should start in 0,0`() {
        val robot = CommandInterface(Channel(), Channel())
        assertEquals(Position(0, 0), robot.position)
    }

    @Test
    fun `should not move on walls and record wall position`() {
        runBlocking {
            val commands = Channel<CardinalDirection>(5)
            val result = Channel<CellType>(5)
            val robot = CommandInterface(commands, result)
            result.send(CellType.WALL)
            robot.move(CardinalDirection.NORTH)
            assertEquals(Position(0, 0), robot.position)
            assertEquals(CellType.WALL, robot.map[Position(0, -1)])
        }
    }

    @Test
    fun `should move if path is clear and keep record`() {
        runBlocking {
            val commands = Channel<CardinalDirection>(5)
            val result = Channel<CellType>(5)
            val robot = CommandInterface(commands, result)
            result.send(CellType.EMPTY)
            robot.move(CardinalDirection.NORTH)
            assertEquals(Position(0, -1), robot.position)
            assertEquals(CellType.EMPTY, robot.map[Position(0, -1)])
        }
    }

    @Test
    fun `should not know oxygen tank position on start`() {
        val robot = CommandInterface(Channel(), Channel())
        assertNull(robot.oxygen)
    }

    @Test
    fun `should record position of the oxygen tank`() {
        runBlocking {
            val commands = Channel<CardinalDirection>(5)
            val result = Channel<CellType>(5)
            val robot = CommandInterface(commands, result)
            result.send(CellType.OXYGEN)
            robot.move(CardinalDirection.NORTH)
            assertEquals(Position(0, -1), robot.position, "Droid position")
            assertEquals(Position(0, -1), robot.oxygen, "Oxygen position")
        }
    }
}