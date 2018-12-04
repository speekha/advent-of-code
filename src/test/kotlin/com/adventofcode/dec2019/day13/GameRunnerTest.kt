package com.adventofcode.dec2019.day13

import com.adventofcode.dec2019.intCode.intCodeRunner
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class GameRunnerTest {

    @Test
    fun `should paint panels`() = runBlocking {
        val commands = Channel<Long>(6)
        listOf(1, 2, 3, 6, 5, 4).forEach {
            commands.send(it.toLong())
        }
        commands.close()
        val game = GameRunner(commands, Channel(1000))
        game.start()
        assertEquals(2, game.tiles.size)
        assertEquals(3, game.tiles[Position(1, 2)])
        assertEquals(4, game.tiles[Position(6, 5)])
    }

    @Test
    fun `should count block tiles`() = runBlocking {
        val commands = Channel<Long>(10)
        val input = Channel<Long>(10)
        val program = File("src/main/kotlin/com/adventofcode/dec2019/day13/input.txt").readLines()
        val runner = intCodeRunner(program[0], input, commands)
        val job = runner.executeProgram()
        val game = GameRunner(commands, Channel(1000))
        game.start()
        job.join()
        println(game.blockTiles)
        assertEquals(251, game.blockTiles)
    }


    @Test
    fun `should play game`() = runBlocking {
        val display = Channel<Long>(10)
        val joystick = Channel<Long>(10)
        val program = File("src/main/kotlin/com/adventofcode/dec2019/day13/input.txt").readLines()
        val runner = intCodeRunner(program[0], joystick, display)
        runner.setValueInMemory(0, 2)
        val job = runner.executeProgram()
        val game = GameRunner(display, joystick)
        game.start()
        job.join()
        assertEquals(12779, game.score)
    }
}