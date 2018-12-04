package com.adventofcode.dec2020.day18

import com.adventofcode.readInputAsList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MathProcessorTest {

    @Test
    fun `should parse expression with no parentheses`() {
        val input = "1 + 2 * 3 + 4 * 5 + 6"
        val processor = MathProcessor(false)
        Assertions.assertEquals(71L, processor.evaluate(input))
    }

    @Test
    fun `should parse expression with parentheses`() {
        val input = "1 + (2 * 3) + (4 * (5 + 6))"
        val processor = MathProcessor(false)
        Assertions.assertEquals(51L, processor.evaluate(input))
    }

    @Test
    fun `should parse actual data`() {
        val processor = MathProcessor(false)
        Assertions.assertEquals(30753705453324, readInputAsList().fold(0L) { acc, s ->
            acc + processor.evaluate(s)
        })
    }

    @Test
    fun `should parse expression with priorities`() {
        val processor = MathProcessor(true)
        val input = "1 + 2 * 3 + 4 * 5 + 6"
        Assertions.assertEquals(231L, processor.evaluate(input))
    }

    @Test
    fun `should parse actual data with priorities`() {
        val processor = MathProcessor(true)
        Assertions.assertEquals(244817530095503, readInputAsList().fold(0L) { acc, s ->
            acc + processor.evaluate(s)
        })
    }
}