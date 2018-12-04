package com.adventofcode.dec2019.day15

import com.adventofcode.positioning.CardinalDirection
import com.adventofcode.dec2019.intCode.IntCodeRunner
import com.adventofcode.positioning.Position
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

class ExplorerTest {

    @Test
    fun `should find oxygen tank in next cell`() = testScenario { commands, result ->
        result.send(CellType.OXYGEN)
        val pathToOxygen = findOxygen()
        assertEquals(listOf(Position(0, -1), Position(0, 0)), pathToOxygen)
    }

    @Test
    fun `should find oxygen tank in two steps`() = testScenario { commands, result ->
        result.sendScenario(CellType.EMPTY, CellType.OXYGEN)
        val pathToOxygen = findOxygen()
        assertEquals(listOf(Position(0, -2), Position(0, -1), Position(0, 0)), pathToOxygen)
    }

    @Test
    fun `should handle one wall`() = testScenario { commands, result ->
        result.sendScenario(CellType.WALL, CellType.OXYGEN)
        val pathToOxygen = findOxygen()
        assertEquals(listOf(Position(1, 0), Position(0, 0)), pathToOxygen)
    }

    @Test
    fun `should backtrack in case of dead end`() = testScenario { _, result ->
        result.sendScenario(CellType.EMPTY, CellType.WALL, CellType.WALL, CellType.WALL, CellType.EMPTY, CellType.OXYGEN)
        val pathToOxygen = findOxygen()
        assertEquals(listOf(Position(1, 0), Position(0, 0)), pathToOxygen)
    }

    @Test
    fun `should find actual oxygen tank`() = testScenario { commands, result ->
        val program = File("src/main/kotlin/com/adventofcode/dec2019/day15/input.txt").readLines()
        val computer = IntCodeRunner(program[0], commands, result, CommandInterface.Companion::convertDirection, CommandInterface.Companion::convertCell)
        computer.executeProgram()
        val pathToOxygen = findOxygen()
        assertEquals(298, pathToOxygen.size - 1)
    }

    @Test
    fun `should fill all map with oxygen`() = testScenario { _, result ->
        //    #
        //   # #
        //  #O #
        //   ##
        result.sendScenario(
                CellType.EMPTY,
                CellType.WALL, CellType.WALL, CellType.WALL,
                CellType.EMPTY,
                CellType.OXYGEN, CellType.WALL, CellType.WALL,
                CellType.EMPTY, CellType.WALL, CellType.WALL)
                mapArea(true)
        printMap()
        val timing = fillOxygen()
        assertEquals(2, timing)
    }

    @Test
    fun `should fill all actual map with oxygen`() = testScenario { commands, result ->
        val program = File("src/main/kotlin/com/adventofcode/dec2019/day15/input.txt").readLines()
        val computer = IntCodeRunner(program[0], commands, result, CommandInterface.Companion::convertDirection, CommandInterface.Companion::convertCell)
        computer.executeProgram()
        mapArea(true)
        printMap()
        val timing = fillOxygen()
        assertEquals(346, timing)
    }

    private fun testScenario(scenario: suspend Explorer.(Channel<CardinalDirection>, Channel<CellType>) -> Unit) = runBlocking {
        val commands = Channel<CardinalDirection>(100)
        val result = Channel<CellType>(100)
        val robot = CommandInterface(commands, result)
        val explorer = Explorer(robot)
        withTimeout(1000) {
            explorer.scenario(commands, result)
        }
    }

    private suspend fun Channel<CellType>.sendScenario(vararg cells: CellType) = cells.forEach { send(it) }
}