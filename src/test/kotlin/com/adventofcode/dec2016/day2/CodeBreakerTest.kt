package com.adventofcode.dec2016.day2

import org.junit.Assert.assertEquals
import org.junit.Test

class CodeBreakerTest {
    @Test
    fun no_move_should_mean_5() {
        val parser = CodeBreaker()
        assertEquals('5', parser.parseDigit("", nextDigit = parser::simpleKeypad))
    }

    @Test
    fun U_should_be_2() {
        val parser = CodeBreaker()
        assertEquals('2', parser.parseDigit("U", nextDigit = parser::simpleKeypad))
    }

    @Test
    fun ULL_should_be_1() {
        val parser = CodeBreaker()
        assertEquals('1', parser.parseDigit("ULL", nextDigit = parser::simpleKeypad))
    }

    @Test
    fun RRDDD_should_be_9() {
        val parser = CodeBreaker()
        assertEquals('9', parser.parseDigit("RRDDD", '1', parser::simpleKeypad))
    }

    @Test
    fun LURDL_should_be_8() {
        val parser = CodeBreaker()
        assertEquals('8', parser.parseDigit("LURDL", '9', parser::simpleKeypad))
    }

    @Test
    fun UUUUD_should_be_5() {
        val parser = CodeBreaker()
        assertEquals('5', parser.parseDigit("UUUUD", '8', parser::simpleKeypad))
    }

    @Test
    fun code_should_be_1985() {
        val input = "ULL\n" +
                "RRDDD\n" +
                "LURDL\n" +
                "UUUUD"
        val parser = CodeBreaker()
        assertEquals("1985", parser.parseCode(input, parser::simpleKeypad))
    }

    @Test
    fun ULL_should_now_be_5() {
        val parser = CodeBreaker()
        assertEquals('5', parser.parseDigit("ULL", nextDigit = parser::complexKeypad))
    }

    @Test
    fun RRDDD_should_now_be_D() {
        val parser = CodeBreaker()
        assertEquals('D', parser.parseDigit("RRDDD", '5', parser::complexKeypad))
    }

    @Test
    fun LURDL_should_now_be_B() {
        val parser = CodeBreaker()
        assertEquals('B', parser.parseDigit("LURDL", 'D', parser::complexKeypad))
    }

    @Test
    fun UUUUD_should_now_be_3() {
        val parser = CodeBreaker()
        assertEquals('3', parser.parseDigit("UUUUD", 'D', parser::complexKeypad))
    }
}