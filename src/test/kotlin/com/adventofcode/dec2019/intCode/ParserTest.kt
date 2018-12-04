package com.adventofcode.dec2019.intCode

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ParserTest {
    @Test
    fun `should disassemble EXIT`() {
        val program = "99"
        val result = "000 EXIT\n"
        testProgram(program, result)
    }

    @Test
    fun `should disassemble ADD with immediate parameters`() {
        val program = "1101,9,10,3,99"
        val result = "000 ADD 9 10 @3\n" +
                "004 EXIT\n"
        testProgram(program, result)
    }

    @Test
    fun `should disassemble ADD`() {
        val program = "1,9,10,3,99"
        val result = "000 ADD @9 @10 @3\n" +
                "004 EXIT\n"
        testProgram(program, result)
    }

    @Test
    fun `should disassemble handle unknown instruction codes`() {
        val program = "1,9,10,3,99,14"
        val result = "000 ADD @9 @10 @3\n" +
                "004 EXIT\n" +
                "005 00014\n"
        testProgram(program, result)
    }

    private fun testProgram(input: String, output: String) {
        val parser = Parser()
        assertEquals(output, parser.parse(input))
    }
}