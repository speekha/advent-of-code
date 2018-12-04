package com.adventofcode.dec2016.day25

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AssembunnyProcessorTest {

    val input = listOf(
            "cpy a d",
            "cpy 7 c",
            "cpy 365 b",
            "inc d",
            "dec b",
            "jnz b -2",
            "dec c",
            "jnz c -5",
            "cpy d a",
            "jnz 0 0",
            "cpy a b",
            "cpy 0 a",
            "cpy 2 c",
            "jnz b 2",
            "jnz 1 6",
            "dec b",
            "dec c",
            "jnz c -4",
            "inc a",
            "jnz 1 -7",
            "cpy 2 b",
            "jnz c 2",
            "jnz 1 4",
            "dec b",
            "dec c",
            "jnz 1 -4",
            "jnz 0 0",
            "out b",
            "jnz a -19",
            "jnz 1 -21"
    )


    @Test
    fun `should process actual instructions`() {
        val processor = AssembunnyProcessor(input = input, output = listOf(0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1))
        val minStart = (0..Int.MAX_VALUE).firstOrNull {
            processor.reset(a = it)
            processor.run()
        }
        Assertions.assertEquals(175, minStart)
    }

}