package com.adventofcode.dec2016.day23

import org.junit.Assert
import org.junit.Ignore
import org.junit.Test

class AssembunnyProcessorTest {

    val input = listOf(
            "cpy 2 a",
            "tgl a",
            "tgl a",
            "tgl a",
            "cpy 1 a",
            "dec a",
            "dec a")

    val actualInput = listOf(
            "cpy a b",
            "dec b",
            "cpy a d",
            "cpy 0 a",
            "cpy b c",
            "inc a",
            "dec c",
            "jnz c -2",
            "dec d",
            "jnz d -5",
            "dec b",
            "cpy b c",
            "cpy c d",
            "dec d",
            "inc c",
            "jnz d -2",
            "tgl c",
            "cpy -16 c",
            "jnz 1 c",
            "cpy 96 c",
            "jnz 91 d",
            "inc a",
            "inc d",
            "jnz d -2",
            "inc c",
            "jnz c -5")
    @Test
    fun `should process instructions`() {
        with(AssembunnyProcessor(input = input)) {
            run()
            Assert.assertEquals(3, registers["a"])
        }
    }

    @Test
    fun `should process actual instructions`() {
        with(AssembunnyProcessor(a = 7, input = actualInput)) {
            run()
            Assert.assertEquals(13776, registers["a"])
        }
    }

    @Test
    @Ignore
    fun `should process actual instructions with second input`() {
        with(AssembunnyProcessor(a = 12, input = actualInput)) {
            run()
            Assert.assertEquals(479010336, registers["a"])
        }
    }
}