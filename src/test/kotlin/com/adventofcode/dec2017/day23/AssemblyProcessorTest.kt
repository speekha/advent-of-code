package com.adventofcode.dec2017.day23

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class AssemblyProcessorTest {

    @Test
    fun `should set a to 2`() {
        val player = AssemblyProcessor(listOf("set a 1"))
        player.execute()
        assertEquals(1, player.getRegister("a"))
    }

    @Test
    fun `should sub 2 to a`() {
        val player = AssemblyProcessor(listOf(
                "set a 1",
                "sub a 2"))
        player.execute()
        assertEquals(-1, player.getRegister("a"))
    }

    @Test
    fun `should multiply a by a`() {
        val player = AssemblyProcessor(listOf("set a 2",
                "mul a a"))
        player.execute()
        assertEquals(4, player.getRegister("a"))
    }


    @Test
    fun `should jump an instruction`() {
        val player = AssemblyProcessor(listOf(
                "set a 10",
                "jnz 1 2",
                "sub a 1",
                "jnz 0 1",
                "sub a 1"
                ))
        player.execute()
        assertEquals(9, player.getRegister("a"))
    }

    @Test
    fun `test real value`() {
        val input = File("src/main/kotlin/com/adventofcode/dec2017/day23/input.txt").readLines()
        with(AssemblyProcessor(input)) {
            execute()
            assertEquals(3025, counter[Mul::class.simpleName])
        }
    }
}