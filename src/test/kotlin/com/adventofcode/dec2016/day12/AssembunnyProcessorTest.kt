package com.adventofcode.dec2016.day12

import org.junit.Assert
import org.junit.Test

class AssembunnyProcessorTest {

    val input = listOf("cpy 41 a",
            "inc a",
            "inc a",
            "dec a",
            "jnz a 2",
            "dec a")

    @Test
    fun `should process instructions`() {
        with(AssembunnyProcessor()) {
            process(input)
            Assert.assertEquals(42, registers["a"])
        }
    }
}