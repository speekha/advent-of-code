package com.adventofcode.dec2016.day12

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

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
            assertEquals(42, registers["a"])
        }
    }
}