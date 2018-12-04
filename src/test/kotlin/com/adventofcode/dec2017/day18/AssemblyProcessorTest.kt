package com.adventofcode.dec2017.day18

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class AssemblyProcessorTest {

    val input = listOf("set a 1",
            "add a 2",
            "mul a a",
            "mod a 5",
            "snd a",
            "set a 0",
            "rcv a",
            "jgz a -1",
            "set a 1",
            "jgz a -2").map { Instruction.parseInstruction(it) }

    @Test
    fun `should set a to 2`() {
        val player = AssemblyProcessor(0)
        player.execute(input.take(1))
        assertEquals(1, player.getRegister("a"))
    }

    @Test
    fun `should add 2 to a`() {
        val player = AssemblyProcessor(0)
        player.execute(input.take(2))
        assertEquals(3, player.getRegister("a"))
    }

    @Test
    fun `should multiply a by a`() {
        val player = AssemblyProcessor(0)
        player.execute(input.take(3))
        assertEquals(9, player.getRegister("a"))
    }

    @Test
    fun `should compute a mod 5 `() {
        val player = AssemblyProcessor(0)
        player.execute(input.take(4))
        assertEquals(4, player.getRegister("a"))
    }

    @Test
    fun `should play a`() {
        val player = AssemblyProcessor(0)
        player.execute(input.take(5))
        assertEquals(4, player.lastPlayed)
    }

    @Test
    fun `should recover frequency of 4`() {
        val player = AssemblyProcessor(0)
        player.execute(input)
        assertEquals(4, player.recoverLastSound())
    }

    @Test
    fun `test real value`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2017/day18/input.txt").readLines()
        with(AssemblyProcessor(0)) {
            execute(input.map { Instruction.parseInstruction(it) })
            assertEquals(1187, recoverLastSound())
        }
    }
}